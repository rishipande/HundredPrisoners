package net.fryol.jtoys.hprisoners.prison;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class BoxLoops {
    private SimpleGraph<Box, DefaultEdge> boxLoop;
    private List<SimpleGraph<Box, DefaultEdge>> boxLoopsList;
    private int nextBoxIndex;

    public BoxLoops(PrisonFloor prisonFlr) {
        this.nextBoxIndex = 0;
        this.boxLoop = new SimpleGraph<>(DefaultEdge.class);
        this.boxLoopsList = new ArrayList<>();

        this.computeLoops(prisonFlr.getBoxesList(), this.nextBoxIndex);
    }

    
    public BoxLoops(PrisonFloor prisonFlr, int nextBoxIndex) {
        this.nextBoxIndex = nextBoxIndex;
        this.boxLoop = new SimpleGraph<>(DefaultEdge.class);
        this.boxLoopsList = new ArrayList<>();
        
        this.computeLoops(prisonFlr.getBoxesList(), this.nextBoxIndex);
    }

    private void computeLoops(List<Box> boxList, int nextBoxIndex) {
        HashMap<Box, Boolean> boxesVisited = new HashMap<>();

        this.nextBoxIndex = nextBoxIndex;

        try {
            boxList.forEach(box -> 
                boxesVisited.put(box, false)
            );

            this.nextBoxIndex = nextBoxIndex;
            
            while(boxesVisited.containsValue(false)) {
                Box currentBox = boxList.get(this.nextBoxIndex);
                Boolean currentBoxVisited = boxesVisited.get(currentBox);            

                // cleaning this object up every time the loop initializes 
                this.boxLoop = null;
                this.boxLoop = new SimpleGraph<>(DefaultEdge.class);
                
                while(currentBox.getBoxIndex() == this.nextBoxIndex && Boolean.FALSE.equals(currentBoxVisited)) {
                    boxesVisited.put(currentBox, true);

                    // avoid creating a loop from the same vertex to itself
                    // resolving: java.lang.IllegalArgumentException: loops not allowed
                    // https://jgrapht.org/guide/UserOverview#graph-structures has more 
                    // info on which of these allow self-loops
                    if(currentBox.getBoxIndex() == currentBox.getSlipIndex()) {
                        this.boxLoop.addVertex(currentBox);
                        break;
                    }

                    // opening the current box to find out where to go next
                    this.nextBoxIndex = currentBox.getSlipIndex();

                    this.boxLoop.addVertex(currentBox);
                    this.boxLoop.addVertex(boxList.get(this.nextBoxIndex));
                    this.boxLoop.addEdge(currentBox, boxList.get(this.nextBoxIndex));

                    currentBox = boxList.get(this.nextBoxIndex);
                    currentBoxVisited = boxesVisited.get(currentBox);
                }

                this.boxLoopsList.add(this.boxLoop);

                // find the next box that is not visited
                boxList.forEach(box -> {
                    if(Boolean.FALSE.equals(boxesVisited.get(box))) {
                        this.nextBoxIndex = box.getBoxIndex();
                        return;
                    }
                });
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }

        // this.boxLoopsList is set when this finishes.
    }

    public int getLargestLoop() {
        int largestLoop = 0;

        for (SimpleGraph<Box, DefaultEdge> myBoxLoop : this.boxLoopsList) {
            Set<Box> vertices = myBoxLoop.vertexSet();
            if(vertices.size() > largestLoop) {
                largestLoop = vertices.size();
            }
        } 
        return largestLoop;
    }

    public List<SimpleGraph<Box, DefaultEdge>> getBoxLoops() {
        return this.boxLoopsList;
    }

    @Override
    public String toString() {
        StringBuilder loopString = new StringBuilder();
		String lineSeparator = System.lineSeparator();
        int increment = 1;

        for(SimpleGraph<Box, DefaultEdge> myBoxLoop : this.boxLoopsList) {
            Set<Box> vertices = myBoxLoop.vertexSet();
            Set<DefaultEdge> edges = myBoxLoop.edgeSet();

            loopString.append("Loop: " + increment + " | Size: " + vertices.size());
            loopString.append(lineSeparator);

            if(!edges.isEmpty()) {
                for (DefaultEdge edge : edges) {
                    Box source = myBoxLoop.getEdgeSource(edge);
                    Box target = myBoxLoop.getEdgeTarget(edge);
                    loopString.append(String.format("Box %02d -> Box %02d", source.getBoxNumber(), target.getBoxNumber()));
                    loopString.append(lineSeparator);
                }
            } else {
                for (Box vertex : vertices) {
                    loopString.append(String.format("Box %02d", vertex.getBoxNumber()));
                    loopString.append(lineSeparator);
                }
            }

            loopString.append(lineSeparator);
            loopString.append(lineSeparator);
            increment++;
        }
        return loopString.toString();
    }
}
