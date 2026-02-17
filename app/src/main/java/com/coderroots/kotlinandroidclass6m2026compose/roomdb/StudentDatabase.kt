package com.coderroots.kotlinandroidclass6m2026compose.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [StudentEntity::class], version = 1, exportSchema = false)
abstract class StudentDatabase  : RoomDatabase(){

    abstract fun studentDao() : StudentDao

    companion object {
        private var studentDatabase: StudentDatabase? = null

        fun getInstance(context: Context): StudentDatabase? {
            synchronized(this) {
                if (studentDatabase == null) {
                    studentDatabase = Room.databaseBuilder(
                        context, StudentDatabase::class.java,
                        "student_database"
                    )
                        .build()

                }
            }
            return studentDatabase
        }
    }


}