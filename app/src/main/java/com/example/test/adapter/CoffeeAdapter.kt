package com.example.test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.CoffeeItemBinding

class CoffeeAdapter : RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder>() {




    inner class CoffeeViewHolder(val itemCoffeeBinding: CoffeeItemBinding) :
        RecyclerView.ViewHolder(itemCoffeeBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeViewHolder {
        val view = CoffeeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoffeeViewHolder(view)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CoffeeViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


}