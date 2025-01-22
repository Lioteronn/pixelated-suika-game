package com.teide.suikagame.physics;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.teide.suikagame.SuikaGame;
import com.teide.suikagame.fruits.Fruit;
import com.teide.suikagame.fruits.FruitType;
import com.teide.suikagame.fruits.Grape;

import java.util.HashMap;

public class CollisionListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        Fruit fruitA = SuikaGame.getFruitBodies().get(bodyA);
        Fruit fruitB = SuikaGame.getFruitBodies().get(bodyB);

        if (fruitA == null || fruitB == null) {
            return;
        }

        // Ensure both fruits are of the same type
        if (fruitA.getClass().equals(fruitB.getClass())) {
            FruitType currentType = FruitType.valueOf(fruitA.getClass().getSimpleName().toUpperCase());
            FruitType nextType = currentType.getNextType();

            if (nextType != null) {
                // Remove old fruits from the world and fruitBodies map
                SuikaGame.getBodiesToDestroy().add(bodyA);
                SuikaGame.getBodiesToDestroy().add(bodyB);

                if (nextType == FruitType.MELON) return;

                float[] fruitACoords = {bodyA.getPosition().x, bodyA.getPosition().y};
                float[] fruitBCoords = {bodyB.getPosition().x, bodyB.getPosition().y};
                float[] newCoords = {(fruitACoords[0] + fruitBCoords[0]) / 2, (fruitACoords[1] + fruitBCoords[1]) / 2};

                // Create the new fruit at the collision point
                new Thread(() -> {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    nextType.create(newCoords[0], newCoords[1]);
                }).start();

            }
        }
    }


    @Override
    public void endContact(Contact contact) {
        System.out.println("Contact ended");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        // Optional: Modify collision logic before solving
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        // Optional: Get collision results after solving
    }
}
