package com.example.medicaapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.medicaapp.data.RetrofitService
import kotlinx.coroutines.launch
import retrofit2.http.DELETE

class AppointmentRVAdapter internal constructor(data: MutableList<Cites>, service : RetrofitService, private val onDelete: (Int) -> (Unit)) :
    RecyclerView.Adapter<AppointmentRVAdapter.ViewHolder>() {
    private var mData: MutableList<Cites> = data
    private var serviceRetrofit = service;


    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var doctorName: TextView = itemView.findViewById<TextView>(R.id.doctorName)
        var reason: TextView = itemView.findViewById<TextView>(R.id.motiveText)
        var appointmentDate: TextView = itemView.findViewById<TextView>(R.id.appointmentDate)
        var deleteButton: Button = itemView.findViewById<Button>(R.id.cancelAppointment)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.appointment_list_view, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val appointment: Cites = mData[position]
        holder.doctorName.setText(appointment.doctor)
        holder.reason.setText(appointment.motiu)
        holder.appointmentDate.setText(appointment.data_cita.split("T")[0])

        holder.deleteButton.setOnClickListener(View.OnClickListener {
            onDelete(position)


        })

    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun removeItem(position: Int) {
        mData.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }
}