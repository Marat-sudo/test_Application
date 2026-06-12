package com.example.myapplication_2.utilits

import models.UserCache

enum class AppStates(val state: String) {
    ONLINE("в сети"),
    OFFLINE("был"),
    TYPING("печатает");

    companion object {
        fun updateState(appState: AppStates) {
            UserCache.currentUser?.state = appState.state
        }
    }
}