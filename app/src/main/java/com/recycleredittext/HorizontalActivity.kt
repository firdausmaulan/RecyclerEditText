package com.recycleredittext

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_horizontal.*
import android.support.v7.widget.LinearLayoutManager


class HorizontalActivity : AppCompatActivity() {

    private var mListOne = ArrayList<ModelProduct>()
    private var mListTwo = ArrayList<ModelProduct>()
    private lateinit var mAdapterOne: HorizontalAdapter
    private lateinit var mAdapterTwo: HorizontalAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizontal)

        setListOne()
        setListTwo()
        setRecycleViewOne()
        setRecycleViewTwo()
    }

    private fun setListOne() {
        val id = 1
        var model: ModelProduct
        for (i in 0..5) {
            model = ModelProduct()
            model.id = id + i
            model.name = "Product ${i + 1}"
            model.quantity = 0
            mListOne.add(model)
        }
    }

    private fun setListTwo() {
        val id = 11
        var model: ModelProduct
        for (i in 0..5) {
            model = ModelProduct()
            model.id = id + i
            model.name = "Product ${i + 1}"
            model.quantity = 0
            mListTwo.add(model)
        }
    }

    private fun setRecycleViewOne() {
        val llm = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvOne?.layoutManager = llm
        mAdapterOne = HorizontalAdapter(this, mListOne)
        rvOne?.adapter = mAdapterOne

        mAdapterOne.setOnItemClickListener(object : HorizontalAdapter.ClickListener {
            override fun onUpdateQuantity() {
                updateTotalQuantity()
            }
        })
    }

    private fun setRecycleViewTwo() {
        val llm = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvTwo?.layoutManager = llm
        mAdapterTwo = HorizontalAdapter(this, mListTwo)
        rvTwo?.adapter = mAdapterTwo

        mAdapterTwo.setOnItemClickListener(object : HorizontalAdapter.ClickListener {
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
        for (i in 0 until mListTwo.size) {
            totalQuantity += mListTwo[i].quantity
        }
        tvQuantity.text = totalQuantity.toString()
    }
}
