package com.androidwavelength.uiapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtMessage;
    private EditText edtUsername, edtPassword;
    private Button btnLogin;
    private ImageView imgInd, imgUsa;

    private int selectedCountry = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setUpListeners();
    }

    private void setUpListeners(){
        //setup listeners
        btnLogin.setOnClickListener(new BtnLoginClickListener());

        CountryFlagsImageviewClickListener countryFlagsImageviewClickListener
                = new CountryFlagsImageviewClickListener();
        imgUsa.setOnClickListener(countryFlagsImageviewClickListener);
        imgInd.setOnClickListener(countryFlagsImageviewClickListener);
    }

    private void initViews() {
        //code to obtain references to the view objects
        txtMessage = findViewById(R.id.txtMessage);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        imgInd = findViewById(R.id.imgIndia);
        imgUsa = findViewById(R.id.imgUsa);
    }

    private class BtnLoginClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(edtUsername.getText().toString().equals("bitcode") &&
                    edtPassword.getText().toString().equals("bitcode")) {
                txtMessage.setText("Success");

                //code to start home activity
                //code to let android know that we want to start home activity

                Intent homeActivityIntent =
                        new Intent(
                                MainActivity.this,
                                HomeActivity.class);
                homeActivityIntent.putExtra(
                        Keys.KEY_USERNAME,
                        edtUsername.getText().toString()
                );
                homeActivityIntent.putExtra(
                        Keys.KEY_COUNTRY_CODE,
                        selectedCountry
                );
                startActivity(homeActivityIntent);

                finish();
            } else {
                txtMessage.setText("Failed");
            }
        }
    }

    private class CountryFlagsImageviewClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //if(view == imgInd) {
            if(view.getId() == R.id.imgIndia) {
                imgInd.setBackgroundColor(Color.BLACK);
                imgUsa.setBackgroundColor(Color.WHITE);
                selectedCountry = 1;
            } else {
                selectedCountry = 2;
                imgInd.setBackgroundColor(Color.WHITE);
                imgUsa.setBackgroundColor(Color.BLACK);
            }
        }
    }
}