package com.example.myapplication.modules.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.modules.list.AllMovieActivity
import com.example.myapplication.R
import com.example.myapplication.adapter.MovieAdapter
import com.example.myapplication.model.FilmModel
import com.example.myapplication.modules.details.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var dataList = ArrayList<FilmModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_movie.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

       loadDataSample();

        rv_movie.adapter =
            MovieAdapter(dataList) {
                val intent = Intent(this, DetailActivity::class.java)
                    .putExtra("data", it)
                startActivity(intent)
            }

        tv_all_movie.setOnClickListener{
            val intent = Intent(this, AllMovieActivity::class.java)
                .putExtra("data", dataList)
            startActivity(intent)
        }

    }

    private fun loadDataSample() {
        dataList.add(
            FilmModel(
                "1",
                "Ad Astra",
                "he near future, a time when both hope and hardships drive humanity to look to the stars and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut Roy McBride undertakes a mission across the immensity of space and its many perils to uncover the truth about a lost expedition that decades before boldly faced emptiness and silence in search of the unknown.",
                " Science Fiction, Drama",
                R.drawable.ic_ad_astra,
                R.raw.video_sample,
                4.5F
            )
        )

        dataList.add(
            FilmModel(
                "1",
                "Avengers",
                "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.",
                " Adventure, Science Fiction, Action ",
                R.drawable.ic_avengers,
                R.raw.video_avengers,
                4.0F
            )
        )

        dataList.add(
            FilmModel(
                "1",
                "A Rainy Day in New York",
                "Two young people arrive in New York to spend a weekend, but once they arrive they're met with bad weather and a series of adventures.",
                "Comedy, Romance ",
                R.drawable.ic_poster_a_rainy_day_in_new_york,
                R.raw.video_a_rainy_day,
                3.0F
            )
        )
        dataList.add(
            FilmModel(
                "1",
                "Sonic the Hedgehog",
                "Based on the global blockbuster videogame franchise from Sega, Sonic the Hedgehog tells the story of the world’s speediest hedgehog as he embraces his new home on Earth. In this live-action adventure comedy, Sonic and his new best friend team up to defend the planet from the evil genius Dr. Robotnik and his plans for world domination.",
                " Action, Science Fiction, Comedy, Family",
                R.drawable.ic_poster_sonic,
                R.raw.video_sonic,
                5.0F
            )
        )
    }
}