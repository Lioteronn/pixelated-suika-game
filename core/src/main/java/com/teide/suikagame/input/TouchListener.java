package com.teide.suikagame.input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.teide.suikagame.SuikaGame;
import com.teide.suikagame.entities.Cloud;
import com.teide.suikagame.fruits.Apple;
import com.teide.suikagame.fruits.Cherry;
import com.teide.suikagame.fruits.Melon;

public class TouchListener implements InputProcessor {

    private Cloud cloud = SuikaGame.getCloud();

    public TouchListener() {
    }

    private void moveCloud(int x, int y) {
        Vector3 worldCoords = new Vector3(x, y, 0);

        SuikaGame.getCamera().unproject(worldCoords);

        float halfWidth = cloud.getSprite().getWidth() / 2;

        if (worldCoords.x - halfWidth < 0) {
            worldCoords.x = 0 + halfWidth;
        } else if (worldCoords.x + halfWidth > 50) {
            worldCoords.x = 50 - halfWidth;
        }

        cloud.move(worldCoords.x - halfWidth);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        moveCloud(screenX, screenY);
        return true;
    }

    @Override
    public boolean keyDown(int keycode) { return false; }

    @Override
    public boolean keyUp(int keycode) { return false; }

    @Override
    public boolean keyTyped(char character) { return false; }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        cloud.dropFruit();
        return true;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        moveCloud(screenX, screenY);
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) { return false; }

    @Override
    public boolean scrolled(float amountX, float amountY) { return false; }
}
