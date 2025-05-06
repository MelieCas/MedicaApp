package com.example.medicaapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {

    private var _Usuari: String = ""
    private var _Nom: String = ""
    private var _Email: String = ""
    private var _Password: String = ""

    private var _BannedWords: ArrayList<String> = arrayListOf("Puto", "Caca")
    private val _ErrorUser = MutableLiveData<String>("")
    val errorUser: LiveData<String> = _ErrorUser

    fun setUsuari(usuari: String) {
        _Usuari = usuari
    }

    fun setNom(nom: String) {
        _Nom = nom
    }

    fun setEmail(email: String) {
        _Email = email
    }

    fun setPassword(password: String) {
        _Password = password
    }

    private fun checkUser() {
        if (_Usuari.isEmpty()) {
            _ErrorUser.value = "El nom d'usuari no pot estar buit"
        } else if (_BannedWords.contains(_Usuari)) {
            _ErrorUser.value = "El nom d'usuari no pot ser despectiu"
        }
    }

    private fun chechEmail() {

    }

}