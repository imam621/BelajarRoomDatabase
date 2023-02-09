package com.example.belajarroomdatabase.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbsiswa")
data class Siswa(
    @PrimaryKey @ColumnInfo(name = "nis")var nis:Int=0,
    @ColumnInfo(name = "namasiswa")var namasiswa:String,
    @ColumnInfo(name = "alamat")var alamat:String,
    @ColumnInfo(name = "jekel")var jenis_kelamin:String,
)