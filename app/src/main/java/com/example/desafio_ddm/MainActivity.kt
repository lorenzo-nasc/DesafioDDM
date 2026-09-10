package com.example.desafio_ddm

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.desafio_ddm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val USUARIO_VALIDO = "admin"
        private const val SENHA_VALIDA = "1234"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnEntrar.setOnClickListener {
            val usuario = binding.etUsuario.text.toString()
            val senha = binding.etSenha.text.toString()

            if (usuario == USUARIO_VALIDO && senha == SENHA_VALIDA) {
                startActivity(Intent(this, CadastroActivity::class.java))
            } else {
                Toast.makeText(this, R.string.erro_login, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
