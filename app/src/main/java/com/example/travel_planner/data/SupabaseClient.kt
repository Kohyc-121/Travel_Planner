package com.example.travel_planner.data

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

object SupabaseConfig {
    // Replace with your Supabase Project URL and Public Anon Key
    const val SUPABASE_URL =
        "https://spdswxqtaiddvylqsezw.supabase.co"
    const val SUPABASE_ANON_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNwZHN3eHF0YWlkZHZ5bHFzZXp3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3OTQxOTY3NjQsImV4cCI6MjEwOTc3Mjc2NH0.02jivs1zp3H7pC9VEp4Tx0JEH6Ngkpw9Q17yzUunh5w"
}

object SupabaseProvider {
    val client: SupabaseClient by lazy {
        createSupabaseClient(
            supabaseUrl = SupabaseConfig.SUPABASE_URL,
            supabaseKey = SupabaseConfig.SUPABASE_ANON_KEY
        ) {
            install(Auth)
            install(Postgrest)
        }
    }
}

@Serializable
data class UserProfile(
    @SerialName("id")
    val id: String,
    @SerialName("full_name")
    val fullName: String,
    @SerialName("email")
    val email: String
)