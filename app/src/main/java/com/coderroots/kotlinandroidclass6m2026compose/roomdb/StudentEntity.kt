package com.coderroots.kotlinandroidclass6m2026compose.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class StudentEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var studentName: String? = null,
    var rollNo: Int = 0,

)
