<%@ page import="fr.insta.cinemax.model.Movie" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<jsp:useBean id="movies" type="java.util.List<fr.insta.cinemax.model.Movie>" scope="request"/>

<title>Ciné Max</title>

<div>
    <%
        for (int index = 0; index < movies.size(); index++) {
        	Movie movie = movies.get(index);
        	if (index % 3 == 0) {
    %>
        <div class="row" style="margin-top: 16px">
    <%
        }
    %>
        <div class="col">
            <div class="card" style="margin-left: auto; margin-right: auto; width: 24rem;">
                <img class="card-img-top" src="https://placeimg.com/180/100/any" alt="Card image cap">
                <div class="card-body">
                    <H4><%= movie.getTitle() %></H4>
                    <span><b class="card-title"><%= movie.getVersion() %></b></span><br>
                    <span><b class="card-text"><%= movie.getDuration().intValue() %> minutes</b></span><br><br>
                    <span><%= movie.getDescription() %></span><br><br>
                    <a href="<%= "/movie?id=" + movie.getId()%>" class="btn btn-primary">Voir les scéances</a>
                </div>
            </div>
        </div>
        <%
            if (index % 3 == 2) {
            %>
        </div>
    <%
            }
        }
    %>
</div>
<%@include file="footer.jsp"%>