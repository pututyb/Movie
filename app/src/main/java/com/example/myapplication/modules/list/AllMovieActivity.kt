package com.example.myapplication.modules.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.AllMovieAdapter
import com.example.myapplication.model.FilmModel
import com.example.myapplication.modules.details.DetailActivity
import kotlinx.android.synthetic.main.content_all_movie.*

class AllMovieActivity : AppCompatActivity() {

    private var datalist = ArrayList<FilmModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_movie)
        setSupportActionBar(findViewById(R.id.toolbar))

        datalist = intent.getParcelableArrayListExtra("data")!!

        rv_all_movie.layoutManager = LinearLayoutManager(this)

        rv_all_movie.adapter =
            AllMovieAdapter(datalist) {
                val intent = Intent(this, DetailActivity::class.java)
                    .putExtra("data", it)
                startActivity(intent)
            }
    }
}