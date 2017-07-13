package app.game.organism.animal;

import app.game.BoardData;
import app.game.organism.Animal;
import app.view.Images;

import java.awt.image.BufferedImage;

public class Human extends Animal {
    private int additionalStrength = 0;
    private int cooldown = 0;
    private int wantsToGoLeft;
    private int wantsToGoTop;

    public Human(int x, int y, BoardData boardData) {
        super(x, y, boardData);
    }

    public void wantsToGo(int left, int top) {
        this.wantsToGoLeft = left;
        this.wantsToGoTop = top;
    }

    public void applySpecialAbility() {
        additionalStrength = 10;
    }

    private void lowerAdditionalStrength() {
        if (additionalStrength > 0) {
            additionalStrength--;
        }
    }

    private void lowerCooldown() {
        if (cooldown > 0) {
            cooldown--;
        }
    }

    public void applyCooldown() {
        this.cooldown = 5;
    }

    @Override
    public void action() {
        int newX = getX() + wantsToGoLeft;
        int newY = getY() + wantsToGoTop;

        this.performAction(newX, newY);

        this.lowerAdditionalStrength();
        this.lowerCooldown();
    }

    @Override
    public int getStrength() {
        return strength + additionalStrength;
    }

    @Override
    public int getInitiative() {
        return 4;
    }

    @Override
    public BufferedImage getImage() {
        return Images.human;
    }

    @Override
    public String getName() {
        return "Człowiek";
    }

    @Override
    public void reproduce() {
        throw new RuntimeException("Human can't reproduce");
    }

    public int getCooldown() {
        return cooldown;
    }
}
