<%@ page import="fr.insta.cinemax.model.CartElement" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<% List<CartElement> cart = HttpSessionManager.getCartFromSession(session); %>

<title>Mon Panier</title>

<%= cart %>

<%@include file="footer.jsp"%>