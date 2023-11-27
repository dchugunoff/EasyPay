package com.prmatch.easypay

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prmatch.easypay.login_screen.LoginScreenFragment
import com.prmatch.easypay.payments_screen.PaymentsScreenFragment
import com.prmatch.easypay.utils.SharedPreferencesHelper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferencesHelper = SharedPreferencesHelper(this)
        if (sharedPreferencesHelper.checkToken()) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_container, PaymentsScreenFragment())
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_container, LoginScreenFragment())
                .commit()
        }
    }


}