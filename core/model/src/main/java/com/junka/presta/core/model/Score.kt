package com.junka.presta.core.model

data class Score(
    val time : Long = 0,
    val status : String
){
    fun getScoreStatus() : ScoreStatus {
        return ScoreStatus.valueOf(status.uppercase())
    }
}

enum class ScoreStatus(val value : String){
    ERROR("error"),
    APPROVE("approve"),
    REJECTED("rejected")
}