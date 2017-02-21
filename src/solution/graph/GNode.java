/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solution.graph;

/**
 * Assuming that the Graph is undirected.
 * Assumptions
 *    Graph is undirected
 *    Graph has no duplicate values
 *    Graph is connected
 * @author cguttula
 */
public interface GNode {
    public String getName();
    public GNode[] getChildren();
}
