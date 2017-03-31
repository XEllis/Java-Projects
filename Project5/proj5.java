import java.util.Scanner;
import java.io.*;
import java.lang.Math;

class Tree {
    private static class Node {
        private char element;
        private int numChildren;
        private int mark;
        private Node parent;
        private Node[] children;
        public Node(char e, int n, Node[] c) {
            element = e;
            numChildren = n;
            mark = 0;
            parent = null;
            children = c;
        }
        public char getElement() {
            return element;
        }
        public int getNumChildren() {
            return numChildren;
        }
        public int getMark() {
            return mark;
        }
        public Node getParent() {
            return parent;
        }
        public Node[] getChildren() {
            return children;
        }
        public void setMark() {
            mark = 1;
        }
        public void eraseMark() {
            mark = 0;
        }
        public void setParent(Node p) {
            parent = p;
        }
    }
    private static class Queue {
        private int front = 0;
        private int rear = 0;
        private int size = 0;
        private Node[] queue = new Node[256];
        public Queue() {}
        public boolean isEmpty() {
            return (size == 0);
        }
        public void enqueue(Node n) {
            queue[rear] = n;
            rear++;
            size++;
        }
        public Node dequeue() {
            Node result = queue[front];
            front++;
            size--;
            return result;
        }
    }
    private Node root = null;
    public Tree() {}
    public Node buildTree(int size, int prestart, int poststart) {
        if (size == 1) {
            root = new Node(proj2.pretrav[prestart], 0, null);
            return root;
        }
        else {
            int numChildren = 0;
            int[] sz = new int[256];
            int[] pre = new int[256];
            int[] post = new int[256];
            int pres = prestart;
            int temp = poststart+size-1;
            prestart++;
            int pst = poststart;
            while (poststart != temp) {
                if (proj2.posttrav[poststart] == proj2.pretrav[prestart]) {
                    sz[numChildren] = poststart-pst+1;
                    pre[numChildren] = prestart;
                    post[numChildren] = pst;
                    numChildren++;
                    prestart = prestart+sz[numChildren-1];
                    pst = poststart+1;
                }
                poststart++;
            }
            Node[] c = new Node[numChildren];
            for (int i = 0; i < numChildren; i++) {
                c[i] = buildTree(sz[i], pre[i], post[i]);
            }
            root = new Node(proj2.pretrav[pres], numChildren, c);
            for (int j = 0; j < numChildren; j++) {
                c[j].setParent(root);
            }
            return root;
        }
    }
    public void queries(char A, char B, PrintStream output) {
        int pathA = 0;
        int pathB = 0;
        Queue q = new Queue();
        Node r = root;
        Node a = null;
        Node b = null;
        if (r != null) {
            q.enqueue(r);
            while (!q.isEmpty()) {
                if (a != null && b != null) {
                    break;
                }
                Node temp = q.dequeue();
                if (temp.getElement() == A) {
                    a = temp;
                }
                if (temp.getElement() == B) {
                    b = temp;
                }
                Node[] c = temp.getChildren();
                for (int i = 0; i < temp.getNumChildren(); i++) {
                    q.enqueue(c[i]);
                }
            }
        }
        a.setMark();
        Node p = a.getParent();
        while (p != null) {
            p.setMark();
            pathA++;
            p = p.getParent();
        }
        int m = b.getMark();
        if (m == 0) {
            b.setMark();
            Node pp = b.getParent();
            while (pp != null && pp.getMark() == 0) {
                pp.setMark();
                pathB++;
                pp = pp.getParent();
            }
            pathB++;
            if (pp != null) {
                pathA = 0;
                Node ap = a;
                while (ap != pp) {
                    ap = ap.getParent();
                    pathA++;
                }
            }
        } else {
            pathA = 0;
            Node app = a;
            while (app != b) {
                app = app.getParent();
                pathA++;
            }
        }
        if (pathA == 0) {
            if (pathB == 0) {
                output.println(A+" is "+B+".");
            } else if (pathB == 1){
                output.println(A+" is "+B+"'s parent.");
            } else if (pathB == 2){
                output.println(A+" is "+B+"'s grandparent.");
            } else if (pathB == 3){
                output.println(A+" is "+B+"'s great-grandparent.");
            } else {
                output.println(A+" is "+B+"'s (great)"+(pathB-2)+"-grandparent.");
            }
        } else if (pathA == 1) {
            if (pathB == 0) {
                output.println(A+" is "+B+"'s child.");
            } else if (pathB == 1){
                output.println(A+" is "+B+"'s sibling.");
            } else if (pathB == 2){
                output.println(A+" is "+B+"'s aunt/uncle.");
            } else {
                output.println(A+" is "+B+"'s (great)"+(pathB-2)+"-aunt/uncle.");
            }
        } else if (pathA == 2) {
            if (pathB == 0) {
                output.println(A+" is "+B+"'s grandchild.");
            } else if (pathB == 1){
                output.println(A+" is "+B+"'s niece/nephew.");
            } else {
                output.println(A+" is "+B+"'s 1th cousin "+(pathB-pathA)+" times removed.");
            }
        } else{
            if (pathB == 0) {
                output.println(A+" is "+B+"'s (great)"+(pathA-2)+"-grandchild.");
            } else if (pathB == 1) {
                output.println(A+" is "+B+"'s (great)"+(pathA-2)+"-niece/nephew.");
            } else {
                output.println(A+" is "+B+"'s "+(Math.min(pathA, pathB)-1)+"th cousin "+Math.abs(pathB-pathA)+" times removed.");
            }
        }
        Queue qq = new Queue();
        Node rr = root;
        if (rr != null) {
            qq.enqueue(rr);
            while (!qq.isEmpty()) {
                Node tmp = qq.dequeue();
                tmp.eraseMark();
                Node[] cc = tmp.getChildren();
                for (int j = 0; j < tmp.getNumChildren(); j++) {
                    qq.enqueue(cc[j]);
                }
            }
        }
    }
    public void levelOrder(PrintStream output) {
        Queue q = new Queue();
        Node r = root;
        if (r != null) {
            q.enqueue(r);
            while (!q.isEmpty()) {
                Node temp = q.dequeue();
                output.print(temp.getElement());
                Node[] c = temp.getChildren();
                for (int i = 0; i < temp.getNumChildren(); i++) {
                    q.enqueue(c[i]);
                }
                if (!q.isEmpty()) {
                    output.print(", ");
                } else {
                    output.println(".");
                }
            }
        }
    }
}

