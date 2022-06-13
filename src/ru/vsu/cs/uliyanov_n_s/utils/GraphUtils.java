package ru.vsu.cs.uliyanov_n_s.utils;

import ru.vsu.cs.uliyanov_n_s.Graph;
import ru.vsu.cs.uliyanov_n_s.VertexColor;

public class GraphUtils {
    public static Graph graphFromString(String graphStr) {
        Graph graph = new Graph();
        String[] lines = graphStr.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String[] values = lines[i].split(" ");
            graph.addEdge(values[0], values[2], colorFromString(values[1]), colorFromString(values[3]));
        }
        return graph;
    }

    public static VertexColor colorFromString(String colorStr) {
        if (colorStr.equals("r")) {
            return VertexColor.RED;
        } else if (colorStr.equals("g")) {
            return VertexColor.GREEN;
        } else {
            return VertexColor.NULL;
        }
    }
}
