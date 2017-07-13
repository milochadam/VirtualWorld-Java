package app.game.organism;

import app.game.BoardData;

import java.awt.*;
import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;

public abstract class Animal extends Organism {
    protected Animal(int x, int y, BoardData boardData) {
        super(x, y, boardData, 5);
    }

    @Override
    public void action() {
        Point direction = generateMovement();
        this.performAction(getX() + direction.x, getY() + direction.y);
    }

    public Point generateMovement() {
        int top = 0;
        int left = 0;
        int randomInt = new Random().nextInt(4);

        switch (randomInt) {
            case 0:
                top++;
                break;
            case 1:
                top--;
                break;
            case 2:
                left--;
                break;
            case 3:
                left++;
                break;
            default:
                throw new RuntimeException("Niechciany ruch");
        }
        return new Point(left, top);
    }

    @Override
    public boolean defend(Animal attacker) {
        if (this == attacker) {
            throw new RuntimeException("Cannot kill itself");
        }

        if (isAttackReflected(attacker)) {
            attacker.die();
            boardData.addSelfDefenseEvent(this, attacker);
            return true;
        }

        this.die();
        boardData.addKillEvent(this, attacker);
        return false;
    }

    protected boolean isAttackReflected(Animal attacker) {
        return attacker.getStrength() < this.getStrength();
    }

    protected void performAction(int newX, int newY) {
        newX = min(max(0, newX), boardData.getWidth() - 1);
        newY = min(max(0, newY), boardData.getHeight() - 1);

        if (newX == getX() && newY == getY()) {
            return;
        }

        if (boardData.isEmpty(newX, newY)) {
            this.setNewPosition(newX, newY);
            return;
        }

        if (boardData.getOrganism(newX, newY).occupy(this)) {
            this.setNewPosition(newX, newY);
        }
    }

    protected void performNoMove() {
        this.setNewPosition(getX(), getY());
    }

    @Override
    public String getTooltip() {
        return String.format("<html>%s<br>Siła: %d</html>", getName(), getStrength());
    }

    @Override
    public void resetPosition() {
        this.x = this.newX;
        this.y = this.newY;
    }
}
