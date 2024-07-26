package com.example.test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.CardCoffeeBinding
import com.example.test.model.CoffeeResponseItem

class BasketAdapter : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    private val productList = arrayListOf<CoffeeResponseItem>()

    class BasketViewHolder(private val cardBasketBinding: CardCoffeeBinding) :
        RecyclerView.ViewHolder(cardBasketBinding.root) {
        fun bind(productResponseModel: CoffeeResponseItem) {
            cardBasketBinding.item = productResponseModel
            cardBasketBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val layout = CardCoffeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BasketViewHolder(layout)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun updateList(list: List<CoffeeResponseItem>) {
        productList.clear()
        productList.addAll(list)
        notifyDataSetChanged()
    }
}