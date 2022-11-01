package it.unibo.generics.graph.impl;

import java.util.Deque;

import it.unibo.generics.graph.api.FrontierAccumulationStrategy;

public class DepthFirst<S> implements FrontierAccumulationStrategy<S> {

    private static final DepthFirst<?> INSTANCE = new DepthFirst<>();

    @SuppressWarnings("unchecked")
    public static <S> DepthFirst<S> getInstance() {
        return (DepthFirst<S>) INSTANCE;
    }

    @Override
    public void addToFrontier(Deque<? super S> frontier, S step) {
        frontier.addFirst(step);
    }
    
}
