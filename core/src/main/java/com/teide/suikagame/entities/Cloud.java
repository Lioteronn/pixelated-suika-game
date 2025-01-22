package com.teide.suikagame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.teide.suikagame.fruits.Apple;
import com.teide.suikagame.fruits.Banana;
import com.teide.suikagame.fruits.Cherry;
import com.teide.suikagame.fruits.Fruit;
import com.teide.suikagame.fruits.Grape;
import com.teide.suikagame.fruits.Orange;
import com.teide.suikagame.fruits.SpriteManager;
import com.teide.suikagame.SuikaGame;

import java.util.HashMap;

public class Cloud {

    private float x, y;

    private Sprite sprite;

    private boolean canDrop = true;


    public Cloud(float x, float y) {
        this.x = x;
        this.y = y;
        sprite = SpriteManager.loadSprite(new Texture("cloud.png"));
        sprite.setSize(sprite.getWidth() * 0.8f, sprite.getHeight() * 0.8f);
    }

    public void move(float x) {
        this.x = x;
    }

    public void render() {
        sprite.setPosition(x, y);
        sprite.draw(SuikaGame.getBatch());
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void dropFruit() {
        if (canDrop) {
            canDrop = false;

            int random = SuikaGame.getFruitBodies().isEmpty() ? 0 : (int) (Math.random() * 4);

            float x = this.x + sprite.getWidth() / 4;

            switch (random) {
                case 0:
                    new Cherry(x, y);
                    break;
                case 1:
                    new Apple(x, y);
                    break;
                case 2:
                    new Grape(x, y);
                    break;
                case 3:
                    new Banana(x, y);
                    break;
            }

            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                canDrop = true;
            }).start();
        }
    }
}
