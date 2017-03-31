import java.lang.Integer;
import java.lang.System;
import java.lang.String;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.io.File;
import java.io.PrintStream;
import java.io.FileNotFoundException;

public class VertexCoverVerification {
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
        Scanner candidate = new Scanner(new File(args[1]));
        int k = Integer.parseInt(args[2]);
        while (candidate.hasNextLine()) {
            String line = candidate.nextLine();
            String[] vc = line.split(" ");
            if (vc.length != k) {
                System.out.println("no");
            } else {
                int cover = 0;
                HashSet<Integer> vertexCover = new HashSet<Integer>();
                for (int j = 0; j < k; j++) {
                    vertexCover.add(new Integer(vc[j]));
                }
                if (vertexCover.size() != k) {
                    System.out.println("no");
                } else {
                    ArrayList<Integer> t = new ArrayList<Integer>();
                    for (Integer z: vertexCover) {
                        t.add(z);
                        for (Integer zz: graph.get(z)) {
                            if (!t.contains(zz)) {
                                cover++;
                            } 
                        }
                    }
                    if (cover != edge) {
                        System.out.println("no");
                    } else {
                        System.out.println("yes");
                    }
                }
            }
        }
    }
}
