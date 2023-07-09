package com.netherbyte.pizzashopsimulator.client.gui.screen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.netherbyte.pizzashopsimulator.client.GuiManager;

import static com.badlogic.gdx.Gdx.gl;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static com.netherbyte.pizzashopsimulator.SharedConstants.DEFAULT_HEIGHT;
import static com.netherbyte.pizzashopsimulator.SharedConstants.DEFAULT_WIDTH;

public class MenuScreen implements Screen {
    private GuiManager game;
    private OrthographicCamera camera;
    private Viewport viewport;



    private Image title;
    private Image playButton;

    public MenuScreen(GuiManager game) {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new FitViewport(DEFAULT_WIDTH, DEFAULT_HEIGHT, camera);

        title = new Image(new Texture("assets/pizzashopsimulator/textures/title.png"));
        title.scaleBy(3f);
        title.setPosition(
                (DEFAULT_WIDTH / 2f) - (title.getWidth() * title.getScaleX() / 2),
                (DEFAULT_HEIGHT) - (title.getHeight() * title.getScaleY())
        );

        playButton = new Image(new Texture("assets/pizzashopsimulator/textures/play.png"));
        playButton.scaleBy(2f);
        playButton.setPosition(
                (DEFAULT_WIDTH / 2f) - (title.getWidth() * title.getScaleX() / 2),
                (DEFAULT_HEIGHT /2f) - (title.getHeight() * title.getScaleY() / 2)
        );
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

        //game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        title.draw(game.batch, 1f);
        playButton.draw(game.batch, 1f);

        game.batch.end();
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
