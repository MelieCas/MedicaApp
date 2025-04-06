package com.example.medicaapp.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "medicaStore")


val appointmentsCreated = intPreferencesKey("appointmentsCreated")

// Function to get the current clicks for a specific day
suspend fun getAppointments(context: Context, day: String): Int {
    val preferences = context.dataStore.data.first()
    return preferences[intPreferencesKey(day)] ?: 0
}

// Function to save the current clicks for a specific day
suspend fun saveAppointments(context: Context, day: String, clicks: Int) {
    context.dataStore.edit { preferences ->
        preferences[intPreferencesKey(day)] = clicks
    }
}
