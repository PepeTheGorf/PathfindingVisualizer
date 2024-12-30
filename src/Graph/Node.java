package Graph;

import java.awt.*;
import java.util.Objects;

public class Node {
    private int id;
    private Point point;

    private boolean highlight;

    public Node(int id, Point point) {
        this.id = id;
        this.point = point;
        this.highlight = false;
    }

    public boolean isHighlight() {
        return highlight;
    }

    public void setHighlight(boolean highlight) {
        this.highlight = highlight;
    }

    public Node(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Point getPoint() {
        return point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node node)) return false;
        return id == node.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                '}';
    }
}
