public class NFA2 {
  private int stateSet;

  public void reset() {
    stateSet = 1<<0;
  }

  static private int[][] delta = 
     {{1<<0|1<<1, 1<<0},
      {1<<2, 0},
      {1<<2, 1<<2}};

  public void process(String in) {
    for (int i = 0; i < in.length(); i++) {
      char c = in.charAt(i);
      int nextSS = 0;
      for (int s = 0; s <= 2; s++) {
        if ((stateSet&(1<<s)) != 0) {
          try {
            nextSS |= delta[s][c-'0'];
          }
          catch (ArrayIndexOutOfBoundsException ex) {
            // in effect, nextSS |= 0
          }
        }
      }
      stateSet = nextSS;
    }
  }

  public boolean accepted() {
    return (stateSet&(1<<2))!=0;
  }
}
