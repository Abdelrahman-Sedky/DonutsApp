package com.example.donuts.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.donuts.R
import com.example.donuts.ui.screens.details.viewmodel.DetailsUiState
import com.example.donuts.ui.screens.details.viewmodel.DetailsViewModel
import com.example.donuts.ui.theme.DonutsCustomColors
import com.example.donuts.ui.theme.DonutsTheme


@Composable
fun DetailsScreen(
    navController: NavController,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value

    DetailsContent(
        state = state,
        onBackClick = { navController.popBackStack() },
        onIncrementClick = viewModel::incrementQuantity,
        onDecrementClick = viewModel::decrementQuantity,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsContent(
    state: DetailsUiState,
    onBackClick: () -> Unit,
    onIncrementClick: () -> Unit,
    onDecrementClick: () -> Unit,
) {
    val colors = DonutsCustomColors.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { /*TODO*/ },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .padding(15.dp)
                            .size(35.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        colors = IconButtonDefaults.iconButtonColors(
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.backspace),
                            contentDescription = "back",
                            tint = colors.primary,
                        )
                    }

                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Transparent,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .systemBarsPadding(),
            )
        },

        ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFC7D0))
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                painter = painterResource(id = state.image),
                contentDescription = "image holder",
            )

            Box {
                Card(
                    modifier = Modifier
                        .background(Color.Transparent)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(
                        topStart = 32.dp,
                        topEnd = 32.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 40.dp, vertical = 35.dp),
                    ) {

                        Text(
                            text = state.name,
                            modifier = Modifier
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.titleMedium,
                            color = colors.textColor
                        )

                        Spacer(modifier = Modifier.height(33.dp))

                        Text(
                            text = "About Gonut",
                            modifier = Modifier
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = colors.onCard.copy(alpha = 0.8f)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = state.description,
                            modifier = Modifier
                                .fillMaxWidth(),
                            fontSize = 14.sp,
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                            color = colors.onCard,
                            textAlign = TextAlign.Start
                        )

                        Spacer(modifier = Modifier.height(26.dp))

                        Text(
                            text = "Quantity",
                            modifier = Modifier
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = colors.onCard.copy(alpha = 0.8f)
                        )

                        Spacer(modifier = Modifier.height(19.dp))

                        Quantity(
                            modifier = Modifier,
                            quantity = state.quantity,
                            onIncrementClick = onIncrementClick,
                            onDecrementClick = onDecrementClick,
                        )

                        Spacer(modifier = Modifier.height(45.dp))

                        AddToCart(
                            price = state.price * state.quantity,
                            modifier = Modifier
                        )
                    }
                }

                Button(
                    modifier = Modifier
                        .size(48.dp)
                        .offset(x = (-32).dp, y = -(24).dp)
                        .align(Alignment.TopEnd),
                    shape = RoundedCornerShape(16.dp),
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = colors.primary
                    ),
                    contentPadding = PaddingValues(0.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 5.dp,
                    ),
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(
                            id = if (state.isLiked) R.drawable.filled_heart else R.drawable.ic_heart
                        ),
                        contentDescription = "Like"
                    )
                }
            }
        }
    }
}

@Composable
fun Quantity(
    quantity: Int,
    onIncrementClick: () -> Unit,
    onDecrementClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
    ) {

        Button(
            modifier = Modifier.size(48.dp),
            shape = RoundedCornerShape(16.dp),
            onClick = {
                if (quantity > 1) onDecrementClick()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            contentPadding = PaddingValues(0.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 5.dp,
            ),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.minus),
                contentDescription = "plus",
            )
        }


        Button(
            modifier = Modifier.size(48.dp),
            shape = RoundedCornerShape(16.dp),
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 5.dp,
            ),
            contentPadding = PaddingValues(0.dp),
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = quantity.toString(),
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 22.sp),
            )
        }

        Button(
            modifier = Modifier.size(48.dp),
            shape = RoundedCornerShape(16.dp),
            onClick = {
                if (quantity < 10) onIncrementClick()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 5.dp,
            ),
            contentPadding = PaddingValues(0.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.plus),
                contentDescription = "plus",
                tint = Color.White,
            )
        }

    }
}

@Composable
fun AddToCart(
    price: Int,
    modifier: Modifier = Modifier
) {
    val colors = DonutsCustomColors.current

    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(26.dp),
    ) {

        Text(
            text = "£${price}",
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black,
        )

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .weight(1f)
                .height(67.dp),
            shape = RoundedCornerShape(32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.primary,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Add to cart",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
            )
        }
    }
}

@Preview
@Composable
fun ItemScreenPreview() {
    DonutsTheme {
        DetailsContent(
            state = DetailsUiState(
                image = R.drawable.donutup1,
                name = "Chocolate Donut",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod, diam sit amet dictum ultrices, nisl velit ultricies nunc, quis aliquam nunc nisl eu eros. Sed euismod, diam sit amet dictum ultrices, nisl velit ultricies nunc, quis aliquam nunc nisl eu eros.",
                price = 5,
                quantity = 1,
            ),
            onBackClick = { /*TODO*/ },
            onIncrementClick = { /*TODO*/ },
            onDecrementClick = { /*TODO*/ },
        )
    }
}

