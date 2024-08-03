package com.example.test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.CoffeeItemBinding
import com.example.test.model.CoffeeResponseItem

class CoffeeAdapter : RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder>() {


    lateinit var onClickItem: (CoffeeResponseItem) -> Unit
    lateinit var navigateToDetail: (id: String) -> Unit

    private val coffeeList = arrayListOf<CoffeeResponseItem>()

    inner class CoffeeViewHolder(val itemCoffeeBinding: CoffeeItemBinding) :
        RecyclerView.ViewHolder(itemCoffeeBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeViewHolder {
        val view = CoffeeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoffeeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return coffeeList.size
    }

    override fun onBindViewHolder(holder: CoffeeViewHolder, position: Int) {
        val coffeeItem = coffeeList[position]
        holder.itemCoffeeBinding.item = coffeeItem

//        holder.itemCoffeeBinding.buttonaddToBasket.setOnClickListener {
//            onClickItem(coffeeItem)
//        }

        holder.itemCoffeeBinding.root.setOnClickListener {
            navigateToDetail(coffeeItem.id)
        }
    }

    fun updateList(newList: List<CoffeeResponseItem>) {
        coffeeList.clear()
        coffeeList.addAll(newList)
        notifyDataSetChanged()
    }
}
