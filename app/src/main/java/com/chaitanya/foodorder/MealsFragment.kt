package com.chaitanya.foodorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.chaitanya.foodorder.databinding.FragmentLogInBinding
import com.chaitanya.foodorder.databinding.FragmentMealsBinding
import com.chaitanya.foodorder.viewModel.OrderViewModel

class MealsFragment : Fragment() {

    private val sharedViewModel : OrderViewModel by activityViewModels()
    private var binding : FragmentMealsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentMealsBinding.inflate(inflater,container,false)
        binding = fragmentBinding
        // Inflate the layout for this fragment
        return fragmentBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            breakFast.setOnClickListener {
                sharedViewModel.setMeal("Breakfast")
                findNavController().navigate(R.id.action_mealsFragment_to_dayFragment)
            }
            lunch.setOnClickListener {
                sharedViewModel.setMeal("Lunch")
                findNavController().navigate(R.id.action_mealsFragment_to_dayFragment)
            }
            dinner.setOnClickListener {
                sharedViewModel.setMeal("Dinner")
                findNavController().navigate(R.id.action_mealsFragment_to_dayFragment)
            }
        }



    }
}