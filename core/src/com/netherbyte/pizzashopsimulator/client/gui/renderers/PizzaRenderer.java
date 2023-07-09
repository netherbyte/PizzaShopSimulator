package com.netherbyte.pizzashopsimulator.client.gui.renderers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.netherbyte.pizzashopsimulator.item.Ingredient;
import com.netherbyte.pizzashopsimulator.item.Pizza;
import com.netherbyte.pizzashopsimulator.registry.Registries;
import com.netherbyte.pizzashopsimulator.resource.AssetProvider;

public class PizzaRenderer {
    public static void render(Pizza pizza, Viewport viewport, Batch batch, float x, float y, float scale) {
        Stage stage = new Stage(viewport, batch);

        for (Ingredient ingredient : pizza.getIngredients()) {
            var id = Registries.INGREDIENTS.getIdentifier(ingredient);
            var image = new Image(AssetProvider.getTexture(id));
            image.setPosition(x, y);
            image.scaleBy(scale);
            stage.addActor(image);
        }

        stage.draw();
    }
}
