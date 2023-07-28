package com.chaitanya.foodorder

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chaitanya.foodorder.adapters.FoodAdapter
import com.chaitanya.foodorder.viewModel.OrderViewModel

class FoodFragment : Fragment() {
    private val orderViewModel: OrderViewModel by activityViewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var foodAdapter: FoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rvShowFood)
        foodAdapter = FoodAdapter(){foodItem ->
            orderViewModel.addToCart(foodItem)
        }

        recyclerView.adapter = foodAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        orderViewModel.getFilteredFoodItems()
        orderViewModel.filteredFoodItems.observe(viewLifecycleOwner, Observer { filteredItems ->
            // Update the RecyclerView adapter with the filteredItems
            foodAdapter.updateFoodItems(filteredItems)
        })
    }

}