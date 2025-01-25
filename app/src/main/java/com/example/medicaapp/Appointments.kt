package com.example.medicaapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.window.OnBackInvokedDispatcher

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class Appointments : AppCompatActivity() {
    var drawerLayout: DrawerLayout? = null;
    var navigationView: NavigationView? = null;
    var toolbar: Toolbar? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_appointments)
        drawerLayout = findViewById<DrawerLayout>(R.id.main)
        navigationView = findViewById<NavigationView>(R.id.drawer_nav)
        toolbar = findViewById<Toolbar>(R.id.toolbar)
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

        navigationView?.setCheckedItem(R.id.nav_appointment);


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


}