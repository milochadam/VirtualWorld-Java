package app.game;

import app.game.organism.Organism;
import app.game.organism.animal.*;
import app.game.organism.plant.*;

public class OrganismFactory {
    public static Organism createOrganism(String name, int x, int y, BoardData boardData) {
        switch (name) {
            case "Antelope":
                return new Antelope(x, y, boardData);
            case "Fox":
                return new Fox(x, y, boardData);
            case "Sheep":
                return new Sheep(x, y, boardData);
            case "Turtle":
                return new Turtle(x, y, boardData);
            case "Wolf":
                return new Wolf(x, y, boardData);
            case "Dandelion":
                return new Dandelion(x, y, boardData);
            case "Grass":
                return new Grass(x, y, boardData);
            case "Guarana":
                return new Guarana(x, y, boardData);
            case "SosnowskysHogweed":
                return new SosnowskysHogweed(x, y, boardData);
            case "WolfBerry":
                return new WolfBerry(x, y, boardData);

            default:
                throw new RuntimeException("Invalid organism name");
        }
    }
}
