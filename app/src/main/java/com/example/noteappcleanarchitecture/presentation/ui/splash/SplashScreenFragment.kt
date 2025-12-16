package com.example.noteappcleanarchitecture.presentation.ui.splash

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.noteappcleanarchitecture.R
import com.example.noteappcleanarchitecture.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {
    //private lateinit var fragmentSplashScreenBinding: FragmentSplashScreenBinding
    private val viewModel: SplashScreenViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                SplashScreen()
                LaunchedEffect(Unit) {
                    viewModel.events.collect {
                        findNavController().navigate(R.id.action_splashScreenFragment_to_homeFragment)
                    }
                }
            }
        }
    }

}