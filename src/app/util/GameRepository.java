package app.util;

import app.game.BoardData;
import app.game.Game;
import app.game.organism.Organism;
import app.game.organism.animal.Human;

import java.io.*;
import java.util.List;

public class GameRepository {
    public static void save(Game game) {
        ObjectOutputStream stream = null;
        try {
            stream = new ObjectOutputStream(new FileOutputStream("save.dat"));
            saveGameToStream(game, stream);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void load(Game game) {
        ObjectInputStream stream = null;
        try {
            stream = new ObjectInputStream(new FileInputStream("save.dat"));
            loadGameFromStream(game, stream);
        } catch (IOException | ClassNotFoundException exception) {
            throw new RuntimeException(exception);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void loadGameFromStream(Game game, ObjectInputStream stream) throws IOException, ClassNotFoundException {
        int width = stream.readInt();
        int height = stream.readInt();

        int turn = stream.readInt();
        Human human = (Human) stream.readObject();
        List<Organism> organisms = (List<Organism>) stream.readObject();
        List<String> events = (List<String>) stream.readObject();

        game.reset(turn, human, organisms, events, width, height);
    }

    private static void saveGameToStream(Game game, ObjectOutputStream stream) throws IOException {
        BoardData boardData = game.getBoardData();

        stream.writeInt(game.getWidth());
        stream.writeInt(game.getHeight());

        List<Organism> listOfOrganisms = boardData.geOrganisms();
        listOfOrganisms.forEach(Organism::resetPosition);

        stream.writeInt(game.getTurn());
        stream.writeObject(game.getHuman());
        stream.writeObject(listOfOrganisms);
        stream.writeObject(boardData.getEvents());
    }
}
