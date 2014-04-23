package coreservlets.listeners;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/** Listener that keeps track of yacht purchases
 *  by monitoring the orderedItem and purchasedItem
 *  session attributes.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages Volume II
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://volume2.coreservlets.com/.
 *  &copy; 2005 coreservlets.com; may be freely used or adapted.
 */

public class YachtWatcher
    implements HttpSessionAttributeListener {
  private String orderAttributeName = "orderedItem";
  private String purchaseAttributeName = "purchasedItem";
  private String itemName = "yacht";

  /** Checks for initial ordering and final purchase of
   *  yacht. Records "Customer ordered a yacht" if the
   *  orderedItem attribute matches "yacht".
   *  Records "Customer finalized purchase of a yacht" if the
   *  purchasedItem attribute matches "yacht".
   */
  
  public void attributeAdded(HttpSessionBindingEvent event) {
    checkAttribute(event, orderAttributeName, itemName,
                   " ordered a ");
    checkAttribute(event, purchaseAttributeName, itemName,
                   " finalized purchase of a ");
  }

  /** Checks for order cancellation: was an order for "yacht"
   *  cancelled?  Records "Customer cancelled an order for
   *  a yacht" if the orderedItem attribute matches "yacht".
   */
  
  public void attributeRemoved(HttpSessionBindingEvent event) {
    checkAttribute(event, orderAttributeName, itemName,
                   " cancelled an order for a ");
  }

  /** Checks for item replacement: was "yacht" replaced
   *  by some other item? Records "Customer changed to a new
   *  item instead of a yacht" if the orderedItem attribute
   *  matches "yacht".
   */
  
  public void attributeReplaced(HttpSessionBindingEvent event) {
    checkAttribute(event, orderAttributeName, itemName,
                   " changed to a new item instead of a ");
  }

  private void checkAttribute(HttpSessionBindingEvent event,
                              String orderAttributeName,
                              String keyItemName,
                              String message) {
    String currentAttributeName = event.getName();
    String currentItemName = (String)event.getValue();
    if (currentAttributeName.equals(orderAttributeName) &&
        currentItemName.equals(keyItemName)) {
      ServletContext context =
        event.getSession().getServletContext();
      context.log("Customer" + message + keyItemName + ".");
    }
  }
}
