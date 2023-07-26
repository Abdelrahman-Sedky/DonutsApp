@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.donuts.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.donuts.R
import com.example.donuts.ui.screens.details.navigateToDetails
import com.example.donuts.ui.screens.home.viewmodel.HomeUiState
import com.example.donuts.ui.screens.home.viewmodel.HomeViewModel
import com.example.donuts.ui.theme.DonutsCustomColors
import com.example.donuts.ui.theme.DonutsTheme

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    HomeContent(
        state = state.value,
        onItemClick = { id ->
            navController.navigateToDetails(
                id,
                state.value.todayOffer.find { it.id == id }!!.isLiked
            )
        },
        onLikeClick = viewModel::likeDonut
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    state: HomeUiState,
    onItemClick: (Int) -> Unit,
    onLikeClick: (Int) -> Unit
) {
    Scaffold(
        modifier = Modifier,
        containerColor = Color(0xFFF1F1F1),
        bottomBar = { BottomBar() },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(
                    bottom = paddingValues.calculateBottomPadding(),
                    top = 50.dp
                ),
        ) {

            Header(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
            )

            Spacer(modifier = Modifier.height(54.dp))

            Text(
                modifier = Modifier.padding(horizontal = 40.dp),
                text = "Today Offers",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
            )

            Spacer(modifier = Modifier.height(24.dp))

            TodayOffers(
                todayOffers = state.todayOffer,
                onClick = onItemClick,
                contentPadding = PaddingValues(horizontal = 40.dp),
                onLikeClick = onLikeClick
            )

            Spacer(modifier = Modifier.height(46.dp))

            Text(
                modifier = Modifier.padding(horizontal = 40.dp),
                text = "Donuts",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
            )

            Spacer(modifier = Modifier.height(24.dp))

            Donuts(
                donuts = state.donuts,
                onClick = { /*TODO*/ },
                contentPadding = PaddingValues(horizontal = 40.dp)
            )


        }
    }
}

@Composable
fun BottomBar() {
    BottomAppBar(
        containerColor = Color(0xFFF1F1F1),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomBarItem(
                icon = R.drawable.home,
                text = "Home",
                isSelected = true,
                onClick = { /*TODO*/ }
            )
            BottomBarItem(
                icon = R.drawable.heart,
                text = "Cart",
                isSelected = false,
                onClick = { /*TODO*/ }
            )

            BottomBarItem(
                icon = R.drawable.notification,
                text = "Cart",
                isSelected = false,
                onClick = { /*TODO*/ }
            )

            BottomBarItem(
                icon = R.drawable.buy,
                text = "Profile",
                isSelected = false,
                onClick = { /*TODO*/ }
            )
            BottomBarItem(
                icon = R.drawable.profile,
                text = "Profile",
                isSelected = false,
                onClick = { /*TODO*/ }
            )
        }
    }
}

@Composable
fun BottomBarItem(icon: Int, text: String, isSelected: Boolean, onClick: () -> Unit) {
    IconButton(onClick = { /*TODO*/ }) {
        Icon(
            painter = painterResource(id = icon),
            tint = DonutsCustomColors.current.primary,
            contentDescription = "search"
        )
    }
}

@Composable
fun Header(
    modifier: Modifier = Modifier
) {
    val customColors = DonutsCustomColors.current

    Row(
        modifier = modifier,
    ) {
        Column {
            Text(
                text = "Let’s Gonuts!",
                style = MaterialTheme.typography.titleMedium,
                color = customColors.primary,
            )
            Text(
                text = "Order your favourite donuts from here",
                style = MaterialTheme.typography.bodySmall,
                color = customColors.onCard,
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.background(
                color = customColors.background,
                shape = MaterialTheme.shapes.large
            ),
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = customColors.primary
            )
        ) {
            Icon(
                modifier = Modifier.padding(start = 2.dp, top = 2.dp),
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search"
            )
        }
    }
}

