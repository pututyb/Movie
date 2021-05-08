package com.example.myapplication.modules.details

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.example.myapplication.modules.home.MainActivity
import com.example.myapplication.R
import com.example.myapplication.database.DatabaseContract
import com.example.myapplication.database.DatabaseContract.NoteColums.Companion.DESC
import com.example.myapplication.database.DatabaseContract.NoteColums.Companion.GENRE
import com.example.myapplication.database.DatabaseContract.NoteColums.Companion.POSTER
import com.example.myapplication.database.DatabaseContract.NoteColums.Companion.RATING
import com.example.myapplication.database.DatabaseContract.NoteColums.Companion.TITLE
import com.example.myapplication.database.DatabaseContract.NoteColums.Companion.TRAILER
import com.example.myapplication.database.DatabaseContract.NoteColums.Companion._ID
import com.example.myapplication.database.MovieHelper
import com.example.myapplication.model.FilmModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    lateinit var data : FilmModel
    lateinit var noteHelper: MovieHelper
    private var values = ContentValues()

    private var statusFavorites = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        data = intent.getParcelableExtra<FilmModel>("data")!!
        noteHelper = MovieHelper.getInstance(this)
        noteHelper.open()

        iv_back.setOnClickListener {
            val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
        }

        initView()
        initListener()
    }

    fun initView() {
        tv_title.text = (data.judul)
        tv_genre.text = (data.genre)
        tv_desc.text = (data.desc)

        statusFavorites()
    }

    fun initListener() {
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        videoView.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + data.trailer))
        videoView.start()

        iv_favorite.setOnClickListener {
            if (statusFavorites) {
                noteHelper.deleteById(data.id.toString())
                iconFavorites(false)
            }else{
                values.put(_ID, data.id)
                values.put(TITLE, data.judul)
                values.put(DESC, data.desc)
                values.put(GENRE, data.genre)
                values.put(POSTER, data.poster)
                values.put(TRAILER, data.trailer)
                values.put(RATING, data.rating)

                noteHelper.insert(values)
                iconFavorites(true)
            }
        }
    }

    fun iconFavorites(boolean: Boolean) {
        if (boolean) {
            statusFavorites = true
            iv_favorite.setImageResource(R.drawable.ic_baseline_favorite_enable)
            
        }else{
            statusFavorites = false
            iv_favorite.setImageResource(R.drawable.ic_baseline_favorite_disable)
        }
    }

    fun statusFavorites() {
        val cursor = noteHelper.queryById(data.id.toString())
        if (cursor.moveToNext()){
            iconFavorites(true)
        }else {
            iconFavorites(false)
        }
    }
}