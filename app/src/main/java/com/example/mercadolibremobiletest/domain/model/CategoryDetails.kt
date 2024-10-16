package com.example.mercadolibremobiletest.domain.model

import android.os.Parcel
import android.os.Parcelable

data class CategoryDetails(
    val attributable: Boolean,
    val attributeTypes: String,
    val id: String,
    val name: String,
    val pathFromRootResponse: List<PathFromRoot>,
    val permalink: Any?,
    val picture: Any?,
    val totalItemsInThisCategory: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        TODO("pathFromRootResponse"),
        TODO("permalink"),
        TODO("picture"),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (attributable) 1 else 0)
        parcel.writeString(attributeTypes)
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeInt(totalItemsInThisCategory)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CategoryDetails> {
        override fun createFromParcel(parcel: Parcel): CategoryDetails {
            return CategoryDetails(parcel)
        }

        override fun newArray(size: Int): Array<CategoryDetails?> {
            return arrayOfNulls(size)
        }
    }
}
