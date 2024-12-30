package Graph;

import GUI.Frame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dijkstra {

    private Graph graph;

    public Dijkstra(Graph graph) {
        this.graph = graph;
    }

    private int minDistance(boolean[] visited, int[] distances) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int i = 0; i < distances.length; i++) {
            if(!visited[i] && distances[i] < min) {
                min = distances[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public void run(Node source, List<Integer> finalPath, int end) {
        int[] distances = new int[graph.getAdjacencyList().size()];
        boolean[] visited = new boolean[graph.getAdjacencyList().size()];

        Arrays.fill(distances, Integer.MAX_VALUE);
        int[] path = new int[graph.getAdjacencyList().size()];
        Arrays.fill(path, -1);

        distances[source.getId()] = 0;

        for (int i = 0; i < graph.getAdjacencyList().size() - 1; i++) {
            int minIndex = minDistance(visited, distances);

            if(minIndex == -1) break; //todo: not reachable...
            visited[minIndex] = true;

            for(Edge adj : graph.getAdjacencyList().get(new Node(minIndex))) {
                if(!visited[adj.getTo().getId()]) {
                    if(distances[minIndex] + adj.getWeight() < distances[adj.getTo().getId()]) {
                        distances[adj.getTo().getId()] = distances[minIndex] + adj.getWeight();
                        path[adj.getTo().getId()] = minIndex;
                    }
                }
            }
        }
        finalPath.add(end);
        while(end != source.getId()) {
            finalPath.add(path[end]);
            end = path[end];
        }
    }
}
