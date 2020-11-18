package br.com.becare.entities.dtos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Hospital(
    @SerializedName("id") val id: String,
    @SerializedName("nome") val nome: String,
    @SerializedName("telefone") val telefone: String,
    @SerializedName("logradouro") val logradouro: String,
    @SerializedName("cep") val cep: String,
    @SerializedName("sus") val sus: Boolean,
    @SerializedName("publico") val publico: Boolean,
    @SerializedName("longitude") val longitude: String,
    @SerializedName("latitude") val latitude: String,
    @SerializedName("fila") val fila: String,
    @SerializedName("ps") val ps: Boolean
) : Serializable

