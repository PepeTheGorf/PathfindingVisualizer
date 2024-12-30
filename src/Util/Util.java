package Util;

import Graph.Node;

import java.awt.*;

public class Util {

    public static double distance(Node first, Node second) {
        return Math.sqrt(Math.pow(first.getPoint().getX() - second.getPoint().getX(), 2) + Math.pow(first.getPoint().getY() - second.getPoint().getY(), 2));
    }

    public static double distance(Point first, Point second) {
        return Math.sqrt(Math.pow(first.getX() - second.getX(), 2) + Math.pow(first.getY() - second.getY(), 2));
    }
}
