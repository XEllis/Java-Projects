import java.io.*;
import java.lang.*;

public class NFA1Filter {
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String s = in.readLine();
    long start = System.nanoTime();
    while (s!=null) {
      if (NFA1.accepts(s)) {
        // System.out.println(s);
      }
      s = in.readLine();
    }
    long runtime = System.nanoTime() - start;
    System.out.println(runtime);
  }
}
