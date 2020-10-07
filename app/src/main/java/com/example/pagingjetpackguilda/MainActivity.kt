package com.example.pagingjetpackguilda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel = MagicCardViewModel()
    private val adapter = MagicCardListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMagicCard.layoutManager = LinearLayoutManager(this)
        rvMagicCard.adapter = adapter
        rvMagicCard.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))

        viewModel.errorLoading.observe(this, Observer {
            rvMagicCard.visibility = View.GONE
            tvMessage.visibility = View.VISIBLE
            tvMessage.text = it
        } )

        viewModel.listCards.observe(this, Observer {
            rvMagicCard.visibility = View.VISIBLE
            tvMessage.visibility = View.GONE
            adapter.updateData(it)
        })

        viewModel.showLoadingView.observe(this, Observer {
            val isVisible = if(it) View.VISIBLE else View.GONE
            progressBar.visibility = isVisible
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchMagicCards()
    }

}