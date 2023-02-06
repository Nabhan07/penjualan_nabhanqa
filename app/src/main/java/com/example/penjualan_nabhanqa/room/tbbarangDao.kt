package com.example.penjualan_nabhanqa.room

import androidx.room.*

@Dao
interface tbbarangDao {
    @Insert
    fun addTbbrg (tbbrg: tbbarang)

    @Update
    fun upTbbrg (tbbrg: tbbarang)

    @Delete
    fun delTbbrg (tbbrg: tbbarang)

    @Query("SELECT * FROM tbbarang")
    fun tampilsemua () : List<tbbarang>

    @Query("SELECT * FROM tbbarang WHERE id=:id_brg")
    fun tampilall (id_brg: Int) : List<tbbarang>
}


