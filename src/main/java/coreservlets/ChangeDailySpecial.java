package coreservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that changes the daily specials. The web.xml
 *  file specifies that only authenticated users in the
 *  ceo role can access the servlet. A servlet context
 *  attribute listener resets the count of daily special
 *  orders when this servlet (or any other program) changes
 *  the daily specials.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages Volume II
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://volume2.coreservlets.com/.
 *  &copy; 2005 coreservlets.com; may be freely used or adapted.
 */

public class ChangeDailySpecial extends HttpServlet {
  private static final long serialVersionUID = -1070278453010542238L;

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
       throws ServletException, IOException {
    String dailySpecialNames =
      request.getParameter("newSpecials");
    if ((dailySpecialNames == null) ||
        (dailySpecialNames.equals(""))) {
      dailySpecialNames = "MISSING-VALUE";
    }
    ArrayList<String> specials = new ArrayList<String>();
    StringTokenizer tok =
      new StringTokenizer(dailySpecialNames);
    while(tok.hasMoreTokens()) {
      specials.add(tok.nextToken());
    }
    ServletContext context = getServletContext();
    context.setAttribute("daily-special-item-names",
                         specials);
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
    String title = "New Daily Specials";
    out.println
      (docType +
       "<HTML>\n" +
       "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
       "<BODY BGCOLOR=\"#FDF5E6\">\n" +
       "<H2 ALIGN=\"CENTER\">" + title + "</H2>\n" +
       "<UL>");
    String special;
    for(int i=0; i<specials.size(); i++) {
      special = (String)specials.get(i);
      out.println("<LI>" + special);
    }
    out.println("</UL>\n" +
                "</BODY></HTML>");
  }
}
