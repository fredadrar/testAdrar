package com.premier.fred.fredpremierandroid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Déclaration des composants graphiques
    private Button bt_valider;
    private Button bt_annuler;
    private Button bt_secondActivity;
    private TextView textField;
    private RadioButton radio_jaime;
    private RadioButton radio_jaimepas;
    private RadioGroup radioGroup;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("hello titre");

        bt_valider = findViewById(R.id.bt_valider);
        bt_annuler = findViewById(R.id.bt_annuler);
        bt_secondActivity = findViewById(R.id.bt_secondActivity);
        textField = findViewById(R.id.textField);
        radio_jaime = findViewById(R.id.radio_jaime);
        radio_jaimepas = findViewById(R.id.radio_jaimepas);
        radioGroup = findViewById(R.id.radioGroup);
        image = findViewById(R.id.image);

        bt_valider.setOnClickListener(this);
        bt_annuler.setOnClickListener(this);
        bt_secondActivity.setOnClickListener(this);

        Log.w("Test Log Main", "activity: main_activity ");
    }

    @Override
    public void onClick(View v) {
        if (v == bt_annuler) {
//            Toast.makeText(this, "click annuler", Toast.LENGTH_SHORT).show();
            radioGroup.clearCheck();
            textField.setText("");
            image.setImageResource(R.drawable.ic_backup_black_48dp);
            image.setColorFilter(Color.RED);

        } else if (v == bt_valider) {
//            Toast.makeText(this, "click valider", Toast.LENGTH_SHORT).show();
            if (radio_jaime.isChecked()) {
                textField.setText(radio_jaime.getText());
            } else if (radio_jaimepas.isChecked()) {
                textField.setText(radio_jaimepas.getText());
            } else {
                textField.setText(R.string.noradio);
            }
            image.setImageResource(R.drawable.ic_check_circle_black_48dp);
            image.setColorFilter(Color.GREEN);
        } else if (v == bt_secondActivity) {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
//       Tuer l’activité courante
//            finish();
        }
    }
}
