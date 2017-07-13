package app.game;

import app.game.organism.Animal;
import app.game.organism.Organism;
import app.game.organism.Plant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class BoardData {
    private final int width;
    private final int height;

    private final Organism[][] board;
    private final List<String> events = new ArrayList<>();

    public BoardData(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new Organism[width][height];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<String> getEvents() {
        return events;
    }

    public boolean isValid(int x, int y) {
        if (x < 0 || y < 0) {
            return false;
        }
        if (x >= width || y >= height) {
            return false;
        }
        return true;
    }

    public boolean isEmpty(int x, int y) {
        return board[x][y] == null;
    }

    public Organism getOrganism(int x, int y) {
        if (isEmpty(x, y)) {
            throw new RuntimeException("Nie ma organizmu w tym miejscu");
        }
        return board[x][y];
    }

    public List<Organism> geOrganisms() {
        return stream(board)
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .filter(Organism::isAlive)
                .sorted()
                .collect(toList());
    }

    List<Organism> getOrganismsWithoutHuman() {
        return stream(board)
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .filter(Organism::isAlive)
                .filter(Organism::isNotHuman)
                .collect(toList());
    }

    public void addOrganism(Organism organism) {
        int x = organism.getNewX();
        int y = organism.getNewY();
        if (!isEmpty(x, y)) {
            throw new RuntimeException(
                    format("Nie mozna postawic organizmu %s na zajete miejsce", organism.getClass().getSimpleName())
            );
        }
        board[x][y] = organism;
    }

    public void removeOrganism(Organism organism) {
        int x = organism.getX();
        int y = organism.getY();
        if (board[x][y] != organism) {
            throw new RuntimeException("Nie ma tutaj organizmu");
        }
        board[x][y] = null;
    }

    void resetEvents(List<String> events) {
        this.events.clear();
        this.events.addAll(events);
    }

    void resetOrganisms(List<Organism> newOrganisms) {
        newOrganisms.forEach(organism -> organism.useBoardData(this));

        geOrganisms().forEach(this::removeOrganism);
        newOrganisms.forEach(this::addOrganism);
    }


    public void addReproducedEvent(Organism organism) {
        events.add(organism.getName() + " has reproduced");
    }

    public void addPoisonedEvent(Organism victim, Organism poisoner) {
        events.add(format("%s was poisoned by %s", victim.getName(), poisoner.getName()));
    }

    public void addEatenEvent(Plant victim, Organism attacker) {
        events.add(format("%s ate %s", attacker.getName(), victim.getName()));
    }

    public void addEatenEvent(Plant victim, Organism attacker, String additional) {
        events.add(format("%s ate %s %s", attacker.getName(), victim.getName(), additional));
    }

    public void addSelfDefenseEvent(Organism victim, Organism attacker) {
        events.add(format("%s killed %s in self-defense", victim.getName(), attacker.getName()));
    }

    public void addKillEvent(Animal victim, Organism attacker) {
        events.add(format("%s killed %s", attacker.getName(), victim.getName()));
    }

    public void addSpawnEvent(Organism organism) {
        events.add(format("Spawned %s", organism.getName()));
    }
}
