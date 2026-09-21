package com.example.mynavigationapp // 기존 프로젝트의 패키지명을 유지해주세요.

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mynavigationapp.R
import com.example.mynavigationapp.databinding.FragmentMenuBinding

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMenuBinding.bind(view)

        // 1번 메뉴 클릭
        binding.btnMenu1.setOnClickListener {
            navigateToDetail("아메리카노")
        }

        // 2번 메뉴 클릭
        binding.btnMenu2.setOnClickListener {
            navigateToDetail("카페라떼")
        }

        // 3번 메뉴 클릭
        binding.btnMenu3.setOnClickListener {
            navigateToDetail("바닐라라떼")
        }

        // 4번 메뉴 클릭
        binding.btnMenu4.setOnClickListener {
            navigateToDetail("복숭아 아이스티")
        }
    }

    /**
     * [핵심 로직] 메뉴 이름을 Bundle에 담아 DetailFragment로 전달하는 공통 함수
     */
    private fun navigateToDetail(selectedMenuName: String) {
        // 1. 데이터를 담을 Bundle 빈 상자를 만듭니다.
        val bundle = Bundle()

        // 2. "menuName"이라는 이름표(Key)를 붙여 선택된 메뉴 이름(Value)을 상자에 넣습니다.
        bundle.putString("menuName", selectedMenuName)

        // 3. 목적지(DetailFragment)로 이동하면서 상자(bundle)를 함께 전달합니다.
        // nav_graph.xml에 정의된 Action ID를 사용합니다.
        findNavController().navigate(
            R.id.action_menuFragment_to_detailFragment,
            bundle
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}