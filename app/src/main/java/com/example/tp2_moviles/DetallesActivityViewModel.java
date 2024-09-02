package com.example.tp2_moviles;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class DetallesActivityViewModel extends AndroidViewModel {

    private MutableLiveData<Libro> mLibro;

    public DetallesActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Libro> getMLibro() {
        if (mLibro == null) {
            mLibro = new MutableLiveData<>();
        }
        return mLibro;
    }

    public void obtenerLibro(Intent intent) {
        Bundle bundle = intent.getBundleExtra("libro");
        if (bundle != null) {
            Libro libro = (Libro) bundle.getSerializable("libro");

            if (libro != null) {
                mLibro.setValue(libro);
            }
        }
    }
}
