package app.game.organism.animal;

import app.game.BoardData;
import app.game.organism.Animal;

import java.awt.*;
import java.awt.image.BufferedImage;

import static app.view.Images.turtle;
import static java.lang.Math.random;

public class Turtle extends Animal {
    public Turtle(int x, int y, BoardData boardData) {
        super(x, y, boardData);
    }

    @Override
    public void action() {
        if (random() * 4 <= 1) {
            super.action();
        } else {
            this.performNoMove();
        }
    }

    @Override
    public int getStrength() {
        return 2;
    }

    @Override
    public int getInitiative() {
        return 1;
    }

    @Override
    public BufferedImage getImage() {
        return turtle;
    }

    @Override
    public String getName() {
        return "Żółw";
    }

    @Override
    public boolean defend(Animal attacker) {
        if (isAttackReflected(attacker)) {
            attacker.rollbackPosition();
            return true;
        }

        this.die();
        boardData.addKillEvent(this, attacker);
        return false;
    }

    @Override
    protected boolean isAttackReflected(Animal attacker) {
        return attacker.getStrength() < 5;
    }

    @Override
    public void reproduce() {
        if (hasSpace()) {
            Point random = getRandomAvailablePosition();
            boardData.addOrganism(new Turtle(random.x, random.y, boardData));
            boardData.addReproducedEvent(this);
        }
    }
}
