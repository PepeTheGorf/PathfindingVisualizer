package Graph;

import java.awt.*;

public class Node {
    private int id;
    private Point point;

    public Node(int id, Point point) {
        this.id = id;
        this.point = point;
    }

    public int getId() {
        return id;
    }

    public Point getPoint() {
        return point;
    }
}
