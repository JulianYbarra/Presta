package com.junka.presta.core.model

data class Customer(
    val id : String,
    val name : String,
    val lastName : String,
    val dni : Int,
    val score : Score?
){
    val fullName = "$name $lastName"
}