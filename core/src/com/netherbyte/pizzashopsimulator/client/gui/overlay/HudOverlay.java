package com.netherbyte.pizzashopsimulator.client.gui.overlay;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.netherbyte.pizzashopsimulator.save.Saves;

import static com.badlogic.gdx.Gdx.files;
import static com.netherbyte.pizzashopsimulator.SharedConstants.DEFAULT_HEIGHT;
import static com.netherbyte.pizzashopsimulator.SharedConstants.DEFAULT_WIDTH;

public class HudOverlay {
    public Stage stage;
    private Viewport viewport;

    private int sales;
    private float cash;
    private int pendingSales;

    Label salesLabel;
    Label cashLabel;
    Label pendingSalesLabel;
    Label salesTextLabel;
    Label cashTextLabel;
    Label pendingSalesTextLabel;

    public HudOverlay(SpriteBatch batch) {
        sales = Saves.getCurrentSave().getSales();
        cash = Saves.getCurrentSave().getCash();
        pendingSales = Saves.getCurrentSave().getPendingSalesCount();

        viewport = new FitViewport(DEFAULT_WIDTH / 1.5f, DEFAULT_HEIGHT / 1.5f, new OrthographicCamera());
        stage = new Stage(viewport, batch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        salesLabel = new Label(String.valueOf(sales), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        cashLabel = new Label("$" + cash, new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        pendingSalesLabel = new Label(String.valueOf(pendingSales), new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        salesTextLabel = new Label("Sales", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        cashTextLabel = new Label("Cash", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        pendingSalesTextLabel = new Label("Pending Sales", new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        table.add(salesTextLabel);
        table.add(cashTextLabel);
        table.add(pendingSalesTextLabel);

        table.row();
        table.add(salesLabel).expandX().padTop(10);
        table.add(cashLabel).expandX().padTop(10);
        table.add(pendingSalesLabel).expandX().padTop(10);

        stage.addActor(table);
    }

    public void updateAmounts() {
        sales = Saves.getCurrentSave().getSales();
        cash = Saves.getCurrentSave().getCash();
        pendingSales = Saves.getCurrentSave().getPendingSalesCount();
    }
}
