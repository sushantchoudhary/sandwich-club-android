package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String sandwichJson) throws JSONException {
        String ingredientName;
        String aliasName;

        Sandwich newSandwich = new Sandwich();
        JSONObject sandwichData = new JSONObject(sandwichJson);
        JSONObject sandwichName = sandwichData.getJSONObject("name");

        // Set mainName
        newSandwich.setMainName(sandwichName.getString("mainName"));

        //Set Description
        newSandwich.setDescription(sandwichData.getString("description"));

        // Set image url
        newSandwich.setImage(sandwichData.getString("image"));

        // Set list of ingredients
        JSONArray ingredientList = sandwichData.getJSONArray("ingredients");
        List<String> ingredients = new ArrayList<>(ingredientList.length());
        for (int i = 0; i < ingredientList.length(); i++) {
            ingredientName = ingredientList.getString(i);
            if(ingredientName != null) {
                ingredients.add(ingredientName);
            }
        }
        newSandwich.setIngredients(ingredients);

        //Set list of aliases
        JSONArray aliasNames = sandwichName.getJSONArray("alsoKnownAs");
        List<String> aliases = new ArrayList<>(aliasNames.length());
        for(int i = 0; i < aliasNames.length(); i++) {
            aliasName = aliasNames.getString(i);
            if(aliasName != null) {
                aliases.add(aliasName);
            }
        }
        newSandwich.setAlsoKnownAs(aliases);

        //Set place of origin
        newSandwich.setPlaceOfOrigin(sandwichData.getString("placeOfOrigin"));
        return newSandwich;
    }
}
