package com.recycleredittext

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_vertical.*


class VerticalActivity : AppCompatActivity() {

    private var mListOne = ArrayList<ModelProduct>()
    private lateinit var mAdapterOne: VerticalAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vertical)

        setListOne()
        setRecycleViewOne()
    }

    private fun setListOne() {
        val id = 1
        var model: ModelProduct
        for (i in 0..100) {
            model = ModelProduct()
            model.id = id + i
            model.name = "Product ${i + 1}"
            model.quantity = 0
            mListOne.add(model)
        }
    }

    private fun setRecycleViewOne() {
        val llm = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvOne?.layoutManager = llm
        mAdapterOne = VerticalAdapter(this, mListOne)
        rvOne?.adapter = mAdapterOne

        mAdapterOne.setOnItemClickListener(object : VerticalAdapter.ClickListener {
            override fun onUpdateQuantity() {
                updateTotalQuantity()
            }
        })
    }

    private fun updateTotalQuantity() {
        var totalQuantity = 0
        for (i in 0 until mListOne.size) {
            totalQuantity += mListOne[i].quantity
        }
        tvQuantity.text = totalQuantity.toString()
    }
}
