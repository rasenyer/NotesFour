package com.rasenyer.notesfour.screens

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.rasenyer.notesfour.entity.Note
import com.rasenyer.notesfour.viewmodel.NoteViewModel
import com.rasenyer.notesfour.viewmodel.NoteViewModelFactory

@Composable
fun DetailScreen(note: Note, navController: NavController) {

    val context = LocalContext.current
    val noteViewModel: NoteViewModel = viewModel(factory = NoteViewModelFactory(context.applicationContext as Application))

    Scaffold(

        topBar = {

            TopAppBar(

                title = { Text(text = "Detail") },

                navigationIcon = {

                    IconButton(

                        onClick = { navController.navigate("HomeScreen") }

                    ) { Icon(Icons.Filled.ArrowBack,"") }

                },

                actions = {

                    IconButton(

                        onClick = {
                            val noteVal = Gson().toJson(note)
                            navController.navigate("EditScreen/${noteVal}")
                        }

                    ) { Icon(Icons.Filled.Edit , contentDescription = "") }

                    IconButton(

                        onClick = {
                            noteViewModel.delete(note = note)
                            navController.navigate("HomeScreen")
                            Toast.makeText(context, "Note removed successfully", Toast.LENGTH_SHORT).show()
                        }

                    ) { Icon(Icons.Filled.Delete , contentDescription = "") }

                }

            )

        },

        content = {

            Column(Modifier.padding(10.dp).verticalScroll(rememberScrollState())) {

                Text(
                    text = note.title,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )

                Text(
                    text = note.description,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 0.dp, vertical = 10.dp)
                )

                Text(
                    text = note.date,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )

            }

        }

    )

}