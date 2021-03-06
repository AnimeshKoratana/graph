package com.ga.tsp;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.Collection;
import java.util.Iterator;

public class GraphExplore {
    public static void main(String args[]) {
        // new com.ga.tsp.GraphExplore();
        //System.out.print("Hello World");
    }

    public GraphExplore() {
        Graph graph = new SingleGraph("inputMap");
        boolean isDirected = false;

        graph.setStrict(false);
        graph.setAutoCreate(true);

        graph.addAttribute("ui.stylesheet" , labelStyleSheet );



        graph.addEdge("AB", "A", "B", isDirected);
        graph.addEdge("BC", "B", "C", isDirected);
        graph.addEdge("CD", "C", "D", isDirected);

        addAllEdgeInstances(graph, isDirected);
        /*graph.addEdge("AD", "A", "D", isDirected);
        graph.addEdge("DE", "D", "E", isDirected);
        graph.addEdge("DF", "D", "F", isDirected);
        graph.addEdge("EF", "E", "F", isDirected);
        graph.addEdge("EG", "E", "G", isDirected);
        graph.addEdge("EH", "E", "H", isDirected);
        graph.addEdge("EI", "E", "I", isDirected);*/

        graph.getEdge("AB").setAttribute("weight", 50.0);
        graph.getEdge("BC").setAttribute("weight", 150.0);
        graph.getEdge("CD").setAttribute("weight", 250.0);
        graph.getEdge("AC").setAttribute("weight", 250.0);
        graph.getEdge("AD").setAttribute("weight", 250.0);
        graph.getEdge("BD").setAttribute("weight", 250.0);
        /*graph.getEdge("AD").setAttribute("weight", 25);
        graph.getEdge("DE").setAttribute("weight", 5);
        graph.getEdge("DF").setAttribute("weight", 50);
        graph.getEdge("EF").setAttribute("weight", 150);*/

        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
        }

        for (Edge edge : graph.getEdgeSet())
        {
            edge.addAttribute("ui.label", edge.getAttribute("distance"));
        }


        explore(graph.getNode("A"));
    }

    protected String styleSheet =
            "node {" +
                    "       fill-color: black;" +
                    "}" +
                    "node.marked {" +
                    "       fill-color: red;" +
                    "}";

    protected String labelStyleSheet =
            "edge {" +
                    " shape: line;" +
                    " size: 1px, 0px;" +
                    " fill-color: brown3;" +
                    ";" +
                    " text-mode: normal;" +
                    " text-alignment: center;" +
                    "}";

    public void explore(Node source) {
        Iterator<? extends Node> k = source.getBreadthFirstIterator();

        while (k.hasNext()) {
            Node next = k.next();
            next.setAttribute("ui.class", "marked");
            sleep();
        }
    }

    protected void sleep() {
        try { Thread.sleep(1000); } catch (Exception e) {}
    }


    private static void addAllEdgeInstances (Graph graph, boolean isDirected){ //run this function s
        Collection<Node> noteSet = graph.getNodeSet();

        for (Iterator iterator = noteSet.iterator(); iterator.hasNext();) {
            Node startNode = (Node) iterator.next();

            for (Iterator<Node> iterator2 = noteSet.iterator(); iterator2.hasNext();) {
                Node endNode = (Node) iterator2.next();

                if (!(startNode == endNode)){
                    graph.addEdge(startNode.toString().concat(endNode.toString()), startNode, endNode, isDirected);
                }

            }
        }
    }
}


