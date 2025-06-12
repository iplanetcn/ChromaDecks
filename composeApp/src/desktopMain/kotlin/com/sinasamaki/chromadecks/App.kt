package com.sinasamaki.chromadecks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sinasamaki.chromadecks._001_MeshGradients.MeshGradientPresentation
import com.sinasamaki.chromadecks._002_PathAnimations.PathAnimationPresentation
import org.jetbrains.compose.ui.tooling.preview.Preview
import com.sinasamaki.chromadecks.ui.theme.ChromaTheme
import kotlinx.serialization.Serializable

sealed interface AppRoute {
    @Serializable
    object Entry : AppRoute

    @Serializable
    object MeshGradient : AppRoute

    @Serializable
    object PathAnimation : AppRoute
}

@Composable
@Preview
fun App(
    onNavHostReady: suspend (NavController) -> Unit = {}
) {
    val navController = rememberNavController()

    LaunchedEffect(navController) {
        onNavHostReady(navController)
    }

    ChromaTheme {
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = AppRoute.Entry
        ) {
            composable<AppRoute.Entry> {
                EntryScreen(navController)
            }

            composable<AppRoute.MeshGradient> {
                MeshGradientPresentation()
            }

            composable<AppRoute.PathAnimation> {
                PathAnimationPresentation()
            }
        }
    }
}

@Preview
@Composable
fun EntryScreen(navController: NavController) {
    Surface(
        Modifier
            .aspectRatio(16 / 9f)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Spacer(modifier = Modifier.size(24.dp))
            Button(
                onClick = {
                    navController.navigate(AppRoute.MeshGradient)
                }
            ) {
                Text("Mesh Gradient Presentation")
            }

            Button(
                onClick = {
                    navController.navigate(AppRoute.PathAnimation)
                }
            ) {
                Text("Path Animation Presentation")
            }
        }
    }
}