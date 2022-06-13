package ru.vsu.cs.uliyanov_n_s;

import java.util.*;

public class Graph {
    private final Map<String, Vertex> graph;

    public static class Edge {
        public final String v1, v2;

        public Edge(String v1, String v2) {
            this.v1 = v1;
            this.v2 = v2;
        }
    }

    public static class Vertex {
        public final String name;
        public VertexColor color = VertexColor.NULL;
        public Map<String, Vertex> neighbours = new HashMap<>();
//        public  neighbours = new ArrayList<>();

        public Vertex(String name) {
            this.name = name;
        }

        public Vertex(String name, VertexColor color) {
            this.name = name;
            this.color = color;
        }

        public boolean oneOfNeighboursHasSameColor() {
            for (String vName : this.neighbours.keySet()) {
                if (this.neighbours.get(vName).color.equals(this.color)) {
                    return true;
                }
            }

            return false;
        }
    }

    public Graph() {
        graph = new HashMap<>(0);
    }

    public void addEdge(String v1, String v2, VertexColor c1, VertexColor c2) {
        Edge e = new Edge(v1, v2);
        if (!graph.containsKey(e.v1)) graph.put(e.v1, new Vertex(e.v1, c1));
        if (!graph.containsKey(e.v2)) graph.put(e.v2, new Vertex(e.v2, c2));

        graph.get(v1).neighbours.put(v2, graph.get(v2));
        graph.get(v2).neighbours.put(v1, graph.get(v1)); // если нужно сделать неориентированный граф
    }

    public void addEdge(String v1, String v2) {
        if (graph.containsKey(v1) && graph.containsKey(v2)) {
            graph.get(v1).neighbours.put(v2, graph.get(v2));
            graph.get(v2).neighbours.put(v1, graph.get(v1));
        }
    }

    public void removeEdge(String v1, String v2) {
        if (graph.containsKey(v1) && graph.containsKey(v2)) {
            if (graph.get(v1).neighbours.containsKey(v2) && graph.get(v2).neighbours.containsKey(v1)) {
                graph.get(v1).neighbours.remove(v2);
                graph.get(v2).neighbours.remove(v1);
            }
        }
    }

    public void addVertex(String name, VertexColor color) {
        if (!graph.containsKey(name)) {
            Vertex v = new Vertex(name, color);
            graph.put(name, v);
        }
    }

    public void removeVertex(String name) {
        if (graph.containsKey(name)) {
            Vertex vertexForRemoving = graph.get(name);
            for (String currName : graph.keySet()) {
                Vertex currVertex = graph.get(currName);
                currVertex.neighbours.remove(vertexForRemoving.name);
            }
            graph.remove(name);
        }
    }

    public String toDot() {
        StringBuilder sb = new StringBuilder();
        String nl = System.getProperty("line.separator");
        sb.append("graph").append(" {").append(nl).append("rankdir=LR").append(nl);
        List<Vertex> vertixes = new ArrayList<>();

        for (Map.Entry<String, Vertex> entry : graph.entrySet()) {
            Vertex v = entry.getValue();
            String vColor = "red";

            if (v.color == VertexColor.GREEN) {
                vColor = "green";
            }

            if (v.neighbours.size() == 0) {
                sb.append("{ ").append(v.name).append(" [color=\"").append(vColor).append("\"] } ");
            } else {
                for (String v1 : v.neighbours.keySet()) {
                    if (!vertixes.contains(graph.get(v1))) {
                        String v1Color = "red";
                        if (graph.get(v1).color == VertexColor.GREEN) {
                            v1Color = "green";
                        }

                        sb.append("{ ").append(v.name).append(" [color=\"").append(vColor).append("\"] } ");
                        sb.append(" -- ").append("{ ").append(v1).append(" [color=\"").append(v1Color).append("\"] } ");
                        sb.append(nl);
                    }
                }
                vertixes.add(v);
            }
        }
        sb.append("}").append(nl);

        return sb.toString();
    }

    public boolean isBigraph(String vertexName) {
        return bigraphChecker(graph.get(vertexName), new HashSet<>());
    }

    private boolean bigraphChecker(Vertex v, Set<Vertex> set) {
        if (!set.contains(v)) {
            set.add(v);
            if (v.oneOfNeighboursHasSameColor()) {
                return false;
            }
            for (String neighbourName : v.neighbours.keySet()) {
                Vertex neighbour = v.neighbours.get(neighbourName);
                if (!set.contains(neighbour)) {
                    if (!bigraphChecker(neighbour, set)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
