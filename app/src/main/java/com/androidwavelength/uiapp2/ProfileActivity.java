package com.androidwavelength.uiapp2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private EditText edtUsername;
    private ImageView img1, img2, img3, img4;
    private Button btnSaveProfile;
    private int selectedCountry = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initViews();
        setUpListeners();
    }

    private void setUpListeners() {
        btnSaveProfile.setOnClickListener(new BtnSaveProfileClickListener());

        ImageViewClickListener imageViewClickListener =
                new ImageViewClickListener();
        img1.setOnClickListener(imageViewClickListener);
        img2.setOnClickListener(imageViewClickListener);
        img3.setOnClickListener(imageViewClickListener);
        img4.setOnClickListener(imageViewClickListener);
    }

    private void initViews() {
        edtUsername = findViewById(R.id.edtUsername);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        btnSaveProfile = findViewById(R.id.btnSaveProfile);
    }

    private class ImageViewClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            view.setBackgroundColor(Color.BLACK);

            switch (view.getId()) {
                case R.id.img1:
                    selectedCountry = Country.COUNTRY_ALASKA;
                    break;
                case R.id.img2:
                    selectedCountry = Country.COUNTRY_CANADA;
                    break;
                case R.id.img3:
                    selectedCountry = Country.COUNTRY_GREENLAND;
                    break;
                case R.id.img4:
                    selectedCountry = Country.COUNTRY_NORWAY;
                    break;
            }
        }
    }

    private class BtnSaveProfileClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            Intent resultIntent = new Intent();
            resultIntent.putExtra(Keys.KEY_USERNAME, edtUsername.getText().toString());
            resultIntent.putExtra(Keys.KEY_COUNTRY_CODE, selectedCountry);

            setResult(1, resultIntent);

            finish();
        }
    }
}
