package com.junka.presta.feature.customer.navigation

sealed class Navigation(
    val route : String
){
    data object Create : Navigation("app://presta/customer_create")
    data class Update(val id : String) : Navigation("app://presta/customer_update"){
        fun getArgRoute() = "$route/$id"
    }
}