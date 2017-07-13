package app.view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Images {
    public final static BufferedImage
            wolf = loadImage("Wolf.jpg"),
            sheep = loadImage("Sheep.jpg"),
            fox = loadImage("Fox.jpg"),
            turtle = loadImage("Turtle.jpg"),
            antelope = loadImage("Antelope.jpg"),
            grass = loadImage("Grass.jpg"),
            dandelion = loadImage("Dandelion.jpg"),
            wolfBerry = loadImage("WolfBerry.jpg"),
            guarana = loadImage("Guarana.jpg"),
            sosnowskysHogweed = loadImage("SosnowskysHogweed.jpg"),
            human = loadImage("Human.jpg"),
            empty = loadImage("Blank.jpg");

    private static BufferedImage loadImage(String filename) {
        URL resource = Images.class.getClassLoader().getResource(filename);
        if (resource == null) {
            throw new RuntimeException("File '" + filename + "' doesn't exist");
        }
        try {
            return ImageIO.read(resource);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
