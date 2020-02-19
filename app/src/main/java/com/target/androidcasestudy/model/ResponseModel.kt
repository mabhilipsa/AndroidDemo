package com.target.androidcasestudy.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class ResponseModel (var data: List<Item>)

@Entity(tableName = "item")
data class Item (
    @PrimaryKey
    var _id:String,
    var aisle:String,
    var description:String,
    var guid:String,
    var image:String,
    var index:String,
    var price:String,
    var salePrice:String?,
    var title:String)

