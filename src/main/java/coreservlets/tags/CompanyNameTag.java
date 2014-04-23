package coreservlets.tags;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import coreservlets.listeners.InitialCompanyNameListener;

/**
 * The InitialCompanyNameListener class has static methods that permit access to the
 * current and former company names. But, using these methods in JSP requires explicit
 * Java code, and creating beans that provided the information would have yielded a
 * cumbersome result. So, we simply move the code into a custom tag.
 * <P>
 * Taken from Core Servlets and JavaServer Pages Volume II from Prentice Hall and Sun
 * Microsystems Press, http://volume2.coreservlets.com/. &copy; 2005 coreservlets.com; may
 * be freely used or adapted.
 */
public class CompanyNameTag
  extends TagSupport {
  private static final long serialVersionUID = -4011702326815531379L;

  public int doStartTag() {
    try {
      ServletContext context = pageContext.getServletContext();
      String companyName = InitialCompanyNameListener.getCompanyName(context);
      JspWriter out = pageContext.getOut();
      out.print(companyName);
    }
    catch (IOException ioe) {
      System.out.println("Error printing company name.");
    }
    return (SKIP_BODY);
  }
}
