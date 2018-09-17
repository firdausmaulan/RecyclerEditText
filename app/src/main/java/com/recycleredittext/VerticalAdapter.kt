package com.recycleredittext

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.adapter_vertical.view.*
import java.util.concurrent.TimeUnit

class VerticalAdapter(val context: Context?,
                      private val listProduct: ArrayList<ModelProduct>)
    : RecyclerView.Adapter<VerticalAdapter.ViewHolder>() {

    private var clickListener: ClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_vertical, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.tvName.text = listProduct[position].name
        holder.etQuantity.setText(listProduct[position].quantity.toString())
        holder.btnMin.setOnClickListener {
            if (listProduct[position].quantity > 0) {
                listProduct[position].quantity--
                holder.etQuantity.tag = listProduct[position].quantity.toString()
                holder.etQuantity.setText(listProduct[position].quantity.toString())
                clickListener?.onUpdateQuantity()
            }
        }

        holder.btnPlus.setOnClickListener {
            listProduct[position].quantity++
            holder.etQuantity.tag = listProduct[position].quantity.toString()
            holder.etQuantity.setText(listProduct[position].quantity.toString())
            clickListener?.onUpdateQuantity()
        }

        RxTextView.textChanges(holder.etQuantity)
                .filter { charSequence -> charSequence.toString() != holder.etQuantity.tag }
                .map { charSequence ->
                    if (charSequence.isNotEmpty()) {
                        return@map charSequence.toString().toInt()
                    } else {
                        return@map 0
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { quantity ->
                    holder.etQuantity.tag = quantity.toString()
                    listProduct[position].quantity = quantity
                    clickListener?.onUpdateQuantity()
                }
    }

    override fun getItemCount(): Int = listProduct.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = itemView.tvName
        val btnMin = itemView.btnMinus
        val btnPlus = itemView.btnPlus
        val etQuantity = itemView.etQuantity
    }

    fun setOnItemClickListener(mClickListener: ClickListener) {
        clickListener = mClickListener
    }

    interface ClickListener {
        fun onUpdateQuantity()
    }
}