package com.teide.suikagame.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.badlogic.gdx.physics.box2d.*;

public class PhysicsLoader {
    public static Body createCircularBody(Sprite sprite, World world, BodyDef.BodyType bodyType) {
        // Define body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(
            (sprite.getX() + sprite.getWidth() / 2),
            (sprite.getY() + sprite.getHeight() / 2)
        );

        Body body = world.createBody(bodyDef);

        // Define shape
        CircleShape circle = new CircleShape();
        circle.setRadius((Math.min(sprite.getWidth(), sprite.getHeight()) / 2));

        // Define fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 2f; // Adjust based on fruit weight
        fixtureDef.friction = 0.5f; // Adjust for sliding/friction
        fixtureDef.restitution = 0.2f; // Bounciness of fruit

        body.createFixture(fixtureDef);
        circle.dispose(); // Clean up after use

        return body;
    }
}
