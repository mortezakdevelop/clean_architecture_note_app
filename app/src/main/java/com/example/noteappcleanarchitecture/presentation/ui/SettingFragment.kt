package com.example.noteappcleanarchitecture.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noteappcleanarchitecture.R
import com.example.noteappcleanarchitecture.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    private lateinit var fragmentSettingBinding:FragmentSettingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentSettingBinding = FragmentSettingBinding.inflate(layoutInflater,container,false)
        return fragmentSettingBinding.root
    }

}