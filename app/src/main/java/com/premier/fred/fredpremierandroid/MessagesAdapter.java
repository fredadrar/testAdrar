package com.premier.fred.fredpremierandroid;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import dao.PostBean;
import dao.UserBean;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {

    private ArrayList<PostBean> messageBeanList;
    private UserBean user;

    //Constructeur
    public MessagesAdapter(ArrayList<PostBean> messageBeanList, UserBean user) {
        this.messageBeanList = messageBeanList;
        this.user = user;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvLeft, tvRight;
        LinearLayout celluleTchat;

        ViewHolder(View itemView) {
            super(itemView);
            tvLeft = itemView.findViewById(R.id.tv_tchat_left);
            tvRight = itemView.findViewById(R.id.tv_tchat_right);
            celluleTchat = itemView.findViewById(R.id.root);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_tchat, parent, false);
        return new MessagesAdapter.ViewHolder(v);
    }

    //Remplir les composants graphiques de chaque cellule
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final PostBean message = messageBeanList.get(position);
        String text = message.getUser().getPseudo() + " : " + message.getContenu();

        holder.tvLeft.setText(text);
    }

    //Combien de cellules on affiche
    @Override
    public int getItemCount() {
        return messageBeanList.size();
    }
}
