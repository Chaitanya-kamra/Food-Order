package com.chaitanya.foodorder.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.math.BigDecimal


class OrderViewModel : ViewModel() {
    private val foodItems = MutableLiveData<ArrayList<FoodItem>>()

    private val _meal = MutableLiveData<String>()
    val meal: LiveData<String> = _meal

    private val _day = MutableLiveData<String>()
    val day: LiveData<String> = _meal

    private val _price = MutableLiveData<BigDecimal>()
    val price: LiveData<BigDecimal> = _price

    private val _filteredFoodItems = MutableLiveData<ArrayList<FoodItem>?>()
    val filteredFoodItems: MutableLiveData<ArrayList<FoodItem>?> = _filteredFoodItems

    private val _cartItemList = MutableLiveData<ArrayList<CartItem>>()
    val cartItemList: LiveData<ArrayList<CartItem>> get() = _cartItemList

    init {
        foodItems.value = ConstantObject.getFoodItems()
        _cartItemList.value = arrayListOf()
        _price.value = BigDecimal.ZERO
    }


    fun setMeal(meal: String) {
        _meal.value = meal
    }

    fun setDay(day: String) {
        _day.value = day
    }

    fun setMealAndDay(meal: String, day: String) {
        _meal.value = meal
        _day.value = day

        // Update the filtered food items
        filterFoodItems()
    }

    private fun filterFoodItems() {
        val selectedMeal = _meal.value
        val selectedDay = _day.value

        if (selectedMeal != null && selectedDay != null) {
            val filteredList: ArrayList<FoodItem> = ArrayList()
            for (item in foodItems.value!!) {
                if (item.meal == selectedMeal && item.day == selectedDay) {
                    filteredList.add(item)
                }
            }

            _filteredFoodItems.value = filteredList
        } else {
            _filteredFoodItems.value = foodItems.value
        }
    }

    fun getFilteredFoodItems() {
        filterFoodItems()
    }

    fun addToCart(foodItem: FoodItem) {
        val currentCart = _cartItemList.value ?: ArrayList()
        val existingCartItem = currentCart.find { it.foodItem == foodItem }
        if (existingCartItem != null) {
            existingCartItem.quantity++

        } else {
            currentCart.add(CartItem(1, foodItem))
        }
        _cartItemList.value = currentCart
        _price.value = _price.value?.add(BigDecimal.valueOf(foodItem.price))
    }
    fun removeItemFromCart(foodItem: FoodItem) {
        val currentCart = _cartItemList.value ?: ArrayList()
        val existingCartItem = currentCart.find { it.foodItem == foodItem }

        if (existingCartItem != null) {
            if (existingCartItem.quantity > 1) {

                existingCartItem.quantity--
            } else {
                currentCart.remove(existingCartItem)
            }
            _price.value = _price.value?.minus(BigDecimal.valueOf(foodItem.price))
            _cartItemList.value = currentCart

        }
    }

    fun getFoodItems(): MutableLiveData<ArrayList<FoodItem>> {
        return foodItems
    }

}