package com.netherbyte.pizzashopsimulator.client.gui.screen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.netherbyte.pizzashopsimulator.client.GuiManager;
import com.netherbyte.pizzashopsimulator.client.block.BlockState;
import com.netherbyte.pizzashopsimulator.client.block.Blocks;
import com.netherbyte.pizzashopsimulator.client.gui.overlay.HudOverlay;
import com.netherbyte.pizzashopsimulator.client.gui.overlay.IngredientsOverlay;
import com.netherbyte.pizzashopsimulator.client.gui.renderers.PizzaRenderer;
import com.netherbyte.pizzashopsimulator.item.Pizza;
import com.netherbyte.pizzashopsimulator.client.resource.AssetProvider;
import com.netherbyte.pizzashopsimulator.save.Save;
import com.netherbyte.pizzashopsimulator.save.Saves;
import com.netherbyte.pizzashopsimulator.util.PointerUtil;
import com.netherbyte.pizzashopsimulator.util.PosUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.badlogic.gdx.Gdx.gl;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static com.netherbyte.pizzashopsimulator.SharedConstants.DEFAULT_HEIGHT;
import static com.netherbyte.pizzashopsimulator.SharedConstants.DEFAULT_WIDTH;

public class GameScreen implements Screen {
    private static final Logger logger = LoggerFactory.getLogger(GameScreen.class);

    private GuiManager game;
    private OrthographicCamera camera;
    private Viewport viewport;

    private HudOverlay hudOverlay;
    private IngredientsOverlay ingredientsOverlay;

    private Pizza currentPizza;

    private Pizza ovenContent = null;

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

        Vector2 pizzaSize = PizzaRenderer.getDimensions(currentPizza, viewport, game.batch);
        float pizzaScale = 2f;
        Vector2 pizzaPos = PosUtil.center(viewport, pizzaSize.x * pizzaScale, pizzaSize.y * pizzaScale);
        //logger.info(pizzaSize.toString());
        if (ovenContent == null) PizzaRenderer.render(currentPizza, viewport, game.batch, pizzaPos.x, pizzaPos.y, pizzaScale);

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        //logger.info((PointerUtil.getX() - (DEFAULT_WIDTH / 2)) + " : " + ((PointerUtil.getY() - (DEFAULT_HEIGHT / 2)) * -1));
        //logger.info(PointerUtil.getX(viewport) + " : " + PointerUtil.getY(viewport));

        if (PointerUtil.checkHitbox(
                pizzaPos.x - (pizzaSize.x * pizzaScale / 2),
                pizzaPos.y - (pizzaSize.y * pizzaScale / 2),
                pizzaPos.x + (pizzaSize.x * pizzaScale),
                pizzaPos.y + (pizzaSize.y * pizzaScale)
        ) && PointerUtil.isButtonJustPressed(0) && ovenContent == null) {
            ovenContent = currentPizza;
        }

        Image oven;
        if (ovenContent != null) {
            oven = new Image(AssetProvider.getTexture(BlockState.ofBlock(Blocks.OVEN, "on")));
        } else {
            oven = new Image(AssetProvider.getTexture(BlockState.ofBlock(Blocks.OVEN, "off")));

        }
        oven.draw(game.batch, 1f);


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
