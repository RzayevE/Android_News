package com.reset.news

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item.view.*

class MainAdapter(val homeFeed: HomeFeed) : RecyclerView.Adapter<CustomViewHolder>() {

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
        holder.itemView.newsTitle_text?.text = news.title
        val newsImageView = holder.itemView.item_image
        val imageUrl = news.urlToImage.toString()
        Picasso.get()
            .load(imageUrl)
            .into(newsImageView)
        Picasso.get().isLoggingEnabled = true

    }

}

class CustomViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    init {
        v.setOnClickListener {
            val intent = Intent(v.context, DetailsActivity::class.java)

            v.context.startActivity(intent)
        }
    }

}