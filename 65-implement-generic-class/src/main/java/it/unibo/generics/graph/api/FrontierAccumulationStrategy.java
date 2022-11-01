package it.unibo.generics.graph.api;

import java.util.Deque;

public interface FrontierAccumulationStrategy<S> {

    void addToFrontier(Deque<? super S> frontier, S step);
}
