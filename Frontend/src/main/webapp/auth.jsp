<%@ page import="fr.insta.cinemax.model.Movie" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<jsp:useBean id="errorMessage" type="java.lang.String" scope="request"/>

<title>Authentification</title>

<%
    if (!errorMessage.isEmpty()) {
%>
<div class="alert alert-danger" role="alert">
    <%= errorMessage %>
</div>
<%
    }
%>

<div class="row">
    <div class="col col-5 offset-md-1 border rounded-left"
         style="padding: 16px; margin-top: 16px;border-color: darkgray; border-width: 1px">

        <h4 style="text-align: center">Inscription</h4>
        <form action="/auth" method="post">
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="lastName">Nom</label>
                    <input type="text" class="form-control" name="lastName" id="lastName" placeholder="Nom">
                </div>
                <div class="form-group col-md-6">
                    <label for="firstName">Prénom</label>
                    <input type="text" class="form-control" name="firstName" id="firstName" placeholder="Prénom">
                </div>
            </div>
            <div class="form-group">
                <label for="email-sign-up">Email</label>
                <input type="email" class="form-control" name="email" id="email-sign-up" placeholder="Email">
            </div>
            <div class="form-group">
                <label for="password-sign-up">Mot de passe</label>
                <input type="password" class="form-control" name="password" id="password-sign-up" placeholder="Mot de passe">
            </div>
            <div class="form-group">
                <label for="birth-date">Date de naissance</label>
                <input class="form-control" type="date" name="birthDate" value="1990-01-01" id="birth-date">
            </div>
            <input type="hidden" name="auth-type" value="sign-up">
            <button type="submit" class="btn btn-primary">S'inscrire</button>
        </form>

    </div>

    <div class="col col-5 border-top border-bottom border-right rounded-right"
         style="padding: 16px; margin-top: 16px;border-color: darkgray; border-width: 1px;">

        <h4 style="text-align: center">Connexion</h4>
        <form action="/auth" method="post">
            <div class="form-group">
                <label for="email-login">Email</label>
                <input type="email" class="form-control" name="email" id="email-login" placeholder="Email">
            </div>
            <div class="form-group">
                <label for="password-login">Mot de passe</label>
                <input type="password" class="form-control" name="password" id="password-login" placeholder="Mot de passe">
            </div>
            <input type="hidden" name="auth-type" value="login">
            <button type="submit" class="btn btn-primary">Se connecter</button>
        </form>

    </div>
</div>