public class proj5 {
    public static char[] pretrav = new char[256];
    public static char[] posttrav = new char[256];
    public static void main(String[] args) throws FileNotFoundException {
        System.out.print("Enter the name of the input file: ");
        Scanner console = new Scanner(System.in);
        String userInput1 = console.next();
        Scanner input = new Scanner(new File(userInput1));
        System.out.print("Enter the name of the output file: ");
        String userInput2 = console.next();
        PrintStream output = new PrintStream(new File(userInput2));
        int preSize = 0;
        int postSize = 0;
        String s = input.nextLine();
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ',' || s.charAt(i) == '.') {
                pretrav[preSize] = s.charAt(i-1);
                preSize++;
            }
        }
        s = input.nextLine();
        for (int j = 1; j < s.length(); j++) {
            if (s.charAt(j) == ',' || s.charAt(j) == '.') {
                posttrav[postSize] = s.charAt(j-1);
                postSize++;
            }
        }
        int size = preSize;
        Tree tree = new Tree();
        tree.buildTree(size, 0, 0);
        char A = '?';
        char B = '?';
        while (input.hasNextLine()) {
            s = input.nextLine();
            for (int k = 1; k < s.length(); k++) {
                if (s.charAt(k) == ',') {
                    A = s.charAt(k-1);
                } else if (s.charAt(k) == '.') {
                    B = s.charAt(k-1);
                }
            }
            if (A != '?' && B != '?') {
                tree.queries(A, B, output);
            }
            A = '?';
            B = '?';
        }
        tree.levelOrder(output);
    }
}
