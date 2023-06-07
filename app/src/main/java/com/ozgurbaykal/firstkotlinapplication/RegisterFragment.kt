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
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.checkbox.MaterialCheckBox
import com.ozgurbaykal.firstkotlinapplication.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment(R.layout.fragment_register) {
    private val TAG = "_RegisterFragment"

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var loginButton: Button
    private lateinit var registerButton: Button
    private lateinit var nameInput: EditText
    private lateinit var surnameInput: EditText
    private lateinit var mailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var passwordAgainInput: EditText
    private lateinit var privacyPolicyCheckbox: MaterialCheckBox

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root

        loginButton = binding.loginButton
        nameInput = binding.nameFieldInRegisterPage
        surnameInput = binding.surnameFieldInRegisterPage
        mailInput = binding.emailFieldInRegisterPage
        passwordInput = binding.passwordFieldInRegisterPage
        passwordAgainInput = binding.passwordAgainFieldInRegisterPage
        registerButton = binding.registerButton
        privacyPolicyCheckbox = binding.privacyPolicyCheckbox

        val arrayList = ArrayList<EditText>()
        arrayList.add(nameInput)
        arrayList.add(surnameInput)
        arrayList.add(mailInput)
        arrayList.add(passwordInput)
        arrayList.add(passwordAgainInput)

        registerButton.setOnClickListener {
            Log.i(TAG, "registerButton Clicked")
            if(emptyInputControl(arrayList)){
                Log.i(TAG, "All Inputs OK")

                val intent = Intent (activity, TutorialActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }else{
                Log.i(TAG, "Some Inputs Empty")
            }

        }

        loginButton.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.login_fragment_view, LoginFragment())
                addToBackStack(null)
            }
        }

        return view
    }

    private fun emptyInputControl(editTextArray : ArrayList<EditText>) : Boolean{

        var isAllInputsOk : Boolean = true
        for(editText in editTextArray){
            if(editText.text.isEmpty()){
                val shake: Animation =
                    AnimationUtils.loadAnimation(requireContext(), R.anim.shake_animation)
                editText.startAnimation(shake)
                editText.setBackgroundResource(R.drawable.custom_edit_text_on_error)
                isAllInputsOk = false
            }else{
                editText.setBackgroundResource(R.drawable.custom_edit_text)
            }
        }

        if(!isAllInputsOk)
            return false

        if(!privacyPolicyCheckbox.isChecked){
            val shakeCheckBox: Animation =
                AnimationUtils.loadAnimation(requireContext(), R.anim.shake_animation)
            privacyPolicyCheckbox.startAnimation(shakeCheckBox)
            isAllInputsOk = false

            Toast.makeText(requireContext(), "Please read and agree privacy policy!", Toast.LENGTH_LONG).show()
        }

        return isAllInputsOk
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}