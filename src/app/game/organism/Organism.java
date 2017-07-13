package app.game.organism;

import app.game.BoardData;
import app.game.organism.animal.Human;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.String.format;

public abstract class Organism implements Comparable<Organism>, Serializable {
    protected int newX, newY;
    int x;
    int y;

    private int age = 0;
    private boolean alive = true;
    private boolean ableToReproduce = false;

    protected int strength;
    protected transient BoardData boardData;

    Organism(int x, int y, BoardData boardData, int strength) {
        this.newX = x;
        this.newY = y;
        this.x = x;
        this.y = y;
        this.boardData = boardData;
        this.strength = strength;
    }

    public void useBoardData(BoardData boardData) {
        this.boardData = boardData;
    }

    @Override
    public String toString() {
        return format("%s {%d,%d  ->  %d,%d}", getClass().getSimpleName(), x, y, newX, newY);
    }

    void setNewPosition(int x, int y) {
        this.x = this.newX;
        this.y = this.newY;
        this.newX = x;
        this.newY = y;
    }

    public void setNewAndOldPosition(int x, int y) {
        this.x = x;
        this.y = y;
        this.newX = x;
        this.newY = y;
    }

    void setAbleToReproduce(Boolean ableToReproduceNewState) {
        this.ableToReproduce = ableToReproduceNewState;
    }

    public int getNewX() {
        return newX;
    }

    public int getNewY() {
        return newY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void addBonusStrength() {
        this.strength += 3;
    }

    public int getStrength() {
        return strength;
    }

    int getAge() {
        return this.age;
    }

    public String getTooltip() {
        return getName();
    }

    public void die() {
        this.alive = false;
        boardData.removeOrganism(this);
    }

    public boolean isAlive() {
        return alive;
    }

    public void incAge() {
        this.age++;
    }

    protected Integer[] getRandomElement(List<Integer[]> availableMoves) {
        return availableMoves.get(new Random().nextInt(availableMoves.size()));
    }

    protected List<Integer[]> getAvailableMoves() {
        int[][] moves = {
                {-1, -1},
                {-1, 0},
                {-1, 1},

                {0, -1},
                {0, 1},

                {1, -1},
                {1, 0},
                {1, 1},
        };
        List<Integer[]> availableMoves = new ArrayList<>();
        for (int[] move : moves) {
            int x = move[0] + getX();
            int y = move[1] + getY();

            if (isMoveAvailable(x, y)) {
                availableMoves.add(new Integer[]{x, y});
            }
        }
        return availableMoves;
    }

    protected Point getRandomAvailablePosition() {
        Integer[] move = getRandomElement(getAvailableMoves());
        return new Point(move[0], move[1]);
    }

    protected boolean isMoveAvailable(int x, int y) {
        if (!boardData.isValid(x, y)) {
            return false;
        }

        if (boardData.isEmpty(x, y)) {
            return true;
        }

        return false;
    }

    protected boolean hasSpace() {
        List<Integer[]> availableMoves = getAvailableMoves();

        if (availableMoves.isEmpty()) {
            setAbleToReproduce(false);
            return false;
        }
        return true;
    }

    protected boolean isAbleToReproduce() {
        return ableToReproduce;
    }

    public void rollbackPosition() {
        this.newX = this.x;
        this.newY = this.y;
    }

    boolean occupy(Animal animal) {
        if (animal.getClass() == this.getClass()) {
            this.reproduce();
            return false;
        }

        return !this.defend(animal);
    }

    protected abstract int getInitiative();

    public abstract BufferedImage getImage();

    public abstract String getName();

    public abstract void action();

    protected abstract boolean defend(Animal attacker);

    protected abstract void reproduce();

    public abstract void resetPosition();

    @Override
    public int compareTo(Organism other) {
        if (this.getInitiative() == other.getInitiative()) {
            return other.getAge() - this.getAge();
        }
        return other.getInitiative() - this.getInitiative();
    }

    public static boolean isNotHuman(Organism organism) {
        return !(organism instanceof Human);
    }
}
