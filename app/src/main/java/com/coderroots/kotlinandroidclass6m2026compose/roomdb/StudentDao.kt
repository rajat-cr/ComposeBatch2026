package com.coderroots.kotlinandroidclass6m2026compose.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {


    @Insert
    fun insertData(studentEntity: StudentEntity)

    @Query("select * from StudentEntity")
    fun getAllStudent(): Flow<List<StudentEntity>>


}