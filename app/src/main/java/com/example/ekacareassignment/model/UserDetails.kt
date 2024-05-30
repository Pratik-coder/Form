package com.example.ekacareassignment.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "usertable")
data class UserDetails(

    @PrimaryKey(autoGenerate = true)
    var id:Int=0,

    @ColumnInfo(name="userName")
    var userName:String,

    @ColumnInfo(name = "userAge")
    var userAge:String,

    @ColumnInfo(name="dateOfBirth")
    var dateOfBirth:String,

    @ColumnInfo(name = "address")
    var address:String

    )
