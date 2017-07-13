package app.view.listener;

import app.game.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameActionKeyListener extends KeyAdapter {
    private final Game game;

    public GameActionKeyListener(Game game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case 37:
                game.playerKeyMovedTurn(-1, 0);
                break;
            case 38:
                game.playerKeyMovedTurn(0, -1);
                break;
            case 39:
                game.playerKeyMovedTurn(+1, 0);
                break;
            case 40:
                game.playerKeyMovedTurn(0, +1);
                break;
            case 32:
                game.playerSpecialAbility();
                break;
        }
    }
}
