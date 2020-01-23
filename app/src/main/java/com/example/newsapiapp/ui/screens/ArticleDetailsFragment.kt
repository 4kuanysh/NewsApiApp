package com.example.newsapiapp.ui.screens


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide

import com.example.newsapiapp.R
import com.example.newsapiapp.ui.screens.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_article_details.view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ArticleDetailsFragment : Fragment() {

    private lateinit var rootView: View
    private val safeArgs: ArticleDetailsFragmentArgs by navArgs()
    private val homeViewModel: HomeViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_article_details, container, false)
        initUI()
        return rootView
    }

    private fun initUI() {
        val article = safeArgs.article
        with(rootView) {
            Glide.with(context).load(article.urlToImage).into(image)
            title.text = article.title
            author.text = article.author
            publicationDate.text = article.publishedAt
            content.text = article.content
            markBtn.setOnClickListener {
                homeViewModel.markArticle(article)
            }
        }
    }


}
