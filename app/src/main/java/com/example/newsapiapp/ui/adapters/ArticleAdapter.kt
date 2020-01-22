package com.example.newsapiapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapiapp.R
import com.example.newsapiapp.data.models.NewsResponse
import kotlinx.android.synthetic.main.rv_item_article.view.*

class ArticleAdapter: PagedListAdapter<NewsResponse.Article, ArticleAdapter.ArticleViewHolder>(ARTICLE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
        ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item_article, parent, false))

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: NewsResponse.Article?) {
            with(itemView) {
                title.text = item?.title
                description.text = item?.description
            }
        }
    }


    companion object {
        private val ARTICLE_COMPARATOR = object : DiffUtil.ItemCallback<NewsResponse.Article>() {
            override fun areItemsTheSame(
                oldItem: NewsResponse.Article,
                newItem: NewsResponse.Article
            ): Boolean = oldItem.title == newItem.title

            override fun areContentsTheSame(
                oldItem: NewsResponse.Article,
                newItem: NewsResponse.Article
            ): Boolean = oldItem.title == newItem.title &&
                    oldItem.description == newItem.description
        }
    }


}