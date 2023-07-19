package com.example.donuts.ui.screens.getstarted

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.donuts.R
import com.example.donuts.ui.screens.home.navigateToHome
import com.example.donuts.ui.theme.DonutsCustomColors
import com.example.donuts.ui.theme.DonutsTheme

@Composable
fun GetStartedScreen(
    navController: NavController
) {
    GetStartedContent(
        onButtonClick = {
            navController.navigateToHome()
        }
    )

}

@Composable
fun GetStartedContent(onButtonClick: () -> Unit) {

    val customColors = DonutsCustomColors.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = customColors.background)
            .padding(start = 40.dp, end = 40.dp, bottom = 40.dp, top = 110.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.get_started),
            contentDescription = "Donuts",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .scale(2.4f)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Gonuts\n" +
                    "with\n" +
                    "Donuts",
            style = MaterialTheme.typography.titleLarge,
            color = customColors.primary,
        )

        Spacer(modifier = Modifier.height(19.dp))

        Text(
            text = "Gonuts with Donuts is a Sri Lanka dedicated food outlets for specialize manufacturing of Donuts in Colombo, Sri Lanka.",
            style = MaterialTheme.typography.bodyMedium,
            color = customColors.textColor,
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onButtonClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(67.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),

            ) {
            Text(
                text = "Get Started",
                style = MaterialTheme.typography.bodyLarge,
            )
        }

    }

}

@Preview
@Composable
fun GetStartedScreenPreview() {
    DonutsTheme {
        GetStartedScreen(navController = rememberNavController())
    }
}
