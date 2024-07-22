# Hundred Prisoners Riddle
This riddle is well described on: [wiki](https://en.wikipedia.org/wiki/100_prisoners_problem). To summarize, 100 prisoners can get out of prison, if they all go into a room and are able to open a box which has a number on it, and a slip inside it w/ the number of the prisoner. If even one prisoner fails to do so, they all get executed. Every prisoner can open upto 50 boxes on his/her turn.

I have to give credit to Veritasium's [video](https://www.youtube.com/watch?v=iSNsgj1OCLA) which prompted great interest from my kids into calculating the probability of the boxes, which is the reason I created this program.

The problem here is to figure a strategy which will maximize their chances of surviving. It is understood that they can never be 100% successful.

# HundredPrisoners

This Java project is an attempt to show a success percentage, given 3 knobs: number of prisoners (hence the number of boxes), number of attempts allowed (hence the number of loops allowed), and the number of runs of this problem (this is just to get a sufficiently large sample, to get closer to a real probability).

## Experiment
This package runs the experiement based on the strategy described on this [wiki](https://en.wikipedia.org/wiki/100_prisoners_problem). The idea is to run a lot of experiments, record success or failure, and then calculate the percentage of success. Also, adjust the 3 knobs and see if it has any effect on the success percentage.

The experiment here is basically when every prisoner goes and opens a box starting with the number of the prisoner him/herself, finds a slip w/ a number on it, and then opens the box w/ the number on the slip. They continue until they reach a box which has the number of the prisoner him/herself.

# Running HundredPrisoners

## Pre-requisites

 * You need [JRE](https://www.java.com/en/download/manual.jsp) installed wherever you want to run this
 * Minimum JRE version = 13 | Recommended Python version = 21
 * You need [gradle](https://gradle.org/) installed 
 * Note: For MacOS, if you have Homebrew installed ([see how](https://brew.sh/)), a simple `brew install openjdk gradle` should do the trick.
   * For Ubuntu: See [this](https://linuxize.com/post/how-to-install-gradle-on-ubuntu-20-04/).
   * For other OSes, use [this](https://duckduckgo.com).

## Running

 * Ensure pre-requisites (above).
 * Check out the repository.
 * Issue: `./gradlew run` on the command line from the project root (`HundredPrisoners/`).
   * Note: For Windows, issue: `gradlew.bat run` from the project root.

# Design

## Getting Started (To Develop / Contribute)

### License

See LICENSE.

### Where To Develop

Best place to develop is using Visual Studio Code: [here](https://code.visualstudio.com/).

### Structure

This project is packaged in `net.fryol.jtoys.hprisoners.prison` and `net.fryol.jtoys.hprisoners.riddle`.

 * **HundredPrisoners/**
   
   Project root.
 
 * **HundredPrisoners/build.gradle**

   This is the build file, carefull crafted to import the jgrapht (SimpleGraph) dependency, and to be able to issue these commands:
   * `./gradlew clean`
   * `./gradlew build`
   * `./gradlew run`

 * *HundredPrisoners/src/main/java/net/fryol/jtoys/hprisoners/***prison**
 
   The `prison` package has four classes: `Box`, `BoxLoops`, `PrisonFloor` and `Slip`. Slips are inside Boxes, Boxes are on PrisonFloor, and BoxLoops are made when a prisoner goes from Box to Box.

 * *HundredPrisoners/src/main/java/net/fryol/jtoys/hprisoners/***riddle**
 
   The `riddle` package has two classes: `Riddle` and `RiddleExperiment`. **`Riddle`** is the main class for this project, and is the **entry point.** `RiddleExperiment` is a convenience class that records data about each run (experiment). See the **Experiment** section above.

### Code Flow
As described earlier, the main class (entry point) is the `Riddle` class. This has the `main()` method. The `Riddle` class has a `runExperiment()` method, which takes the 3 knobs: how many experiments to run, number of boxes (or prisoners), and number of attempts (loops) allowed. The `runExperiment()` method instantiates a `PrisonFloor`, which is then used to instantiate a `BoxLoops` object. This is because you need to have a `PrisonFloor`, and then you can calculate how many `BoxLoops` will happen there.

`PrisonFloor` internally uses `Box` and `Slip` objects to create `numBoxes` number of boxes (and the same number of slips). It keeps the list of all the `Box`'es it has, and exposes this list by the `getBoxesList()` method.

`BoxLoops` uses a `SimpleGraph` object to keep a graph of boxes (boxLoop). Each `Box` is a vertex, and when a `Slip` in the `Box` points to a different `Box`, a new vertex (`Box`) is added to the `boxLoop`, and a `DefaultEdge` is added between the two vertices (`Box`'es).

A list of all the `boxLoop`'s is kept in a `boxLoopsList`, because there is a possibility of more than one loop across all boxes on the `PrisonFloor`. All these loops are computed via the `computeLoops()` method, which has the core logic of the experiment.


### Rough Design Visualization
```
  Riddle              <---------------------------------------------------------------.
     |                                                                                 |
     `--runExperiment()--> PrisonFloor                                                 |
           |                |                                                          |
           |                `----> BoxLoops (SimpleGraph[Boxes, Edgdes]                |
           |                        |                                                  |
      multiple experiments          `--computeLoop()--> SimpleGraph[Boxes, Edges]      |
           |                                |                                          |
           |                                `----> List<BoxLoops>                      |
           |                                                                           |
           |                                                                           |
            `--------List<RiddleExperiment> --------- used to calculate success %age --'
```
