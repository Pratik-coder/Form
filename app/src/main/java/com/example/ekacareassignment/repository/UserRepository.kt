package com.example.ekacareassignment.repository

import android.content.Context
import com.example.ekacareassignment.model.UserDetails
import com.example.ekacareassignment.roomdatabase.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class UserRepository {

    companion object{

        var userDatabase:UserDatabase?=null

        fun initializeDB(context: Context):UserDatabase{
            return UserDatabase.setUserDetails(context)
        }
    }

    fun insertUserDetails(context: Context,id:Int,userName:String,userAge:String,birthDate:String, address:String){
        userDatabase= initializeDB(context)
        CoroutineScope(IO).launch {
           val user=UserDetails(id,userName,userAge,birthDate,address)
            userDatabase!!.userDao().insertUserData(user)
        }
    }
}