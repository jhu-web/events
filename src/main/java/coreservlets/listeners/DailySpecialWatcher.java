package coreservlets.listeners;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/** Listener that keeps track of orders of the
 *  current daily special.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages Volume II
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://volume2.coreservlets.com/.
 *  &copy; 2005 coreservlets.com; may be freely used or adapted.
 */

public class DailySpecialWatcher
    implements HttpSessionAttributeListener {
  private static int dailySpecialCount = 0;
  
  /** If the name of the session attribute that was added
   *  matches one of the stored order-attribute-names AND
   *  the value of the attribute matches one of the
   *  stored daily-special-item-names, then increment
   *  the count of daily specials ordered.
   */

  public void attributeAdded(HttpSessionBindingEvent event) {
    checkForSpecials(event, 1);
  }

  /** If the name of the session attribute that was removed
   *  matches one of the stored order-attribute-names AND
   *  the value of the attribute matches one of the
   *  stored daily-special-item-names, then decrement
   *  the count of daily specials ordered.
   */
  
  public void attributeRemoved(HttpSessionBindingEvent event) {
    checkForSpecials(event, -1);
  }

  /** If the name of the session attribute that was replaced
   *  matches one of the stored order-attribute-names AND
   *  the value of the attribute matches one of the
   *  stored daily-special-item-names, then increment
   *  the count of daily specials ordered. Note that the
   *  value here is the old value (the one being replaced);
   *  the attributeAdded method will handle the new value
   *  (the replacement).
   */
  
  public void attributeReplaced(HttpSessionBindingEvent event) {
    checkForSpecials(event, -1);
  }

  // Check whether the attribute that was just added or removed
  // matches one of the stored order-attribute-names AND
  // the value of the attribute matches one of the
  // stored daily-special-item-names. If so, add the delta
  // (+1 or -1) to the count of daily specials ordered.
  
  private void checkForSpecials(HttpSessionBindingEvent event,
                                int delta) {
    ServletContext context =
      event.getSession().getServletContext();
    ArrayList<String> attributeNames =
      getList(context, "order-attribute-names");
    ArrayList<String> itemNames =
      getList(context, "daily-special-item-names");
    synchronized(attributeNames) {
      for(int i=0; i<attributeNames.size(); i++) {
        String attributeName = (String)attributeNames.get(i);
        for(int j=0; j<itemNames.size(); j++) {
          String itemName = (String)itemNames.get(j);
          if (attributeName.equals(event.getName()) &&
              itemName.equals((String)event.getValue())) {
            dailySpecialCount = dailySpecialCount + delta;
          }
        }
      }
    }
    context.setAttribute("dailySpecialCount",
                         new Integer(dailySpecialCount));
  }

  // Get either the order-attribute-names or
  // daily-special-item-names list.
  
  private ArrayList<String> getList(ServletContext context,
                            String attributeName) {
    @SuppressWarnings("unchecked")
    ArrayList<String> list =
      (ArrayList<String>)context.getAttribute(attributeName);
    return(list);
  }

  /** Reset the count of daily specials that have
   *  been ordered. This operation is normally performed
   *  only when the daily special changes.
   */
  
  public static void resetDailySpecialCount() {
    dailySpecialCount = 0;
  }
}
