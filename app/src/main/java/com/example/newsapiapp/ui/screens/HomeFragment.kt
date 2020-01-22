package com.example.newsapiapp.ui.screens


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.newsapiapp.R
import com.example.newsapiapp.ui.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    private val viewPagerAdapter by lazy { ViewPagerAdapter(childFragmentManager) }
    private lateinit var rootView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewPagerAdapter.addFragment(TopHeadlinesFragment.newInstance(), getString(R.string.text_top_headlines))
        viewPagerAdapter.addFragment(EverythingFragment.newInstance(), getString(R.string.text_everything))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false)
        initUI()
        return rootView
    }

    private fun initUI() {
        with(rootView) {
            //init viewpager
            viewPager.adapter = viewPagerAdapter
            tabLayout.setupWithViewPager(viewPager)
        }
    }


}
