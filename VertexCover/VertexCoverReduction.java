import java.lang.Integer;
import java.lang.System;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import java.io.File;
import java.io.PrintStream;
import java.io.FileNotFoundException;

public class VertexCoverReduction {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File(args[0]));
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();
        int vertex = 0;
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
        }
        Set<Integer> keys = graph.keySet();
        vertex = keys.size();
        Set<Map.Entry<Integer, ArrayList<Integer>>> s = graph.entrySet();
        HashMap<Integer, ArrayList<Integer>> cgraph = new HashMap<Integer, ArrayList<Integer>>();
        for (Map.Entry<Integer, ArrayList<Integer>> e: s) {
             ArrayList<Integer> x = new ArrayList<Integer>();
             Integer y = e.getKey();
             boolean f = false; 
             for (Integer i: keys) {
                 if ((i != y) && (!e.getValue().contains(i))) {
                     x.add(i);
                     f = true;
                 }
             }
             if (f) {
                 cgraph.put(y, x);
             }
        }
        PrintStream output = new PrintStream(new File("edgeListFilename"));
        int k = Integer.parseInt(args[1]);
        int ck = vertex - k;
        Set<Map.Entry<Integer, ArrayList<Integer>>> cs = cgraph.entrySet();
        ArrayList<Integer> p = new ArrayList<Integer>();
        for (Map.Entry<Integer, ArrayList<Integer>> ce: cs) {
            Integer m = ce.getKey();
            p.add(m);
            for (Integer n: ce.getValue()) {
                if (!p.contains(n)) {
                    output.print(m);
                    output.print(" ");
                    output.println(n);
                }
            }
        }
        CliqueDecision.decide("edgeListFilename", ck);
    }
}
