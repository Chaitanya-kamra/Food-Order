package com.chaitanya.foodorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chaitanya.foodorder.adapters.CartAdapter
import com.chaitanya.foodorder.viewModel.OrderViewModel

class CartFragment : Fragment() {

    private val orderViewModel: OrderViewModel by activityViewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var btCheckout: Button
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvPrice :TextView = view.findViewById(R.id.tvPrice)
        btCheckout = view.findViewById(R.id.btCheckout)
        recyclerView = view.findViewById(R.id.rvShowFood)
        btCheckout.setOnClickListener {
            findNavController().navigate(R.id.action_cartFragment_to_checkoutFragment)
        }


        cartAdapter = CartAdapter({foodItem ->
            orderViewModel.addToCart(foodItem)
        },{foodItem->
            orderViewModel.removeItemFromCart(foodItem)
        })
        recyclerView.adapter = cartAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        orderViewModel.price.observe(viewLifecycleOwner,Observer{ price ->
            tvPrice.text = "Total price $price"

        })

        orderViewModel.cartItemList.observe(viewLifecycleOwner, Observer { cartItems ->
            // Update the RecyclerView adapter with the filteredItems
            cartAdapter.updateFoodItems(cartItems)
        })
    }

}