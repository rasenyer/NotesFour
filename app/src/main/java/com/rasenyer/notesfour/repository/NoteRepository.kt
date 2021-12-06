package com.rasenyer.notesfour.repository

import androidx.lifecycle.LiveData
import com.rasenyer.notesfour.dao.NoteDao
import com.rasenyer.notesfour.entity.Note

class NoteRepository(private val noteDao: NoteDao) {

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun update(note: Note) {
        noteDao.update(note)
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }

    suspend fun deleteAll() {
        noteDao.deleteAll()
    }

    val getAll: LiveData<List<Note>> = noteDao.getAll()

}