package com.premier.fred.fredpremierandroid;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WebViewExempleActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView wv;
    private TextView tvUrl;
    private TextView tvHtml;
    private Button btEnvoyer;
    private String url;
    private AsyncTaskGetRequest chargementSourceHtml;

    private Button btTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_exemple);

        wv = findViewById(R.id.wv);
        tvUrl = findViewById(R.id.tv_url);
        tvHtml = findViewById(R.id.tv_html);

        btEnvoyer = findViewById(R.id.bt_envoyer);
        btEnvoyer.setOnClickListener(this);

        btTest = findViewById(R.id.bt_test);
        btTest.setOnClickListener(this);

        //Sinon cela lance le navigateur du téléphone
        wv.setWebViewClient(new WebViewClient());
        //Active le Javascript (Attention aux performances)
        WebSettings webviewSettings = wv.getSettings();
        webviewSettings.setJavaScriptEnabled(true);

//        wv.loadUrl("http://www.google.fr");
//        wv.loadData("<html><body>Bonjour !</body></html>", "text/html", "UTF-8");

    }

    private class AsyncTaskGetRequest extends AsyncTask {
        private String resultat;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected Object doInBackground(Object[] objects) {
//            url = tvUrl.getText().toString();
            url = "http://192.168.0.10:8888/chatServeur/rest/WebService/getPosts";
            try {
                resultat = OkHttpUtils.sendGetOkHttpRequest(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        // Dans UIThread
        protected void onPostExecute(Object object) {
            tvHtml.setText(resultat);
            wv.loadUrl(url);
        }
    }

    public static boolean isInternetConnexion(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onClick(View v) {
        if (v == btEnvoyer && isInternetConnexion(this)) {

            if (chargementSourceHtml == null || chargementSourceHtml.getStatus() == AsyncTask.Status.FINISHED) {
                chargementSourceHtml = new AsyncTaskGetRequest();
                chargementSourceHtml.execute();
            } else {
                Toast.makeText(this, "Pas de connexion internet !", Toast.LENGTH_SHORT).show();
            }
        } else if (v == btTest) {
            try {
                chargementSourceHtml = new AsyncTaskGetRequest();
                chargementSourceHtml.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
