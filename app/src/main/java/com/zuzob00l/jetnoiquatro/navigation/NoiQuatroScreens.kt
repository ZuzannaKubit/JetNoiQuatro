package com.zuzob00l.jetnoiquatro.navigation

import java.lang.IllegalArgumentException

enum class NoiQuatroScreens {
    StartScreen,
    LoginScreen,
    HomeScreen,
    ProfileScreen,
    PaymentScreen,
    ProductDetailScreen,
    ShoppingBagScreen,
    MapScreen,
    OrderHistoryScreen;

    companion object {
        fun fromRoute(route: String?): NoiQuatroScreens
        = when(route?.substringBefore("/")){
            StartScreen.name -> StartScreen
            LoginScreen.name -> LoginScreen
            HomeScreen.name -> HomeScreen
            ProfileScreen.name -> ProfileScreen
            PaymentScreen.name -> PaymentScreen
            ProductDetailScreen.name -> ProductDetailScreen
            ShoppingBagScreen.name -> ShoppingBagScreen
            MapScreen.name -> MapScreen
            OrderHistoryScreen.name -> OrderHistoryScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}