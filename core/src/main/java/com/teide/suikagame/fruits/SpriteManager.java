package com.teide.suikagame.fruits;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.teide.suikagame.SuikaGame;

public class SpriteManager {

    private static TextureAtlas textureAtlas = new TextureAtlas("sprites.txt");

    private static final float RESIZE_FACTOR = 0.1f;

    public static Sprite loadSprite(Texture texture) {
        Sprite sprite = new Sprite(texture);
        sprite.setSize(sprite.getWidth() * RESIZE_FACTOR, sprite.getHeight() * RESIZE_FACTOR);
        return sprite;
    }

    public static Sprite createCherrySprite() {
        System.out.println("Creating sprite....");
        Sprite sprite = new Sprite(textureAtlas.findRegion("fruit1"));
        System.out.println("Sprite created.");
        System.out.println("Increasing size...");
        sprite.setSize(sprite.getWidth() * 0.3f * RESIZE_FACTOR, sprite.getHeight() * 0.3f * RESIZE_FACTOR);
        System.out.println("Size increased.");
        return sprite;
    }

    public static Sprite createAppleSprite() {
        Sprite sprite = new Sprite(textureAtlas.findRegion("fruit2"));
        sprite.setSize(sprite.getWidth() * 0.4f * RESIZE_FACTOR, sprite.getHeight() * 0.4f * RESIZE_FACTOR);
        return sprite;
    }

    public static Sprite createGrapeSprite() {
        Sprite sprite = new Sprite(textureAtlas.findRegion("fruit3"));
        sprite.setSize(sprite.getWidth() * 0.5f * RESIZE_FACTOR, sprite.getHeight() * 0.5f * RESIZE_FACTOR);
        return sprite;
    }

    public static Sprite createBananaSprite() {
        Sprite sprite = new Sprite(textureAtlas.findRegion("fruit4"));
        sprite.setSize(sprite.getWidth() * 0.6f * RESIZE_FACTOR, sprite.getHeight() * 0.6f * RESIZE_FACTOR);
        return sprite;
    }

    public static Sprite createOrangeSprite() {
        Sprite sprite = new Sprite(textureAtlas.findRegion("fruit5"));
        sprite.setSize(sprite.getWidth() * 0.8f * RESIZE_FACTOR, sprite.getHeight() * 0.8f * RESIZE_FACTOR);
        return sprite;
    }

    public static Sprite createPeachSprite() {
        Sprite sprite = new Sprite(textureAtlas.findRegion("fruit6"));
        sprite.setSize(sprite.getWidth() * 1f * RESIZE_FACTOR, sprite.getHeight() * 1f * RESIZE_FACTOR);
        return sprite;
    }

    public static Sprite createMelonSprite() {
        Sprite sprite = new Sprite(textureAtlas.findRegion("fruit7"));
        sprite.setSize(sprite.getWidth() * 1.4f * RESIZE_FACTOR, sprite.getHeight() * 1.4f * RESIZE_FACTOR);
        return sprite;
    }
}
