package com.netherbyte.pizzashopsimulator.client.gui.renderers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.netherbyte.pizzashopsimulator.item.Ingredient;
import com.netherbyte.pizzashopsimulator.item.Pizza;
import com.netherbyte.pizzashopsimulator.registry.Registries;
import com.netherbyte.pizzashopsimulator.client.resource.AssetProvider;

public class PizzaRenderer {
    public static void render(Pizza pizza, Viewport viewport, Batch batch, float x, float y, float scale) {
        generateStage(pizza, viewport, batch, x, y, scale).draw();
    }

    public static Stage generateStage(Pizza pizza, Viewport viewport, Batch batch, float x, float y, float scale) {
        Stage stage = new Stage(viewport, batch);

        for (Ingredient ingredient : pizza.getIngredients()) {
            var id = Registries.INGREDIENTS.getIdentifier(ingredient);
            var image = new Image(AssetProvider.getTexture(id));
            image.setPosition(x, y);
            image.scaleBy(scale);
            stage.addActor(image);
        }

        return stage;
    }

    public static Vector2 getDimensions(Pizza pizza, Viewport viewport, Batch batch) {
        Stage stage = new Stage(viewport, batch);

        for (Ingredient ingredient : pizza.getIngredients()) {
            var id = Registries.INGREDIENTS.getIdentifier(ingredient);
            var image = new Image(AssetProvider.getTexture(id));
            stage.addActor(image);
            return new Vector2(image.getWidth(), image.getHeight());
        }

        return new Vector2(stage.getWidth(), stage.getHeight());
    }
}
