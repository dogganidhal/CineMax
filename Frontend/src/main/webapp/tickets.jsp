<%@ page import="fr.insta.cinemax.model.Ticket" %>
<%@ page import="fr.insta.cinemax.util.DateUtils" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<jsp:useBean id="tickets" type="java.util.List<fr.insta.cinemax.model.Ticket>" scope="request"/>

<title>Mes Tickets</title>

<div>
    <div class="col col-4 offset-md-4 border rounded" style="margin-top: 24px; padding: 16px 0px; border-color: darkgray; border-width: 1px">
        <div class="border-bottom" style="text-align: center; padding-bottom: 12px"><h3>Mes Tickets</h3></div>
        <%
            if (tickets.size() > 0) {
            	Integer index = 0;
            	for (Ticket ticket: tickets) {
            		index++;
        %>
        <div class="<%if(index < tickets.size()) {%>border-bottom<%}%>" style="padding: 16px;">
            <p>Film : <a href="/movie?id=<%=ticket.getSession().getMovie().getId()%>"><b><%=ticket.getSession().getMovie().getTitle()%></b></a></p>
            <p>Séance : <b><%=DateUtils.formatDate(ticket.getSession().getStartDate(), DateUtils.DateFormat.LONG)%></b></p>
            <p>Date d'achat : <b><%=DateUtils.formatDate(ticket.getCreatedDate(), DateUtils.DateFormat.SHORT)%></b></p>
            <p>Salle : <b><%=ticket.getSession().getRoom().getName()%></b></p>
        </div>
        <%
            }
            	} else {
        %>
        <div style="padding: 24px; text-align: center">
            <h5> Vous n'avez pas encore acheté de tickets </h5>
        </div>
        <%}%>
    </div>
</div>

<%@include file="footer.jsp"%>