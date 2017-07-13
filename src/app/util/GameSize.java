package app.util;

import app.Application;

import static java.lang.Integer.parseInt;
import static javax.swing.JOptionPane.*;

public class GameSize {
    private final int width, height;

    private GameSize(int width, int height) {
        this.width = width;
        this.height = height;

        if (width < 6 || height < 6 || width > 35 || height > 18) {
            throw new GameSizeExceeded();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static GameSize getFromUserInteraction() {
        String input = "15, 10";
        while (true) {
            input = getInputOrExit(input);

            try {
                return tryParseGameSize(input);
            } catch (NumberFormatException exception) {
                error("Podałeś coś co nie jest liczbą!");
            } catch (GameSizeExceeded exceeded) {
                error("Zakres może być od <6,6> do <35,18>!");
            } catch (InvalidNumberOfArguments invalidArguments) {
                error("Podaj dwie liczby oddzielone przecinkiem!");
            }
        }
    }

    private static String getInputOrExit(String input) {
        input = showInputDialog(null, "Podaj rozmiar gry:", input);
        if (input == null) {
            Application.close();
        }
        return input;
    }

    private static GameSize tryParseGameSize(String input) {
        String[] parts = input.split(",");
        if (parts.length != 2) {
            throw new InvalidNumberOfArguments();
        }

        String width = parts[0].trim();
        String height = parts[1].trim();
        return new GameSize(parseInt(width), parseInt(height));
    }

    private static void error(String text) {
        showMessageDialog(null, text, "Podałeś dane w złym formacie", ERROR_MESSAGE);
    }

    private static class GameSizeExceeded extends RuntimeException {
    }

    private static class InvalidNumberOfArguments extends RuntimeException {
    }
}
