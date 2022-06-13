package ru.vsu.cs.uliyanov_n_s;

import org.junit.jupiter.api.Test;
import ru.vsu.cs.uliyanov_n_s.utils.GraphUtils;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void isBigraphTest1() {
        String inputGraphStr = "a r b g\n" +
                "c g a r\n" +
                "d r c g";
        Graph inputGraph = GraphUtils.graphFromString(inputGraphStr);
        boolean actualResult = inputGraph.isBigraph("a");
        boolean expectedResult = true;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void isBigraphTest2() {
        String inputGraphStr = "a r b g\n" +
                "c g a r\n" +
                "d r c g\n" +
                "b g w g";
        Graph inputGraph = GraphUtils.graphFromString(inputGraphStr);
        boolean actualResult = inputGraph.isBigraph("c");
        boolean expectedResult = false;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void isBigraphTest3() {
        String inputGraphStr = "a r b g\n" +
                "c g a r\n" +
                "d r c g\n" +
                "c g e r\n" +
                "f r b g\n" +
                "e r a r";
        Graph inputGraph = GraphUtils.graphFromString(inputGraphStr);
        boolean actualResult = inputGraph.isBigraph("d");
        boolean expectedResult = false;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void isBigraphTest4() {
        String inputGraphStr = "a r b g\n" +
                "c g a r\n" +
                "d r c g\n" +
                "c g e r\n" +
                "f r b g\n" +
                "h g i r\n" +
                "i r b g\n" +
                "m r c g\n" +
                "l r q g\n" +
                "q g e r";
        Graph inputGraph = GraphUtils.graphFromString(inputGraphStr);
        boolean actualResult = inputGraph.isBigraph("e");
        boolean expectedResult = true;
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void isBigraphTest5() {
        String inputGraphStr = "a r b g\n" +
                "i r b g\n" +
                "m r c g\n" +
                "l r q g\n" +
                "q g e r\n" +
                "c g i r\n" +
                "a r q g\n" +
                "b g e r\n" +
                "t g e r\n" +
                "r g e r\n" +
                "h g m r";
        Graph inputGraph = GraphUtils.graphFromString(inputGraphStr);
        boolean actualResult = inputGraph.isBigraph("a");
        boolean expectedResult = true;
        assertEquals(expectedResult, actualResult);
    }
}