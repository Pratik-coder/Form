package com.example.ekacareassignment.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.ekacareassignment.repository.UserRepository

class UserViewModel:ViewModel() {

    private val userRepository=UserRepository()

    fun insertData(context: Context,id:Int,userName:String,userAge:String,birthDate:String,address:String){
        userRepository.insertUserDetails(context,id,userName,userAge,birthDate,address)
    }
}