package com.example.tp2_moviles;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp2_moviles.databinding.ActivityDetallesBinding;

public class DetallesActivity extends AppCompatActivity {

    private DetallesActivityViewModel mv;
    private ActivityDetallesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(DetallesActivityViewModel.class);
        binding = ActivityDetallesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mv.getMLibro().observe(this, new Observer<Libro>() {
            @Override
            public void onChanged(Libro libro) {
                binding.tvAutor.setText(libro.getAutor());
                binding.tvTitulo.setText(libro.getTitulo());
                binding.tvDescripcion.setText(libro.getDescripcion());
                binding.tvPaginas.setText(libro.getPaginas()+ " pags.");
                binding.ivImg.setImageResource(libro.getFoto());
            }
        });

        mv.obtenerLibro(getIntent());

        binding.btnVolver.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}