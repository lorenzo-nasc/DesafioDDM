package com.example.desafio_ddm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio_ddm.databinding.ActivityListaBinding

class ListaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaBinding
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movies = MovieRepository.getAll()
        adapter = MovieAdapter(movies) { updateEmptyState() }

        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        binding.rvMovies.adapter = adapter

        binding.btnVoltar.setOnClickListener { finish() }

        updateEmptyState()
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
        updateEmptyState()
    }

    private fun updateEmptyState() {
        val isEmpty = MovieRepository.getAll().isEmpty()
        binding.tvEmpty.visibility = if (isEmpty) android.view.View.VISIBLE else android.view.View.GONE
        binding.rvMovies.visibility = if (isEmpty) android.view.View.GONE else android.view.View.VISIBLE
    }
}
