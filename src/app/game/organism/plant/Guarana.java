package app.game.organism.plant;

import app.game.BoardData;
import app.game.organism.Animal;
import app.game.organism.Plant;
import app.view.Images;

import java.awt.image.BufferedImage;

public class Guarana extends Plant {
    public Guarana(int x, int y, BoardData boardData) {
        super(x, y, boardData);
    }

    @Override
    public BufferedImage getImage() {
        return Images.guarana;
    }

    @Override
    public String getName() {
        return "Guarana";
    }

    @Override
    public boolean defend(Animal attacker) {
        attacker.addBonusStrength();
        this.die();
        boardData.addEatenEvent(this, attacker, " and got +3 to strength");
        return false;
    }

    @Override
    public void reproduce() {
        boardData.addOrganism(new Guarana(newX, newY, boardData));
        boardData.addReproducedEvent(this);
    }

    @Override
    public int getStrength() {
        return 0;
    }
}
