package com.dhruv.retrofit1

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dhruv.retrofit1.screens.MainScreen
//import com.dhruv.retrofit1.screens.SearchScreen

@Composable
fun MainNav(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route ) {
        composable(Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(Screen.SearchScreen.route){
//            SearchScreen(navController = navController)
        }
    }

}
sealed class Screen(val route : String){
    object MainScreen : Screen("Main")
    object SearchScreen  : Screen("Search")

}