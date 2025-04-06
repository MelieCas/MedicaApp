package com.example.medicaapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import com.example.medicaapp.data.getAppointments
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Graphics : AppCompatActivity() {
    private lateinit var graphic: BarChart
    var bottomNav: BottomNavigationView? = null;
    var navigationView: NavigationView? = null;
    var toolbar: Toolbar? = null;
    var drawerLayout: DrawerLayout? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_graphics)
        drawerLayout = findViewById<DrawerLayout>(R.id.main)
        navigationView = findViewById<NavigationView>(R.id.drawer_nav)
        toolbar = findViewById<Toolbar>(R.id.toolbar)
        bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigatin_view)
        graphic = findViewById(R.id.graphic)

        val entriesAppointments = mutableListOf<BarEntry>()
        val dates = ArrayList<String>()


        lifecycleScope.launch(Dispatchers.IO) {
            for (i in 0..6) {
                val date = getDate(i)
                val dateAppointments = getAppointments(applicationContext, date)

                entriesAppointments.add(BarEntry(i.toFloat(), dateAppointments.toFloat()))
                dates.add(date)

            }

            if (entriesAppointments.isEmpty()) {
                Log.d("info", "no pilla appointments")
            }
            val dataSet = BarDataSet(entriesAppointments, getString(R.string.appointments_day))
            dataSet.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return value.toInt().toString()
                }
            }
            val graphicData = BarData(dataSet)
            graphic.data = graphicData
            graphic.setExtraBottomOffset(40f)
            graphic.invalidate()


            val xAxis = graphic.xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(false)
            xAxis.setDrawLabels(true)
            xAxis.valueFormatter = IndexAxisValueFormatter(dates)
            xAxis.setLabelRotationAngle(45f)

            val yAxis = graphic.axisLeft
            yAxis.setDrawGridLines(true)
            yAxis.granularity = 1f
            yAxis.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return value.toInt().toString()
                }
            }

            graphic.axisRight.isEnabled = false



            navigationView?.setNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.nav_preferences -> {
                        val intent: Intent = Intent( this@Graphics, Preferences::class.java)
                        startActivity(intent)
                    }

                    R.id.nav_logout -> {
                        val intent: Intent = Intent( this@Graphics, MainActivity::class.java)
                        startActivity(intent)
                    }

                    R.id.nav_appointment -> {
                        val intent: Intent = Intent( this@Graphics, Appointments::class.java)
                        startActivity(intent)
                    }

                }
                drawerLayout?.closeDrawer(GravityCompat.START)
                true
            }

            bottomNav?.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.bottom_preferences -> {
                        val intent: Intent = Intent( this@Graphics, Preferences::class.java)
                        startActivity(intent)
                        true
                    }

                    R.id.bottom_appointment -> {
                        val intent: Intent = Intent( this@Graphics, Appointments::class.java)
                        startActivity(intent)
                        true
                    }
                    else -> {
                        false
                    }
                }
            }

        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }

    private fun getDate(days: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -days)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }
}