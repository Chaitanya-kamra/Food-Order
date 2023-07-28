package com.chaitanya.foodorder.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chaitanya.foodorder.R
import com.chaitanya.foodorder.viewModel.CartItem
import com.chaitanya.foodorder.viewModel.FoodItem

class CartAdapter(private val onAdd: (FoodItem) -> Unit,private val onRemove: (FoodItem) -> Unit) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    private var cartItems: ArrayList<CartItem> = ArrayList()
    class CartViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val tvDay: TextView = itemView.findViewById(R.id.tvDay)
        val tvMeal: TextView = itemView.findViewById(R.id.tvMeal)
        val removeButton :Button = itemView.findViewById(R.id.removeCart)
        val addButton :Button = itemView.findViewById(R.id.addCart)
        val quantity : TextView = itemView.findViewById(R.id.tvQuantity)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItems[position]
        holder.apply {
            tvName.text = cartItem.foodItem.name
            tvDescription.text = cartItem.foodItem.description
            tvPrice.text = "Price: $${cartItem.foodItem.price}"
            tvDay.text = "Day: ${cartItem.foodItem.day}"
            tvMeal.text = "Meal: ${cartItem.foodItem.meal}"
            quantity.text = cartItem.quantity.toString()
            addButton.setOnClickListener { onAdd(cartItem.foodItem) }
            removeButton.setOnClickListener {
                onRemove(cartItem.foodItem)
            }
        }

    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    fun updateFoodItems(newCartItems: ArrayList<CartItem>?) {

        if (newCartItems != null) {
            cartItems = newCartItems
        }
        notifyDataSetChanged()
    }

}