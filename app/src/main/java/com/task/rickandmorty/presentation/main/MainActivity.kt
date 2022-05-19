package com.task.rickandmorty.presentation.main


//import com.task.rickandmorty.presentation.details.ItemDetailsActivity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.task.rickandmorty.R
import com.task.rickandmorty.data.networck.dto.CharacterDto
import com.task.rickandmorty.presentation.details.CharacterDetailsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

const val ITEM = "ITEM"

class MainActivity : AppCompatActivity(), CharactersAdapter.Listener {

    private val itemsAdapter = CharactersAdapter(this)
    private var characters: List<CharacterDto>? = null
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemsRecyclerView: RecyclerView = findViewById(R.id.items)


        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        itemsRecyclerView.layoutManager = linearLayoutManager
        itemsRecyclerView.adapter = itemsAdapter


        mainViewModel.itemsLiveData.observe(this) { items ->
            this.characters = items
            this.itemsAdapter.setItems(items)
        }

        mainViewModel.getData()
    }

    override fun onItemClick(characterDto: CharacterDto) {
        val intent = Intent(this, CharacterDetailsActivity::class.java)
        intent.putExtra(ITEM, characterDto)
        startActivity(intent)
    }

    override fun getNewPage() {
        mainViewModel.getNewPage()
    }
}


