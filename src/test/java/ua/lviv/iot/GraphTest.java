package ua.lviv.iot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class GraphTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private Graph graph = null;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void willPrintVerticesInCorrectOrderStartingFrom0() {
        graph.depthFirstSearch(0);
        assertEquals("0 1 2 3 ", outContent.toString());
    }

    @Test
    public void willPrintVerticesInCorrectOrderStartingFrom1() {
        graph.depthFirstSearch(1);
        assertEquals("1 2 0 3 ", outContent.toString());
    }

    @Test
    public void willPrintVerticesInCorrectOrderStartingFrom2() {
        graph.depthFirstSearch(2);
        assertEquals("2 0 1 3 ", outContent.toString());
    }

    @Test
    public void willPrintVerticesInCorrectOrderStartingFrom3() {
        graph.depthFirstSearch(3);
        assertEquals("3 ", outContent.toString());
    }
}
