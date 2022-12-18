package com.example.trylistjson

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.trylistjson.models.Book
import com.example.trylistjson.models.BookType
import java.util.*
import java.util.concurrent.Executors

class AddbookActivity : AppCompatActivity() {
    //лист который можно имзменять

    private lateinit var title: EditText
    private lateinit var genre: EditText
    private lateinit var author: EditText
    private lateinit var year_of_publishing: EditText
    private lateinit var cover_type: EditText
    private lateinit var NUMBER_OF_PAGES: EditText
    private lateinit var button: Button
    private var BookList: MutableList<Book> = mutableListOf()
    private var GenreList: MutableList<BookType> = mutableListOf()
    var cheeck: Int = 0
    var year_of_publishing_numConvert: Int = -1
    var NUMBER_OF_PAGES_numConvert: Int = -1
    private lateinit var remove_button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)
        // метод
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);

        this.supportActionBar!!.hide()
        val index = intent.getIntExtra("number", -1)
        title = findViewById(R.id.editTextTexttitle)
        genre = findViewById(R.id.editTextTexjanr)
        author = findViewById(R.id.editTextTextEmailAuhtor)
        year_of_publishing = findViewById(R.id.editTextTextyear_of_publishing)
        cover_type = findViewById(R.id.editTextTextcover_type)
        NUMBER_OF_PAGES = findViewById(R.id.editTextTextNUMBER_OF_PAGES)
        remove_button = findViewById(R.id.remove_button)
        button = findViewById(R.id.button3)
        remove_button.setEnabled(false)
        GenreList.clear()
        var db: Bookdatabase =
            Room.databaseBuilder(this, Bookdatabase::class.java, DATABASE_NAME).build()
        val bookDAO = db.bookDao()
        db.bookDao().getAllGenre().observe(this, androidx.lifecycle.Observer {
            GenreList.addAll(it)

        })
        if (index > -1) {
            remove_button.setEnabled(true)
            AddInfo_in_List()
            button.text = "изменить"
            var db: Bookdatabase =
                Room.databaseBuilder(this, Bookdatabase::class.java, DATABASE_NAME).build()
            val bookDAO = db.bookDao()
            db.bookDao().getAllBookMain().observe(this, androidx.lifecycle.Observer {
                BookList.addAll(it)
                title.setText(BookList[index].title_data)
                author.setText(BookList[index].author_data)
                year_of_publishing.setText(BookList[index].year_of_publishing_data.toString())
                cover_type.setText(BookList[index].cover_type_data)
                NUMBER_OF_PAGES.setText(BookList[index].NUMBER_OF_PAGES_data.toString())

            })
            db.bookDao().getAllGenre().observe(this, androidx.lifecycle.Observer {
                try
                {
                    GenreList.addAll(it)
                    genre.setText(GenreList[index].genre_data.toString())
                }catch (e:Exception)
                {

                }

            })

        }
        remove_button.setOnClickListener {
            var db: Bookdatabase =
                Room.databaseBuilder(this, Bookdatabase::class.java, DATABASE_NAME).build()
            val bookDAO = db.bookDao()
            var check1: Int = 0
            var id_genre: UUID = UUID.randomUUID()
            GenreList.forEach()
            {
                check1++
                if (it.genre_data == genre.text.toString()) {
                    id_genre = it.uuid
                    cheeck++
                    Log.d("fsfd", it.genre_data+" " + genre.text.toString() + cheeck)


                }

            }
            if (cheeck== 1) {
                val executor = Executors.newSingleThreadExecutor()
                executor.execute {
                    bookDAO.killBookMain(
                        Book(
                            BookList[index].uuid,
                            GenreList[index].uuid,
                            title.text.toString(),
                            author.text.toString(),
                            year_of_publishing_numConvert,
                            cover_type.text.toString(),
                            NUMBER_OF_PAGES_numConvert,

                            )
                    )

                }
                val executor2 = Executors.newSingleThreadExecutor()
                executor2.execute {
                    bookDAO.killGenre(
                        BookType(
                            GenreList[index].uuid,
                            genre.text.toString()
                        )
                    )

                }
                Toast.makeText(this, "удаленны все элементы", Toast.LENGTH_SHORT).show()
            }
            if (cheeck > 1) {
                val executor = Executors.newSingleThreadExecutor()
                executor.execute {
                    bookDAO.killBookMain(
                        Book(
                            BookList[index].uuid,
                            GenreList[index].uuid,
                            title.text.toString(),
                            author.text.toString(),
                            year_of_publishing_numConvert,
                            cover_type.text.toString(),
                            NUMBER_OF_PAGES_numConvert,

                            )
                    )

                }
                Toast.makeText(this, "удалены все элементы кроме жанра", Toast.LENGTH_SHORT).show()
            }
            super.onBackPressed();


        }

        button.setOnClickListener {
            var check1: Int = 0
            var id_genre: UUID = UUID.randomUUID()
            val num3: String = genre.text.toString()
            GenreList.forEach()
            {
                check1++
                Log.d("fsfd", it.genre_data + genre.text.toString() + check1)
                if (it.genre_data == genre.text.toString()) {

                    id_genre = it.uuid
                    cheeck++

                }
                if (num3 == "") {
                    cheeck++
                }
            }

            if (index == -1) {
                val executor = Executors.newSingleThreadExecutor()
                val executor3 = Executors.newSingleThreadExecutor()
                //принятие данных эдит текста 1
                val num1: String = year_of_publishing.text.toString()
                //принятие данных эдит текста 2
                val num2: String = NUMBER_OF_PAGES.text.toString()
                try {
                    year_of_publishing_numConvert = num1!!.toInt()
                    NUMBER_OF_PAGES_numConvert = num2!!.toInt()

                    val uuidgenre: UUID = UUID.randomUUID()
                    val uuidbook: UUID = UUID.randomUUID()
                    if (cheeck == 0) {
                        executor.execute {
                            bookDAO.addGenre(BookType(uuidgenre, genre.text.toString()))
                        }

                    }
                    if (cheeck > 0) {
                        /* executor.execute {
                             bookDAO.addGenre(BookType(uuidgenre, "   fd"))
                         }*/

                    }
                    if (cheeck == 0) {
                        executor3.execute {
                            bookDAO.addBookMain(
                                Book(
                                    uuidbook,
                                    uuidgenre,
                                    title.text.toString(),
                                    author.text.toString(),
                                    year_of_publishing_numConvert,
                                    cover_type.text.toString(),
                                    NUMBER_OF_PAGES_numConvert
                                )
                            )

                        }
                    }
                    if (cheeck > 0) {
                        executor3.execute {
                            bookDAO.addBookMain(
                                Book(
                                    uuidbook,
                                    id_genre,
                                    title.text.toString(),
                                    author.text.toString(),
                                    year_of_publishing_numConvert,
                                    cover_type.text.toString(),
                                    NUMBER_OF_PAGES_numConvert
                                )
                            )

                        }
                    }
                    val types = bookDAO.getAllGenre()
                    val book34 = bookDAO.getAllBookMain()
                    types.observe(this, androidx.lifecycle.Observer {
                        it.forEach {
                            Log.d("TAG1", "ID:  ЖАНР: ${it.genre_data}")
                        }

                    })
                    book34.observe(this, androidx.lifecycle.Observer {
                        it.forEach {
                            Log.d(
                                "TAG12",
                                "ID:${it.id}   НАЗВАНИЕ:${it.title_data}  Автор:${it.author_data}  ГОД ПУБЛИКАЦИИ:${it.year_of_publishing_data} ТИП ОБЛОЖКИ: ${it.cover_type_data} КОЛИЧЕСТВО СТРАНИЦ:${it.NUMBER_OF_PAGES_data} "
                            )
                        }

                    })
                    if (index == -1 && cheeck == 0) {
                        Toast.makeText(this, "all good", Toast.LENGTH_LONG).show();
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "repeat the input ", Toast.LENGTH_LONG).show();
                }


            }

            if (index > -1) {
                val executor = Executors.newSingleThreadExecutor()
                executor.execute {
                    var db: Bookdatabase =
                        Room.databaseBuilder(this, Bookdatabase::class.java, DATABASE_NAME).build()
                    val bookDAO = db.bookDao()
                    //принятие данных эдит текста 1
                    val num1: String = year_of_publishing.text.toString()
                    //принятие данных эдит текста 2
                    val num2: String = NUMBER_OF_PAGES.text.toString()

                    try {
                        year_of_publishing_numConvert = num1!!.toInt()
                        NUMBER_OF_PAGES_numConvert = num2!!.toInt()

                    } catch (e: Exception) {
                        Toast.makeText(this, "repeat the input ", Toast.LENGTH_LONG).show();

                    }
                    executor.execute {
                        bookDAO.savetGenre(BookType(GenreList[index].uuid, genre.text.toString()))

                    }
                    bookDAO.savetBookMain(
                        Book(
                            BookList[index].uuid,
                            GenreList[index].uuid,
                            title.text.toString(),
                            author.text.toString(),
                            year_of_publishing_numConvert,
                            cover_type.text.toString(),
                            NUMBER_OF_PAGES_numConvert,
                        )
                    )

                }

            }
            super.onBackPressed();

        }

    }

    private fun AddInfo_in_List() {
        BookList.clear()
        var db: Bookdatabase =
            Room.databaseBuilder(this, Bookdatabase::class.java, DATABASE_NAME).build()
        val bookDAO = db.bookDao()
        var booksget = bookDAO.getAllBookMain()
        booksget.observe(this, androidx.lifecycle.Observer {
            BookList.addAll(it)
        })
    }


}


