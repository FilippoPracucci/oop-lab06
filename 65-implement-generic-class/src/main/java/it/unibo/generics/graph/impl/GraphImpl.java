package it.unibo.generics.graph.impl;

import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import it.unibo.generics.graph.api.FrontierAccumulationStrategy;
import it.unibo.generics.graph.api.Graph;

public class GraphImpl<N> implements Graph<N> {

    private Map<N, Set<N>> edges = new LinkedHashMap<>();
    private final FrontierAccumulationStrategy<Step<N>> strategy;

    public GraphImpl(final FrontierAccumulationStrategy<Step<N>> s) {
        this.strategy = Objects.requireNonNull(s);
    }

    /**
     * New graph with DepthFirst strategy
     */
    public GraphImpl() {
        this(DepthFirst.getInstance());
    }

    @Override
    public void addNode(final N node) {
        this.edges.putIfAbsent(node, new HashSet<>());
    }

    @Override
    public void addEdge(final N source, final N target) {
        if (nodesExist(source, target)) {
            this.edges.get(source).add(target);
        }
    }

    @Override
    public Set<N> nodeSet() {
        return new HashSet<>(this.edges.keySet());
    }

    @Override
    public Set<N> linkedNodes(final N node) {
        return this.edges.get(node);
    }

    @Override
    public List<N> getPath(final N source, final N target) {
        if (nodesExist(source, target)) {
            return pathSearch(source, target);
        } else {
            return Collections.emptyList();
        }
    }
    
    @SafeVarargs
    private boolean nodesExist(final N... nodes) {
        for (final N n: nodes) {
            if (!edges.containsKey(n)) {
                throw new IllegalArgumentException("The node " + n + " doesn't exist");
            }
        }
        return true;
    }

    /* Generic searching algorithm.
     * Idea developed from https://artint.info/html/ArtInt_51.html
     */
    private List<N> pathSearch(final N source, final N target) {
        final Deque<Step<N>> frontier = new LinkedList<>();
        frontier.add(new Step<>(source));
        final Set<N> nodesAlreadyVisited = new HashSet<>();
        while (!frontier.isEmpty() && nodesAlreadyVisited.size() < getNodesCount()) {
            final Step<N> lastStep = frontier.removeFirst();
            final N currentNode = lastStep.getPosition();
            if (currentNode.equals(target)) {
                return lastStep.getPath();
            } else if (!nodesAlreadyVisited.contains(currentNode)) {
                nodesAlreadyVisited.add(currentNode);
                expandFrontier(frontier, lastStep);
            }
        }
        return Collections.emptyList();
    }

    private int getNodesCount() {
        return this.edges.keySet().size();
    }

    private void expandFrontier(final Deque<Step<N>> frontier, final Step<N> lastStep) {
        final N currentNode = lastStep.getPosition();
        for (final N reachableNode: linkedNodes(currentNode)) { 
            strategy.addToFrontier(frontier, new Step<>(lastStep, reachableNode));
        }
    }
}
