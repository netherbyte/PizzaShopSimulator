package com.netherbyte.pizzashopsimulator.client;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.netherbyte.pizzashopsimulator.client.gui.screen.GameScreen;
import com.netherbyte.pizzashopsimulator.client.main.Main;

public class GuiManager extends Game {
    public SpriteBatch batch;

    @Override
    public void create () {
        Main.postInit();
        batch = new SpriteBatch();
        // this is for development only!!! production versions should have MenuScreen as the default
        setScreen(new GameScreen(this));
    }

    @Override
    public void render () {
        super.render();
    }

    public float delta() {
        return Gdx.graphics.getDeltaTime();
    }

    public float fps() {
        return 1000 / delta();
    }
}
