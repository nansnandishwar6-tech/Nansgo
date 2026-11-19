package com.nansgroup.nansgo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CustomerHomeScreen() {
    val repository = remember { NansGoRepository() }
    var products by remember { mutableStateOf<List<ProductItem>>(emptyList()) }
    var statusMessage by remember { mutableStateOf("NANS GO • Adoni Quick Commerce Ready") }

    LaunchedEffect(Unit) {
        val result = repository.fetchAdoniProducts()
        result.onSuccess { list ->
            products = list
            statusMessage = if (list.isEmpty()) {
                "Connected to Supabase (0 products yet)"
            } else {
                "Loaded ${list.size} items in Adoni"
            }
        }.onFailure {
            statusMessage = "NANS GO • Offline / Sample Mode"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "NANS GO",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = statusMessage,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (products.isEmpty()) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Hyperlocal Delivery • Adoni",
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("Groceries, food, and essentials delivered fast.")
                }
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(products) { item ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(text = item.name, fontWeight = FontWeight.Bold)
                            Text(text = "₹${item.price} • ${item.eta_minutes} mins • ${item.city}")
                        }
                    }
                }
            }
        }
    }
}
