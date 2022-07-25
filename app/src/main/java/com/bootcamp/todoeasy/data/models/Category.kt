package com.bootcamp.todoeasy.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "category", indices = [Index(value = ["categoryName"],
    unique = true)])
@Parcelize
class Category(
    @PrimaryKey(autoGenerate = true)
    val idCategory: Long?,
    val categoryName: String
) : Parcelable
