package com.rasenyer.notesfour.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.rasenyer.notesfour.database.NoteDatabase
import com.rasenyer.notesfour.entity.Note
import com.rasenyer.notesfour.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class NoteViewModel(application: Application): AndroidViewModel(application) {

    private val noteRepository: NoteRepository

    fun insert(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.insert(note = note)
        }
    }

    fun update(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.update(note = note)
        }
    }

    fun delete(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.delete(note = note)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.deleteAll()
        }
    }

    val getAll: LiveData<List<Note>>

    init {
        val noteDao = NoteDatabase.getInstance(application).noteDao()
        noteRepository = NoteRepository(noteDao = noteDao)
        getAll = noteRepository.getAll
    }

}

class NoteViewModelFactory(private val application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            return NoteViewModel(application) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")

    }

}