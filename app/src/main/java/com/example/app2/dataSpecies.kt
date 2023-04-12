package com.example.app2

import android.os.Parcel
import android.os.Parcelable
data class dataSpecies(var dataImage:Int, var dataTitle:String, var dataDesc: String, var dataDetailImage: Int): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()
    ) {
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(dataImage)
        parcel.writeString(dataTitle)
        parcel.writeString(dataDesc)
        parcel.writeInt(dataDetailImage)
    }
    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<dataSpecies> {
        override fun createFromParcel(parcel: Parcel): dataSpecies {
            return dataSpecies(parcel)
        }
        override fun newArray(size: Int): Array<dataSpecies?> {
            return arrayOfNulls(size)
        }
    }
}
