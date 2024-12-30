package Graph;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<Node, List<Edge>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    public Map<Node, List<Edge>> getAdjacencyList() {
        return adjacencyList;
    }

    public void addEdge(Node from, Node to, int weight) {
        adjacencyList.putIfAbsent(from, new ArrayList<>());
        adjacencyList.get(from).add(new Edge(to, weight));
    }

    public void printGraph() {
        System.out.println("************************************************************************************************************");
        adjacencyList.forEach((source, edges) -> {
            System.out.println("Node with ID: " + source.getId() + "  has: " + edges.size() + " edges.");
            System.out.print("\t");
            edges.forEach(edge -> {
                System.out.print(edge.getTo().getId() + ",");
            });
            System.out.println();
        });
        System.out.println("************************************************************************************************************");
    }

    public void drawGraph(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        adjacencyList.forEach((source, edges)-> {
            g2d.setColor(new Color(0x2671A4));
            g2d.fillOval(source.getPoint().x - 8, source.getPoint().y - 8, 16, 16);
            edges.forEach(edge -> {
                g2d.setColor(new Color(0x2671A4));
                g2d.fillOval(edge.getTo().getPoint().x - 8,edge.getTo().getPoint().y - 8,16,16);

                g2d.setColor(new Color(0xE07A00));
                g2d.setStroke(new BasicStroke(3));
                double angle = Math.atan2(edge.getTo().getPoint().y - source.getPoint().y,
                        edge.getTo().getPoint().x - source.getPoint().x);

                int startX = source.getPoint().x + (int)(Math.cos(angle) * 8);
                int startY = source.getPoint().y + (int)(Math.sin(angle) * 8);

                int endX = edge.getTo().getPoint().x - (int)(Math.cos(angle) * 8);
                int endY = edge.getTo().getPoint().y - (int)(Math.sin(angle) * 8);

                g2d.drawLine(startX, startY, endX, endY);
            });
        });
    }
}
