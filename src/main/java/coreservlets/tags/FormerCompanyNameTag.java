package coreservlets.tags;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import coreservlets.listeners.InitialCompanyNameListener;

/** The InitialCompanyNameListener class has static
 *  methods that permit access to the current and former
 *  company names. But, using these methods in JSP requires
 *  explicit Java code, and creating beans that provided
 *  the information would have yielded a cumbersome result.
 *  So, we simply move the code into a custom tag.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages Volume II
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://volume2.coreservlets.com/.
 *  &copy; 2005 coreservlets.com; may be freely used or adapted.
 */

public class FormerCompanyNameTag extends TagSupport {
  private static final long serialVersionUID = -5322371466060575133L;
  private boolean useFullDescription = false;
  
  public int doStartTag() {
    try {
      ServletContext context = pageContext.getServletContext();
      String formerCompanyName =
       InitialCompanyNameListener.getFormerCompanyName(context);
      JspWriter out = pageContext.getOut();
      if (useFullDescription) {
        String formerCompanyDescription = "";
        if (!formerCompanyName.equals("")) {
          formerCompanyDescription = 
            "(formerly " + formerCompanyName + ")";
        }
        out.print(formerCompanyDescription);
      } else {
        out.print(formerCompanyName);
      }
    } catch(IOException ioe) {
      System.out.println("Error printing former company name.");
    }
    return(SKIP_BODY);
  }

  /** If the user supplies a fullDescription attribute
   *  with the value "true" (upper, lower, or mixed case),
   *  set the useFullDescription instance variable to true.
   *  Otherwise, leave it false.
   */
  
  public void setFullDescription(String flag) {
    if (flag.equalsIgnoreCase("true")) {
      useFullDescription = true;
    }
  }

  /** Servers are permitted to reuse tag instances
   *  once a request is finished. So, this resets
   *  the useFullDescription field. This method
   *  is automatically called after the system is
   *  finished using the tag.
   */
  
  public void release() {
    useFullDescription = false;
  }
}
