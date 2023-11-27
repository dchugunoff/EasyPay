package com.prmatch.easypay.login_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.prmatch.easypay.Application
import com.prmatch.easypay.R
import com.prmatch.easypay.databinding.FragmentLoginBinding
import com.prmatch.easypay.utils.DataState

class LoginScreenFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding ?: throw RuntimeException("FragmentLoginBinding == null")

    private val viewModel: LoginScreenViewModel by viewModels {
        LoginScreenViewModelFactory(requireActivity().application as Application)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkDataState()
        binding.loginButton.setOnClickListener {
            login()
        }
    }

    private fun checkDataState() {
        viewModel.loginState.observe(viewLifecycleOwner) {
            when(it) {
                DataState.Error -> {
                    binding.dataStatusBar.visibility = View.INVISIBLE
                }
                DataState.Loading -> {
                    binding.dataStatusBar.visibility = View.VISIBLE
                }
                DataState.Success -> {
                    binding.dataStatusBar.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun login() {
        val login = binding.loginEditText.text
        val password = binding.passwordEditText.text
        if (checkInputFields()) {
            viewModel.login(login.toString(), password.toString())
        }
    }

    private fun checkInputFields(): Boolean {
        var isValid = true

        with(binding.loginEditText) {
            if (text.isNullOrBlank()) {
                error = context.getString(R.string.error_empty_text)
                isValid = false
            }
            if (text.toString().contains(' ')) {
                error = context.getString(R.string.error_contains_spacers)
                isValid = false
            }
        }

        with(binding.passwordEditText) {
            if (text.isNullOrBlank()) {
                error = context.getString(R.string.error_empty_text)
                isValid = false
            }
            if (text.toString().contains(' ')) {
                error = context.getString(R.string.error_contains_spacers)
                isValid = false
            }
        }

        return isValid
    }


}