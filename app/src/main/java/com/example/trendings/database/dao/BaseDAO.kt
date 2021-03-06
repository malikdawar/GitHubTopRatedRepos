package com.example.trendings.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

/**
 * The BaseDAO.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

interface BaseDAO<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(t: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(ts: List<T>): List<Long>

    @Update
    fun update(t: T)

    @Delete
    fun delete(t: T)

    @Delete
    fun deleteAll(ts: List<T>)
}