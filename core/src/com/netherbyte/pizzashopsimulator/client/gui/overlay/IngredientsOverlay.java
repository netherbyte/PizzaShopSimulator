package com.netherbyte.pizzashopsimulator.client.gui.overlay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.netherbyte.pizzashopsimulator.item.Ingredient;
import com.netherbyte.pizzashopsimulator.item.Items;
import com.netherbyte.pizzashopsimulator.item.Pizza;
import com.netherbyte.pizzashopsimulator.registry.Registries;
import com.netherbyte.pizzashopsimulator.resource.AssetProvider;
import com.netherbyte.pizzashopsimulator.save.Saves;
import com.netherbyte.pizzashopsimulator.util.PointerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.netherbyte.pizzashopsimulator.SharedConstants.DEFAULT_HEIGHT;
import static com.netherbyte.pizzashopsimulator.SharedConstants.DEFAULT_WIDTH;

public class IngredientsOverlay {
    private static final Logger logger = LoggerFactory.getLogger(IngredientsOverlay.class);

    public Stage stage;
    private Viewport viewport;

    public Image doughIcon;
    public Image sauceIcon;
    public Image cheeseIcon;
    public Image pepperoniIcon;

    private Table table;
    private List<Ingredient> rows;

    public IngredientsOverlay(SpriteBatch batch, Pizza pizza) {

        viewport = new FitViewport(DEFAULT_WIDTH, DEFAULT_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, batch);

        table = new Table();
        table.left();
        table.setFillParent(true);

        doughIcon = new Image(AssetProvider.getTexture(Registries.INGREDIENTS.getIdentifier(Items.DOUGH)));
        sauceIcon = new Image(AssetProvider.getTexture(Registries.INGREDIENTS.getIdentifier(Items.SAUCE)));
        cheeseIcon = new Image(AssetProvider.getTexture(Registries.INGREDIENTS.getIdentifier(Items.CHEESE)));
        pepperoniIcon = new Image(AssetProvider.getTexture(Registries.INGREDIENTS.getIdentifier(Items.PEPPERONI)));

        rows = new ArrayList<>();

        table.add(doughIcon);
        rows.add(Items.DOUGH);
        table.row();
        table.add(sauceIcon);
        rows.add(Items.SAUCE);
        table.row();
        table.add(cheeseIcon);
        rows.add(Items.CHEESE);
        table.row();
        table.add(pepperoniIcon);
        rows.add(Items.PEPPERONI);

        stage.addActor(table);
    }

    public Ingredient checkButtons() {
        for (int row = 0; row < table.getRows(); row++) {
            int r = 3 - table.getRow(PointerUtil.getY());
            boolean isInBounds = PointerUtil.getX() < table.getColumnWidth(0);
            if (isInBounds && -1 < r && r < rows.size() && PointerUtil.isButtonJustPressed(0)) {
                return rows.get(r);
            }
        }
        return null;
    }
}
