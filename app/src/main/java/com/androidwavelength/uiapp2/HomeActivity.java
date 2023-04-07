package com.androidwavelength.uiapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private String username;
    private int selectedCountryFlagImageId;

    private TextView txtUsername;
    private Button btnEditProfile;
    private ImageView imgCountryFlag;
    private int selectedCountry;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_activity);

        initViews();
        extractInput();
        setupListeners();
    }

    private void setupListeners() {
        btnEditProfile.setOnClickListener(
                new BtnEditProfileClickListener()
        );
    }

    private void extractInput() {
        Intent intent = getIntent();
        /*Bundle inputBundle = intent.getExtras();
        username = inputBundle.getString("username", "Guest");
        selectedCountryFlagImageId = inputBundle.getInt("country_flag_image_id",1);*/

        username = intent.getStringExtra(Keys.KEY_USERNAME);
        selectedCountry = intent.getIntExtra(Keys.KEY_COUNTRY_CODE, Country.COUNTRY_INDIA);

        Toast.makeText(
                this,
                username + " " + selectedCountryFlagImageId,
                Toast.LENGTH_SHORT
        ).show();

        int imageId = R.drawable.flag_india;
        String countryName = "India";
        switch (selectedCountry) {

            case Country.COUNTRY_INDIA:
                imageId = R.drawable.flag_india;
                countryName = Country.COUNTRY_NAME_INDIA;
                break;

            case Country.COUNTRY_USA:
                imageId = R.drawable.flag_usa;
                countryName = Country.COUNTRY_NAME_USA;
                break;

            default:
                imageId = R.drawable.flag_india;
                countryName = Country.COUNTRY_NAME_INDIA;
                break;
        }

        imgCountryFlag.setImageResource(imageId);
        txtUsername.setText("Welcome " + username + "," + countryName);
    }

    private void initViews() {
        imgCountryFlag = findViewById(R.id.imgCountryFlag);
        txtUsername = findViewById(R.id.txtUsername);
        btnEditProfile = findViewById(R.id.btnEditProfile);
    }

    private class BtnEditProfileClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //code to start profile activity
            Intent intentProfileActivity =
                    new Intent(HomeActivity.this, ProfileActivity.class);
            intentProfileActivity.putExtra(
                    Keys.KEY_USERNAME,
                    username
            );

            //startActivity(intentProfileActivity);
            startActivityForResult(
                    intentProfileActivity,
                    1
            );

        }
    }

    @Override
    protected void onActivityResult(
            int requestCode,
            int resultCode,
            @Nullable Intent data
    ) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data == null) {
            return;
        }

        username = data.getStringExtra(Keys.KEY_USERNAME);
        txtUsername.setText(username);
        selectedCountry = data.getIntExtra(Keys.KEY_COUNTRY_CODE, Country.COUNTRY_INDIA);
        int flagImageId = R.drawable.flag_india;

        switch (selectedCountry) {
            case Country.COUNTRY_INDIA:
                flagImageId = R.drawable.flag_india;
                break;
            case Country.COUNTRY_USA:
                flagImageId = R.drawable.flag_usa;
                break;
            case Country.COUNTRY_NORWAY:
                flagImageId = R.drawable.flag_norway;
                break;
            case Country.COUNTRY_CANADA:
                flagImageId = R.drawable.flag_canada;
                break;
            case Country.COUNTRY_ALASKA:
                flagImageId = R.drawable.flag_alaska;
                break;
            case Country.COUNTRY_GREENLAND:
                flagImageId = R.drawable.flag_greenland;
                break;
        }
        imgCountryFlag.setImageResource(flagImageId);
    }
}
