package com.example.inventpanroll.data.models

import com.google.gson.annotations.SerializedName

data class Equipo(
    @SerializedName("NoSerie")
    val noSerie: String,

    @SerializedName("Responsable")
    val responsable: String,

    @SerializedName("FormatoEquipo")
    val formatoEquipo: String,

    @SerializedName("Marca")
    val marca: String,

    @SerializedName("Modelo")
    val modelo: String,

    @SerializedName("ROM")
    val rom: String,

    @SerializedName("RAM")
    val ram: String,

    @SerializedName("CPU")
    val cpu: String,

    @SerializedName("Sesion")
    val sesion: String,

    @SerializedName("SO")
    val so: String,

    @SerializedName("InstalacionSO")
    val instalacionSO: String,

    @SerializedName("Voffice")
    val voffice: String,

    @SerializedName("ProgramasInt")
    val programasInt: String,

    @SerializedName("UniRed")
    val uniRed: String,

    @SerializedName("IP")
    val ip: String,

    @SerializedName("Area")
    val area: String,

    @SerializedName("Status")
    val status: String
)