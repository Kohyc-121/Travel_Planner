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
        "https://iahtwydbiabksehwftvf.supabase.co"
    const val SUPABASE_ANON_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImlhaHR3eWRiaWFia3NlaHdmdHZmIiwicm9sZSI6ImFub24iLCJpYXQiOjE3OTQxODU1MTksImV4cCI6MjEwOTc2MTUxOX0.XxCBoYxVlYDZ6FpA80Bl-ar4WpYZVc83JUJghlXIE9w"
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