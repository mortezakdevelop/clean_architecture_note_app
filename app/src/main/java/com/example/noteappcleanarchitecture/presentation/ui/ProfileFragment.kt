package com.example.noteappcleanarchitecture.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noteappcleanarchitecture.R
import com.example.noteappcleanarchitecture.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    lateinit var fragmentProfileBinding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentProfileBinding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        return fragmentProfileBinding.root
    }
}