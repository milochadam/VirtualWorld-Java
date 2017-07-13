package app.game.organism;

import app.game.BoardData;

import java.util.List;
import java.util.Random;

public abstract class Plant extends Organism {
    private final static int CHANCE_TO_GROW = 10;

    protected Plant(int x, int y, BoardData boardData) {
        super(x, y, boardData, 0);
    }

    @Override
    public int getInitiative() {
        return 0;
    }

    @Override
    void setNewPosition(int x, int y) {
        this.newX = x;
        this.newY = y;
    }

    private Boolean hasChanceToGrow() {
        return new Random().nextInt(100) < CHANCE_TO_GROW;
    }

    @Override
    public void action() {
        if (!isAbleToReproduce()) {
            if (getAge() > 10 && getAge() < 30) {
                setAbleToReproduce(true);
            }
        }

        if (isAbleToReproduce() && hasChanceToGrow()) {
            List<Integer[]> availableMoves = getAvailableMoves();

            if (availableMoves.isEmpty()) {
                setAbleToReproduce(false);
                return;
            }

            Integer[] move = getRandomElement(availableMoves);
            this.setNewPosition(move[0], move[1]);
            this.reproduce();
        }
    }

    public boolean defend(Animal attacker) {
        this.die();
        boardData.addEatenEvent(this, attacker);
        return false;
    }

    @Override
    public void resetPosition() {
        this.rollbackPosition();
    }
}
