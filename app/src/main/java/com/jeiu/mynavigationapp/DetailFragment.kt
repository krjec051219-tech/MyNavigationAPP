package com.example.mynavigationapp // 기존 프로젝트의 패키지명을 유지해주세요.

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mynavigationapp.R
import com.example.mynavigationapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)

        // 1. MenuFragment에서 전달한 Bundle 데이터 꺼내기
        // arguments가 null이거나 해당 Key가 없을 경우를 대비해 기본값을 설정합니다.
        val menuName = arguments?.getString("menuName") ?: "선택된 메뉴 없음"

        // 2. 전달받은 메뉴 이름에 따라 가격 결정하기 (when 문 활용)
        val price = when (menuName) {
            "아메리카노" -> 3000
            "카페라떼" -> 3500
            "바닐라라떼" -> 4000
            "복숭아 아이스티" -> 3000
            else -> 0
        }

        // 3. 화면 UI(TextView)에 데이터 반영하기
        binding.tvSelectedMenu.text = "선택한 메뉴: $menuName"
        binding.tvPrice.text = "가격: ${price}원"
        binding.tvCount.text = "수량: 1개"

        // 4. [장바구니 담기] 버튼 클릭 시 CartFragment로 데이터 전달하며 이동
        binding.btnAddToCart.setOnClickListener {
            // CartFragment로 전달할 새로운 Bundle 생성
            val bundle = Bundle().apply {
                putString("menuName", menuName) // 메뉴 이름 전달
                putInt("price", price)          // 가격 전달 (정수형 Int)
            }

            // nav_graph.xml에 정의된 Detail -> Cart 이동 액션 실행
            findNavController().navigate(
                R.id.action_detailFragment_to_cartFragment,
                bundle
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}