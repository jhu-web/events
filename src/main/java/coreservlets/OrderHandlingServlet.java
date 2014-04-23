package coreservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** Servlet that handles submissions from the order form.
 *  If the user selects the "Reserve Order" button, the
 *  selected item is put into the orderedItem attribute.
 *  If the user selects the "Cancel Order" button, the
 *  orderedItem attribute is deleted.
 *  If the user selects the "Purchase Item" button, the
 *  selected item is put into the purchasedItem attribute.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages Volume II
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://volume2.coreservlets.com/.
 *  &copy; 2005 coreservlets.com; may be freely used or adapted.
 */

public class OrderHandlingServlet extends HttpServlet {
  private static final long serialVersionUID = -4205856324683377865L;
  
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession(true);
    String itemName = request.getParameter("itemName");
    if ((itemName == null) || (itemName.equals(""))) {
      itemName = "<B>MISSING ITEM</B>";
    }
    String message;
    if (request.getParameter("order") != null) {
      session.setAttribute("orderedItem", itemName);
      message = "Thanks for ordering " + itemName + ".";
    } else if (request.getParameter("cancel") != null) {
      session.removeAttribute("orderedItem");
      message = "Thanks for nothing.";
    } else {
      session.setAttribute("purchasedItem", itemName);
      message = "Thanks for purchasing " + itemName + ".";
    }
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
    out.println
      (docType +
       "<HTML>\n" +
       "<HEAD><TITLE>" + message + "</TITLE></HEAD>\n" +
       "<BODY BGCOLOR=\"#FDF5E6\">\n" +
       "<H2 ALIGN=\"CENTER\">" + message + "</H2>\n" +
       "</BODY></HTML>");
  }
}
