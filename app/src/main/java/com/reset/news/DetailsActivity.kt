package com.reset.news

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        val actionBar = supportActionBar
        actionBar!!.title = intent.getStringExtra(CustomViewHolder.CATEGORY_KEY).toUpperCase()

        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        val details_image: ImageView = findViewById(R.id.details_image)
        val details_title: TextView = findViewById(R.id.details_title_text)
        val details_desc: TextView = findViewById(R.id.details_desc_text)
        val details_published: TextView = findViewById(R.id.details_published_text)

        val publishedDate = intent.getStringExtra(CustomViewHolder.DATE_KEY)
        val strs = publishedDate.split("T").get(0)
        details_title.text = intent.getStringExtra(CustomViewHolder.TITLE_KEY)
        details_desc.text = intent.getStringExtra(CustomViewHolder.DESK_KEY)
        details_published.text = strs
        val imageUrl = intent.getStringExtra(CustomViewHolder.IMAGE_KEY)
        if (imageUrl.isNullOrEmpty()) {
            return
        } else {
            Picasso.get()
                .load(imageUrl)
                .into(details_image)
            Picasso.get().isLoggingEnabled = true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}