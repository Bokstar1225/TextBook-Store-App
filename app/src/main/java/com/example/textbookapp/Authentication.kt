package com.example.textbookapp

import androidx.lifecycle.ViewModel

class Authentication : ViewModel(){
    companion object {
        private val users = mutableMapOf<String, String>()
    }
    fun signUp(email: String, password: String) : Boolean{
        if(users.containsKey(email)){
            return false
        }else{
            users[email] = password
            return true
        }
    }
    fun login(email: String, password: String) : Boolean{
        return users[email] == password
    }
}