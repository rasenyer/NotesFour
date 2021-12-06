package com.rasenyer.notesfour.screens

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
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
fun HomeScreen(navController: NavController) {

    val context = LocalContext.current
    val noteViewModel: NoteViewModel = viewModel(factory = NoteViewModelFactory(context.applicationContext as Application))
    val getAll = noteViewModel.getAll.observeAsState(listOf()).value

    Scaffold(

        topBar = {

            TopAppBar(

                title = {Text(text = "Home")},

                actions = {

                    IconButton(

                        onClick = { navController.navigate("AddScreen") }

                    ) { Icon(Icons.Outlined.Add , contentDescription = "") }

                    IconButton(

                        onClick = {
                            noteViewModel.deleteAll()
                            Toast.makeText(context, "Notes removed successfully", Toast.LENGTH_SHORT).show()
                        }

                    ) { Icon(Icons.Outlined.Delete , contentDescription = "") }

                }

            )

        },

        content = {

            LazyColumn {

                items(getAll.size) { note ->
                    ItemNote(getAll[note], navController)
                }

            }

        }

    )

}

@Composable
fun ItemNote(note: Note, navController: NavController) {

    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(5.dp)
            .clickable {
                val noteVal = Gson().toJson(note)
                navController.navigate("DetailScreen/${noteVal}")
            }
    ) {

        Column(modifier = Modifier.padding(10.dp)) {

            Text(
                text = note.title,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = note.description,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 0.dp, vertical = 10.dp)
            )

            Text(
                text = note.date,
                fontSize = 14.sp,
                textAlign = TextAlign.End,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )

        }

    }

}
