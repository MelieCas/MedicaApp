package com.example.medicaapp

import org.junit.Rule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.medicaapp.SignInViewModel
import org.junit.Assert.*
import org.junit.Test

class SignInViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val viewModel: SignInViewModel = SignInViewModel()


}