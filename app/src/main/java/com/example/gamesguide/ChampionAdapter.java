package com.example.gamesguide;

import android.content.Context;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class ChampionAdapter extends RecyclerView.Adapter<ChampionAdapter.ChampViewHolder> {
    String nama[], diff[], role[];
    int img[];
    Context context;
    public ChampionAdapter(Context ct, String nm[], String df[], String rl[], int im[]){
        context = ct;
        nama = nm;
        diff = df;
        role = rl;
        img = im;
    }

    @NonNull
    @Override
    public ChampionAdapter.ChampViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater lyInflater = LayoutInflater.from(parent.getContext());
        View view = lyInflater.inflate(R.layout.item_champion, parent, false);
        return new ChampViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChampionAdapter.ChampViewHolder holder, int position) {
        holder.txtNama.setText(nama[position]);
        holder.txtDiff.setText(diff[position]);
        holder.txtRole.setText(role[position]);
        holder.imgChamp.setImageResource(img[position]);
    }

    @Override
    public int getItemCount() {
        return img.length;
    }

    public class ChampViewHolder extends RecyclerView.ViewHolder{
        TextView txtNama, txtDiff, txtRole;
        ImageView imgChamp;
        ConstraintLayout clayout;

        public ChampViewHolder(View itemView){
            super(itemView);
            txtNama = itemView.findViewById(R.id.txtChampName);
            txtDiff = itemView.findViewById(R.id.txtChampDiff);
            txtRole = itemView.findViewById(R.id.txtChampRole);
            imgChamp = itemView.findViewById(R.id.imgChamp);
            clayout = itemView.findViewById(R.id.itemLayout);
        }
    }
}
