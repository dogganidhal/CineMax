<%@ page import="fr.insta.cinemax.model.CartElement" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<jsp:useBean id="cart" type="fr.insta.cinemax.model.Cart" scope="request"/>
<jsp:useBean id="errorMessage" type="java.lang.String" scope="request"/>

<title>Mon Panier</title>

<%
    if (!errorMessage.isEmpty()) {
%>
<div class="alert alert-danger" role="alert">
    <%= errorMessage %>
</div>
<%
    }
%>

<div>
    <div class="col col-4 offset-md-4 border rounded" style="margin-top: 24px; padding: 16px 0px; border-color: darkgray; border-width: 1px">
        <div style="text-align: center"><h3>Mon Panier</h3></div>
        <%
            if (cart.getCartElements().size() > 0) {
        %>
        <%
            DecimalFormat decimalFormat = new DecimalFormat("#0.00");
            String totalPrice = decimalFormat.format(cart.getTotalPrice());
            for (CartElement cartElement: cart.getCartElements()) {
                String movieTitle = cartElement.getSession().getMovie().getTitle();
                Integer ticketCount = cartElement.getCount();
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE dd/MM/yyyy HH'h'mm");
                String sessionStartDate = dateFormat.format(cartElement.getSession().getStartDate());
        %>
        <div class="border-bottom" style="padding: 16px;">
            <p>Filme : <b><%=movieTitle%></b></p>
            <p>Séance : <b><%=sessionStartDate%></b></p>
            <p>Nombre de tickets : <b><%=ticketCount%></b></p>
        </div>
        <%
            }
        %>
        <div style="margin-top: 16px; margin-left: 16px">
            <p>Totale : <h3><%=totalPrice%> €</h3></p>
        </div>
        <% } else { %>
        <div style="padding: 24px; text-align: center">
            <h5> Vous n'avez pas de tickets dans votre panier </h5>
        </div>
        <%}%>
    </div>
    <% if (cart.getCartElements().size() > 0) { %>
    <form class="row col-2 offset-md-5" method="post" action="/cart">
        <button style="margin-top: 24px; margin-left: auto; margin-right: auto" type="submit" class="btn btn-primary btn-lg">Acheter les tickets</button>
    </form>
    <%} else {%>
    <a href="/" class="row col-2 offset-md-5">
        <button style="margin-top: 24px; margin-left: auto; margin-right: auto" type="button" class="btn btn-primary btn-lg">Explorer notre catalogue</button>
    </a>
    <%}%>

</div>

<%@include file="footer.jsp"%>