package com.example.exerciseday_android.ui.course.custom

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.exerciseday_android.*
import com.example.exerciseday_android.databinding.FragmentCustomStartBinding

class CustomStartFragment : Fragment() {
    lateinit var binding: FragmentCustomStartBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var index = arguments?.getInt("index")
        binding = FragmentCustomStartBinding.inflate(inflater, container, false)

        val mainActivity = activity as MainActivity
        val prevFragment = HomeFragment() //뒤로가기
        val defaultFragment = SearchTypeFragment()
        val focusFragment = SearchLateFragment()
        val searchResultFragment = SearchResultFragment()
        val customDoneFragment = CustomDoneFragment()

        parentFragmentManager.beginTransaction()
            .replace(R.id.search_type_frm, defaultFragment)
            .commit()

        binding.customStartBackBtn.setOnClickListener {
            mainActivity.replaceFragment(prevFragment)
        }

        binding.customStartSearchEt.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if(hasFocus){
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.search_type_frm, focusFragment)
                        .commit()
                } else {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.search_type_frm, defaultFragment)
                        .commit()
                }
            }

        binding.customStartSearchEt.setOnEditorActionListener(object: TextView.OnEditorActionListener{
            override fun onEditorAction(p0: TextView?, actionId: Int, p2: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    val bundle = Bundle()
                    bundle.putString("keyword", binding.customStartSearchEt.text.toString())

                    parentFragmentManager.beginTransaction()
                        .replace(R.id.search_type_frm, defaultFragment)
                        .commit()

                    searchResultFragment.arguments = bundle
                    mainActivity.replaceFragment(searchResultFragment)
                }
                return false
            }
        })

        binding.fabBag.setOnClickListener {
            mainActivity.replaceFragment(customDoneFragment)
        }

        return binding.root
    }
}