package com.example.medicaapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beust.jcommander.internal.Console
import java.util.Locale

class SignInViewModel : ViewModel() {

    private var _Usuari: String = ""
    private var _Nom: String = ""
    private var _Email: String = ""
    private var _Password: String = ""
    private var _ConfirmPassword: String = ""
    private var _EmailRegexString: String = "^[A-Za-z0-9._%+-]+@(gmail\\.com|hotmail\\.com)$"
    private var _EmailRegex = Regex(_EmailRegexString)
    private var _PasswordRegexString: String = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\\$).{8,20}$"
    private var _PasswordRegex = Regex(_PasswordRegexString)

    private var _BannedWords: ArrayList<String> = arrayListOf("puto", "caca")
    private val _ErrorUser = MutableLiveData<String>("")
    private val _ErrorEmail = MutableLiveData<String>("")
    private val _ErrorPassword = MutableLiveData<String>("")
    private val _ErrorName = MutableLiveData<String>("")
    private val _ErrorConfirmPassword = MutableLiveData<String>("")
    val errorUser: LiveData<String> = _ErrorUser
    val errorEmail: LiveData<String> = _ErrorEmail
    val errorPassword: LiveData<String> = _ErrorPassword
    val errorName: LiveData<String> = _ErrorName
    val errorConfirmPassword: LiveData<String> = _ErrorConfirmPassword

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

    fun setConfirmPassword(confirmPassword: String) {
        _ConfirmPassword = confirmPassword;
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
        } else {
            _ErrorUser.value = ""
        }
    }

    public fun checkEmail() {
        if (_Email.isBlank()) {
            _ErrorEmail.value = "El email no pot estar buit"
        } else if (!_EmailRegex.matches(_Email)) {
            _ErrorEmail.value = "El format del Gmail no es correcte"
        } else {
            _ErrorEmail.value = ""
        }
    }

    public fun checkPassword() {
        if (_Password.isBlank()) {
            _ErrorPassword.value = "La contrasenya no pot ser buida"
        } else if (!_PasswordRegex.matches(_Password)) {
            _ErrorPassword.value = "La contrasenya no es molt segura"
        } else {
            _ErrorPassword.value = ""
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
        } else {
            _ErrorName.value = ""
        }
    }

    public fun checkConfirmPassword() {
        if (!_ConfirmPassword.equals(_Password)) {
            _ErrorConfirmPassword.value = "No es la mateixa contrasenya"
        } else {
            _ErrorConfirmPassword.value = ""
        }
    }

    public fun validForm(): Boolean {
        if (_ErrorName.value.equals("") && _ErrorUser.value.equals("") && _ErrorEmail.value.equals("") && _ErrorPassword.value.equals("") && _ErrorConfirmPassword.value.equals("")) {
            return true;
        } else {
            return false
        }

    }
}