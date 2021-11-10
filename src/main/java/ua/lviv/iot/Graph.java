package ua.lviv.iot;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.util.*;

class Graph {

    private static final Logger LOGGER = Logger.getLogger(Graph.class);

    static {
        BasicConfigurator.configure();
    }

    private static class Edge implements Comparable<Edge> {
        int src;
        int dest;
        int weight;

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return weight == edge.weight;
        }

        @Override
        public int hashCode() {
            return Objects.hash(weight);
        }
    }

    private static class Subset {
        int parent;
        int rank;
    }

    private final int v;
    private final Edge[] edge;


    Graph(int v) {
        int e = v + 1;
        this.v = v;
        edge = new Edge[e];
        for (int i = 0; i < e; i++) {
            edge[i] = new Edge();
        }
    }


    int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }

        return subsets[i].parent;
    }

    void union(Subset[] subsets, int x, int y) {
        int xRoot = find(subsets, x);
        int yRoot = find(subsets, y);

        if (subsets[xRoot].rank < subsets[yRoot].rank) {
            subsets[xRoot].parent = yRoot;
        } else if (subsets[xRoot].rank > subsets[yRoot].rank) {
            subsets[yRoot].parent = xRoot;
        } else {
            subsets[yRoot].parent = xRoot;
            subsets[xRoot].rank++;
        }
    }


    void kruskalMST() {

        Edge[] result = new Edge[this.v];

        int eIndex = 0;

        int i;

        for (i = 0; i < this.v; i++) {
            result[i] = new Edge();
        }

        Arrays.sort(edge);

        Subset[] subsets = new Subset[this.v];
        for (i = 0; i < this.v; i++) {
            subsets[i] = new Subset();
        }

        for (int vIndex = 0; vIndex < this.v; ++vIndex) {
            subsets[vIndex].parent = vIndex;
            subsets[vIndex].rank = 0;
        }

        i = 0;


        while (eIndex < this.v - 1) {
            Edge nextEdge = edge[i++];

            int x = find(subsets, nextEdge.src);
            int y = find(subsets, nextEdge.dest);

            if (x != y) {
                result[eIndex++] = nextEdge;
                union(subsets, x, y);
            }
        }

        printResult(result, eIndex);
    }

    private void printResult(Edge[] result, int eIndex) {
        int i;
        LOGGER.info("Following are the edges in the constructed MST");
        int minimumCost = 0;
        for (i = 0; i < eIndex; ++i) {
            LOGGER.info(result[i].src + " -- " + result[i].dest + " == " + result[i].weight);
            minimumCost += result[i].weight;
        }
        LOGGER.info("Minimum Cost Spanning Tree " + minimumCost);
    }

    // Driver Code
    public static void main(String[] args) {

        int v = 4;
        Graph graph = new Graph(v);

        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = 10;

        graph.edge[1].src = 0;
        graph.edge[1].dest = 2;
        graph.edge[1].weight = 6;

        graph.edge[2].src = 0;
        graph.edge[2].dest = 3;
        graph.edge[2].weight = 5;

        graph.edge[3].src = 1;
        graph.edge[3].dest = 3;
        graph.edge[3].weight = 15;

        graph.edge[4].src = 2;
        graph.edge[4].dest = 3;
        graph.edge[4].weight = 4;

        graph.kruskalMST();
    }
}
