package com.netherbyte.pizzashopsimulator.client.gui.screen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.netherbyte.pizzashopsimulator.client.GuiManager;
import com.netherbyte.pizzashopsimulator.client.gui.overlay.HudOverlay;
import com.netherbyte.pizzashopsimulator.client.gui.overlay.IngredientsOverlay;
import com.netherbyte.pizzashopsimulator.client.gui.renderers.PizzaRenderer;
import com.netherbyte.pizzashopsimulator.item.Items;
import com.netherbyte.pizzashopsimulator.item.Pizza;
import com.netherbyte.pizzashopsimulator.save.Save;
import com.netherbyte.pizzashopsimulator.save.Saves;
import com.netherbyte.pizzashopsimulator.util.PointerUtil;

import static com.badlogic.gdx.Gdx.gl;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static com.netherbyte.pizzashopsimulator.SharedConstants.DEFAULT_HEIGHT;
import static com.netherbyte.pizzashopsimulator.SharedConstants.DEFAULT_WIDTH;

public class GameScreen implements Screen {
    private GuiManager game;
    private OrthographicCamera camera;
    private Viewport viewport;

    private HudOverlay hudOverlay;
    private IngredientsOverlay ingredientsOverlay;

    private Pizza currentPizza;

    public GameScreen(GuiManager game) {
        this.game = game;
        Saves.setCurrentSave(new Save());
        camera = new OrthographicCamera();
        viewport = new FitViewport(DEFAULT_WIDTH, DEFAULT_HEIGHT, camera);

        hudOverlay = new HudOverlay(game.batch);
        ingredientsOverlay = new IngredientsOverlay(game.batch, currentPizza);

        currentPizza = new Pizza();
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {

    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        gl.glClearColor(1,1,1,1);
        gl.glClear(GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(hudOverlay.stage.getCamera().combined);
        hudOverlay.stage.draw();

        game.batch.setProjectionMatrix(ingredientsOverlay.stage.getCamera().combined);
        ingredientsOverlay.stage.draw();

        var cb = ingredientsOverlay.checkButtons();
        if (cb != null) currentPizza.addIngredient(cb);

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        game.batch.end();

        PizzaRenderer.render(currentPizza, viewport, game.batch, DEFAULT_WIDTH / 2f, DEFAULT_HEIGHT / 2f, 2f);
    }

    /**
     * @param width
     * @param height
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    /**
     * @see ApplicationListener#pause()
     */
    @Override
    public void pause() {

    }

    /**
     * @see ApplicationListener#resume()
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     */
    @Override
    public void hide() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }
}
