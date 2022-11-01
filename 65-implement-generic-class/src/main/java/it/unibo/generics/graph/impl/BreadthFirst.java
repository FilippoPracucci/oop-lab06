package it.unibo.generics.graph.impl;

import java.util.Deque;

import it.unibo.generics.graph.api.FrontierAccumulationStrategy;

public class BreadthFirst<S> implements FrontierAccumulationStrategy<S> {

    private static final BreadthFirst<?> INSTANCE = new BreadthFirst<>();

    @SuppressWarnings("unchecked")
    public static <S> BreadthFirst<S> getInstance() {
        return (BreadthFirst<S>) INSTANCE;
    }

    @Override
    public void addToFrontier(Deque<? super S> frontier, S step) {
        frontier.addLast(step);
    }
    
}
