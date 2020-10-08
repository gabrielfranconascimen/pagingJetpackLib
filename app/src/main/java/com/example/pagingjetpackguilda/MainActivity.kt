package com.example.pagingjetpackguilda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pagingjetpackguilda.paging2.NetworkState
import com.example.pagingjetpackguilda.paging2.PagingMagicCardAdapter
import com.example.pagingjetpackguilda.paging2.PagingMagicCardViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel = PagingMagicCardViewModel()
    private val adapter = PagingMagicCardAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMagicCard.layoutManager = LinearLayoutManager(this)
        rvMagicCard.adapter = adapter
        rvMagicCard.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))

        viewModel.ordersLiveData.observe(this, Observer {
            adapter.submitList(it)
        })

        viewModel.getInitialState().observe(this, Observer { initialState ->
            when(initialState.status) {
                NetworkState.Status.FAILED -> {
                    tvMessage.visibility = View.VISIBLE
                    rvMagicCard.visibility = View.GONE
                    progressBar.visibility = View.GONE

                    tvMessage.text = initialState.message
                }

                NetworkState.Status.RUNNING -> {
                    tvMessage.visibility = View.GONE
                    rvMagicCard.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                }

                NetworkState.Status.SUCCESS -> {
                    tvMessage.visibility = View.GONE
                    rvMagicCard.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }
            }
        })
    }
}