package com.example.noteappcleanarchitecture

import com.example.noteappcleanarchitecture.presentation.ui.splash.SplashScreenViewModel
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class SplashScreenViewModelTest {


    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `before 4 second no event is emitted`() = runTest {
        val viewModel = SplashScreenViewModel()

        val getEvent = async {
            viewModel.events.first()
            true
        }

        advanceTimeBy(3999)

        assertFalse(getEvent.isCompleted)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `after 4 second event is emitted`() = runTest {
        val viewModel = SplashScreenViewModel()
        val getEvent = async {
            viewModel.events.first()
            true
        }

        advanceTimeBy(4000)

        assertTrue(getEvent.await())
    }
}