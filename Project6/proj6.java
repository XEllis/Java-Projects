// compile command: javac proj3.java
// run command: java proj3 < input-file-name >| output-file-name

import java.util.Scanner;

class Edge {
    private int vertex1;
    private int vertex2;
    private double weight;
    private Edge next;
    public Edge(int v1, int v2, double w) {
        vertex1 = v1;
        vertex2 = v2;
        weight = w;
        next = null;
    }
    public int getV1() {
        return vertex1;
    }
    public int getV2() {
        return vertex2;
    }
    public double getWeight() {
        return weight;
    }
    public Edge getNext() {
        return next;
    }
    public void setNext(Edge e) {
        next = e;
    }
}

class Heap {
    private int size = 0;
    private Edge[] heap= new Edge[5000];
    public Heap() {}
    public void insert(Edge e) {
        heap[size] = e;
        size++;
        upHeap(size-1);
    }
    public void upHeap(int i) {
        if (i > 0) {
            if (heap[(i-1)/2].getWeight() > heap[i].getWeight()) {
                Edge tmp = heap[i];
                heap[i] = heap[(i-1)/2];
                heap[(i-1)/2] = tmp;
                upHeap((i-1)/2);
            }
        }
    }
    public Edge deleteMin() {
        Edge result = heap[0];
        size--;
        heap[0] = heap[size];
        downHeap(0);
        return result;
    }
    public void downHeap(int m) {
        int i = 0;
        if (2*m+2 < size) {
            if (heap[2*m+2].getWeight() <= heap[2*m+1].getWeight()) {
                i = 2*m+2;
            }
            else {
                i = 2*m+1;
            }
        }
        else if (2*m+1 < size) {
            i = 2*m+1;
        }
        if (i > 0 && heap[m].getWeight() > heap[i].getWeight()) {
            Edge tmp = heap[i];
            heap[i] = heap[m];
            heap[m] = tmp;
            downHeap(i);
        }
    }
    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.printf("%4d %4d%n", heap[i].getV1(), heap[i].getV2());
        }
    }
}

class UpTree {
    private int[] upTree;
    private int components;
    public UpTree(int n) {
        upTree = new int[n];
        for (int i = 0; i < n; i++) {
            makeSet(i);
        }
        components = n;
    }
    public void makeSet(int i) {
        upTree[i] = -1;
    }
    public int getComponents() {
        return components;
    }
    public int union(int v1, int v2) {
        if (upTree[v1]*(-1) >= upTree[v2]*(-1)) {
            upTree[v1] = upTree[v1]+upTree[v2];
            upTree[v2] = v1;
            components--;
            return v1;
        }
        else {
            upTree[v2] = upTree[v1]+upTree[v2];
            upTree[v1] = v2;
            components--;
            return v2;
        }
    }
    public int find(int v) {
        while (upTree[v] >= 0) {
            v = upTree[v];
        }
        return v;
    }
    public int pathCompressFind(int v) {
        int result = find(v);
        while (v != result) {
            int tmp = v;
            v = upTree[v];
            upTree[tmp] = result;
        }
        return result;
    }
}

class AdjacencyList {
    private Edge[] adjacencyList = new Edge[1000];
    private int size = 0;
    public AdjacencyList() {}
    public void setSize(int n) {
        size = n;
    }
    public void insertE(Edge e) {
        int v1 = e.getV1();
        int v2 = e.getV2();
        double w = e.getWeight();
        Edge e1 = new Edge(v1, v2, w);
        Edge h1 = adjacencyList[v1];
        Edge h2 = h1;
        while (h1 != null && v2 > h1.getV2()) {
            h2 = h1;
            h1 = h1.getNext();
        }
        if (h2 == null) {
            adjacencyList[v1] = e1;
        }
        else if (h1 == h2) {
            e1.setNext(h1);
            adjacencyList[v1] = e1;
        }
        else {
            h2.setNext(e1);
            e1.setNext(h1);
        }
        Edge e2 = new Edge(v2, v1, w);
        Edge h3 = adjacencyList[v2];
        Edge h4 = h3;
        while (h3 != null && v1 > h3.getV2()) {
            h4 = h3;
            h3 = h3.getNext();
        }
        if (h4 == null) {
            adjacencyList[v2] = e2;
        }
        else if (h3 == h4) {
            e2.setNext(h3);
            adjacencyList[v2] = e2;
        }
        else {
            h4.setNext(e2);
            e2.setNext(h3);
        }
    }
    public void printAdjacencyList() {
        for (int i = 0; i < size; i++) {
            Edge e = adjacencyList[i];
            while (e != null) {
                System.out.printf("%4d", e.getV2());
                e = e.getNext();
                if (e != null) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}

public class proj6 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int v1 = 0;
        int v2 = 0;
        double w = 0.0;
        int maxVID = 0;
        Heap heap = new Heap();
        AdjacencyList adj = new AdjacencyList();
        while (input.hasNext()) {
            v1 = input.nextInt();
            if (v1 == -1) {
                break;
            }
            v2 = input.nextInt();
            w = input.nextDouble();
            if (v2 < v1) {
                int tmp = v1;
                v1 = v2;
                v2 = tmp;
            }
            if (v2 > maxVID) {
                maxVID = v2;
            }
            Edge e = new Edge(v1, v2, w);
            heap.insert(e);
            adj.insertE(e);
        }
        heap.printHeap();
        adj.setSize(maxVID+1);
        UpTree upTree = new UpTree(maxVID+1);
        Edge MST = new Edge(-1, -1, -1.0);
        while (upTree.getComponents() > 1) {
            Edge min = heap.deleteMin();
            int V1 = min.getV1();
            int V2 = min.getV2();
            int u = upTree.pathCompressFind(V1);
            int v = upTree.pathCompressFind(V2);
            if (u != v) {
                upTree.union(u, v);
                Edge head = MST;
                Edge tmp = head;
                while (head != null && (V1 > head.getV1() || (V1 == head.getV1() && V2 > head.getV2()))) {
                    tmp = head;
                    head = head.getNext();
                }
                tmp.setNext(min);
                min.setNext(head);
            }
        }
        Edge h = MST;
        h = h.getNext();
        while (h != null) {
            System.out.printf("%4d %4d%n", h.getV1(), h.getV2());
            h = h.getNext();
        }
        adj.printAdjacencyList();
    }
}
