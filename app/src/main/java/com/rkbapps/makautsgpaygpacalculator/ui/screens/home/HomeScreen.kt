package com.rkbapps.makautsgpaygpacalculator.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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
    val homeItems = remember {
        mutableStateListOf(
            HomeScreenItem(
                title = "Yearly Marks % Convert",
                subTitle = "Calculate marks for a specific year",
                onClick = { navController.navigate(route = NavigationRoute.YearlyMarksCalculator) }
            ),
            HomeScreenItem(
                title = "Mid Sem % Calculator",
                subTitle = "Calculate marks for a specific year",
                onClick = { navController.navigate(route = NavigationRoute.MidSemCalculator) }
            ),
            HomeScreenItem(
                title = "DGPA % Calculate from SGPA",
                subTitle = "Calculate marks for a specific year",
                onClick = { navController.navigate(route = NavigationRoute.DgpaCalculator) }
            ),
            HomeScreenItem(
                title = "SGPA/YGPA to % Calculator",
                subTitle = "Calculate marks for a specific year",
                onClick = { navController.navigate(route = NavigationRoute.SgpaYgpaPercentageCalculator) }
            ),
            HomeScreenItem(
                title = "History",
                subTitle = "Check your calculation history.",
                onClick = { navController.navigate(route = NavigationRoute.History) }
            ),
        )

    }
    Scaffold(
        topBar = {
            AppTopBar()
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {

            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(homeItems) {
                    HomeScreenListItems(item = it)
                }
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                Text(
                    text = "Made with ❤️\nby 'RKB APPS'",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String = "CGPA Calculator - MAKAUT",
    showBack: Boolean = false,
    onBackClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(title, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        },
        navigationIcon = {
            if (showBack) {
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}


@Composable
fun HomeScreenListItems(
    item: HomeScreenItem
) {
    OutlinedCard(
        onClick = { item.onClick.invoke() },
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(text = item.title, style = MaterialTheme.typography.titleMedium)
            Text(text = item.subTitle, style = MaterialTheme.typography.bodyMedium)
        }
    }

}


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

@Immutable
@Stable
data class HomeScreenItem(
    val title: String,
    val subTitle: String,
    val onClick: () -> Unit
)