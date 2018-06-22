package com.example.elmdroid.login.presentation

interface LoginStateRenderer {
    fun renderEmail(email: String)
    fun renderPassword(password: String)
    fun renderLoginEnabled(loginEnabled: Boolean)
    fun renderLoadingVisible(loadingVisible: Boolean)
    fun renderLoggedUsername(loggedUsername: String)
    fun renderMsgText(msgText: String)
    fun renderLoggedTimer(loggedTimer: Int)
}