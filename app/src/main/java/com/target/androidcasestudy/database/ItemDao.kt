package com.target.androidcasestudy.database

import androidx.room.*
import com.target.androidcasestudy.model.Item

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(employeeModel: Item)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(employeeModelList: List<Item>)

    @Delete
    fun delete(employeeModel: Item)

    @Query("SELECT * FROM item")
    fun getALL():List<Item>

}