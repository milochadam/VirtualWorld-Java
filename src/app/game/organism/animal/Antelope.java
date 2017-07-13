package app.game.organism.animal;

import app.game.BoardData;
import app.game.organism.Animal;
import app.util.Points;

import java.awt.*;
import java.awt.image.BufferedImage;

import static app.view.Images.antelope;

public class Antelope extends Animal {
    public Antelope(int x, int y, BoardData boardData) {
        super(x, y, boardData);
    }

    @Override
    public Point generateMovement() {
        Point firstMove = super.generateMovement();
        Point secondMove = super.generateMovement();
        return Points.join(firstMove, secondMove);
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
        return antelope;
    }

    @Override
    public String getName() {
        return "Antylopa";
    }

    @Override
    public void reproduce() {
        if (hasSpace()) {
            Point position = getRandomAvailablePosition();
            boardData.addOrganism(new Antelope(position.x, position.y, boardData));
            boardData.addReproducedEvent(this);
        }
    }
}
