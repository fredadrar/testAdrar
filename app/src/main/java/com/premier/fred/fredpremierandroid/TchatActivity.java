package com.premier.fred.fredpremierandroid;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import dao.PostBean;
import dao.UserBean;

public class TchatActivity extends AppCompatActivity implements View.OnClickListener {

    private UserBean user;
    private ArrayList<PostBean> messageBeanList;
    private ArrayList<UserBean> userBeanList;

    private MessagesAdapter messagesRvAdapter;
    private UsersAdapter usersRvAdapter;

    private Button btSend;
    private TextView inputBar;

    private SendMessageAT sendMessageAT = null;
    private GetMessagesAT getMessagesAT = null;
    private SendUserAT sendUserAT = null;
    private GetUsersAT getUsersAT = null;

    //Composant graphique afficheur de RecyclerView.Adapter
    private RecyclerView rvMessages;
    private RecyclerView rvUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tchat);

        //Création des listes
        messageBeanList = new ArrayList<>();
        userBeanList = new ArrayList<>();

        //Création de l'objet user et envoi au WS
        Bundle extras = getIntent().getExtras();
        user = new UserBean();
        user.setPseudo(extras.getString("login"));
        user.setLastRequestTime(Calendar.getInstance().getTimeInMillis());
        sendUserAT = new SendUserAT();
        sendUserAT.execute(user);

        //Recycler View
        rvMessages = findViewById(R.id.rv_messages);
        rvUsers = findViewById(R.id.rv_users);

        //Instanciation des Adapters
        messagesRvAdapter = new MessagesAdapter(messageBeanList, user);
        usersRvAdapter = new UsersAdapter(userBeanList);

        //Injection des Adapters dans leurs RecyclerView
        rvMessages.setAdapter(messagesRvAdapter);
        rvUsers.setAdapter(usersRvAdapter);

        //Réglage : Est ce qu'on affiche ligne par ligne, ou sous forme de tableau à 2 colonnes
        rvMessages.setLayoutManager(new LinearLayoutManager(this));
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
//        rv.setLayoutManager(new GridLayoutManager(this, 2));

        // Réglage : type d’animation qu’on utilise
        rvMessages.setItemAnimator(new DefaultItemAnimator());
        rvUsers.setItemAnimator(new DefaultItemAnimator());

        inputBar = findViewById(R.id.tv_input);
        btSend = findViewById(R.id.bt_send);
        btSend.setOnClickListener(this);

        refreshDisplay();
    }

    @Override
    public void onClick(View v) {
        if (v == btSend) {
            if (sendMessageAT == null || sendMessageAT.getStatus() == AsyncTask.Status.FINISHED) {

                //Création de l'objet message et envoi au WS
                PostBean message = new PostBean();
                message.setContenu(inputBar.getText().toString());
                message.setHeure(Calendar.getInstance().getTimeInMillis());
                message.setUser(user);
                System.out.println(message.getContenu());
                sendMessageAT = new SendMessageAT();
                sendMessageAT.execute(message);
                inputBar.setText("");
            }
            refreshDisplay();
            rvMessages.scrollToPosition(messageBeanList.size() - 1);
        }
    }

    private void refreshDisplay() {
        if (getMessagesAT == null || getMessagesAT.getStatus() == AsyncTask.Status.FINISHED) {
            getMessagesAT = new GetMessagesAT();
            getMessagesAT.execute();
        }
        if (getUsersAT == null || getUsersAT.getStatus() == AsyncTask.Status.FINISHED) {
            getUsersAT = new GetUsersAT();
            getUsersAT.execute();
        }
    }

    //Async Tasks :
    private class SendMessageAT extends AsyncTask {
        protected Object doInBackground(Object[] objects) {
            try {
                WebServiceUtils.sendMessage((PostBean) objects[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Object object) {
        }
    }

    private class SendUserAT extends AsyncTask {
        protected Object doInBackground(Object[] objects) {
            try {
                WebServiceUtils.sendUser((UserBean) objects[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Object object) {
        }
    }

    private class GetUsersAT extends AsyncTask {
        protected Object doInBackground(Object[] objects) {
            try {
                return WebServiceUtils.getUsers();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Object resultat) {
            userBeanList.clear();
            userBeanList.addAll((Collection<? extends UserBean>) resultat);
            usersRvAdapter.notifyDataSetChanged();
        }
    }

    private class GetMessagesAT extends AsyncTask {
        protected Object doInBackground(Object[] objects) {
            try {
                return WebServiceUtils.getMessages();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Object resultat) {
            messageBeanList.clear();
            messageBeanList.addAll((Collection<? extends PostBean>) resultat);
            messagesRvAdapter.notifyItemInserted(messageBeanList.size() - 1);
            rvMessages.scrollToPosition(messageBeanList.size() - 1);
        }
    }
}
