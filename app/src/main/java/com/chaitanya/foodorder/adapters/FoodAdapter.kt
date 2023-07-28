package com.chaitanya.foodorder.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chaitanya.foodorder.R
import com.chaitanya.foodorder.viewModel.FoodItem

class FoodAdapter(private val onItemClick: (FoodItem) -> Unit) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    private var foodItems: ArrayList<FoodItem> = ArrayList()
    class FoodViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val tvDay: TextView = itemView.findViewById(R.id.tvDay)
        val tvMeal: TextView = itemView.findViewById(R.id.tvMeal)
        val btnCart : Button = itemView.findViewById(R.id.btnCart)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val foodItem = foodItems[position]
        holder.apply {
            tvName.text = foodItem.name
            tvDescription.text = foodItem.description
            tvPrice.text = "Price: $${foodItem.price}"
            tvDay.text = "Day: ${foodItem.day}"
            tvMeal.text = "Meal: ${foodItem.meal}"
            btnCart.setOnClickListener {
                onItemClick(foodItem)
            }
        }

    }

    override fun getItemCount(): Int {
        return foodItems.size
    }

    fun updateFoodItems(filteredItems: ArrayList<FoodItem>?) {
//        foodItems.clear()
        if (filteredItems != null) {
            foodItems = filteredItems
        }
        notifyDataSetChanged()
    }

}
