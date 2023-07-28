package com.chaitanya.foodorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.chaitanya.foodorder.databinding.FragmentLogInBinding



class LogInFragment : Fragment() {

    companion object {
        private const val SIGN_IN_VIEW = "SIGN_IN_VIEW"
        private const val SIGN_UP_VIEW = "SIGN_UP_VIEW"
    }

    private var currentVisibleView: String = SIGN_IN_VIEW

    private var binding : FragmentLogInBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentLogInBinding.inflate(inflater,container,false)
        binding = fragmentBinding
        // Inflate the layout for this fragment
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.rgLogin?.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.rbSignUp){
                showSignUpUi()
            }else{
                showSignInUi()
            }
        }
        binding?.btnLogIn?.setOnClickListener {
            if (currentVisibleView == SIGN_IN_VIEW){
//                findNavController().navigate(R.id.action_logInFragment3_to_mealsFragment)
            }
            else{
                Toast.makeText(requireContext(),"Sign Up",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showSignUpUi(){
        currentVisibleView = SIGN_UP_VIEW
        binding?.apply {
            tilName.visibility = View.VISIBLE
            btnLogIn.text = "Sign Up"
            etName.text?.clear()
            etPassword.text?.clear()
            etUsername.text?.clear()
        }

    }
    private fun showSignInUi(){
        currentVisibleView = SIGN_IN_VIEW
        binding?.apply {
            tilName.visibility = View.GONE
            btnLogIn.text = "Sign In"
            etPassword.text?.clear()
            etUsername.text?.clear()
        }
    }
}