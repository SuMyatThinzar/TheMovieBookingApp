package com.padcmyanmar.smtz.themoviebookingapp.data.vos

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "snacks")

data class SnackVO (
        var quantity : Int,

        @SerializedName("id")
        @PrimaryKey
        val id : Int,

        @SerializedName("name")
        @ColumnInfo(name = "name")
        val name : String?,
        @SerializedName("description")
        @ColumnInfo(name = "description")
        val description : String?,
        @SerializedName("price")
        @ColumnInfo(name = "price")
        val price : String?,
        @SerializedName("image")
        @ColumnInfo(name = "image")
        val image : String?,
        ) : Parcelable {

        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readInt(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeInt(quantity)
                parcel.writeInt(id)
                parcel.writeString(name)
                parcel.writeString(description)
                parcel.writeString(price)
                parcel.writeString(image)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<SnackVO> {
                override fun createFromParcel(parcel: Parcel): SnackVO {
                        return SnackVO(parcel)
                }

                override fun newArray(size: Int): Array<SnackVO?> {
                        return arrayOfNulls(size)
                }
        }
}