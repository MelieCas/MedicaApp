package com.example.medicaapp

class AppointmentModel {
    public var motive: String?;
    public var doctor: String?;
    public var date: String?;

    constructor(motive: String? = null, doctor: String? = null, date: String? = null) {
        this.motive = motive
        this.doctor = doctor
        this.date = date
    }

}