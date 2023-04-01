package com.example.noteappcleanarchitecture.presentation.ui

import android.net.IpSecManager.ResourceUnavailableException
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.noteappcleanarchitecture.R
import com.example.noteappcleanarchitecture.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {
    private lateinit var fragmentSplashScreenBinding:FragmentSplashScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentSplashScreenBinding = FragmentSplashScreenBinding.inflate(layoutInflater,container,false)
        startCountDown()
        return fragmentSplashScreenBinding.root
    }

    private fun startCountDown(){
        object : CountDownTimer(2000,1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                findNavController().navigate(R.id.action_splashScreenFragment_to_homeFragment)
            }

        }.start()
    }
}