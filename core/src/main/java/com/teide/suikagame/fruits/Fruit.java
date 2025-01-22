package com.teide.suikagame.fruits;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.teide.suikagame.physics.PhysicsLoader;
import com.teide.suikagame.SuikaGame;

public abstract class Fruit {

    private Sprite sprite;
    private Body body;
    private float x, y;

    public Fruit(Sprite sprite, float x, float y) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;

        sprite.setPosition(this.x, this.y);

        createBody();
        addBodyToMap();
    }

    public void createBody() {
        body = PhysicsLoader.createCircularBody(sprite, SuikaGame.getWorld(), BodyDef.BodyType.DynamicBody);
    }

    public void addBodyToMap() {
        SuikaGame.getFruitBodies().put(body, this);
    }

    public void render() {
        sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
        sprite.draw(SuikaGame.getBatch());
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
