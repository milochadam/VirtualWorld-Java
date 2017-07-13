package app.view;

import app.Application;
import app.game.Game;
import app.game.organism.Organism;
import app.game.organism.animal.Human;
import app.util.GameRepository;
import app.view.listener.GameActionKeyListener;
import app.view.listener.SpawnMouseActionListener;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Window {
    private final Game game;
    private BoardPanel boardPanel;

    private final JFrame frame = new JFrame("Virtual World");
    private final JOrganismsComboBox organismToSpawn = new JOrganismsComboBox();
    private final TextPanel textPanel = new TextPanel(10, 10);

    private final JButton
            newTurn = new JButton("Nowa tura"),
            saveButton = new JButton("Zapisz"),
            loadButton = new JButton("Wczytaj"),
            closeButton = new JButton("Zamknij");

    private final JLabel
            strengthLabel = new JLabel(),
            turnLabel = new JLabel(),
            cooldownLabel = new JLabel();

    public Window(Game game) {
        this.game = game;
        this.boardPanel = new BoardPanel(game.getWidth(), game.getHeight());
        initializeForm();
        initializeComponents();
        setupComboBox();
    }

    private void initializeForm() {
        applyWindowSizeToGame();

        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void applyWindowSizeToGame() {
        frame.setSize(
                game.getWidth() * 50 + 250,
                game.getHeight() * 50 + 80
        );
        frame.setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        frame.setLayout(new BorderLayout());
        addBoardPanel();
        frame.add(textPanel, CENTER);
        frame.add(createToolbarPanel(), NORTH);
        frame.add(createButtonsPanel(), SOUTH);

        newTurn.addActionListener(event -> game.buttonClickedTurn());
        saveButton.addActionListener(event -> GameRepository.save(game));
        loadButton.addActionListener(event -> GameRepository.load(game));
        addListenerToBoardPanel();
        closeButton.addActionListener(event -> Application.close());

        loadButton.addActionListener(event -> this.setFocus());
        saveButton.addActionListener(event -> this.setFocus());
        newTurn.addActionListener(event -> this.setFocus());
    }

    private void addBoardPanel() {
        frame.add(boardPanel, WEST);
    }

    private JPanel createToolbarPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        panel.add(strengthLabel);
        panel.add(cooldownLabel);
        panel.add(turnLabel);

        return panel;
    }

    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(newTurn);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(loadButton);
        buttonsPanel.add(closeButton);

        buttonsPanel.add(organismToSpawn);

        return buttonsPanel;
    }

    private void addListenerToBoardPanel() {
        boardPanel.addKeyListener(new GameActionKeyListener(game));
        boardPanel.addOnClickListener(new SpawnMouseActionListener(organismToSpawn, game));
    }

    private void setupComboBox() {

        organismToSpawn.setSelectedIndex(-1);
    }

    public void show() {
        frame.setVisible(true);
    }

    public void updateEvents() {
        textPanel.clearText();
        for (String event : game.getBoardData().getEvents()) {
            textPanel.appendText(event);
        }
    }

    public void updateOrganisms() {
        boardPanel.removeAllOrganisms();

        game.getBoardData()
                .geOrganisms()
                .forEach(organism -> {
                    organism.resetPosition();
                    displayOrganism(organism);
                });
    }

    private void displayOrganism(Organism organism) {
        int y = organism.getNewY();
        int x = organism.getNewX();
        boardPanel.displayImage(organism.getImage(), organism.getTooltip(), x, y);
    }

    public void removeOrganism(Organism organism) {
        int x = organism.getX();
        int y = organism.getY();
        boardPanel.displayImage(Images.empty, "", x, y);
    }

    public void updateToolbar() {
        Human human = game.getHuman();

        strengthLabel.setText("Siła: " + human.getStrength());
        cooldownLabel.setText("Cooldown: " + human.getCooldown());
        turnLabel.setText("Tura: " + game.getTurn());
    }

    public void setFocus() {
        boardPanel.requestFocus();
    }

    public void resetBoardPanel() {
        frame.remove(boardPanel);
        boardPanel = new BoardPanel(game.getWidth(), game.getHeight());
        addBoardPanel();
        addListenerToBoardPanel();
        applyWindowSizeToGame();
        setFocus();
    }
}
