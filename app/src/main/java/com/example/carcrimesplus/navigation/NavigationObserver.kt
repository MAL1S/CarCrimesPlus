package com.example.carcrimesplus.navigation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow

val navigationObserver = NavigationObserver()

val coroutineScope = CoroutineScope(Dispatchers.Default)

class NavigationObserver {

    private val _navFlow = MutableSharedFlow<NavigationEvent>()
    val navFlow = _navFlow

    suspend fun sendNavigationEvent(navigationEvent: NavigationEvent) {
        _navFlow.emit(navigationEvent)
    }
}