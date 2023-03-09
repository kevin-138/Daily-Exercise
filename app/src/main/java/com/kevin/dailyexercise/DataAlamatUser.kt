package com.kevin.dailyexercise

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataAlamatUser(
    var dataAlamat:String,
    var dataDetail:String,
    var dataLabel:String,
    var dataPenerima:String,
    var dataHandphone:String,
    var dataSwitch:Boolean
) : Parcelable