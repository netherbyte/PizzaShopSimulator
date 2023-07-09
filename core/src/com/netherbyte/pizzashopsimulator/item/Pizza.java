package com.netherbyte.pizzashopsimulator.item;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private List<Ingredient> ingredients;

    public Pizza() {
        ingredients = new ArrayList<>();
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void setIngredient(int index, Ingredient ingredient) {
        ingredients.set(index, ingredient);
    }

    public void removeIngredient(int index) {
        ingredients.remove(index);
    }

    public void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
    }

    public int getIngredientStackSize() {
        return ingredients.size();
    }
}
