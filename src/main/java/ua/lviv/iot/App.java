package ua.lviv.iot;

public class App {

    public static void main(String[] args) {
        Graph g = Graph.createGraphFromFile("graph.in");
        g.depthFirstSearch(2);
    }
}
