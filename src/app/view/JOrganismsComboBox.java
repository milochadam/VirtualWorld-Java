package app.view;

import app.game.organism.animal.*;
import app.game.organism.plant.*;

import javax.swing.*;

public class JOrganismsComboBox extends JComboBox<String> {
    JOrganismsComboBox() {
        addItem(Antelope.class.getSimpleName());
        addItem(Fox.class.getSimpleName());
        addItem(Sheep.class.getSimpleName());
        addItem(Turtle.class.getSimpleName());
        addItem(Wolf.class.getSimpleName());
        addItem(Dandelion.class.getSimpleName());
        addItem(Grass.class.getSimpleName());
        addItem(Guarana.class.getSimpleName());
        addItem(SosnowskysHogweed.class.getSimpleName());
        addItem(WolfBerry.class.getSimpleName());

        setSelectedIndex(-1);
    }

    public String getSelectedItem() {
        return (String) super.getSelectedItem();
    }
}
