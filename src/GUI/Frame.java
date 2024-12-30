package GUI;

import Util.Util;
import Graph.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Frame extends JFrame {

    private Point nodePosition = null; // Holds the current position of the node
    private boolean clicked = false;

    private static int nodeCount = 0;

    private Node first,second;

    private Graph graph;

    public Frame() {
        this.setSize(new Dimension(1080, 800));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                graph.drawGraph(g);
            }
        };
        mainPanel.setBackground(new Color(0x0B1F3F));
        mainPanel.setBounds(0, 0, 1080, 800);
        mainPanel.setLayout(null);

        this.add(mainPanel);

        graph = new Graph();

        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if(first==null) {
                    boolean found = false;
                    for(Map.Entry<Node, List<Edge>> map : graph.getAdjacencyList().entrySet()) {
                        if(Util.distance(e.getPoint(), map.getKey().getPoint()) <= Graph.DIAMETER) {
                            first = map.getKey();
                            found = true;
                            break;
                        }
                    }
                    if(!found) first = new Node(nodeCount++, e.getPoint());
                } else {
                    boolean found = false;
                    for(Map.Entry<Node, List<Edge>> map : graph.getAdjacencyList().entrySet()) {
                        if(Util.distance(e.getPoint(), map.getKey().getPoint()) <= 25.0) {
                            second = map.getKey();
                            found = true;
                            break;
                        }
                    }
                    if(!found) second = new Node(nodeCount++, e.getPoint());
                }

                if(second != null) {
                    String weightString = JOptionPane.showInputDialog(Frame.this,
                            "Enter weight for the edge: ", "Edge Weight", JOptionPane.PLAIN_MESSAGE);                    try {
                        int weight = Integer.parseInt(weightString);
                        graph.addEdge(first, second, weight);
                        graph.addEdge(second, first, weight);
                        first = second = null;

                        repaint();
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(Frame.this, "Invalid input. Please enter an integer.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });

        mainPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
            }
        });

        mainPanel.setFocusable(true);
        mainPanel.requestFocusInWindow();
        mainPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    graph.printGraph();
                }
            }
        });
        this.setVisible(true);
    }
}
