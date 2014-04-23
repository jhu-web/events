package coreservlets.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

/** Listener that monitors changes in the company
 *  name (which is stored in the companyName attribute
 *  of the servlet context).
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages Volume II
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://volume2.coreservlets.com/.
 *  &copy; 2005 coreservlets.com; may be freely used or adapted.
 */
public class ChangedCompanyNameListener
    implements ServletContextAttributeListener {

  /** When the companyName attribute changes, put
   *  the previous value into the formerCompanyName
   *  attribute.
   */
  public void attributeReplaced
                   (ServletContextAttributeEvent event) {
    if (event.getName().equals("companyName")) {
      String oldName = (String)event.getValue();
      ServletContext context = event.getServletContext();
      context.setAttribute("formerCompanyName", oldName);
    }
  }

  public void attributeAdded
                   (ServletContextAttributeEvent event) {}
  
  public void attributeRemoved
                   (ServletContextAttributeEvent event) {}
}
