package com.example.medicaapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.Console

class AppointmentRVAdapter internal constructor(context: Context?, data: MutableList<AppointmentModel>) :
    RecyclerView.Adapter<AppointmentRVAdapter.ViewHolder>() {
    private var mData: MutableList<AppointmentModel> = data

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

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
        val appointment: AppointmentModel = mData[position]
        holder.doctorName.setText(appointment.doctor)
        holder.reason.setText(appointment.motive)
        holder.appointmentDate.setText(appointment.date)

        holder.deleteButton.setOnClickListener(View.OnClickListener {
            mData.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemCount)

        })

    }

    override fun getItemCount(): Int {
        return mData.size
    }
}