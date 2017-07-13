package app.game.organism.animal;

import app.game.BoardData;
import app.game.organism.Animal;
import app.game.organism.Organism;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import static app.view.Images.fox;

public class Fox extends Animal {
    public Fox(int x, int y, BoardData boardData) {
        super(x, y, boardData);
    }

    @Override
    public void action() {
        List<Integer[]> availableMoves = getAvailableMoves();

        if (availableMoves.isEmpty()) {
            this.performNoMove();
            return;
        }

        Integer[] move = getRandomElement(availableMoves);

        int newX = move[0];
        int newY = move[1];

        this.performAction(newX, newY);
    }

    @Override
    protected boolean isMoveAvailable(int x, int y) {
        if (!boardData.isValid(x, y)) {
            return false;
        }

        if (boardData.isEmpty(x, y)) {
            return true;
        }

        Organism organism = boardData.getOrganism(x, y);

        if (organism instanceof Fox) {
            return true;
        }

        return organism.getStrength() < this.getStrength();
    }

    @Override
    public int getStrength() {
        return 3;
    }

    @Override
    public int getInitiative() {
        return 7;
    }

    @Override
    public BufferedImage getImage() {
        return fox;
    }

    @Override
    public String getName() {
        return "Lis";
    }

    @Override
    public void reproduce() {
        if (hasSpace()) {
            Point random = getRandomAvailablePosition();
            boardData.addOrganism(new Fox(random.x, random.y, boardData));
            boardData.addReproducedEvent(this);
        }
    }
}
