package com.example.newsapiapp.ui.screens


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.newsapiapp.R
import com.example.newsapiapp.ui.adapters.ArticleAdapter
import kotlinx.android.synthetic.main.fragment_my_list.view.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class MyListFragment : Fragment() {


    private lateinit var rootView: View
    private val articleAdapter by lazy { ArticleAdapter() }
    private val myListViewModel: MyListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_my_list, container, false)
        initUI()
        return rootView
    }

    private fun initUI() {
        with(rootView) {
            rvArticles.layoutManager = LinearLayoutManager(context)
            rvArticles.adapter = articleAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        myListViewModel.articles.observe(viewLifecycleOwner, Observer {
            articleAdapter.submitList(it)
        })
    }


}
