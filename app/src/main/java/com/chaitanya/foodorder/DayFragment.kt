package com.chaitanya.foodorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.chaitanya.foodorder.databinding.FragmentDayBinding
import com.chaitanya.foodorder.databinding.FragmentMealsBinding
import com.chaitanya.foodorder.viewModel.OrderViewModel


class DayFragment : Fragment(){

    private val sharedViewModel : OrderViewModel by activityViewModels()
    private var binding : FragmentDayBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentDayBinding.inflate(inflater,container,false)
        binding = fragmentBinding
        // Inflate the layout for this fragment
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            monday.setOnClickListener {
                sharedViewModel.setDay("Monday")
                findNavController().navigate(R.id.action_dayFragment_to_foodFragment)
            }
            tuesday.setOnClickListener {
                sharedViewModel.setDay("Tuesday")
                findNavController().navigate(R.id.action_dayFragment_to_foodFragment)
            }
            wednesday.setOnClickListener {
                sharedViewModel.setDay("Wednesday")
                findNavController().navigate(R.id.action_dayFragment_to_foodFragment)
            }
            thursday.setOnClickListener {
                sharedViewModel.setDay("Thursday")
                findNavController().navigate(R.id.action_dayFragment_to_foodFragment)
            }
            friday.setOnClickListener {
                sharedViewModel.setDay("Friday")
                findNavController().navigate(R.id.action_dayFragment_to_foodFragment)
            }
            saturday.setOnClickListener {
                sharedViewModel.setDay("Saturday")
                findNavController().navigate(R.id.action_dayFragment_to_foodFragment)
            }
            sunday.setOnClickListener {
                sharedViewModel.setDay("Sunday")
                findNavController().navigate(R.id.action_dayFragment_to_foodFragment)
            }
        }
    }


}