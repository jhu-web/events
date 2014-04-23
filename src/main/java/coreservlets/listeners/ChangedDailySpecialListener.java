package coreservlets.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

/** Listener that monitors changes to the names
 *  of the daily specials (which are stored in
 *  the daily-special-item-names attribute of
 *  the servlet context). If the names change, the
 *  listener resets the running count of the number
 *  of daily specials being ordered.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages Volume II
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://volume2.coreservlets.com/.
 *  &copy; 2005 coreservlets.com; may be freely used or adapted.
 */

public class ChangedDailySpecialListener
    implements ServletContextAttributeListener {

  /** When the daily specials change, reset the
   *  order counts.
   */
  
  public void attributeReplaced
                   (ServletContextAttributeEvent event) {
    if (event.getName().equals("daily-special-item-names")) {
      ServletContext context = event.getServletContext();
      context.setAttribute("dailySpecialCount",
                           new Integer(0));
      DailySpecialWatcher.resetDailySpecialCount();
    }
  }

  public void attributeAdded
                   (ServletContextAttributeEvent event) {}
  
  public void attributeRemoved
                   (ServletContextAttributeEvent event) {}
}
