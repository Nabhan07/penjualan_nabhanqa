package com.example.penjualan_nabhanqa.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class tbbarang (
    @PrimaryKey
    val id: Int,
    val nmBrg : String,
    val hrgbrg : Int,
    val stok : Int
)