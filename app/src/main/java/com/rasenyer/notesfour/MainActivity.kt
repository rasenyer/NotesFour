package com.rasenyer.notesfour

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.rasenyer.notesfour.entity.Note
import com.rasenyer.notesfour.screens.AddScreen
import com.rasenyer.notesfour.screens.DetailScreen
import com.rasenyer.notesfour.screens.EditScreen
import com.rasenyer.notesfour.screens.HomeScreen
import com.rasenyer.notesfour.ui.theme.NotesFourTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NotesFourTheme {
                Surface(color = MaterialTheme.colors.background) {
                    NavigateScreen()
                }
            }
        }

    }

}

@Composable
fun NavigateScreen() {

    val navController = rememberNavController()

    NavHost(

        navController = navController,
        startDestination = "HomeScreen",

        builder = {

            composable("HomeScreen", content = { HomeScreen(navController = navController) } )

            composable("AddScreen", content = { AddScreen(navController = navController) } )

            composable("DetailScreen/{note}", arguments = listOf(navArgument("note") { type = NavType.StringType })) { backStackEntry ->

                backStackEntry.arguments?.getString("note")?.let { json ->
                    val note = Gson().fromJson(json, Note::class.java)
                    DetailScreen(note = note, navController = navController)
                }

            }

            composable("EditScreen/{note}", arguments = listOf(navArgument("note") { type = NavType.StringType })) { backStackEntry ->

                backStackEntry.arguments?.getString("note")?.let { json ->
                    val note = Gson().fromJson(json, Note::class.java)
                    EditScreen(note = note, navController = navController)
                }

            }

        }

    )

}