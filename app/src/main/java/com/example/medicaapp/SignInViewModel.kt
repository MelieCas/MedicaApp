package com.example.medicaapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {

    private var _Usuari: String = ""
    private var _Nom: String = ""
    private var _Email: String = ""
    private var _Password: String = ""
    private var _EmailRegexString: String = "\\w+@(gmail.com|hotmail.com)"
    private var _EmailRegex = Regex.fromLiteral(_EmailRegexString)

    private var _BannedWords: ArrayList<String> = arrayListOf("Puto", "Caca")
    private val _ErrorUser = MutableLiveData<String>("")
    private val _ErrorEmail = MutableLiveData<String>("")
    private val _ErrorPassword = MutableLiveData<String>("")
    val errorUser: LiveData<String> = _ErrorUser
    val errorEmail: LiveData<String> = _ErrorEmail
    val errorPassword: LiveData<String> = _ErrorPassword

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

    public fun checkUser() {
        if (_Usuari.isBlank()) {
            _ErrorUser.value = "El nom d'usuari no pot estar buit"
        } else if (_BannedWords.contains(_Usuari)) {
            _ErrorUser.value = "El nom d'usuari no pot ser despectiu"
        } else if (_Usuari.length <= 2) {
            _ErrorUser.value = "El nom d'usuari es molt curt"
        } else if (_Usuari.length > 25) {
            _ErrorUser.value = "El nom d'usuari es molt llarg"
        }
    }

    public fun chechEmail() {
        if (_Email.isBlank()) {
            _ErrorEmail.value = "El email no pot estar buit"
        } else if (!_EmailRegex.matches(_Email)) {
            _ErrorEmail.value = "El format del Gmail no es correcte"
        }
    }

    public fun checkPassword() {
        if (_Password.isBlank()) {
            _ErrorPassword.value = "La contrasenya no pot ser buida"
        }
    }

}