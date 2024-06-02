package com.example.amasappnew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    private List<Receta> data;
    public Adapter(Context context, List<Receta> data) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Receta receta = data.get(position);
        holder.textTitle.setText(receta.getNombre());
        holder.textDuration.setText(receta.getDuracion());
        holder.imageView.setImageResource(receta.getImagen());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addReceta(Receta receta) {
        data.add(receta);
        notifyItemInserted(data.size() - 1);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textTitle,textDuration;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textDuration = itemView.findViewById(R.id.textDuration);
            imageView = itemView.findViewById(R.id.imageView2);
        }
    }

}
