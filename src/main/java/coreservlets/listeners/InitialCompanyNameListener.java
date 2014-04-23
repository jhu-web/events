package coreservlets.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitialCompanyNameListener implements ServletContextListener {
	private static final String DEFAULT_NAME = "MISSING-COMPANY-NAME";
	private static final String FORMER_COMPANY_NAME = "formerCompanyName";
	private static final String COMPANY_NAME = "companyName";

	/**
	 * Looks up the companyName and formerCompanyName init parameters and puts
	 * them into the servlet context.
	 */
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		setInitialAttribute(context, COMPANY_NAME, DEFAULT_NAME);
		setInitialAttribute(context, FORMER_COMPANY_NAME, "");
	}

	public void contextDestroyed(ServletContextEvent event) {
	}

	// Looks for a servlet context init parameter with a
	// given name. If it finds it, it puts the value into
	// a servlet context attribute with the same name. If
	// the init parameter is missing, it puts a default
	// value into the servlet context attribute.
	private void setInitialAttribute(ServletContext context,
			String initParamName, String defaultValue) {
		String initialValue = context.getInitParameter(initParamName);
		if (initialValue != null) {
			context.setAttribute(initParamName, initialValue);
		} else {
			context.setAttribute(initParamName, defaultValue);
		}
	}

	public static String getFormerCompanyName(ServletContext context) {
		return (String) context.getAttribute(FORMER_COMPANY_NAME);
	}

	public static String getCompanyName(ServletContext context) {
		return (String) context.getAttribute(COMPANY_NAME);
	}
}
