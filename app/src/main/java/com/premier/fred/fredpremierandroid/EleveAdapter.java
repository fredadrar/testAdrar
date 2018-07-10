package com.premier.fred.fredpremierandroid;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class EleveAdapter extends RecyclerView.Adapter<EleveAdapter.ViewHolder> {

    private ArrayList<EleveBean> eleveBeanList;
    private OnEleveClickListenerInterface eleveClickListener;

    //Constructeur
    public EleveAdapter(ArrayList<EleveBean> eleveBeanList) {
        this.eleveBeanList = eleveBeanList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView ec_tv_nom, ec_tv_prenom;
        ImageView ec_iv;
        LinearLayout cellule;

        ViewHolder(View itemView) {
            super(itemView);
            ec_tv_nom = itemView.findViewById(R.id.textViewNom);
            ec_tv_prenom = itemView.findViewById(R.id.textViewPrenom);
            ec_iv = itemView.findViewById(R.id.imageViewEleve);
            cellule = itemView.findViewById(R.id.root);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_eleve, parent, false);
        return new EleveAdapter.ViewHolder(v);
    }

    //Remplir les composants graphiques de chaque cellule
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //L'élève correspondant à la ligne
        final EleveBean eleve = eleveBeanList.get(position);
        holder.ec_tv_nom.setText(eleve.getNom());
        holder.ec_tv_prenom.setText(eleve.getPrenom());

        Glide.with(holder.ec_iv.getContext())
                .load(eleve.getUrl())
                .into(holder.ec_iv);

        //Click : remonte l'élève en haut de la liste
        holder.cellule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "Click sur " + eleve.getNom(), Toast.LENGTH_SHORT).show();
                if (eleveClickListener != null) {
                    eleveClickListener.onEleveClick(eleve);
                }
            }
        });
        //Long click : supprime l'élève
        holder.cellule.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                Toast.makeText(v.getContext(), "LongClick sur " + eleve.getNom(), Toast.LENGTH_SHORT).show();
                if (eleveClickListener != null) {
                    eleveClickListener.onEleveLongClick(eleve);
                }
                return true;
            }
        });
    }

    //Combien de cellules on affiche
    @Override
    public int getItemCount() {
        return eleveBeanList.size();
    }

    public void setOnEleveClickListener(OnEleveClickListenerInterface eleveClickListener) {
        this.eleveClickListener = eleveClickListener;
    }

}
