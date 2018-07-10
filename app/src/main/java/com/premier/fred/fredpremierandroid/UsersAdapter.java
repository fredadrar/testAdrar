package com.premier.fred.fredpremierandroid;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import dao.UserBean;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private ArrayList<UserBean> userBeanList;

    //Constructeur
    public UsersAdapter(ArrayList<UserBean> userBeanList) {
        this.userBeanList = userBeanList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvUser;
        LinearLayout celluleUser;

        ViewHolder(View itemView) {
            super(itemView);
            tvUser = itemView.findViewById(R.id.tv_user);
            celluleUser = itemView.findViewById(R.id.cellule_user);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user, parent, false);
        return new UsersAdapter.ViewHolder(v);
    }

    //Remplir les composants graphiques de chaque cellule
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final UserBean user = userBeanList.get(position);

        holder.tvUser.setText(user.getPseudo());

    }

    //Combien de cellules on affiche
    @Override
    public int getItemCount() {
        return userBeanList.size();
    }
}
