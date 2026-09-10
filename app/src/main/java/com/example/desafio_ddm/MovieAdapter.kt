package com.example.desafio_ddm

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.desafio_ddm.databinding.ItemMovieBinding

class MovieAdapter(
    private val movies: MutableList<Movie>,
    private val onListChanged: () -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.tvTitulo.text = movie.title
        holder.binding.tvGenero.text = movie.genre
        holder.binding.ivPoster.load(movie.posterUrl) {
            crossfade(true)
        }

        holder.binding.btnEditar.setOnClickListener {
            val adapterPosition = holder.bindingAdapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                val context = holder.itemView.context
                val intent = Intent(context, CadastroActivity::class.java)
                intent.putExtra(CadastroActivity.EXTRA_POSITION, adapterPosition)
                context.startActivity(intent)
            }
        }

        holder.binding.btnExcluir.setOnClickListener {
            val adapterPosition = holder.bindingAdapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                movies.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
                onListChanged()
            }
        }
    }

    override fun getItemCount(): Int = movies.size
}