@Composable
fun TodayOffers(
    todayOffers: List<HomeUiState.TodayOfferUiState>,
    onClick: (Int) -> Unit,
    onLikeClick: (Int) -> Unit,
    contentPadding: PaddingValues
) {
    LazyRow(
        contentPadding = contentPadding,
        modifier = Modifier.fillMaxWidth(),
    ) {
        items(todayOffers, key = { it.id }) {
            TodayOffersItem(
                todayOffer = it,
                onClick = { onClick(it.id) },
                modifier = Modifier.padding(end = 64.dp),
                color = if (it.id % 2 == 0) Color(0xFFD7E4F6)
                else Color(0xFFFFC7D0),
                onLikeClick = { onLikeClick(it.id) }
            )
        }
    }
}

@Composable
fun TodayOffersItem(
    todayOffer: HomeUiState.TodayOfferUiState,
    onClick: () -> Unit,
    onLikeClick: () -> Unit,
    color: Color,
    modifier: Modifier = Modifier
) {
    val customColors = DonutsCustomColors.current

    Box(
        modifier = modifier
            .size(193.dp, 325.dp)
    ) {

        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(color)
                .clickable { onClick() },
            horizontalAlignment = Alignment.Start,
        ) {
            IconButton(
                onClick = onLikeClick,
                modifier = Modifier
                    .padding(15.dp)
                    .background(
                        color = Color.White,
                        shape = MaterialTheme.shapes.extraLarge
                    )
                    .size(35.dp)
                    .clickable {
                        onClick()
                    },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = customColors.primary
                )
            ) {
                Icon(
                    painter = painterResource(id = if (todayOffer.isLiked) R.drawable.filled_heart else R.drawable.ic_heart),
                    contentDescription = "Search"
                )
            }

            Spacer(modifier = Modifier.weight(11f))

            Text(
                modifier = Modifier.padding(start = 20.dp),
                text = todayOffer.name,
                style = MaterialTheme.typography.titleSmall,
                color = Color.Black,
            )

            Spacer(modifier = Modifier.size(8.dp))

            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = todayOffer.description,
                style = MaterialTheme.typography.labelSmall,
                color = customColors.onCard,
            )

            Spacer(modifier = Modifier.size(8.dp))

            Row(
                modifier = Modifier
                    .padding(end = 15.dp, bottom = 15.dp)
                    .align(
                        Alignment.End
                    ),
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(
                    text = "$${todayOffer.oldPrice}",
                    style = MaterialTheme.typography.labelMedium.copy(
                        textDecoration = TextDecoration.LineThrough
                    ),
                    color = customColors.onCard,
                )

                Spacer(modifier = Modifier.size(4.dp))

                Text(
                    text = "$${todayOffer.price}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 22.sp
                    ),
                    color = Color.Black,
                )
            }
        }

        Image(
            modifier = Modifier
                .size(193.dp)
                .offset(x = 80.dp, y = 20.dp),
            painter = painterResource(id = todayOffer.image),
            contentDescription = "Donut",
        )
    }

}

@Composable
fun Donuts(
    donuts: List<HomeUiState.DonutsUiState>,
    onClick: () -> Unit,
    contentPadding: PaddingValues
) {
    LazyRow(
        contentPadding = contentPadding,
    ) {
        items(donuts, key = { it.id }) {
            DonutsItem(
                donut = it,
                onClick = onClick,
                modifier = Modifier.padding(end = 24.dp, top = 32.dp),
            )
        }
    }
}


@Composable
fun DonutsItem(
    donut: HomeUiState.DonutsUiState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val customColors = DonutsCustomColors.current

    Box(
        modifier = modifier
            .size(height = 111.dp, width = 138.dp)
            .background(
                Color.White,
                shape = RoundedCornerShape(
                    topEnd = 20.dp,
                    topStart = 20.dp,
                    bottomEnd = 10.dp,
                    bottomStart = 10.dp
                )
            )
    ) {
        Box(modifier = Modifier.wrapContentHeight()) {
            Image(
                modifier = Modifier
                    .size(105.dp)
                    .offset(x = 15.dp, y = (-50).dp),
                painter = painterResource(id = donut.image),
                contentDescription = "Donut",
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = donut.name,
                    style = MaterialTheme.typography.bodySmall,
                    color = customColors.onCard,
                )

                Spacer(modifier = Modifier.size(8.dp))

                Text(
                    text = "$${donut.price}",
                    style = MaterialTheme.typography.labelMedium,
                    color = customColors.primary,
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    DonutsTheme {
        HomeScreen(
            navController = rememberNavController(),
        )
    }
}
