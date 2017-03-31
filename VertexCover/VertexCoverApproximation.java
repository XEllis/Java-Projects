import java.lang.Integer;
import java.lang.System;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.Map;
import java.io.File;
import java.io.PrintStream;
import java.io.FileNotFoundException;

public class VertexCoverApproximation {
    public static ArrayList<Integer> greedyVertex(HashMap<Integer, ArrayList<Integer>> graph, int edge) {
        ArrayList<Integer> vertexCover = new ArrayList<Integer>();
        while (edge > 0) {
            int degree = Integer.MIN_VALUE;
            Integer vertex = new Integer(Integer.MIN_VALUE);
            Set<Map.Entry<Integer, ArrayList<Integer>>> s = graph.entrySet();
            for (Map.Entry<Integer, ArrayList<Integer>> e: s) {
                if (e.getValue().size() > degree) {
                    degree = e.getValue().size();
                    vertex = e.getKey();
                }
            }
            vertexCover.add(vertex);
            edge = edge - graph.get(vertex).size();
            for (Integer i: graph.get(vertex)) {
                graph.get(i).remove(vertex);
                if (graph.get(i).isEmpty()) {
                    graph.remove(i);
                }
            }
            graph.remove(vertex);
        }
        return vertexCover;
    }

    public static ArrayList<Integer> greedyEdge(HashMap<Integer, ArrayList<Integer>> graph, int edge) {
        ArrayList<Integer> vertexCover = new ArrayList<Integer>();
        while (edge > 0) {
            Random r = new Random(System.currentTimeMillis());
            ArrayList<Integer> k = new ArrayList<Integer>(graph.keySet());
            Integer u = k.get(r.nextInt(k.size()));
            Integer v = graph.get(u).get(r.nextInt(graph.get(u).size()));
            vertexCover.add(u);
            vertexCover.add(v);
            edge = edge - graph.get(u).size() - graph.get(v).size() + 1;
            for (Integer i: graph.get(u)) {
                graph.get(i).remove(u);
                if (graph.get(i).isEmpty()) {
                    graph.remove(i);
                }
            }
            graph.remove(u);
            if (graph.containsKey(v)) {
                for (Integer j: graph.get(v)) {
                    graph.get(j).remove(v);
                    if (graph.get(j).isEmpty()) {
                        graph.remove(j);
                    } 
                }
                graph.remove(v);
            }
        }
        return vertexCover;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File(args[0]));
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();
        int edge = 0;
        while (input.hasNext()) {
            Integer a = new Integer(input.next());
            Integer b = new Integer(input.next());
            if (graph.containsKey(a)) {
                graph.get(a).add(b);
            } else {
                ArrayList<Integer> c = new ArrayList<Integer>();
                c.add(b);
                graph.put(a, c);
            }
            if (graph.containsKey(b)) {
                graph.get(b).add(a);
            } else {
                ArrayList<Integer> d = new ArrayList<Integer>();
                d.add(a);
                graph.put(b, d);
            }
            edge++;
        }
        PrintStream output = new PrintStream(new File("approxOutput.txt"));
        ArrayList<Integer> vertexCover = new ArrayList<Integer>();
        int algorithm = Integer.parseInt(args[1]);
        if (algorithm == 0) {
            vertexCover = greedyVertex(graph, edge);
        } else if (algorithm == 1) {
            vertexCover = greedyEdge(graph, edge);
        }
        else {
            System.out.println("Run command is \"java VertexCoverApproximation [graph-file] [0|1]\".");
        }
        for (Integer i: vertexCover) {
            output.println(i);
        }
    }
}
