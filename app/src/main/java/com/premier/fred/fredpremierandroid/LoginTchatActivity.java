package com.premier.fred.fredpremierandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginTchatActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btLogin;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_tchat);

        btLogin = findViewById(R.id.bt_login);
        btLogin.setOnClickListener(this);

        tvLogin = findViewById(R.id.tv_login);
    }

    @Override
    public void onClick(View v) {
        String login = tvLogin.getText().toString();
        if (v == btLogin && !login.equals("")) {
            Intent intent = new Intent(this, TchatActivity.class);
            intent.putExtra("login", login);
            startActivity(intent);
        }
    }
}
