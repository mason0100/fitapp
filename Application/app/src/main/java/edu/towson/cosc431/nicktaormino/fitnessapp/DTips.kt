package edu.towson.cosc431.nicktaormino.fitnessapp

import com.google.gson.annotations.SerializedName

data class DTips(
    @SerializedName("pro_tip")
    val tip: String
)