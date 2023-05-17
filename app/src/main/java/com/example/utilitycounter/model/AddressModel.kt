package com.example.utilitycounter.model

data class AddressModel(
    val street: String,
    val numberOfBuild: String,
    val numberOfApartment: String?,
    val privateHouse: String
) {
    constructor() : this(
        street = " ",
        numberOfBuild = " ",
        numberOfApartment = " ",
        privateHouse = " "
    )
}
