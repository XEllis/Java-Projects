public class NFA1 {
  private static int[][][] delta = 
     {{{0,1},{0}},
      {{2},{}},
      {{2},{2}}};

  private static boolean accepts
      (int s, String in, int pos) {
    if (pos==in.length()) {
      return (s==2);
    }

    char c = in.charAt(pos++);
    int[] nextStates;
    try {
      nextStates = delta[s][c-'0'];
    }
    catch (ArrayIndexOutOfBoundsException ex) {
      return false;
    }

    for (int i=0; i < nextStates.length; i++) {
      if (accepts(nextStates[i], in, pos)) return true;
    }

    return false;
  }

  public static boolean accepts(String in) {
    return accepts(0, in, 0);
  }
}
