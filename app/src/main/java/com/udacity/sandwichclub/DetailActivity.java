package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        TextView also_known_tv = findViewById(R.id.also_known_tv);
        TextView ingredients_tv = findViewById(R.id.ingredients_tv);
        TextView origin_tv = findViewById(R.id.origin_tv);
        TextView description_tv = findViewById(R.id.description_tv);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

        if ( TextUtils.isEmpty(sandwich.getAlsoKnownAs().toString().substring(1, sandwich.getAlsoKnownAs().toString().length() - 1))) {
            also_known_tv.setVisibility(View.GONE);
        } else {
            also_known_tv.setText(sandwich.getAlsoKnownAs().toString().substring(1, sandwich.getAlsoKnownAs().toString().length() - 1));
        }

        if ( TextUtils.isEmpty(sandwich.getIngredients().toString().substring(1, sandwich.getIngredients().toString().length() - 1))) {
            ingredients_tv.setVisibility(View.GONE);
        } else {
            ingredients_tv.setText(sandwich.getIngredients().toString().substring(1, sandwich.getIngredients().toString().length() - 1));
        }

        if ( TextUtils.isEmpty(sandwich.getPlaceOfOrigin())) {
            origin_tv.setVisibility(View.GONE);
        } else {
            origin_tv.setText(sandwich.getPlaceOfOrigin());
        }

        if ( TextUtils.isEmpty(sandwich.getDescription())) {
            description_tv.setVisibility(View.GONE);
        } else {
            description_tv.setText(sandwich.getDescription());
        }

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

    }
}
