package com.nansgroup.nansgo

import io.github.jan.tennert.supabase.SupabaseClient
import io.github.jan.tennert.supabase.createSupabaseClient
import io.github.jan.tennert.supabase.auth.Auth
import io.github.jan.tennert.supabase.postgrest.Postgrest
import io.github.jan.tennert.supabase.realtime.Realtime

object SupabaseClientProvider {
    private const val SUPABASE_URL = "https://YOUR_PROJECT_REF.supabase.co"
    private const val SUPABASE_PUBLISHABLE_KEY = "sb_publishable_YOUR_KEY_HERE"

    val client: SupabaseClient by lazy {
        createSupabaseClient(
            supabaseUrl = SUPABASE_URL,
            supabaseKey = SUPABASE_PUBLISHABLE_KEY
        ) {
            install(Auth)
            install(Postgrest)
            install(Realtime)
        }
    }
}
