package com.example.tp2_moviles;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class MainActivityViewModel extends AndroidViewModel {

    private ArrayList<Libro> libros;
    private MutableLiveData<String> mMensaje;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        libros = new ArrayList<>();
    }

    public LiveData<String> getMMensaje() {
        if (mMensaje == null) {
            mMensaje = new MutableLiveData<>();
        }
        return mMensaje;
    }

    public void cargarLibros() {
        libros.add(new Libro("Cien años de soledad", "Gabriel García Márquez", 471, 1967, R.drawable.cien_anos, "Realismo mágico", "Una obra maestra que narra la historia de la familia Buendía en el pueblo ficticio de Macondo."));
        libros.add(new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", 863, 1605, R.drawable.don_quijote, "Novela", "La historia de un caballero idealista que pierde el juicio y vive aventuras imaginarias."));
        libros.add(new Libro("La sombra del viento", "Carlos Ruiz Zafón", 565, 2001, R.drawable.la_sombra, "Misterio", "Un joven encuentra un libro olvidado en un cementerio de libros y descubre un misterio que lo cambiará todo."));
        libros.add(new Libro("El amor en los tiempos del cólera", "Gabriel García Márquez", 422, 1985, R.drawable.el_amor, "Romance", "Una historia de amor que perdura a través de los años y las dificultades."));
        libros.add(new Libro("La casa de los espíritus", "Isabel Allende", 490, 1982, R.drawable.la_casa, "Realismo mágico", "Una saga familiar que abarca varias generaciones y mezcla la realidad con lo sobrenatural."));
        libros.add(new Libro("Rayuela", "Julio Cortázar", 735, 1963, R.drawable.rayuela, "Novela experimental", "Una obra innovadora que permite diferentes lecturas y explora la búsqueda existencial del protagonista."));
    }

    public void buscarLibro(String titulo) {
        Libro libroEncontrado = null;

        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                libroEncontrado = new Libro(libro.getTitulo(), libro.getAutor(), libro.getPaginas(), libro.getAnio(), libro.getFoto(), libro.getGenero(), libro.getDescripcion());
                break;
            }
        }

        if(libroEncontrado == null) {
            mMensaje.setValue("No se encuentra el libro");
        } else {
            Bundle bundle = new Bundle();
            bundle.putSerializable("libro", libroEncontrado);
            Intent intent = new Intent(getApplication(), DetallesActivity.class);
            intent.putExtra("libro", bundle);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplication().startActivity(intent);
        }
    }
}
