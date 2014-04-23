package coreservlets;

/** Small utility to generate random HTML color names.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages Volume II
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://volume2.coreservlets.com/.
 *  &copy; 2005 coreservlets.com; may be freely used or adapted.
 */

public class ColorUtils {
  // The official HTML color names.
  private static String[] htmlColorNames =
    { "AQUA", "BLACK", "BLUE", "FUCHSIA", "GRAY", "GREEN",
      "LIME", "MAROON", "NAVY", "OLIVE", "PURPLE", "RED",
      "SILVER", "TEAL", "WHITE", "YELLOW" };

  public static String randomColor() {
    int index = randomInt(htmlColorNames.length);
    return(htmlColorNames[index]);
  }

  // Returns a random number from 0 to n-1 inclusive.
  
  private static int randomInt(int n) {
    return((int)(Math.random() * n));
  }
}
