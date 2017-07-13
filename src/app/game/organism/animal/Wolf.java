package app.game.organism.animal;

import app.game.BoardData;
import app.game.organism.Animal;

import java.awt.*;
import java.awt.image.BufferedImage;

import static app.view.Images.wolf;

public class Wolf extends Animal {
    public Wolf(int x, int y, BoardData boardData) {
        super(x, y, boardData);
    }

    @Override
    public int getStrength() {
        return 9;
    }

    @Override
    public int getInitiative() {
        return 5;
    }

    @Override
    public BufferedImage getImage() {
        return wolf;
    }

    @Override
    public String getName() {
        return "Wilk";
    }

    @Override
    public void reproduce() {
        if (hasSpace() && isAbleToReproduce()) {
            Point random = getRandomAvailablePosition();
            boardData.addOrganism(new Wolf(random.x, random.y, boardData));
            boardData.addReproducedEvent(this);
        }
    }
}
