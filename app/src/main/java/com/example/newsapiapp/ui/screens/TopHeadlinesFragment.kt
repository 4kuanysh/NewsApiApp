package com.example.newsapiapp.ui.screens


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.newsapiapp.R

class TopHeadlinesFragment : Fragment() {

    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_top_headlines, container, false)
        initUI()
        return rootView
    }

    private fun initUI() {
        with(rootView) {
            
        }
    }

    companion object {
        fun newInstance() = TopHeadlinesFragment()
    }

}
