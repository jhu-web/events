<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Tracking Daily Special Orders</TITLE>
<LINK REL=STYLESHEET
      HREF="events-styles.css"
      TYPE="text/css">
</HEAD>
<BODY>
<CENTER>
<TABLE BORDER=5>
  <TR><TH CLASS="TITLE">Tracking Daily Special Orders
</TABLE>

<H2>Current Specials:</H2>
<%@ page import="coreservlets.listeners.*" %>
<%= DailySpecialRegistrar.specialsList(application) %>

<H2>Number of Orders: 
<%= application.getAttribute("dailySpecialCount") %>
</H2>

</CENTER>
</BODY>
</HTML>