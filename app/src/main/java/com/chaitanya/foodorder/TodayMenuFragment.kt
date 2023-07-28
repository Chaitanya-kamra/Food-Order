package com.chaitanya.foodorder

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chaitanya.foodorder.adapters.FoodAdapter
import com.chaitanya.foodorder.viewModel.OrderViewModel
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.Calendar
import java.util.Locale

class TodayMenuFragment : Fragment() {
    private val orderViewModel: OrderViewModel by activityViewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var foodAdapter: FoodAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_today_menu, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentTime = LocalTime.now()
        // meal based on the current time
        val currentMeal = determineMealFromTime(currentTime)

        val dayOfWeekText = getDayOfWeekText()
        val mealText = currentMeal

        orderViewModel.setMealAndDay(mealText, dayOfWeekText)
        recyclerView = view.findViewById(R.id.rvToday)
        foodAdapter = FoodAdapter(){foodItem ->
            orderViewModel.addToCart(foodItem)
        }

        recyclerView.adapter = foodAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        orderViewModel.filteredFoodItems.observe(viewLifecycleOwner, Observer { filteredItems ->
            // Update the RecyclerView adapter with the filteredItems
            foodAdapter.updateFoodItems(filteredItems)
        })
    }
    private fun getDayOfWeekText(): String {
        return SimpleDateFormat("EEEE", Locale.getDefault()).format(Calendar.getInstance().time)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun determineMealFromTime(time: LocalTime): String {
        val breakfastStart = LocalTime.of(6, 0)
        val breakfastEnd = LocalTime.of(12, 0)

        val lunchStart = LocalTime.of(12, 0)
        val lunchEnd = LocalTime.of(18, 0)

        val dinnerStart = LocalTime.of(18, 0)
        val dinnerEnd = LocalTime.of(5, 0)

        return when {
            time in breakfastStart..breakfastEnd -> "Breakfast"
            time in lunchStart..lunchEnd -> "Lunch"
            time in dinnerStart..dinnerEnd -> "Dinner"
            else -> "Unknown"
        }
    }
}