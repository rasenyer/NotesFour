package com.rasenyer.notesfour.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rasenyer.notesfour.dao.NoteDao
import com.rasenyer.notesfour.entity.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {

        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase {

            synchronized(this) {

                return INSTANCE?: Room.databaseBuilder(

                    context.applicationContext,
                    NoteDatabase::class.java,
                    "NoteDatabase"

                ).build().also {

                    INSTANCE = it

                }

            }

        }

    }

}