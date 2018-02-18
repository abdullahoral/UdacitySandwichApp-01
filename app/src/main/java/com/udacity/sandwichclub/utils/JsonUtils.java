package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        try {

            JSONObject sandwichRoot = new JSONObject(json);

            JSONObject name = sandwichRoot.optJSONObject("name");
            String mainName = name.optString("mainName");
            JSONArray alsoKnownAs = name.optJSONArray("alsoKnownAs");

            List<String> alsoKnownAsList = new ArrayList<>();
            for (int i = 0; i < alsoKnownAs.length(); i++) {
                alsoKnownAsList.add(alsoKnownAs.optString(i));
            }

            String placeOfOrigin = sandwichRoot.optString("placeOfOrigin");
            String description = sandwichRoot.optString("description");
            String image = sandwichRoot.optString("image");

            JSONArray ingredients = sandwichRoot.optJSONArray("ingredients");

            List<String> ingredientsList = new ArrayList<>();
            for (int i = 0; i < ingredients.length(); i++) {
                ingredientsList.add(ingredients.optString(i));
            }

            Sandwich sandwich = new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image, ingredientsList);

            return sandwich;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
