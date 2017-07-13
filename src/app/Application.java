package app;

import app.game.Game;
import app.util.GameSize;
import app.view.Window;

public class Application {
    public static void main(String[] args) {
        GameSize gameSize = GameSize.getFromUserInteraction();
        Game game = new Game(gameSize);

        Window window = game.getWindow();
        window.show();
    }

    public static void close() {
        System.exit(0);
    }
}
