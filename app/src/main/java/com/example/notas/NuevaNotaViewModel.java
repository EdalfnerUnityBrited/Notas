package com.example.notas;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.notas.db.entity.NotaEntity;

import java.util.List;

public class NuevaNotaViewModel extends AndroidViewModel {
    private LiveData<List<NotaEntity>> allNotas;
    private NotasRepository notasRepository;


    public NuevaNotaViewModel (Application application){
        super(application);

        notasRepository=new NotasRepository(application);
        allNotas=notasRepository.getAll();

    }

    public LiveData<List<NotaEntity>>getAllNotas(){
        return allNotas;
    }
    public  void insertarNota(NotaEntity notaEntity){
        notasRepository.insert(notaEntity);
    }
    public void updateNota(NotaEntity notaActualizarEntity){
        notasRepository.update(notaActualizarEntity);
    }
}
