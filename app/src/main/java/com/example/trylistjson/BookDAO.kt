package com.example.trylistjson

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.trylistjson.models.Book
import com.example.trylistjson.models.BookType
import java.util.*

@Dao
interface BookDAO {
    /* таблица BookType*/
    @Query("SELECT * FROM $GENRE_TABLE ")
    fun getAllGenre():LiveData<List<BookType>>
    @Insert
    fun addGenre(bookType: BookType)
    @Query("SELECT uuid FROM $GENRE_TABLE WHERE genre_data=:genre")
    fun  getOnlyGenre(genre:String):UUID
    @Update
    fun  savetGenre(bookType: BookType)
    @Delete
    fun killGenre(bookType: BookType)
    // no use
   // @Query("SELECT genre_data FROM $GENRE_TABLE WHERE uuid=:_uuid")
   // fun  getGenreUseUUid(_uuid:UUID):String
    /* таблица Book*/
    @Query("SELECT * FROM $MAIN_BOOK_TABLE  ")
    fun getAllBookMain():LiveData<List<Book>>
    @Insert
    fun addBookMain(book: Book)
    @Update
    fun  savetBookMain(book: Book)
    @Delete
    fun  killBookMain(book: Book)
}