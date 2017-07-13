package app.view.listener;

import app.game.Game;
import app.game.organism.Organism;
import app.game.OrganismFactory;
import app.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class SpawnMouseActionListener extends MouseAdapter {
    private final JOrganismsComboBox comboBox;
    private final Game game;

    public SpawnMouseActionListener(JOrganismsComboBox comboBox, Game game) {
        this.comboBox = comboBox;
        this.game = game;
    }

    public void imagePanelClicked(Point point) {
        if (comboBox.getSelectedIndex() == -1) {
            return;
        }

        if (!game.getBoardData().isEmpty(point.x, point.y)) {
            JOptionPane.showMessageDialog(null, "Cannot place organism on occupied place!");
            return;
        }

        String name = comboBox.getSelectedItem();

        addOrganismToGame(name, point.x, point.y);
        comboBox.setSelectedIndex(-1);
    }

    private void addOrganismToGame(String name, int x, int y) {
        Organism organism = OrganismFactory.createOrganism(name, x, y, game.getBoardData());

        game.addOrganism(organism);
        game.getBoardData().addSpawnEvent(organism);

        app.view.Window window = game.getWindow();

        window.updateOrganisms();
        window.setFocus();
        window.updateEvents();
    }
}
