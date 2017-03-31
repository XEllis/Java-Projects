import java.util.Scanner;
import java.lang.Integer;

class LinkedList {
    private static class Node {
        private String element;
        private Node next;
        public Node(String e, Node n) {
            element = e;
            next = n;
        }
        public String getElement() {
            return element;
        }
        public Node getNext() {
            return next;
        }
        public void setNext(Node n) {
            next = n;
        }
    }
    private Node head = null;
    public LinkedList() {}
    public void addFirst(String e) {
        head = new Node(e, head);
    }
    public int find(String e) {
        Node p = head;
        int count = 1;
        if (p == null) {
            addFirst(e);
            return 0;
        }
        if (p.getElement().equals(e)) {
            return count;
        }
        p = p.getNext();
        Node q = head;
        count++;
        while (p != null) {
            if (p.getElement().equals(e)) {
                q.setNext(p.getNext());
                addFirst(e);
                return count;
            }
            p = p.getNext();
            q = q.getNext();
            count++;
        }
        addFirst(e);
        return 0;
    }
    public String findNum(int num) {
         Node p = head;
         int count = 1;
         if (count == num) {
             return p.getElement();
         }
         p = p.getNext();
         Node q = head;
         count++;
         while (count != num) {
             p = p.getNext();
             q = q.getNext();
             count++;
         }
         String e = p.getElement();
         q.setNext(p.getNext());
         addFirst(e);
         return e;
    }
}

public class proj4 {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in).useDelimiter("0123456789");
        String e = "";
        String s = "";
        String temp = "";
        char c = '\0';
        int num = 0;
        LinkedList words = new LinkedList();
        if (input.hasNext()) {
            s = input.next();
            if (s.charAt(0) == '0') {
                int len = s.length();
                for (int j = 2; j < len; j++) {
                    c = s.charAt(j);
                    while (c != '0' && (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                        e += c;
                        j++;
                        c = s.charAt(j);
                    }
                    if (!e.equals("")) {
                        words.addFirst(e);
                        System.out.print(e);
                        e = "";
                    }
                    if (c == '0') {
                        break;
                    }
                    else {
                        if (c >= '1' && c <= '9') {
                            while (c >= '0' && c <= '9') {
                                temp += c;
                                j++;
                                c = s.charAt(j);
                            }
                            num = Integer.parseInt(temp);
                            temp = "";
                            e = words.findNum(num);
                            System.out.print(e);
                            e = "";
                            if (c == '0') {
                                break;
                            }
                            else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                                e += c;
                            }
                            else {
                                System.out.print(c);
                            }
                        }
                        else {
                            System.out.print(c);
                        }
                    }
                }
            }
            else {
                int uncpr = 0;
                int cpr = 0;
                uncpr = s.length();
                System.out.print("0 ");
                for (int i = 0; i < uncpr; i++) {
                    c = s.charAt(i);
                    while ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                        e += c;
                        i++;
                        c = s.charAt(i);
                    }
                    if (!e.equals("")) {
                        num = words.find(e);
                        if (num == 0) {
                            cpr += e.length();
                            System.out.print(e);
                        }
                        else {
                            temp = Integer.toString(num);
                            cpr += temp.length();
                            System.out.print(num);
                        }
                        e = "";
                    }
                    cpr++;
                    System.out.print(c);
                }
                System.out.println("0 Uncompressed: " + uncpr + " bytes;  Compressed: " + cpr + " bytes");
            }
        }
        input.close();
    }
}
