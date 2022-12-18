package com.example.trylistjson.models

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.trylistjson.GENRE_TABLE
import java.util.*

@Entity(tableName = GENRE_TABLE)

data class BookType (
    @PrimaryKey(autoGenerate = false)
     var uuid: UUID,
    var genre_data:String

)