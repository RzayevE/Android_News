package com.reset.news.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.reset.news.HomeFeed
import com.reset.news.MainAdapter

import com.reset.news.R
import kotlinx.android.synthetic.main.second_tab_fragment.*
import okhttp3.*
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FifthTabFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondTabFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.second_tab_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rw_two.layoutManager = LinearLayoutManager(context)
        itemsswipetorefresh_second.setOnRefreshListener {
            fetchJson()
            itemsswipetorefresh_second.isRefreshing = false
        }

        fetchJson()
    }

    fun fetchJson() {
        println("Attempting to Fetch Json")

        val api_key = getString(R.string.api_key)
        val country = getString(R.string.country)
        val category = getString(R.string.cat_business)
        val url_part1 = "https://newsapi.org/v2/top-headlines?country="
        val url_part2 = "&category="
        val url_part3 = "&apiKey="

        val url = url_part1 + country + url_part2 + category + url_part3 + api_key
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()

                val gson = GsonBuilder().create()

                val homeFeed = gson.fromJson(body, HomeFeed::class.java)

                activity?.runOnUiThread {
                    rw_two.adapter = MainAdapter(homeFeed)
                }
            }
        })
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Politics.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FifthTabFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
