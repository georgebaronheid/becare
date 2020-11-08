package br.com.becare.entities.dtos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MedicalEntity(
    val hospital: Hospital,

    @SerializedName("id")
    val distancia: Float
) : Serializable
