package com.example.questapi_089.uicontroller

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.questapi_089.uicontroller.route.DestinasiDetail
import com.example.questapi_089.uicontroller.route.DestinasiEdit
import com.example.questapi_089.uicontroller.route.DestinasiEntry
import com.example.questapi_089.uicontroller.route.DestinasiHome
import com.example.questapi_089.view.DetailSiswaScreen
import com.example.questapi_089.view.EditSiswaScreen
import com.example.questapi_089.view.EntrySiswaScreen
import com.example.questapi_089.view.HomeScreen

@Composable
fun DataSiswaApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    HostNavigasi(navController = navController, modifier = modifier)
}

@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {

        // 1. RUTE HOME
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                navigateToItemUpdate = { idSiswa ->
                    navController.navigate("${DestinasiDetail.route}/$idSiswa")
                }
            )
        }

        // 2. RUTE ENTRY
        composable(DestinasiEntry.route) {
            EntrySiswaScreen(navigateBack = { navController.popBackStack() })
        }

        // 3. RUTE DETAIL (MEMERLUKAN ID SISWA)
        composable(
            route = DestinasiDetail.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetail.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            DetailSiswaScreen(
                navigateBack = { navController.popBackStack() },
                // PERBAIKAN: Menggunakan nama parameter 'navigateToEditItem'
                navigateToEditItem = { idSiswa ->
                    navController.navigate("${DestinasiEdit.route}/$idSiswa")
                }
            )
        }

        // 4. RUTE EDIT (MEMERLUKAN ID SISWA)
        composable(
            route = DestinasiEdit.routeWithArgs,
            arguments = listOf(navArgument(DestinasiEdit.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            EditSiswaScreen(
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}