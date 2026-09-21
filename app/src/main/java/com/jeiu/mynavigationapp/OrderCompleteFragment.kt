package com.example.mynavigationapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mynavigationapp.R
import com.example.mynavigationapp.databinding.FragmentOrderCompleteBinding

class OrderCompleteFragment : Fragment(R.layout.fragment_order_complete) {

    private var _binding: FragmentOrderCompleteBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOrderCompleteBinding.bind(view)

        // [처음으로] 버튼 클릭 시 HomeFragment로 이동
        binding.btnGoToHome.setOnClickListener {
            findNavController().navigate(R.id.action_orderCompleteFragment_to_homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}