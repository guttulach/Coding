package solution.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SolutionTest {

	@Test
	public void testWalkGraph() {
		System.out.println("Testing graph Walk");
		Solution sol = new Solution();
        sol.graphNode = Solution.generateGraph();
        ArrayList<GNode> nodes = sol.walkGraph(sol.graphNode);
        System.out.println(nodes);
        assertEquals("[C, G, H, I, A, B, D, E, F, J]", nodes.toString());
        System.out.println();
	}

	@Test
	public void testPaths() {
		System.out.println("Testing graph Paths");
		Solution sol = new Solution();
        sol.graphNode = Solution.generateGraph();
        //Testcase 1: graphNode = C and the geiven Node is C
        GNode gnode = new GNodeImpl("A");
        ArrayList<List<GNode>> paths = new ArrayList<>();
        paths.addAll(sol.paths(gnode));
        String ePathsA = "paths(A)=((ABE)(ABF)(ACG)(ACH)(ACI)(ADJ))";
        String pathsA = Solution.printPaths(gnode,paths);
        assertEquals(pathsA,ePathsA);
        System.out.println(pathsA);
        
        //Testcase 2: graphNode = C and the geiven Node is C
        paths = new ArrayList<>();
        paths.addAll(sol.paths(sol.graphNode));
        String ePathsC = Solution.printPaths(sol.graphNode,paths);
        String pathsC = "paths(C)=((CG)(CH)(CI)(CABE)(CABF)(CADJ))";
        assertEquals(pathsC,ePathsC);
        System.out.println(pathsC);
        
        //Testcase 3: graphNode = X and the geiven Node is c
        gnode = new GNodeImpl("X");
        paths = new ArrayList<>();
        paths.addAll(sol.paths(gnode));
        String ePathsX = Solution.printPaths(gnode,paths);
        String pathsX = "paths(X)=()";
        Solution.printPaths(gnode,paths);
        assertEquals(pathsX,ePathsX);
        System.out.println(pathsX);
        System.out.println();
	}

}
