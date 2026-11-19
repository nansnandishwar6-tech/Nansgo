package com.nansgroup.nansgo

import io.github.jan.tennert.supabase.postgrest.from
import kotlinx.serialization.Serializable

@Serializable
data class ProductItem(
    val id: String = "",
    val name: String = "",
    val category: String = "",
    val price: Double = 0.0,
    val eta_minutes: Int = 15,
    val city: String = "Adoni"
)

class NansGoRepository {
    private val db = SupabaseClientProvider.client

    suspend fun fetchAdoniProducts(): Result<List<ProductItem>> {
        return try {
            val items = db.from("products")
                .select()
                .decodeList<ProductItem>()
            Result.success(items)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
