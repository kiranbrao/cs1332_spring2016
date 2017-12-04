import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.ArrayList;

/**
 * Your implementations of various graph algorithms.
 *
 * @author Kiran Rao
 * @version 1.0
 */
public class GraphAlgorithms {

    /**
     * Perform breadth first search on the given graph, starting at the start
     * Vertex.  You will return a List of the vertices in the order that
     * you visited them.  Make sure to include the starting vertex at the
     * beginning of the list.
     *
     * When exploring a Vertex, make sure you explore in the order that the
     * adjacency list returns the neighbors to you.  Failure to do so may
     * cause you to lose points.
     *
     * You may import/use {@code java.util.Queue}, {@code java.util.Set},
     * {@code java.util.Map}, {@code java.util.List}, and any classes
     * that implement the aforementioned interfaces.
     *
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph we are searching
     * @param <T> the data type representing the vertices in the graph.
     * @return a List of vertices in the order that you visited them
     */
    public static <T> List<Vertex<T>> breadthFirstSearch(Vertex<T> start,
            Graph<T> graph) {
        if ((start == null) || (graph == null)) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        if (!(graph.getAdjacencyList().containsKey(start))) {
            throw new IllegalArgumentException("Vertex is not in the graph");
        }
        Map<Vertex<T>, List<VertexDistancePair<T>>> adjacencyList
                = graph.getAdjacencyList();
        List<Vertex<T>> returnList = new ArrayList<>();
        Set<Vertex<T>> visited = new HashSet<>();
        Queue<Vertex<T>> openVertices = new LinkedList<>();
        openVertices.add(start);
        Vertex<T> currentVertex = null;
        boolean skipThis;
        while (!(openVertices.isEmpty())) {
            skipThis = false;
            currentVertex = openVertices.remove();
            if (visited.contains(currentVertex)) {
                skipThis = true;
            }
            List<VertexDistancePair<T>> connectedVertexList
                    = adjacencyList.get(currentVertex);
            if ((connectedVertexList != null) && (!skipThis)) {
                for (VertexDistancePair<T> currentConnectedVertex
                        : connectedVertexList) {
                    if (!(visited.contains(currentConnectedVertex
                            .getVertex()))) {
                        openVertices.add(currentConnectedVertex.getVertex());
                    }

                }
            }
            if (!(skipThis)) {
                visited.add(currentVertex);
                returnList.add(currentVertex);
            }
        }
        return returnList;
    }

    /**
     * Perform depth first search on the given graph, starting at the start
     * Vertex.  You will return a List of the vertices in the order that
     * you visited them.  Make sure to include the starting vertex at the
     * beginning of the list.
     *
     * When exploring a Vertex, make sure you explore in the order that the
     * adjacency list returns the neighbors to you.  Failure to do so may
     * cause you to lose points.
     *
     * You MUST implement this method recursively.
     * Do not use any data structure as a stack to avoid recursion.
     * Implementing it any other way WILL cause you to lose points!
     *
     * You may import/use {@code java.util.Set}, {@code java.util.Map},
     * {@code java.util.List}, and any classes that implement the
     * aforementioned interfaces.
     *
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph we are searching
     * @param <T> the data type representing the vertices in the graph.
     * @return a List of vertices in the order that you visited them
     */
    public static <T> List<Vertex<T>> depthFirstSearch(Vertex<T> start,
            Graph<T> graph) {
        if ((start == null) || (graph == null)) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        if (!(graph.getAdjacencyList().containsKey(start))) {
            throw new IllegalArgumentException("Vertex is not in the graph");
        }
        Map<Vertex<T>, List<VertexDistancePair<T>>> adjacencyList
                = graph.getAdjacencyList();
        List<Vertex<T>> returnList = new ArrayList<>();
        Set<Vertex<T>> visited = new HashSet<>();
        return depthFirstSearch(start, adjacencyList, visited, returnList);
    }

    /**
     * Perform depth first search by recursively building an implicit stack.
     *
     * @param currentVertex the Vertex that is currently being explored
     * @param adjacencyList the adjacency list that explains how vertices
     *                      of the graph are connected to other vertices
     * @param visited a set containing the information of visited vertices
     * @param returnList a List of vertices in the order that you visited them
     *                   that retains information through recursive calls
     * @param <T> the data type representing the vertices in the graph.
     * @return a List of vertices in the order that you visited them
     */
    private static <T> List<Vertex<T>>
        depthFirstSearch(Vertex<T> currentVertex,
            Map<Vertex<T>, List<VertexDistancePair<T>>> adjacencyList,
            Set<Vertex<T>> visited, List<Vertex<T>> returnList) {
        returnList.add(currentVertex);
        visited.add(currentVertex);
        List<VertexDistancePair<T>> connectedVertexList
                = adjacencyList.get(currentVertex);
        if ((connectedVertexList != null)) {
            for (VertexDistancePair<T> currentConnectedVertex
                    : connectedVertexList) {
                if (!(visited.contains(currentConnectedVertex.getVertex()))) {
                    depthFirstSearch(currentConnectedVertex.getVertex(),
                            adjacencyList, visited, returnList);
                }
            }
        }
        return returnList;
    }


