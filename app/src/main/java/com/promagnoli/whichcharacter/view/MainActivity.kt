package com.promagnoli.whichcharacter.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.promagnoli.whichcharacter.R
import com.promagnoli.whichcharacter.extensions.mainActivityComponent
import com.promagnoli.whichcharacter.goToRandomCharacterActivity
import com.promagnoli.whichcharacter.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var presenter: MainPresenter

    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityComponent.inject(this)
        presenter.configureCharactersList()
        configureRandomCharacterButton()
    }

    private fun configureRandomCharacterButton() {
        btn_random.setOnClickListener {
            goToRandomCharacterActivity(this)
        }
    }

    fun setCharactersList(characters: List<String>) {
        recyclerAdapter = RecyclerAdapter(characters)
        layoutManager = LinearLayoutManager(this)
        val itemDecoration: ItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        list_characters.layoutManager = layoutManager
        list_characters.adapter = recyclerAdapter
        list_characters.addItemDecoration(itemDecoration)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.disposeCalls()
    }
}
