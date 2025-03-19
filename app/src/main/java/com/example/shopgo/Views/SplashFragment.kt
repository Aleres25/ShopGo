package com.example.shopgo.Views

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.shopgo.R
import com.example.shopgo.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val view = binding.root
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_feedFragment)
        }, 3000)
        return view
    }
    //tema değişikliği ! uygulama kendi tema seçsin
    //cihaz temasından etkilenmesin
    //switch kullan


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroy() {
        super.onDestroy()
    }
}