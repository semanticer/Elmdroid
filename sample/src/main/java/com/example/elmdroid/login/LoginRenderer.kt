package com.example.elmdroid.login

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.Observer
import android.view.View


class LoginRenderer(private val view: LoginView) : Observer<LoginState>, LifecycleObserver {
    override fun onChanged(state: LoginState?) {
        state?.apply {
            view.loggedUser().text = loggedUsername
            view.loginButton().isEnabled = loginEnabled
            if (loadingVisible){
                view.email().isEnabled = false
                view.password().isEnabled = false
                view.progressBar().visibility = View.VISIBLE
            } else {
                view.email().isEnabled = true
                view.password().isEnabled = true
                view.progressBar().visibility = View.GONE
            }

        }
    }
}