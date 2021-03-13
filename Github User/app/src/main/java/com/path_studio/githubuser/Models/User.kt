package com.path_studio.githubuser.Models

import android.os.Parcel
import android.os.Parcelable

data class User (
    var username: String?,
    var name: String?,
    var location: String?,
    var repository: Int,
    var starred: Int,
    var company: String?,
    var link: String?,
    var followers: Int,
    var following: Int,
    var avatar: String?,
    var background: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString()

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(name)
        parcel.writeString(location)
        parcel.writeInt(repository)
        parcel.writeInt(starred)
        parcel.writeString(company)
        parcel.writeString(link)
        parcel.writeInt(followers)
        parcel.writeInt(following)
        parcel.writeString(avatar)
        parcel.writeString(background)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}