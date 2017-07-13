package app.game.organism.animal;

import app.game.BoardData;
import app.game.organism.Animal;
import app.view.Images;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sheep extends Animal {
    public Sheep(int x, int y, BoardData boardData) {
        super(x, y, boardData);
    }

    @Override
    public int getStrength() {
        return 4;
    }

    @Override
    public int getInitiative() {
        return 4;
    }

    @Override
    public BufferedImage getImage() {
        return Images.sheep;
    }

    @Override
    public String getName() {
        return "Owca";
    }

    @Override
    public void reproduce() {
        if (hasSpace()) {
            Point random = getRandomAvailablePosition();
            boardData.addOrganism(new Sheep(random.x, random.y, boardData));
            boardData.addReproducedEvent(this);
        }
    }
}
