<%@ page import="fr.insta.cinemax.model.User" %>
<%@ page import="fr.insta.cinemax.manager.HttpSessionManager" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    User currentUser = HttpSessionManager.getUserFromSession(session);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<nav class="navbar fixed-sticky navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">
        <img src="https://cdn.worldvectorlogo.com/logos/cinemax.svg" height="48" class="d-inline-block align-top" alt="">
    </a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <%if (currentUser != null) {%>
        <ul class="row navbar-nav bd-navbar-nav">
            <li class="nav-item" style="margin-left: 16px;">
                <a class="nav-link active" href="/cart">Panier</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="/stats">Statistique</a>
            </li>
        </ul>
        <%}%>
    </div>
    <div class="form-inline my-2 my-lg-0">
        <%
            if (currentUser != null) {
        %>
        <p class="my-2 my-lg-0" style="margin-right: 16px">Bonjour <b><%= currentUser.getFirstName() %></b></p>
        <form action="/auth" method="post">
            <input type="hidden" name="auth-type" value="sign-out">
            <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Déconnexion</button>
        </form>
        <%
            } else {
        %>
        <a href="/auth"><button class="btn btn-outline-primary my-2 my-sm-0">Connexion / Inscription</button></a>
        <%
            }
        %>
    </div>
</nav>