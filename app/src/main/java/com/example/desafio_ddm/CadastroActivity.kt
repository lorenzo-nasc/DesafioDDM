package com.example.desafio_ddm

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.desafio_ddm.databinding.ActivityCadastroBinding

class CadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding
    private var editPosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editPosition = intent.getIntExtra(EXTRA_POSITION, -1)
        if (editPosition >= 0) {
            val movie = MovieRepository.getAll()[editPosition]
            binding.etTitulo.setText(movie.title)
            binding.etGenero.setText(movie.genre)
            binding.etPosterUrl.setText(movie.posterUrl)
            binding.btnSalvar.setText(R.string.btn_atualizar)
        }

        binding.btnVoltar.setOnClickListener { finish() }

        binding.btnSalvar.setOnClickListener {
            val titulo = binding.etTitulo.text.toString().trim()
            val genero = binding.etGenero.text.toString().trim()
            val posterUrl = binding.etPosterUrl.text.toString().trim()

            if (titulo.isEmpty() || genero.isEmpty() || posterUrl.isEmpty()) {
                Toast.makeText(this, R.string.erro_campos_obrigatorios, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (editPosition >= 0) {
                MovieRepository.update(editPosition, Movie(titulo, genero, posterUrl))
                Toast.makeText(this, R.string.filme_atualizado, Toast.LENGTH_SHORT).show()
                finish()
            } else {
                MovieRepository.add(Movie(titulo, genero, posterUrl))
                Toast.makeText(this, R.string.filme_salvo, Toast.LENGTH_SHORT).show()
                binding.etTitulo.text.clear()
                binding.etGenero.text.clear()
                binding.etPosterUrl.text.clear()
            }
        }

        binding.tvVerLista.setOnClickListener {
            startActivity(Intent(this, ListaActivity::class.java))
        }
    }

    companion object {
        const val EXTRA_POSITION = "EXTRA_POSITION"
    }
}
