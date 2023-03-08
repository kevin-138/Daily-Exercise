package com.kevin.dailyexercise

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AccountInfo(
    var accountName: String,
    var accountEmail: String,
    var accountGender: Int,
    var accountDay: Int,
    var accountMonth: Int,
    var accountYear: Int,
    var accountAlamat: String,
    var accountStudy: String
) : Parcelable

