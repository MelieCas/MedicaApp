package com.example.medicaapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Appointments : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_appointments)
        val appointmentListView = findViewById<RecyclerView>(R.id.appointmentList)
        appointmentListView.layoutManager = LinearLayoutManager(this)
        val appointments: ArrayList<AppointmentModel> = ArrayList<AppointmentModel>()
        appointments.add(AppointmentModel("Revisió setmanal", "Juan Perez", "09/03/2025"))
        appointments.add(AppointmentModel("Dolor de cap", "Juan Perez", "12/04/2025"))
        appointments.add(AppointmentModel("Demanar analítica", "Juan Perez", "16/07/2025"))
        appointments.add(AppointmentModel("Revisió analítica", "Juan Perez", "20/09/2025"))
        appointments.add(AppointmentModel("Renovar medicaments", "Juan perez", "22/10/2025"))

        val rvAdapter: AppointmentRVAdapter = AppointmentRVAdapter(this, appointments)
        appointmentListView.adapter = rvAdapter
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}