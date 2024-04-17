/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.compose.rally

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.compose.rally.ui.accounts.AccountsScreen
import com.example.compose.rally.ui.accounts.SingleAccountScreen
import com.example.compose.rally.ui.bills.BillsScreen
import com.example.compose.rally.ui.components.RallyTabRow
import com.example.compose.rally.ui.overview.OverviewScreen
import com.example.compose.rally.ui.theme.RallyTheme

/**
 * This Activity recreates part of the Rally Material Study from
 * https://material.io/design/material-studies/rally.html
 */
class RallyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RallyApp()
        }
    }
}

@Composable
fun RallyApp() {
    RallyTheme {
        val navController = rememberNavController()
        val currentBackstack by navController.currentBackStackEntryAsState()
        val currentDistination = currentBackstack?.destination
        var currentScreen = rallyTabRowScreens.find { it.route == currentDistination?.route } ?: Overview
        Scaffold(
            topBar = {
                RallyTabRow(
                    allScreens = rallyTabRowScreens,
                    onTabSelected = { newScreen -> navController.navigateSingleTop(route = newScreen.route) },
                    currentScreen = currentScreen
                )
            }
        ) { innerPadding ->
            RallyNavHost(navController = navController,
                modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun RallyNavHost(modifier: Modifier = Modifier,
                 navController : NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Overview.route,
        modifier = modifier
    ){
        composable(route = SingleAccount.routeWithArg,
            arguments = SingleAccount.arguments,
            deepLinks = SingleAccount.deepLinks
        ){navBackStackEntery ->
            val accountType = navBackStackEntery.arguments?.getString(SingleAccount.accountTypeArg)
            SingleAccountScreen(accountType)
        }
        composable(route = Overview.route){
            OverviewScreen(
                onClickSeeAllAccounts = {navController.navigateSingleTop(Accounts.route)},
                onClickSeeAllBills = {navController.navigateSingleTop(Bills.route)},
                onAccountClick = {accountType ->
                    navController.navigateToSingleAccount(accountType)
                }
            )
        }
        composable(route = Accounts.route){
            AccountsScreen(
                onAccountClick = {accountType ->
                    navController.navigateToSingleAccount(accountType)
                }
            )
        }
        composable(route = Bills.route){
            BillsScreen()
        }
    }
}
fun NavHostController.navigateSingleTop(route : String) {
    this.navigate(route) {
        popUpTo(this@navigateSingleTop.graph.findStartDestination().id){ saveState = true}
        launchSingleTop = true
        restoreState = true
    }

}
private fun NavHostController.navigateToSingleAccount(accountType: String) {
    this.navigateSingleTop("${SingleAccount.route}/$accountType")
}
