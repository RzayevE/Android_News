package com.reset.news

import android.app.Activity
import android.content.Intent
import android.content.pm.FeatureGroupInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item.view.*
import java.io.Serializable


class MainAdapter(val homeFeed: HomeFeed, val category: String) :
    RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemCount(): Int {
        return homeFeed.articles.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.item, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val news = homeFeed.articles.get(position)
        val date = news.publishedAt.split('T').get(0)
        holder.itemView.newsTitle_text?.text = news.title
        holder.itemView.newsDate_text.text = date
        holder.category = category
        val newsImageView = holder.itemView.item_image
        val imageUrl = news.urlToImage
        holder.news = news
        if (imageUrl.isNullOrEmpty()) {
            return
        } else {
            Picasso.get()
                .load(imageUrl)
                .into(newsImageView)
            Picasso.get().isLoggingEnabled = true
        }


    }

}

class CustomViewHolder(val v: View, var news: Article? = null, var category: String? = null) :
    RecyclerView.ViewHolder(v) {

    companion object {
        val TITLE_KEY = "title"
        val IMAGE_KEY = "imageUrl"
        val DESK_KEY = "desc"
        val AUTHOR_KEY = "author"
        val DATE_KEY = "publishedAt"
        val CATEGORY_KEY = "category"
        val FEED_KEY = "feed"
    }

    init {
        v.setOnClickListener {
            val intent = Intent(it.context, DetailsActivity::class.java)
            intent.putExtra(TITLE_KEY, news?.title)
            intent.putExtra(IMAGE_KEY, news?.urlToImage)
            intent.putExtra(DESK_KEY, news?.description)
            intent.putExtra(AUTHOR_KEY, news?.author)
            intent.putExtra(DATE_KEY, news?.publishedAt)
            intent.putExtra(CATEGORY_KEY, category)

            it.context.startActivity(intent)
        }
    }

}