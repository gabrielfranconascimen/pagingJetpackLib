package com.example.pagingjetpackguilda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pagingjetpackguilda.paging2.PagingMagicCardAdapter
import com.example.pagingjetpackguilda.paging2.PagingMagicCardViewModel
import kotlinx.android.synthetic.main.activity_main.*
import rx.lang.kotlin.subscribeBy
import rx.lang.kotlin.toObservable

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
            it.toObservable().subscribeBy (
                onNext = {
                    Log.i("testeF", "item = '${it.toString()}'")
                }
            )
            adapter.submitList(it)
        })

//        viewModel.errorLoading.observe(this, Observer {
//            rvMagicCard.visibility = View.GONE
//            tvMessage.visibility = View.VISIBLE
//            tvMessage.text = it
//        } )
//
//        viewModel.listCards.observe(this, Observer {
//            rvMagicCard.visibility = View.VISIBLE
//            tvMessage.visibility = View.GONE
//            adapter.updateData(it)
//        })
//
//        viewModel.showLoadingView.observe(this, Observer {
//            val isVisible = if(it) View.VISIBLE else View.GONE
//            progressBar.visibility = isVisible
//        })
    }

    override fun onResume() {
        super.onResume()
//        viewModel.fetchMagicCards()
    }

}