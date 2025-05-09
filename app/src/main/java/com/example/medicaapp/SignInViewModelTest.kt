package com.example.medicaapp

import org.junit.Rule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.*
import org.junit.Test

class SignInViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val viewModel: SignInViewModel = SignInViewModel()

    @Test
    fun nomUsuariRetornaErrorQuanEsOfensiu() {
        viewModel.setUsuari("Puto22")

        viewModel.checkUser()
        assertEquals("El nom d'usuari no pot ser despectiu", viewModel.errorUser.value)
    }

    @Test
    fun nomUsuariRetornaErrorQuanEsBuit() {
        viewModel.setUsuari("")

        viewModel.checkUser()
        assertEquals("El nom d'usuari no pot estar buit", viewModel.errorUser.value)
    }

    @Test
    fun nomUsuariRetornaErrorQuanEsMoltCurt() {
        viewModel.setUsuari("ab")

        viewModel.checkUser()
        assertEquals("El nom d'usuari es molt curt", viewModel.errorUser.value)
    }

    @Test
    fun nomUsuariRetornaErrorQuanEsMoltLlarg() {
        viewModel.setUsuari("1234567890123456789012345678901234")

        viewModel.checkUser()
        assertEquals("El nom d'usuari es molt llarg", viewModel.errorUser.value)
    }

    @Test
    fun correuElectronicEsCorrecte() {
        viewModel.setEmail("exempleValid@gmail.com")

        viewModel.chechEmail()
        assertEquals("", viewModel.errorEmail.value)
    }

    @Test
    fun correuElectronicRetornaErrorQuanEsBuit() {
        viewModel.setEmail("")

        viewModel.chechEmail()
        assertEquals("El email no pot estar buit", viewModel.errorEmail.value)
    }

    @Test
    fun correuElectronicRetornaErrorQuanNoTeUsuari() {
        viewModel.setEmail("@novalid.com")

        viewModel.chechEmail()
        assertEquals("El format del Gmail no es correcte", viewModel.errorEmail.value)
    }

    @Test
    fun correuElectronicRetornaErrorQuanTeEspaiEnBlanc() {
        viewModel.setEmail(" @gmail.com")

        viewModel.chechEmail()
        assertEquals("El format del Gmail no es correcte", viewModel.errorEmail.value)
    }

    @Test
    fun correuElectronicRetornaErrorQuanNoTeDomini() {
        viewModel.setEmail("noValid@")

        viewModel.chechEmail()
        assertEquals("El format del Gmail no es correcte", viewModel.errorEmail.value)
    }

    @Test
    fun correuElectronicRetornaErrorQuanNoTePunt() {
        viewModel.setEmail("noValid@gmailcom")

        viewModel.chechEmail()
        assertEquals("El format del Gmail no es correcte", viewModel.errorEmail.value)
    }

    @Test
    fun contrasenyaEsCorrecte() {
        viewModel.setPassword("aAbB1!")

        viewModel.checkPassword()
        assertEquals("", viewModel.errorPassword.value)
    }

    @Test
    fun contrasenyaRetornaErrorQuanEsBuida() {
        viewModel.setPassword("")

        viewModel.checkPassword()
        assertEquals("La contrasenya no pot ser buida", viewModel.errorPassword.value)
    }

    @Test
    fun cotrasenyaRetornaErrorQuanEsMassaCurta() {
        viewModel.setPassword("123")

        viewModel.checkPassword()
        assertEquals("La contrasenya és massa curta", viewModel.errorPassword.value)
    }

    @Test
    fun contrasenyaRetornaErrorQuanNoTeMajuscules() {
        viewModel.setPassword("abcde1!")

        viewModel.checkPassword()
        assertEquals("La contrasenya no te majúscules", viewModel.errorPassword.value))
    }

    @Test
    fun contrasenyaRetornaErrorQuanNoTeMinuscules() {
        viewModel.setPassword("ABCDE1!")

        viewModel.checkPassword()
        assertEquals("La contrasenya no te minúscules", viewModel.errorPassword.value)
    }

    @Test
    fun contrasenyaRetornaErrorQuanNoTeNumeros() {
        viewModel.setPassword("abcdef!")

        viewModel.checkPassword()
        assertEquals("La contrasenya no te números", viewModel.errorPassword.value)
    }

    @Test
    fun contrasenyaRetornaErrorQuanNoTeCaractersEspecials() {
        viewModel.setPassword("Abcdef1")

        viewModel.checkPassword()
        assertEquals("La contrasenya no te caracters especials", viewModel.errorPassword.value)
    }

    @Test
    fun contrasenyaCoinicideixAmbConfirmacio() {
        viewModel.setPassword("aAbB1!")
        viewModel.setConfirmPassword("aAbB1!")

        viewModel.checkConfirmPassword()
        assertEquals("", viewModel.errorConfirmPassword.value)
    }

    @Test
    fun confirmacioContrasenyaRetornaErrorQuanNoCoincideix() {
        viewModel.setPassword("aAbB1!")
        viewModel.setConfirmPassword("aAbB2!")

        viewModel.checkConfirmPassword()
        assertEquals("Les contrasenyes no coincideixen", viewModel.errorConfirmPassword.value)
    }

    @Test
    fun nomEsCorrecte() {
        viewModel.setNom("Kilian")

        viewModel.checkNom()
        assertEquals("", viewModel.errorName.value)
    }

    @Test
    fun nomRetornaErrorQuanEsBuit() {
        viewModel.setNom("")

        viewModel.checkNom()
        assertEquals("El nom no pot ser buit", viewModel.errorName.value)
    }

    @Test
    fun nomRetornaErrorQuanEsMoltCurt() {
        viewModel.setNom("ab")

        viewModel.checkNom()
        assertEquals("El nom es molt curt", viewModel.errorName.value)
    }

    @Test
    fun nomRetornaErrorQuanEsMoltLlarg() {
        viewModel.setNom("1234567890123456789012345678901234")

        viewModel.checkNom()
        assertEquals("El nom es molt llarg", viewModel.errorName.value)
    }

    @Test
    fun nomRetornaErrorSiConteParaulesOfensives() {
        viewModel.setNom("Puto22")

        viewModel.checkNom()
        assertEquals("El nom no pot ser ofensiu", viewModel.errorName.value)
    }

}