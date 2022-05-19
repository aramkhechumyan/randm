package com.task.rickandmorty.presentation.details


import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.task.rickandmorty.R
import com.task.rickandmorty.data.networck.dto.CharacterDto
import com.task.rickandmorty.presentation.main.ITEM
import org.koin.androidx.viewmodel.ext.android.viewModel


class CharacterDetailsActivity : AppCompatActivity() {

    private val characterDetailsViewModel: CharacterDetailsViewModel by viewModel()
    private val episodesAdapter = EpisodesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        val image = findViewById<ImageView>(R.id.item_avatar)
        val name = findViewById<TextView>(R.id.name_text)
        findViewById<RecyclerView>(R.id.episodes).apply {
            layoutManager = LinearLayoutManager(this@CharacterDetailsActivity)
            adapter = episodesAdapter
        }


        characterDetailsViewModel.episodesLiveData.observe(this) {
            it?.let { episodes ->
                episodesAdapter.setItems(episodes)
            }
        }

        val characterDto = intent?.getParcelableExtra<CharacterDto>(ITEM)

        characterDto?.let { character ->
            image.load(character.image)
            name.text = character.name

            val episodeIds = mutableListOf<String>()
            character.episode?.forEach { episode ->
                val splitEpisode = episode.split("/")
                if (splitEpisode.size > 5) {
                    val episodeId = splitEpisode[5]
                    episodeIds.add(episodeId)
                }
            }

            if (episodeIds.isNotEmpty()) {
                characterDetailsViewModel.getEpisodes(episodeIds.toString())
            }
        }
    }
}
