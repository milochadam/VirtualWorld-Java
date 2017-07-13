package app.game.organism.plant;

import app.game.BoardData;
import app.game.organism.Animal;
import app.game.organism.Plant;
import app.view.Images;

import java.awt.image.BufferedImage;

public class SosnowskysHogweed extends Plant {
    public SosnowskysHogweed(int x, int y, BoardData boardData) {
        super(x, y, boardData);
    }

    @Override
    public BufferedImage getImage() {
        return Images.sosnowskysHogweed;
    }

    @Override
    public String getName() {
        return "Barszcz Sosnowskiego";
    }

    @Override
    public boolean defend(Animal attacker) {
        attacker.die();
        boardData.addPoisonedEvent(attacker, this);
        return true;
    }

    @Override
    public void reproduce() {
        boardData.addOrganism(new SosnowskysHogweed(newX, newY, boardData));
        boardData.addReproducedEvent(this);
    }

    @Override
    public int getStrength() {
        return 10;
    }
}
