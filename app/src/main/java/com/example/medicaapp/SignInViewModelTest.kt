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
    fun `nom usuari retorna error quan és ofensiu`() {
        viewModel.setUsuari("Puto22")

        viewModel.checkUser()
        assertEquals("El nom d'usuari no pot ser despectiu", viewModel.errorUser.value)
    }

    @Test
    fun `nom usuari retorna error quan és buit`() {
        viewModel.setUsuari("")

        viewModel.checkUser()
        assertEquals("El nom d'usuari no pot estar buit", viewModel.errorUser.value)
    }

    @Test
    fun `nom usuari retorna error quan és molt curt`() {
        viewModel.setUsuari("ab")

        viewModel.checkUser()
        assertEquals("El nom d'usuari es molt curt", viewModel.errorUser.value)
    }

    @Test
    fun `nom usuari retorna error quan és molt llarg`() {
        viewModel.setUsuari("1234567890123456789012345678901234")

        viewModel.checkUser()
        assertEquals("El nom d'usuari es molt llarg", viewModel.errorUser.value)
    }

    @Test
    fun `correu electronic és correcte`() {
        viewModel.setEmail("exempleValid@gmail.com")

        viewModel.chechEmail()
        assertEquals("", viewModel.errorEmail.value)
    }

    @Test
    fun `correu electronic retorna error quan es buit`() {
        viewModel.setEmail("")

        viewModel.chechEmail()
        assertEquals("El email no pot estar buit", viewModel.errorEmail.value)
    }

    @Test
    fun `correu electronic retorna error quan no te usuari`() {
        viewModel.setEmail("@novalid.com")

        viewModel.chechEmail()
        assertEquals("El format del Gmail no es correcte", viewModel.errorEmail.value)
    }

    @Test
    fun `correu electronic retorna error quan te espai en blanc`() {
        viewModel.setEmail(" @gmail.com")

        viewModel.chechEmail()
        assertEquals("El format del Gmail no es correcte", viewModel.errorEmail.value)
    }

    @Test
    fun `correu electronic retorna error quan no te domini`() {
        viewModel.setEmail("noValid@")

        viewModel.chechEmail()
        assertEquals("El format del Gmail no es correcte", viewModel.errorEmail.value)
    }

    @Test
    fun `correu electronic retorna error quan no te punt`() {
        viewModel.setEmail("noValid@gmailcom")

        viewModel.chechEmail()
        assertEquals("El format del Gmail no es correcte", viewModel.errorEmail.value)
    }

    @Test
    fun `contrasenya és correcte`() {
        viewModel.setPassword("aAbB1!")

        viewModel.checkPassword()
        assertEquals("", viewModel.errorPassword.value)
    }

    @Test
    fun `contrasenya retorna error quan es buida`() {
        viewModel.setPassword("")

        viewModel.checkPassword()
        assertEquals("La contrasenya no pot ser buida", viewModel.errorPassword.value)
    }

    @Test
    fun `cotrasenya retorna error quan es massa curta`() {
        viewModel.setPassword("123")

        viewModel.checkPassword()
        assertEquals("La contrasenya és massa curta", viewModel.errorPassword.value)
    }

    @Test
    fun `contrasenya retorna error quan no te majúscules`() {
        viewModel.setPassword("abcde1!")

        viewModel.checkPassword()
        assertEquals("La contrasenya no te majúscules", viewModel.errorPassword.value))
    }

    @Test
    fun `contrasenya retorna error quan no te minúscules`() {
        viewModel.setPassword("ABCDE1!")

        viewModel.checkPassword()
        assertEquals("La contrasenya no te minúscules", viewModel.errorPassword.value)
    }

    @Test
    fun `contrasenya retorna error quan no te números`() {
        viewModel.setPassword("abcdef!")

        viewModel.checkPassword()
        assertEquals("La contrasenya no te números", viewModel.errorPassword.value)
    }

    @Test
    fun `contrasenya retorna error quan no te caracters especials`() {
        viewModel.setPassword("Abcdef1")

        viewModel.checkPassword()
        assertEquals("La contrasenya no te caracters especials", viewModel.errorPassword.value)
    }

    @Test
    fun `contrasenya coinicideix amb confirmació`() {
        viewModel.setPassword("aAbB1!")
        viewModel.setConfirmPassword("aAbB1!")

        viewModel.checkConfirmPassword()
        assertEquals("", viewModel.errorConfirmPassword.value)
    }

    @Test
    fun `confirmació contrasenya retorna error quan no coincideix`() {
        viewModel.setPassword("aAbB1!")
        viewModel.setConfirmPassword("aAbB2!")

        viewModel.checkConfirmPassword()
        assertEquals("Les contrasenyes no coincideixen", viewModel.errorConfirmPassword.value)
    }

    @Test
    fun `nom és correcte`() {
        viewModel.setNom("Kilian")

        viewModel.checkNom()
        assertEquals("", viewModel.errorName.value)
    }

    @Test
    fun `nom retorna error quan és buit`() {
        viewModel.setNom("")

        viewModel.checkNom()
        assertEquals("El nom no pot ser buit", viewModel.errorName.value)
    }

    @Test
    fun `nom retorna error quan és molt curt`() {
        viewModel.setNom("ab")

        viewModel.checkNom()
        assertEquals("El nom es molt curt", viewModel.errorName.value)
    }

    @Test
    fun `nom retorna error quan és molt llarg`() {
        viewModel.setNom("1234567890123456789012345678901234")

        viewModel.checkNom()
        assertEquals("El nom es molt llarg", viewModel.errorName.value)
    }

    @Test
    fun `nom retorna error si conté paraules ofensives`() {
        viewModel.setNom("Puto22")

        viewModel.checkNom()
        assertEquals("El nom no pot ser ofensiu")
    }

}