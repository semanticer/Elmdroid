package com.example.elmdroid.login.presentation

import android.view.View

class LoginStateViewRenderer(val view: LoginView?) : LoginStateRenderer {

    override fun renderEmail(email: String) {
        if (email.isEmpty()) {
            view?.email()?.setText("")
        }
    }

    override fun renderPassword(password: String) {
        if (password.isEmpty()) {
            view?.password()?.setText("")
        }
    }

    override fun renderLoginEnabled(loginEnabled: Boolean) {
        view?.loginButton()?.isEnabled = loginEnabled
    }

    override fun renderLoadingVisible(loadingVisible: Boolean) {
        if (loadingVisible) {
            view?.email()?.isEnabled = false
            view?.password()?.isEnabled = false
            view?.progressBar()?.visibility = View.VISIBLE
        } else {
            view?.email()?.isEnabled = true
            view?.password()?.isEnabled = true
            view?.progressBar()?.visibility = View.GONE
        }
    }

    override fun renderLoggedUsername(loggedUsername: String) {
        view?.loggedUser()?.text = loggedUsername
    }

    override fun renderMsgText(msgText: String) {
        if (msgText.isNotBlank()) {
            view?.showUserMsg(msgText)
        }
    }

    override fun renderLoggedTimer(loggedTimer: Int) {
        view?.timer()?.text = "$loggedTimer"
    }
}