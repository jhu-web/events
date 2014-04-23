package coreservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that changes the company name. The web.xml
 *  file specifies that only authenticated users in the
 *  ceo role can access the servlet. A servlet context
 *  attribute listener updates the former company name
 *  when this servlet (or any other program) changes
 *  the current company name.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages Volume II
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://volume2.coreservlets.com/.
 *  &copy; 2005 coreservlets.com; may be freely used or adapted.
 */
public class ChangeCompanyName extends HttpServlet {
  private static final long serialVersionUID = 6618615839938414890L;

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
    boolean isNameChanged = false;
    String newName = request.getParameter("newName");
    if ((newName != null) && (!newName.equals(""))) {
      isNameChanged = true;
      getServletContext().setAttribute("companyName",
                                       newName);
    }
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
    String title = "Company Name";
    out.println
      (docType +
       "<HTML>\n" +
       "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
       "<BODY BGCOLOR=\"#FDF5E6\">\n" +
       "<H2 ALIGN=\"CENTER\">" + title + "</H2>");
    if (isNameChanged) {
      out.println("Company name changed to " + newName + ".");
    } else {
      out.println("Company name not changed.");
    }
    out.println("</BODY></HTML>");
  }
}
