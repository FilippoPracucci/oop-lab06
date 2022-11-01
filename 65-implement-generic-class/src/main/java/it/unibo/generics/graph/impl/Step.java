package it.unibo.generics.graph.impl;

import java.util.LinkedList;
import java.util.List;

public class Step<N> {
    
    private final Step<N> prevSteps;
    private final N position;

    public Step(final Step<N> previousSteps, final N position) {
        this.prevSteps = previousSteps;
        this.position = position;
    }

    public Step(final N position) {
        this(null, position);
    }

    public List<N> getPath() {
        final List<N> result = new LinkedList<>();
        Step<N> curr = this;
        do {
            result.add(0, curr.position);
            curr = curr.prevSteps;
        } while (curr != null);
        return result;
    }

    public N getPosition() {
        return this.position;
    }

    @Override
    public String toString() {
        final List<String> elements = new LinkedList<>();
        for (final N node: getPath()) {
            elements.add(node.toString());
        }
        return String.join(" -> ", elements);
    }

    
}
