package app.game;

import app.Application;
import app.game.organism.Animal;
import app.game.organism.Organism;
import app.game.organism.animal.*;
import app.util.GameSize;
import app.view.Window;

import javax.swing.*;
import java.util.List;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;

public class Game {
    private int width, height;

    private BoardData boardData;
    private final Window window;

    private Human human;
    private int turn = 0;

    public Game(GameSize gameSize) {
        this.width = gameSize.getWidth();
        this.height = gameSize.getHeight();

        this.window = new Window(this);

        this.boardData = new BoardData(width, height);
        this.human = new Human(1, 4, boardData);

        boardData.addOrganism(human);
        window.updateToolbar();
        startUpOrganisms();
        window.updateOrganisms();
    }

    private void startUpOrganisms() {
        boardData.addOrganism(new Antelope(2, 4, boardData));
        boardData.addOrganism(new Antelope(3, 4, boardData));

        boardData.addOrganism(new Fox(5, 5, boardData));
        boardData.addOrganism(new Fox(2, 5, boardData));

        boardData.addOrganism(new Turtle(5, 2, boardData));
        boardData.addOrganism(new Wolf(2, 2, boardData));
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Window getWindow() {
        return window;
    }

    public void playerKeyMovedTurn(int left, int top) {
        human.wantsToGo(left, top);
        performOrganismsTurn();
        performTurn();
    }

    public void buttonClickedTurn() {
        performOrganismsTurnWithoutHuman();
        performTurn();
        window.setFocus();
    }

    private void performTurn() {
        turn++;
        window.updateToolbar();

        if (!human.isAlive()) {
            showRestartDialog();
        }
        getWindow().updateEvents();
    }

    private void showRestartDialog() {
        int result = JOptionPane.showOptionDialog(null,
                "Your human died!",
                "Death!",
                YES_NO_OPTION,
                ERROR_MESSAGE, null,
                new String[]{"Jeszcze raz!", "Nie!"},
                "Jeszcze raz!"
        );

        if (result == 1) {
            Application.close();
        }
    }

    private void performOrganismsTurn() {
        boardData.geOrganisms().forEach(this::performOrganismTurn);
        window.updateOrganisms();
    }

    private void performOrganismsTurnWithoutHuman() {
        boardData.getOrganismsWithoutHuman().forEach(this::performOrganismTurn);
        window.updateOrganisms();
    }

    private void performOrganismTurn(Organism organism) {
        if (!organism.isAlive()) {
            return;
        }

        organism.incAge();
        organism.action();
        if (organism instanceof Animal) {
            updateOrganism(organism);
        }
    }

    public void addOrganism(Organism organism) {
        boardData.addOrganism(organism);
        organism.setNewAndOldPosition(organism.getNewX(), organism.getNewY());
    }

    private void updateOrganism(Organism organism) {
        window.removeOrganism(organism);

        if (organism.isAlive()) {
            boardData.removeOrganism(organism);
            addOrganism(organism);
        }
    }

    public void playerSpecialAbility() {
        if (human.getCooldown() == 0) {
            human.applySpecialAbility();
            human.applyCooldown();
        }
        window.updateToolbar();
    }

    public int getTurn() {
        return turn;
    }

    public Human getHuman() {
        return human;
    }

    public BoardData getBoardData() {
        return boardData;
    }

    public void reset(int turn, Human human, List<Organism> organisms, List<String> events, int width, int height) {
        this.width = width;
        this.height = height;

        this.turn = turn;
        this.human = human;

        this.boardData = new BoardData(width, height);
        this.boardData.resetEvents(events);
        this.boardData.resetOrganisms(organisms);

        this.window.resetBoardPanel();

        window.updateOrganisms();
        window.updateToolbar();
        window.updateEvents();
    }
}
