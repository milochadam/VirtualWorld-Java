package app.game.organism.plant;

import app.game.BoardData;
import app.game.organism.Plant;

import java.awt.image.BufferedImage;

import static app.view.Images.dandelion;

public class Dandelion extends Plant {
    public Dandelion(int x, int y, BoardData boardData) {
        super(x, y, boardData);
    }

    @Override
    public BufferedImage getImage() {
        return dandelion;
    }

    @Override
    public String getName() {
        return "Dmuchawiec";
    }

    @Override
    public void action() {
        super.action();
        super.action();
        super.action();
    }

    @Override
    public void reproduce() {
        boardData.addOrganism(new Dandelion(newX, newY, boardData));
        boardData.addReproducedEvent(this);
    }

    @Override
    public int getStrength() {
        return 0;
    }
}
