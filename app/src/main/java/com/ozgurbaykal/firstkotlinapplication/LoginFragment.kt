package com.ozgurbaykal.firstkotlinapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.ozgurbaykal.firstkotlinapplication.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {
    private val TAG = "_LoginFragment"

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var loginMailInput : EditText
    private lateinit var loginPasswordInput : EditText
    private lateinit var loginButton : Button
    private lateinit var registerButton : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        loginMailInput = binding.emailFieldInLoginPage
        loginPasswordInput = binding.passwordFieldInLoginPage
        loginButton = binding.loginButton
        registerButton = binding.registerButton

        registerButton.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.login_fragment_view, RegisterFragment())
                addToBackStack(null)
            }
        }


        loginButton.setOnClickListener {
            Log.i(TAG, "Login Button Clicked!")

            if(emptyInputControl()){
                val intent = Intent (activity, TutorialActivity::class.java)
                startActivity(intent)
                activity?.finish()

            }


        }


        return view
    }

    private fun emptyInputControl() : Boolean{
        var isAllInputsOk : Boolean = true

        if (loginMailInput.text.isEmpty()) {

            Log.e(TAG, "Login Page Email EMPTY!")
            loginMailInput.setBackgroundResource(R.drawable.custom_edit_text_on_error)
            val shake: Animation =
                AnimationUtils.loadAnimation(requireContext(), R.anim.shake_animation)
            loginMailInput.startAnimation(shake)
            isAllInputsOk = false
        } else {
            loginMailInput.setBackgroundResource(R.drawable.custom_edit_text)
        }

        if (loginPasswordInput.text.isEmpty()) {

            Log.e(TAG, "Login Page Password EMPTY!")
            loginPasswordInput.setBackgroundResource(R.drawable.custom_edit_text_on_error)
            val shake: Animation =
                AnimationUtils.loadAnimation(requireContext(), R.anim.shake_animation)
            loginPasswordInput.startAnimation(shake)
            isAllInputsOk = false
        } else {
            loginPasswordInput.setBackgroundResource(R.drawable.custom_edit_text)
        }

        return isAllInputsOk
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}