package com.example.medicaapp

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medicaapp.data.RetrofitServiceFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.launch

class Appointments : AppCompatActivity() {
    private lateinit var adapter: AppointmentRVAdapter
    var drawerLayout: DrawerLayout? = null;
    var navigationView: NavigationView? = null;
    var toolbar: Toolbar? = null;
    val cites: ArrayList<Cites>? = null;
    var bottomNav: BottomNavigationView? = null;
    val service = RetrofitServiceFactory.makeRetrofitService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_appointments)
        drawerLayout = findViewById<DrawerLayout>(R.id.main)
        navigationView = findViewById<NavigationView>(R.id.drawer_nav)
        toolbar = findViewById<Toolbar>(R.id.toolbar)
        bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigatin_view)


        lifecycleScope.launch { 
            val cites = service.getCites()
            println(cites)


        }
        val appointmentListView = findViewById<RecyclerView>(R.id.appointmentList)
        appointmentListView.layoutManager = LinearLayoutManager(this)

        val addAppointmentButton = findViewById<Button>(R.id.addAppointemntsButton)

        addAppointmentButton.setOnClickListener {showCreateAppointmentDialog()}

        adapter = cites?.let { AppointmentRVAdapter(this, it, service) { position ->
            val citaId = it[position].id
            deleteCita(citaId, position)} }!!
        appointmentListView.adapter = adapter
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(toolbar);
        navigationView?.bringToFront();
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout?.addDrawerListener(toggle)

        toggle.syncState();

        navigationView?.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_preferences -> {
                    val intent: Intent = Intent(
                        this@Appointments,
                        Preferences::class.java
                    )
                    startActivity(intent)
                }

                R.id.nav_logout -> {
                    val intent: Intent = Intent(
                        this@Appointments,
                        MainActivity::class.java
                    )
                    startActivity(intent)
                }

            }
            drawerLayout?.closeDrawer(GravityCompat.START)
            true
        }

        bottomNav?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_preferences -> {
                    val intent: Intent = Intent(
                        this@Appointments,
                        Preferences::class.java

                    )
                    startActivity(intent)
                    true
                }

                else -> {
                    false
                }
            }
        }

        navigationView?.setCheckedItem(R.id.nav_appointment);


    }

    private fun deleteCita(citaId: Int, position: Int) {
        lifecycleScope.launch {
            val response = service.deleteCita(citaId)
            if (response.isSuccessful) {
                adapter.removeItem(position)
            }
        }
    }

    override fun onBackPressed() {
        if (drawerLayout?.isDrawerOpen(GravityCompat.START) == true) {
            drawerLayout?.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed();
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (drawerLayout?.isDrawerOpen(GravityCompat.START) == true) {
                drawerLayout?.closeDrawer(GravityCompat.START)
                return true
            }
        }
        return false
    }

    private fun showCreateAppointmentDialog() {

        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.create_appointment_popup, null)


        val editTextDataCita: EditText = dialogView.findViewById(R.id.editTextDataCita)
        val editTextDoctor: EditText = dialogView.findViewById(R.id.editTextDoctor)
        val editTextMotiu: EditText = dialogView.findViewById(R.id.editTextMotiu)


        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Crear Cita")
            .setView(dialogView)
            .setPositiveButton("Crear") { dialog, which ->
                val dataCita = editTextDataCita.text.toString()
                val doctor = editTextDoctor.text.toString()
                val motiu = editTextMotiu.text.toString()


                if (dataCita.isNotEmpty() && doctor.isNotEmpty() && motiu.isNotEmpty()) {
                    val cita = Cites(dataCita, doctor, 0, motiu, "Melie Casares")
                    Toast.makeText(this, "Cita creada para Melie Casares", Toast.LENGTH_SHORT).show()
                    lifecycleScope.launch {
                        createCita(cita)
                    }

                } else {
                    Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancelar") { dialog, which ->
                dialog.dismiss()
            }

        alertDialog.show()
    }

    suspend fun createCita(cita : Cites) {
        val response = service.addCita(cita);
    }


}