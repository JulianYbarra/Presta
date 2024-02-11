package com.junka.domain

data class Customer(
    val id : String,
    val name : String,
    val lastName : String,
    val dni : Int,
    val status : Status
){
    val fullName = "$name $lastName"


    enum class Status(val value : String){
        ERROR("error"),
        APPROVE("approve"),
        REJECTED("rejected")
    }
}