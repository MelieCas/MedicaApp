package com.example.medicaapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Locale

class SignInViewModel : ViewModel() {

    private var _Usuari: String = ""
    private var _Nom: String = ""
    private var _Email: String = ""
    private var _Password: String = ""
    private var _EmailRegexString: String = "\\w+@(gmail.com|hotmail.com)"
    private var _EmailRegex = Regex.fromLiteral(_EmailRegexString)
    private var _PasswordRegexString: String = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,20}"
    private var _PasswordRegex = Regex.fromLiteral(_PasswordRegexString)

    private var _BannedWords: ArrayList<String> = arrayListOf("puto", "caca")
    private val _ErrorUser = MutableLiveData<String>("")
    private val _ErrorEmail = MutableLiveData<String>("")
    private val _ErrorPassword = MutableLiveData<String>("")
    private val _ErrorName = MutableLiveData<String>()
    val errorUser: LiveData<String> = _ErrorUser
    val errorEmail: LiveData<String> = _ErrorEmail
    val errorPassword: LiveData<String> = _ErrorPassword
    val errorName: LiveData<String> = _ErrorName

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
        } else if (_BannedWords.contains(_Usuari.lowercase(Locale.getDefault()))) {
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
        } else if (!_PasswordRegex.matches(_Password)) {
            _ErrorPassword.value = "La contrasenya no es molt segura"
        }
    }

    public fun checkNom() {
        if (_Nom.isBlank()) {
            _ErrorName.value = "El nom no pot ser buit"
        } else if (_Nom.length <= 2) {
            _ErrorName.value = "El nom es molt curt"
        } else if (_Nom.length > 60) {
            _ErrorName.value = "El nom es molt llarg"
        } else if (_BannedWords.contains(_Nom.lowercase(Locale.getDefault()))) {
            _ErrorName.value = "El nom no pot ser ofensiu"
        }
    }
}