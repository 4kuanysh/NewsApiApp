package com.example.newsapiapp.ui.screens


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import androidx.recyclerview.widget.LinearLayoutManager

import com.example.newsapiapp.R
import com.example.newsapiapp.data.models.Status
import com.example.newsapiapp.ui.adapters.ArticleAdapter
import kotlinx.android.synthetic.main.fragment_everything.view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class EverythingFragment : Fragment() {

    private lateinit var rootView: View
    private val articleAdapter by lazy { ArticleAdapter() }
    private val homeViewModel: HomeViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_everything, container, false)
        initUI()
        return rootView
    }

    private fun initUI() {
        with(rootView) {
            rvEverything.layoutManager = LinearLayoutManager(context)
            rvEverything.adapter = articleAdapter

            swipeRefresh.setOnRefreshListener {
                Log.d("taaag", "swipeRefresh.setOnRefreshListener()")
                homeViewModel.refreshEverything()
            }

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeViewModel.everythingArticles.observe(viewLifecycleOwner, Observer {
            articleAdapter.submitList(it) })

        homeViewModel.refreshEverythingState.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS) {
                rootView.swipeRefresh.isRefreshing = false
            }
        })
    }


    companion object {
        fun newInstance() = EverythingFragment()
    }

}
