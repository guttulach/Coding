/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solution.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author cguttula
 */
public class Solution {
    
    //Tracks the path of traversal when finding the paths
    public LinkedList<GNode> pathtrack= new LinkedList<>();
    //Paths generated from the graph
    public ArrayList<ArrayList<GNode>> paths = new ArrayList<>();
    //Represents the graph if the nodes are visited.
    public Map<GNode,Boolean> visited = new HashMap<>();
    
    //Node from the graph (Similar to root in tree) this needs to be present
    //in order for the code to work
    public GNode graphNode;
    
    /**
     * Performing a breadth first traversal to find all the nodes from the node 
     * present in the graph.
     * @param node
     * @return 
     */
    public ArrayList<GNode> walkGraph(GNode node){
        Set<GNode> graphNodes = new LinkedHashSet<>();
        if(node != null){
            LinkedList<GNode> queue = new LinkedList<>();
            graphNodes.add(node);
            queue.addAll(Arrays.asList(node.getChildren()));
            GNode tempNode;
            
            while(!queue.isEmpty()){
                tempNode = queue.removeFirst();
                if( !graphNodes.contains(tempNode)){
                    graphNodes.add(tempNode);
                    queue.addAll(Arrays.asList(tempNode.getChildren()));
                }
            }
        }
        ArrayList<GNode> nodes = new ArrayList<>();
        nodes.addAll(graphNodes);
        return nodes;
    }
    
    /**
     * Generates path given a node
     * Return empty list of node not present in the graph.
     * @param node
     * @return 
     */
    public ArrayList<ArrayList<GNode>> paths(GNode node){
        GNode gnode = findNode(node);
        clearGraphVisited();
        if(gnode != null){
            buildPaths(gnode);
        }
        return paths;
    }
    
    /**
     * Reset the visited of the GRaph to false
     * Clear the global paths and pathtracking
     */
    public void clearGraphVisited(){
        List<GNode> nodes = walkGraph(graphNode);
        nodes.stream().forEach((GNode n)->visited.put(n, Boolean.FALSE));
        this.paths = new ArrayList<>();
        this.pathtrack = new LinkedList<>();
    }
    
    /**
     * Builds the path for a given Node in the graph. 
     * @param node 
     */
    private void buildPaths(GNode node){
        //Visit
        pathtrack.add(node);
        visited.put(node, Boolean.TRUE);
        if(reachedDepth(node)){
        	ArrayList<GNode> path1 = new ArrayList<>();
            path1.addAll(pathtrack);
            paths.add(path1);
        }
        for (GNode child : node.getChildren()) {
            if(!visited.get(child)) {
                buildPaths(child);
                pathtrack.removeLast();                
            }
        }
    }
    
    /**
     * Find if the node has reached the last level.
     * Since its an undirected tree and we need to check if the lasts level has been reached.
     * @param node
     * @return 
     */
    private boolean reachedDepth(GNode node){
        int count=0;
        for(GNode cnode:node.getChildren()){
            if(visited.get(cnode)){
                count++;
            }
        }        return count==node.getChildren().length;
    }
    
    /**
     * Generates a connected graph and returns one node
     * 
     * @return 
     */
    public static GNode generateGraph(){
        GNodeImpl node1 = new GNodeImpl("E");
        GNodeImpl node2 = new GNodeImpl("F");
        GNodeImpl node3 = new GNodeImpl("G");
        GNodeImpl node4 = new GNodeImpl("H");
        GNodeImpl node5 = new GNodeImpl("I");
        GNodeImpl node6 = new GNodeImpl("J");
        GNodeImpl node7 = new GNodeImpl("D",Arrays.asList(node6));
        GNodeImpl node8 = new GNodeImpl("C",Arrays.asList(node3,node4,node5));
        GNodeImpl node9 = new GNodeImpl("B",Arrays.asList(node1,node2));
        GNodeImpl node10 = new GNodeImpl("A", Arrays.asList(node9,node8,node7));
        node9.addChild(node10);
        node8.addChild(node10);
        node7.addChild(node10);
        node1.addChild(node9);
        node2.addChild(node9);
        node3.addChild(node8);
        node4.addChild(node8);
        node5.addChild(node8);
        node6.addChild(node7);
        return node8;
    }
    
    /**
     * Prints the path given the node and the paths
     * @param gnode
     * @param paths 
     */
    public static String printPaths(GNode gnode, ArrayList<List<GNode>> paths){
    	StringBuilder sb = new StringBuilder();
    	sb.append("paths("+gnode+")=(");
        paths.stream().forEach((path)->{
            sb.append("(");
            path.stream().forEach((node)->{
            		sb.append(node);
            	});
            sb.append(")");
        });
        sb.append(")");
        return sb.toString();
    }
    
    /**
     * Finds if the given node is in the graph and returns it or null if not found
     * @param node
     * @return 
     */
    private GNode findNode(GNode node){
        List<GNode> nodes = walkGraph(graphNode);
        for(GNode gnode : nodes){
            if(node.getName().equals(gnode.getName())){
                return gnode;
            }
        }
        return null;
    }
}