package Graph;

import java.awt.*;

public class Edge  {
    private Node to;
    private int weight;

    private boolean highlight;

    public Edge(Node to, int weight) {
        this.to = to;
        this.weight = weight;
        this.highlight = false;
    }

    public Node getTo() {
        return to;
    }

    public boolean isHighlight() {
        return highlight;
    }

    public void setHighlight(boolean highlight) {
        this.highlight = highlight;
    }

    public int getWeight() {
        return weight;
    }

}
