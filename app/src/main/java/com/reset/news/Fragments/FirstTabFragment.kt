package com.reset.news.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.reset.news.HomeFeed
import com.reset.news.MainActivity
import com.reset.news.MainAdapter
import com.reset.news.R
import kotlinx.android.synthetic.main.first_tab_fragment.*
import okhttp3.*
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstTabFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstTabFragment : Fragment() {
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
        return inflater.inflate(R.layout.first_tab_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rw_one.layoutManager = LinearLayoutManager(context)
        itemsswipetorefresh_first.setOnRefreshListener {
            fetchJson()
            itemsswipetorefresh_first.isRefreshing = false
        }
//        rw_main.adapter = mainAdapter

        fetchJson()
    }

    fun fetchJson() {
        var region = ""
        if (activity?.intent?.hasExtra("region")!!) {
            region = requireActivity().intent.getStringExtra("region")!!
        } else {
            region = "ru"
        }
        val api_key = getString(R.string.api_key)
        val pageSize = getString(R.string.pageSize)
        val url_part1 = "https://newsapi.org/v2/top-headlines?country="
        val url_part3 = "&apiKey="
        val url_part2 = "&pageSize="

        val url = url_part1 + region + url_part2 + pageSize + url_part3 + api_key
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(context, "Couldn't load data from API.", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println(body)

                val gson = GsonBuilder().create()

                val homeFeed = gson.fromJson(body, HomeFeed::class.java)

                activity?.runOnUiThread {
                    rw_one.adapter = MainAdapter(homeFeed, "")

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
         * @return A new instance of fragment MainPage.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstTabFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
