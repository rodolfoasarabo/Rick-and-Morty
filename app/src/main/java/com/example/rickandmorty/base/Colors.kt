package com.example.rickandmorty.base

import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

private val Primary = Color(0xff23335F)
private val PrimaryVariant = Color(0xff69C8EC)
private val OnPrimary = Color(0xffFFFFFF)
private val Secondary = Color(0xffFAFD7C)
private val SecondaryVariant = Color(0xffF9E48C)
private val OnSecondary = Color(0xff000000)

val LightColors = lightColors(
    primary = Primary,
    primaryVariant = PrimaryVariant,
    secondary = Secondary,
    secondaryVariant = SecondaryVariant,
    onPrimary = OnPrimary,
    onSecondary = OnSecondary,
)