package com.kevin.dailyexercise

import android.os.Parcel
import android.os.Parcelable

data class AccountInfo(
    var accountName:String?,
    var accountEmail:String?,
    var accountGender: Int,
    var accountDay: Int,
    var accountMonth: Int,
    var accountYear: Int,
    var accountAlamat:String?,
    var accountStudy: String?): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(accountName)
        parcel.writeString(accountEmail)
        parcel.writeInt(accountGender)
        parcel.writeInt(accountDay)
        parcel.writeInt(accountMonth)
        parcel.writeInt(accountYear)
        parcel.writeString(accountAlamat)
        parcel.writeString(accountStudy)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AccountInfo> {
        override fun createFromParcel(parcel: Parcel): AccountInfo {
            return AccountInfo(parcel)
        }

        override fun newArray(size: Int): Array<AccountInfo?> {
            return arrayOfNulls(size)
        }
    }
}

