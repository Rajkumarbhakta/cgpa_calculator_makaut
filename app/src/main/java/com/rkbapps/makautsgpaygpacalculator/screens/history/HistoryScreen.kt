package com.rkbapps.makautsgpaygpacalculator.screens.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.rkbapps.makautsgpaygpacalculator.R
import com.rkbapps.makautsgpaygpacalculator.navigation.NavigationRoute
import com.rkbapps.makautsgpaygpacalculator.screens.home.HomeScreenItem
import com.rkbapps.makautsgpaygpacalculator.screens.home.HomeScreenListItems
import com.rkbapps.makautsgpaygpacalculator.screens.home.HomeScreenTopBar

@Composable
fun HistoryScreen(navController: NavHostController) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.history))
    val historyItems = remember {
        mutableStateListOf(
            HomeScreenItem(
                title = "Yearly Marks % Convert",
                subTitle = "Get All History of Yearly Marks % Convert",
                onClick = {navController.navigate(route = NavigationRoute.YearlyMarksHistory)}
            ),
            HomeScreenItem(
                title = "Mid Sem % Calculator",
                subTitle = "Get All History of Mid Sem % Calculator",
                onClick = {navController.navigate(route = NavigationRoute.MidSemHistory)}
            ),
            HomeScreenItem(
                title = "DGPA % Calculate from SGPA",
                subTitle = "Get All History of DGPA % Calculate from SGPA",
                onClick = {navController.navigate(route = NavigationRoute.DgpaHistory)}
            ),
            HomeScreenItem(
                title = "SGPA/YGPA to % Calculator",
                subTitle = "Get All History of SGPA/YGPA to % Calculator",
                onClick = {navController.navigate(route = NavigationRoute.SgpaYgpaPercentageHistory)}
            ),

            )

    }

    Scaffold(
        topBar = {
            HomeScreenTopBar(showBack = true){
                navController.navigateUp()
            }
        }
    ) {innerPadding ->
        Column (modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){

            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier
                    .weight(0.5f)
                    .padding(horizontal = 16.dp)
            )

            LazyColumn(modifier = Modifier.weight(0.5f)) {
                items(historyItems){
                    HomeScreenListItems(item = it)
                }
            }


        }
    }

}