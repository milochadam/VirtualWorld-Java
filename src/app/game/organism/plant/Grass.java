package app.game.organism.plant;

import app.game.BoardData;
import app.game.organism.Plant;

import java.awt.image.BufferedImage;

import static app.view.Images.grass;

public class Grass extends Plant {
    public Grass(int x, int y, BoardData boardData) {
        super(x, y, boardData);
    }

    @Override
    public BufferedImage getImage() {
        return grass;
    }

    @Override
    public String getName() {
        return "Trawa";
    }

    @Override
    public void reproduce() {
        boardData.addOrganism(new Grass(newX, newY, boardData));
        boardData.addReproducedEvent(this);
    }

    @Override
    public int getStrength() {
        return 0;
    }
}