    /**
     * Find the shortest distance between the start vertex and all other
     * vertices given a weighted graph where the edges only have positive
     * weights.
     *
     * Return a map of the shortest distances such that the key of each entry is
     * a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing infinity)
     * if no path exists. You may assume that going from a vertex to itself
     * has a distance of 0.
     *
     * There are guaranteed to be no negative edge weights in the graph.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Map}, and any class that implements the aforementioned
     * interface.
     *
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph we are searching
     * @param <T> the data type representing the vertices in the graph.
     * @return a map of the shortest distances from start to every other node
     *         in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
            Graph<T> graph) {
        if ((start == null) || (graph == null)) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        if (!(graph.getAdjacencyList().containsKey(start))) {
            throw new IllegalArgumentException("Vertex is not in the graph");
        }
        Map<Vertex<T>, List<VertexDistancePair<T>>> adjacencyList
                = graph.getAdjacencyList();
        Map<Vertex<T>, Integer> returnMap = new HashMap<>();
        for (Vertex<T> vertex : adjacencyList.keySet()) {
            if (vertex.equals(start)) {
                returnMap.put(vertex, 0);
            } else {
                returnMap.put(vertex, Integer.MAX_VALUE);
            }
        }
        Queue<VertexDistancePair<T>> weightPQ = new PriorityQueue<>();
        weightPQ.add(new VertexDistancePair<T>(start, 0));
        for (VertexDistancePair<T> linkedPair : adjacencyList.get(start)) {
            weightPQ.add(linkedPair);
        }
        Set<Vertex<T>> visited = new HashSet<>();
        visited.add(start);
        int counter = 0;
        while ((counter < adjacencyList.size()) && (!(weightPQ.isEmpty()))) {
            VertexDistancePair<T> currentPair = weightPQ.remove();
            if (!(visited.contains(currentPair.getVertex()))) {
                visited.add(currentPair.getVertex());
                returnMap.put(currentPair.getVertex(),
                        currentPair.getDistance());
                counter++;
                for (VertexDistancePair<T> linkedPair
                        : adjacencyList.get(currentPair.getVertex())) {
                    if (!(visited.contains(linkedPair.getVertex()))) {
                        int distanceToCurrent
                                = returnMap.get(currentPair.getVertex());
                        int distanceUtoV = linkedPair.getDistance();
                        int storedDistance
                                = returnMap.get(linkedPair.getVertex());
                        if ((distanceToCurrent + distanceUtoV)
                                < storedDistance) {
                            weightPQ.add(new VertexDistancePair<T>(linkedPair
                                    .getVertex(), (distanceToCurrent
                                    + distanceUtoV)));
                        }
                    }
                }
            }
        }
        return returnMap;
    }

    /**
     * Run Prim's algorithm on the given graph and return the minimum spanning
     * tree in the form of a set of Edges.  If the graph is disconnected, and
     * therefore there is no valid MST, return null.
     *
     * When exploring a Vertex, make sure you explore in the order that the
     * adjacency list returns the neighbors to you.  Failure to do so may
     * cause you to lose points.
     *
     * You may assume that for a given starting vertex, there will only be
     * one valid MST that can be formed. In addition, only an undirected graph
     * will be passed in.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Set}, and any class that implements the aforementioned
     * interface.
     *
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph we are searching
     * @param <T> the data type representing the vertices in the graph.
     * @return the MST of the graph; null if no valid MST exists.
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        if ((start == null) || (graph == null)) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        if (!(graph.getAdjacencyList().containsKey(start))) {
            throw new IllegalArgumentException("Vertex is not in the graph");
        }
        Map<Vertex<T>, List<VertexDistancePair<T>>> adjacencyList
                = graph.getAdjacencyList();
        int numVertices = adjacencyList.keySet().size();
        if (numVertices == 1) {
            return null;
        }
        Queue<Edge<T>> edgePQ = new PriorityQueue<>();
        Set<Vertex<T>> visited = new HashSet<>();
        visited.add(start);
        Set<Edge<T>> returnSet = new HashSet<>();
        Vertex<T> currentVertex = start;
        for (VertexDistancePair<T> currentConnectedVertex
                : adjacencyList.get(currentVertex)) {
            edgePQ.add(new Edge<T>(currentVertex,
                    currentConnectedVertex.getVertex(),
                    currentConnectedVertex.getDistance(), false));
        }
        while (!(edgePQ.isEmpty()) && ((returnSet.size() + 1) < numVertices)) {
            Edge<T> currentEdge = edgePQ.remove();
            if (!(visited.contains(currentEdge.getV()))) {
                returnSet.add(currentEdge);
                currentVertex = currentEdge.getV();
                visited.add(currentEdge.getV());
                for (VertexDistancePair<T> currentConnectedVertex
                        : adjacencyList.get(currentVertex)) {
                    if (!(visited.contains(currentConnectedVertex
                            .getVertex()))) {
                        edgePQ.add(new Edge<T>(currentVertex,
                                currentConnectedVertex.getVertex(),
                                currentConnectedVertex.getDistance(), false));
                    }
                }
            }
        }
        if (returnSet.size() + 1 < numVertices) {
            return null;
        }
        return returnSet;
    }
}
