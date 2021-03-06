package com.example.newsapiapp.ui.screens.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapiapp.R
import com.example.newsapiapp.data.models.Status
import com.example.newsapiapp.ui.MainActivity
import com.example.newsapiapp.ui.adapters.ArticleAdapter
import kotlinx.android.synthetic.main.fragment_top_headlines.view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class TopHeadlinesFragment : Fragment() {

    private lateinit var rootView: View
    private val articleAdapter by lazy { ArticleAdapter() }
    private val homeViewModel: HomeViewModel by sharedViewModel()
    private val navController by lazy {  Navigation.findNavController( (activity as MainActivity).findViewById(R.id.nav_fragment) ) }

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
            rvEverything.layoutManager = LinearLayoutManager(context)
            rvEverything.adapter = articleAdapter

            articleAdapter.setOnArticleClickListener {
                val actionToArticleDetails =
                    HomeFragmentDirections.actionHomeFragmentToArticleDetailsFragment(
                        it
                    )
                navController.navigate(actionToArticleDetails)
            }

            homeViewModel.refreshTopHeadlines()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        homeViewModel.topHeadlinesArticles.observe(viewLifecycleOwner, Observer {
            articleAdapter.submitList(it) })

        homeViewModel.refreshTopHeadlinesState.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS) {
            }
        })
    }

    companion object {
        fun newInstance() =
            TopHeadlinesFragment()
    }

}
