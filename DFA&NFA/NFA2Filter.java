import java.io.*;
import java.lang.*;

public class NFA2Filter {
  public static void main(String[] args) throws IOException {
    NFA2 m = new NFA2();
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String s = in.readLine();
    long start = System.nanoTime();
    while (s!=null) {
      m.reset();
      m.process(s);
      if (m.accepted()) {
        // System.out.println(s);
      }
      s = in.readLine();
    }
    long runtime = System.nanoTime() - start;
    System.out.println(runtime);
  }
}
