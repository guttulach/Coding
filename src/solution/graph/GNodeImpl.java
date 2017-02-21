/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solution.graph;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cguttula
 */
public class GNodeImpl implements GNode {

    private String name;
    private List<GNode> children = new ArrayList<>();
    
    public GNodeImpl(String name){
        this.name = name;
    }
    
    public GNodeImpl(String name, List<GNode> children){
        this.name = name;
        this.children.addAll(children);
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public GNode[] getChildren() {
        if(children == null || children.isEmpty()){
            return new GNode[0];
        }
        int size = children.size();
        GNode [] childrn = (GNode[])children.toArray(new GNode[size]);
        return childrn;
    }

    @Override
    public String toString() {
        return name;
    }
    
    /**
     * Method to ass child to the graph
     * @param node 
     */
    public void addChild(GNode node){
        children.add(node);
    }
}
