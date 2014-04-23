package coreservlets.listeners;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/** Listener that records how to detect orders
 *  of the daily special. It reads a list of attribute
 *  names from an init parameter: these correspond to
 *  session attributes that are used to record orders.
 *  It also reads a list of item names: these correspond
 *  to the names of the daily specials. Other listeners
 *  will watch to see if any daily special names appear
 *  as values of attributes that are hereby designated
 *  to refer to orders.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages Volume II
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://volume2.coreservlets.com/.
 *  &copy; 2005 coreservlets.com; may be freely used or adapted.
 */

public class DailySpecialRegistrar
    implements ServletContextListener {

  /** When the Web application is loaded, record the
   *  attribute names that correspond to orders and
   *  the attribute values that are the daily specials.
   *  Also set to zero the count of daily specials that have
   *  been ordered.
   */
  
  public void contextInitialized(ServletContextEvent event) {
    ServletContext context = event.getServletContext();
    addContextEntry(context, "order-attribute-names");
    addContextEntry(context, "daily-special-item-names");
    context.setAttribute("dailySpecialCount", new Integer(0));
  }

  public void contextDestroyed(ServletContextEvent event) {}

  /** Read the designated context initialization parameter,
   *  put the values into an ArrayList, and store the
   *  list in the ServletContext with an attribute name
   *  that is identical to the initialization parameter name.
   */
  
  private void addContextEntry(ServletContext context,
                               String initParamName) {
    ArrayList<String> paramValues = new ArrayList<String>();
    String attributeNames =
      context.getInitParameter(initParamName);
    if (attributeNames != null) {
      StringTokenizer tok = new StringTokenizer(attributeNames);
      String value;
      while(tok.hasMoreTokens()) {
        value = tok.nextToken();
        paramValues.add(value);
      }
      context.setAttribute(initParamName, paramValues);
    }
  }

  /** Returns a string containing the daily special
   *  names. For insertion inside an HTML text area.
   */

  public static String dailySpecials(ServletContext context) {
    String attributeName = "daily-special-item-names";
    @SuppressWarnings("unchecked")
    ArrayList<String> itemNames =
      (ArrayList<String>)context.getAttribute(attributeName);
    String itemString = "";
    for(int i=0; i<itemNames.size(); i++) {
      itemString = itemString + (String)itemNames.get(i) + "\n";
    }
    return(itemString);
  }

  /** Returns a UL list containing the daily special
   *  names. For insertion within the body of a JSP page.
   */

  public static String specialsList(ServletContext context) {
    String attributeName = "daily-special-item-names";
    @SuppressWarnings("unchecked")
    ArrayList<String> itemNames =
      (ArrayList<String>)context.getAttribute(attributeName);
    String itemString = "<UL>\n";
    for(int i=0; i<itemNames.size(); i++) {
      itemString = itemString + "<LI>" +
                   (String)itemNames.get(i) + "\n";
    }
    itemString = itemString + "</UL>";
    return(itemString);
  }
}
