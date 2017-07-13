package app.game.organism.plant;

import app.game.BoardData;
import app.game.organism.Animal;
import app.game.organism.Plant;

import java.awt.image.BufferedImage;

import static app.view.Images.wolfBerry;

public class WolfBerry extends Plant {
    public WolfBerry(int x, int y, BoardData boardData) {
        super(x, y, boardData);
    }

    @Override
    public BufferedImage getImage() {
        return wolfBerry;
    }

    @Override
    public String getName() {
        return "Wilcza Jagoda";
    }

    @Override
    public boolean defend(Animal attacker) {
        attacker.die();
        boardData.addPoisonedEvent(attacker, this);
        return true;
    }

    @Override
    public void reproduce() {
        boardData.addOrganism(new WolfBerry(newX, newY, boardData));
        boardData.addReproducedEvent(this);
    }

    @Override
    public int getStrength() {
        return 99;
    }
}
