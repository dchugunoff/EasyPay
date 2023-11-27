package com.prmatch.easypay.payments_screen

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.prmatch.easypay.R
import com.prmatch.easypay.login_screen.LoginScreenFragment
import com.prmatch.easypay.utils.SharedPreferencesHelper

class LogoutDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val sharedPreferencesHelper = SharedPreferencesHelper(requireContext())
        return AlertDialog.Builder(requireContext())
            .setTitle("Logout")
            .setMessage("Are you sure you want to log out?")
            .setPositiveButton("Yes") { _, _ ->
                sharedPreferencesHelper.removeToken()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_container, LoginScreenFragment())
                    .commit()
            }
            .setNegativeButton("No", null)
            .create()
    }
}