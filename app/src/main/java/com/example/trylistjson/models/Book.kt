package com.example.trylistjson.models

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.trylistjson.MAIN_BOOK_TABLE
import java.util.*

@Entity(tableName = MAIN_BOOK_TABLE)
data class Book(
    @PrimaryKey(autoGenerate = false)
    var uuid: UUID,
    @ColumnInfo(index = true)
    val id:UUID ,
    var title_data: String=" ",
    var author_data: String=" ",
    val year_of_publishing_data :Int=0,
    var cover_type_data:String=" ",
    val NUMBER_OF_PAGES_data :Int=0,

    )