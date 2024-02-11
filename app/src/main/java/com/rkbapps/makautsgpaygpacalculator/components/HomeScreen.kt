package com.rkbapps.makautsgpaygpacalculator.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.rkbapps.makautsgpaygpacalculator.R
import com.rkbapps.makautsgpaygpacalculator.navigation.NavigationRoute


@Composable
fun HomeScreen(navController: NavHostController) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.study))
    Scaffold(
        topBar = {
            HomeScreenTopBar()
        },
    ) {
        Column(modifier = Modifier.padding(it)) {

            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            )

            HomeScreenListItems(
                title = "Yearly Marks % Convert",
                body = "Calculate marks for a specific year"
            ) {
                navController.navigate(route = NavigationRoute.YearlyMarksCalculator.route) {

                }
            }
            HomeScreenListItems(
                title = "Mid Sem % Calculator",
                body = "Calculate percentage for mid sem"
            ) {
                navController.navigate(route = NavigationRoute.MidSemCalculator.route)
            }
            HomeScreenListItems(
                title = "DGPA % Calculate from SGPA",
                body = "Calculate percentage for Degree Grade Point"
            ) {
                navController.navigate(route = NavigationRoute.DgpaCalculator.route)
            }
            HomeScreenListItems(
                title = "SGPA/YGPA to % Calculator",
                body = "Calculate your percentage from SGPA or YGPA"
            ) {

                navController.navigate(route = NavigationRoute.SgpaYgpaPersentageCalculator.route)

            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                Text(
                    text = "Made with ❤️ \n by 'RKB APPS'",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.Center,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp, vertical = 16.dp)
//            ) {
//                HomeScreenRowItems(image = R.drawable.about_us, title = "About us") {
//
//                    navController.navigate(NavigationRoute.AboutUs.route)
//
//                }
//                Spacer(modifier = Modifier.width(5.dp))
//                HomeScreenRowItems(image = R.drawable.github, title = "GitHub") {
//
//
//                }
//                Spacer(modifier = Modifier.width(5.dp))
//                HomeScreenRowItems(image = R.drawable.faq, title = "FAQ") {
//
//                }
//            }
        }
    }


}


@Composable
fun HomeScreenTopBar(
    showBack: Boolean = false,
    onBackClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(vertical = 8.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if (showBack) {
            IconButton(onClick = {
                onBackClick()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
        Text(
            text = "CGPA Calculator - MAKAUT",
            modifier = Modifier
                .weight(1f)
                .padding(5.dp),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenListItems(
    title: String,
    body: String,
    onClick: () -> Unit
) {

    OutlinedCard(
        onClick = { onClick() },
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Text(text = body, style = MaterialTheme.typography.bodyMedium)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenRowItems(
    image: Int,
    contentDescription: String? = null,
    title: String,
    onClick: () -> Unit
) {
    Card(
        onClick = {
            onClick()
        }, modifier = Modifier
            .height(80.dp)
            .width(80.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = contentDescription,
                modifier = Modifier
                    .weight(1f)
                    .height(30.dp)
                    .width(30.dp),
            )
            Text(text = title, style = MaterialTheme.typography.titleSmall)
        }
    }
}