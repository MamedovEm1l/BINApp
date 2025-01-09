package com.example.bankcardapp.domain.model

data class BinInfo(
    val number: NumberInfo?,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: CountryInfo?,
    val bank: BankInfo?
)

data class NumberInfo(
    val length: Int?,
    val luhn: Boolean?
)

data class CountryInfo(
    val numeric: String?,
    val alpha2: String?,
    val name: String?,
    val emoji: String?,
    val currency: String?,
    val latitude: Double?,
    val longitude: Double?
)

data class BankInfo(
    val name: String?,
    val url: String?,
    val phone: String?,
    val city: String?
)