package app.util;

import java.awt.*;

public class Points {
    public static Point join(Point firstMove, Point secondMove) {
        return new Point(firstMove.x + secondMove.x, firstMove.y + secondMove.y);
    }
}
