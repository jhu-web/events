<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>${applicationScope.companyName}</TITLE>
<LINK REL=STYLESHEET
      HREF="events-styles.css"
      TYPE="text/css">
</HEAD>

<BODY>
<TABLE BORDER=5 ALIGN="CENTER">
  <TR><TH CLASS="TITLE">
      ${applicationScope.companyName}<BR>
      (formerly ${applicationScope.formerCompanyName})
</TABLE>
<P>
Learn more about <B>${applicationScope.companyName}</B>
(formerly ${applicationScope.formerCompanyName})
<UL>
  <LI><A HREF="products.jsp">${applicationScope.companyName}
  products</A>
  <LI><A HREF="services.jsp">${applicationScope.companyName}
  services</A>
  <LI><A HREF="history.jsp">${applicationScope.companyName}
  history</A>
  <LI><A HREF="invest.jsp">investing in
  ${applicationScope.companyName}</A>
  <LI><A HREF="contact.jsp">contacting
  ${applicationScope.companyName}</A>
</UL>

</BODY>
</HTML>