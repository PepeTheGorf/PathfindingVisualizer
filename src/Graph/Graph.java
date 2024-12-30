package Graph;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<Node, List<Edge>> adjacencyList;

    public static final int DIAMETER = 32;

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
            g2d.setColor(new Color(0x4FC3F7));
            g2d.fillOval(source.getPoint().x - DIAMETER/2, source.getPoint().y - DIAMETER/2, DIAMETER, DIAMETER);
            edges.forEach(edge -> {
                g2d.setColor(new Color(0x4FC3F7));
                g2d.fillOval(edge.getTo().getPoint().x - DIAMETER/2,edge.getTo().getPoint().y - DIAMETER/2,DIAMETER,DIAMETER);
                g2d.setColor(new Color(0x647A98));

                g2d.setStroke(new BasicStroke(3));
                double angle = Math.atan2(edge.getTo().getPoint().y - source.getPoint().y,
                        edge.getTo().getPoint().x - source.getPoint().x);

                int startX = source.getPoint().x + (int)(Math.cos(angle) * DIAMETER/2);
                int startY = source.getPoint().y + (int)(Math.sin(angle) * DIAMETER/2);

                int endX = edge.getTo().getPoint().x - (int)(Math.cos(angle) * DIAMETER/2);
                int endY = edge.getTo().getPoint().y - (int)(Math.sin(angle) * DIAMETER/2);

                g2d.drawLine(startX, startY, endX, endY);
            });
        });

        adjacencyList.forEach((source,  edges) -> {
            for(Edge edge : edges) {
                if(edge.isHighlight()) {
                    g2d.setColor(new Color(0xFF6B6B));

                    g2d.setStroke(new BasicStroke(3));
                    double angle = Math.atan2(edge.getTo().getPoint().y - source.getPoint().y,
                            edge.getTo().getPoint().x - source.getPoint().x);

                    int startX = source.getPoint().x + (int)(Math.cos(angle) * DIAMETER/2);
                    int startY = source.getPoint().y + (int)(Math.sin(angle) * DIAMETER/2);

                    int endX = edge.getTo().getPoint().x - (int)(Math.cos(angle) * DIAMETER/2);
                    int endY = edge.getTo().getPoint().y - (int)(Math.sin(angle) * DIAMETER/2);

                    g2d.drawLine(startX, startY, endX, endY);
                }
            }


            g2d.setColor(new Color(0x000000));

            Font font = new Font("Arial", Font.BOLD, 20);
            g2d.setFont(font);
            FontMetrics metrics = g2d.getFontMetrics(font);
            int letterX = source.getPoint().x - metrics.stringWidth(String.valueOf(source.getId())) / 2;
            int letterY = source.getPoint().y - metrics.getHeight() / 2 + metrics.getAscent();

            g2d.drawString(String.valueOf(source.getId()), letterX, letterY);
        });
    }
}
