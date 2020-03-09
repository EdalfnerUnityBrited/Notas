package com.example.notas.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.notas.NuevaNotaViewModel;
import com.example.notas.db.entity.NotaEntity;
import com.example.notas.R;

import java.util.List;


public class MyNotaRecyclerViewAdapter extends RecyclerView.Adapter<MyNotaRecyclerViewAdapter.ViewHolder> {

    private List<NotaEntity> mValues;
    private Context ctx;
    private NuevaNotaViewModel viewModel;

    public MyNotaRecyclerViewAdapter(List<NotaEntity> items, Context ctx) {
        mValues = items;
        this.ctx = ctx;
        viewModel= ViewModelProviders.of((AppCompatActivity)ctx).get(NuevaNotaViewModel.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_nota, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.textViewTitulo.setText(holder.mItem.getTitulo());
        holder.textViewContenido.setText(holder.mItem.getContenido());

        if (holder.mItem.isFavorita())
        {
            holder.imageViewFav.setImageResource(R.drawable.ic_star_black_24dp);
        }

        holder.imageViewFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (holder.mItem.isFavorita()){
                holder.mItem.setFavorita(false);
                holder.imageViewFav.setImageResource(R.drawable.ic_star_border_black_24dp);
            }
            else {
                holder.mItem.setFavorita(true);
                holder.imageViewFav.setImageResource(R.drawable.ic_star_black_24dp);

            }
            viewModel.updateNota(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setNuevasNotas(List<NotaEntity> nuevasNotas){
        this.mValues=nuevasNotas;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textViewTitulo;
        public final TextView textViewContenido;
        public  final ImageView imageViewFav;
        public NotaEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
           textViewTitulo= view.findViewById(R.id.textViewTitulo);
           textViewContenido= view.findViewById(R.id.textViewContenido);
           imageViewFav= view.findViewById(R.id.imageViewFavorito);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewTitulo.getText() + "'";
        }
    }
}
