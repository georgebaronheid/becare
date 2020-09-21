package br.com.becare.entities

data class Hospital(
    val id: String,
    val nome: String,
    val telefone: String,
    val logradouro: String,
    val cep: String,
    val sus: Boolean,
    val publico: Boolean,
    val longitude: Double,
    val latitude: Double
)
