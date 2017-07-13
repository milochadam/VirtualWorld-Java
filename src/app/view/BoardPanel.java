package app.view;

import app.view.listener.SpawnMouseActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

class BoardPanel extends JPanel {
    private final int width, height;
    private final ImagePanel[][] imagePanels;

    BoardPanel(int width, int height) {
        super(new GridLayout(height, width));

        this.width = width;
        this.height = height;
        this.imagePanels = new ImagePanel[width][height];
        initializeComponents();
    }

    private void initializeComponents() {
        setFocusable(true);

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                ImagePanel panel = new ImagePanel();
                imagePanels[i][j] = panel;
                add(panel);
            }
        }
    }

    void addOnClickListener(SpawnMouseActionListener listener) {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                Point point = new Point(i, j);
                imagePanels[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        listener.imagePanelClicked(point);
                    }
                });
            }
        }
    }

    void displayImage(BufferedImage image, String tooltip, int x, int y) {
        imagePanels[x][y].displayImage(image, tooltip);
    }

    void removeAllOrganisms() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                displayImage(Images.empty, "", i, j);
            }
        }
    }
}
