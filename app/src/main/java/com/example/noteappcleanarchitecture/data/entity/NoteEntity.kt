package com.example.noteappcleanarchitecture.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noteappcleanarchitecture.data.utils.NOTE_TABLE

@Entity(tableName = NOTE_TABLE)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var title:String = "",
    var disc:String = "",
    var category:String = "",
    var priority:String = ""
)

