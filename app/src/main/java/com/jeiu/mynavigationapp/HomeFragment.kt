package com.example.mynavigationapp // 기존 프로젝트의 패키지 경로를 그대로 유지해주세요.

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mynavigationapp.R
import com.example.mynavigationapp.databinding.FragmentHomeBinding

/**
 * [핵심 1] Fragment(R.layout.fragment_home)
 * - 생성자에 레이아웃 ID를 전달하면, onCreateView()를 수동으로 오버라이드하여 인플레이트할 필요가 없습니다.
 */
class HomeFragment : Fragment(R.layout.fragment_home) {

    /**
     * [핵심 2] ViewBinding 이중 프로퍼티 구조
     * - _binding: Fragment의 뷰는 파괴되더라도 Fragment 인스턴스 자체는 살아있을 수 있습니다.
     *             따라서 뷰가 사라졌을 때 null 처리를 할 수 있도록 nullable(? = null)로 선언합니다.
     * - binding:  실제 코드에서 null 체크(binding?.xxx)를 반복하지 않고 안전하게 접근하기 위한 getter입니다.
     */
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // [핵심 3] 생성된 View와 Binding 객체 연결
        // Fragment(R.layout.fragment_home)에 의해 생성된 view를 넘겨받아 바인딩 객체를 초기화합니다.
        _binding = FragmentHomeBinding.bind(view)

        // [메뉴 보기] 버튼 클릭 리스너 설정
        binding.btnGoToMenu.setOnClickListener {
            /**
             * [핵심 4] Navigation Component를 통한 화면 이동
             * - findNavController().navigate()를 호출합니다.
             * - 인자로는 nav_graph.xml에 정의된 Action ID(action_homeFragment_to_menuFragment)
             *   또는 대상 Destination ID(R.id.menuFragment)를 전달합니다.
             */
            findNavController().navigate(R.id.action_homeFragment_to_menuFragment)
        }
    }

    /**
     * [핵심 5] 메모리 누수(Memory Leak) 방지
     * - Fragment의 View 수명주기가 끝날 때(화면 전환, 뒤로 가기 등) 호출됩니다.
     * - _binding을 null로 해제하지 않으면 이미 메모리에서 내려가야 할 View 객체를
     *   계속 참조하고 있어 메모리 누수가 발생합니다.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}