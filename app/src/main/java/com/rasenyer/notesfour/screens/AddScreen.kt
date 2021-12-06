package com.rasenyer.notesfour.screens

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.rasenyer.notesfour.entity.Note
import com.rasenyer.notesfour.viewmodel.NoteViewModel
import com.rasenyer.notesfour.viewmodel.NoteViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun AddScreen(navController: NavController) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val context = LocalContext.current
    val noteViewModel: NoteViewModel = viewModel(factory = NoteViewModelFactory(context.applicationContext as Application))

    Scaffold(

        topBar = {

            TopAppBar(

                title = {Text(text = "Add")},

                navigationIcon = {

                    IconButton(

                        onClick = { navController.navigate("HomeScreen") }

                    ) { Icon(Icons.Filled.ArrowBack,"") }

                },

                actions = {

                    IconButton(

                        onClick = {

                            @SuppressLint("SimpleDateFormat")
                            val dateFormat = SimpleDateFormat("EEEE, d MMMM yyyy, HH:mm:ss")
                            val date: String = dateFormat.format(Date())

                            noteViewModel.insert(Note(id = 0, title = title, description = description, date = date))
                            navController.navigate("HomeScreen")
                            Toast.makeText(context, "Note added successfully", Toast.LENGTH_SHORT).show()

                        }

                    ) { Icon(Icons.Outlined.Check , contentDescription = "") }

                }

            )

        },

        content = {

            Column(Modifier.padding(20.dp).verticalScroll(rememberScrollState())) {

                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Enter title") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.padding(10.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Enter description") },
                    modifier = Modifier.fillMaxWidth()
                )

            }

        }

    )
    
}