package com.example.ekacareassignment.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ekacareassignment.model.UserDetails

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertUserData(userData: UserDetails)
}