package com.premier.fred.fredpremierandroid;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerViewExempleActivity extends AppCompatActivity implements View.OnClickListener, OnEleveClickListenerInterface {

    //Données
    private ArrayList<EleveBean> eleveList;
    private EleveAdapter eleveRvAdapter;
    private Button bt_ajout;
    private Button bt_charger;
    private ProgressBar progressBar;
    private ProgressBar progressBar2;
    private int progress;

    private MonAsyncTask chargementEleveAT = null;

    //Composant graphique afficheur de RecyclerView.Adapter
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_exemple);

        //Cercle de loading
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        //Barre de loading
        progressBar2 = findViewById(R.id.progressBar2);
        progressBar2.setVisibility(View.GONE);

        bt_ajout = findViewById(R.id.bt_ajout);
        bt_ajout.setOnClickListener(this);

        bt_charger = findViewById(R.id.bt_charger);
        bt_charger.setOnClickListener(this);

        //Création de la liste
        eleveList = new ArrayList<>();

        //Instanciation d’un EleveAdapter
        eleveRvAdapter = new EleveAdapter(eleveList);

        //Recycler View
        rv = findViewById(R.id.rv);

        // L’adapter que l’on souhaite afficher
        rv.setAdapter(eleveRvAdapter);

        //Réglage : Est ce qu'on affiche ligne par ligne, ou sous forme de tableau à 2 colonnes
        rv.setLayoutManager(new LinearLayoutManager(this));
//        rv.setLayoutManager(new GridLayoutManager(this, 2));

        // Réglage : type d’animation qu’on utilise
        rv.setItemAnimator(new DefaultItemAnimator());

        eleveRvAdapter.setOnEleveClickListener(this);
    }

    //Async Task interne à l'activity
    private class MonAsyncTask extends AsyncTask {
        private EleveBean resultat;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = 0;
            progressBar.setVisibility(View.VISIBLE);
//            progressBar2.setVisibility(View.VISIBLE);
        }

        // Garantie en dehors de l’UIThread
        protected Object doInBackground(Object[] objects) {
            // TRAITEMENT LONG MAIS INTERDIT DE TOUCHER AUX COMPOSANTS GRAPHIQUES

//            for (; progress <= 100; progress++) {
//                SystemClock.sleep(50);
//                progress++;
//                publishProgress(progress);
//            }
            resultat = WebServiceUtils.loadEleveFromWeb();

            return null;
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
//            progressBar2.setProgress(progress);
        }

        // Dans UIThread
        protected void onPostExecute(Object object) {
            // Méthode appelée quand le doInBackground est terminé
            //On peut modifier les composants graphiques
            progressBar.setVisibility(View.INVISIBLE);
//            progressBar2.setVisibility(View.GONE);
//            progress = 0;
//            progressBar2.setProgress(progress);

            eleveList.add(0, resultat);
            refreshDisplay();
            Toast.makeText(bt_charger.getContext(), "Toto ajouté", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == bt_ajout) {
            eleveList.add(0, new EleveBean("Elève" + eleveList.size(), "Prénom" + eleveList.size()));
            refreshDisplay();
            rv.scrollToPosition(0);
        }
        if (v == bt_charger) {
            if (chargementEleveAT == null || chargementEleveAT.getStatus() == AsyncTask.Status.FINISHED) {
                chargementEleveAT = new MonAsyncTask();
                chargementEleveAT.execute();
            }

        }
    }

    //Refresh après Ajout
    private void refreshDisplay() {
//        eleveRvAdapter.notifyDataSetChanged();
        eleveRvAdapter.notifyItemInserted(0);
    }
    //Refresh après Delete
    private void refreshDisplay(int deleted) {
        eleveRvAdapter.notifyItemRemoved(deleted);
    }
    //Refresh après Déplacement
    private void refreshDisplay(int depart, int arrivee) {
        eleveRvAdapter.notifyItemMoved(depart, arrivee);
    }

    @Override
    public void onEleveClick(EleveBean eleve) {
//        Toast.makeText(this, "Click sur " + eleve.getNom(), Toast.LENGTH_SHORT).show();
        int posDepart = eleveList.indexOf(eleve);
        eleveList.remove(posDepart);
        eleveList.add(0, eleve);

        refreshDisplay(posDepart, 0);
        rv.scrollToPosition(eleveList.indexOf(eleve));
    }

    @Override
    public void onEleveLongClick(EleveBean eleve) {
//        Toast.makeText(v.getContext(), "LongClick sur " + eleve.getNom(), Toast.LENGTH_SHORT).show();
        int posDeleted = eleveList.indexOf(eleve);
        eleveList.remove(posDeleted);

        refreshDisplay(posDeleted);
    }
}
