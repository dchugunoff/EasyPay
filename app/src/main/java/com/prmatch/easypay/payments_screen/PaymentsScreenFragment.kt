package com.prmatch.easypay.payments_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.prmatch.easypay.R
import com.prmatch.easypay.databinding.FragmentPaymentsBinding
import com.prmatch.easypay.utils.DataState
import com.prmatch.easypay.utils.SharedPreferencesHelper

class PaymentsScreenFragment : Fragment() {

    private var _binding: FragmentPaymentsBinding? = null
    private val binding: FragmentPaymentsBinding
        get() = _binding ?: throw RuntimeException("FragmentPaymentsBinding == null")

    private val viewModel: PaymentsViewModel by viewModels()

    private lateinit var adapter: PaymentAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPayments()
        bindAdapter()
        observeLoadingState()
        bindFAB()
    }

    private fun bindFAB() {
        binding.settingFab.setOnClickListener {
            LogoutDialogFragment().show(
                childFragmentManager, "LOGOUT_DIALOG_FRAGMENT"
            )
        }
    }

    private fun observeLoadingState() {
        viewModel.paymentDataState.observe(viewLifecycleOwner) {
            when (it) {
                DataState.Error -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.loading_error_toast),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                DataState.Loading -> {
                    binding.loadingProgressBar.visibility = View.VISIBLE
                }

                DataState.Success -> {
                    binding.loadingProgressBar.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun bindAdapter() {
        adapter = PaymentAdapter()
        binding.payemntsRv.adapter = adapter
        viewModel.paymentsList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun getPayments() {
        val sharedPreferencesHelper = SharedPreferencesHelper(requireContext())
        val token = sharedPreferencesHelper.getToken()
        viewModel.getPayments(token)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}