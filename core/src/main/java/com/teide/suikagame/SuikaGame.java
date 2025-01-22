package com.teide.suikagame;

import static com.badlogic.gdx.Gdx.input;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.teide.suikagame.entities.Cloud;
import com.teide.suikagame.fruits.Cherry;
import com.teide.suikagame.fruits.Fruit;
import com.teide.suikagame.fruits.FruitType;
import com.teide.suikagame.fruits.SpriteManager;
import com.teide.suikagame.input.TouchListener;
import com.teide.suikagame.physics.CollisionListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class SuikaGame extends ApplicationAdapter {

    private static SpriteBatch batch;

    private static OrthographicCamera camera;
    private Viewport viewport;

    private static World world;

    private static ConcurrentHashMap<Body, Fruit> fruitBodies = new ConcurrentHashMap<>();
    private static List<Body> bodiesToDestroy = new ArrayList<>();

    private static Cloud cloud;

    static final float STEP_TIME = 1f / 60f;
    static final int VELOCITY_ITERATIONS = 6;
    static final int POSITION_ITERATIONS = 2;

    float accumulator = 0;

    @Override
    public void create() {
        Box2D.init();

        batch = new SpriteBatch();
        world = new World(new Vector2(0, -20), true);
        camera = new OrthographicCamera();
        viewport = new FitViewport(50, 70, camera);

        createGround();
        createBorders();

        world.setContactListener(new CollisionListener());

        cloud = new Cloud(25, 60);

        Gdx.input.setInputProcessor(new TouchListener()); // input
    }

    @Override
    public void render() {
        ScreenUtils.clear(new Color(Color.CYAN));

        batch.begin();
        renderAllFruits();
        cloud.render();
        batch.end();

        stepWorld();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);

        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void dispose() {
        world.dispose();
        batch.dispose();
    }

    private void stepWorld() {
        float delta = Gdx.graphics.getDeltaTime();
        accumulator += Math.min(delta, 0.25f);

        if (accumulator >= STEP_TIME) {
            accumulator -= STEP_TIME;
            world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
        }

        if (!bodiesToDestroy.isEmpty()) {
            for (Body body : bodiesToDestroy) {
                world.destroyBody(body);
                fruitBodies.remove(body);
            }
            bodiesToDestroy.clear();
        }
    }

    private void renderAllFruits() {
        for (Body body : fruitBodies.keySet()) {
            Fruit fruit = fruitBodies.get(body);
            fruit.render();
        }
    }

    private void createGround() {
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(0, 0);
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        Body groundBody = world.createBody(groundBodyDef);
        EdgeShape shape = new EdgeShape();
        shape.set(new Vector2(0, 0), new Vector2(Gdx.graphics.getWidth(), 0));
        groundBody.createFixture(shape, 0);
        shape.dispose();
    }

    private void createBorders() {
        // Define the screen dimensions in world units
        float screenWidth = viewport.getWorldWidth();
        float screenHeight = viewport.getWorldHeight();

        // Create a body definition for the borders
        BodyDef borderBodyDef = new BodyDef();
        borderBodyDef.type = BodyDef.BodyType.StaticBody;

        // Define the edge shape for each border
        EdgeShape borderShape = new EdgeShape();

        // Create the left border
        borderBodyDef.position.set(0, 0); // Bottom-left corner
        Body leftBorder = world.createBody(borderBodyDef);
        borderShape.set(new Vector2(0, 0), new Vector2(0, screenHeight));
        leftBorder.createFixture(borderShape, 0);

        // Create the right border
        borderBodyDef.position.set(screenWidth, 0); // Bottom-right corner
        Body rightBorder = world.createBody(borderBodyDef);
        borderShape.set(new Vector2(0, 0), new Vector2(0, screenHeight));
        rightBorder.createFixture(borderShape, 0);

        // Dispose the shape to free memory
        borderShape.dispose();
    }


    public static World getWorld() {
        return world;
    }

    public static ConcurrentHashMap<Body, Fruit> getFruitBodies() {
        return fruitBodies;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    public static Cloud getCloud() {
        return cloud;
    }

    public static OrthographicCamera getCamera() {
        return camera;
    }

    public static List<Body> getBodiesToDestroy() {
        return bodiesToDestroy;
    }
}
