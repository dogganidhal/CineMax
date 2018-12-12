<%@ page import="fr.insta.cinemax.model.Session" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<jsp:useBean id="movie" type="fr.insta.cinemax.model.Movie" scope="request"/>
<jsp:useBean id="sessions" type="java.util.List<fr.insta.cinemax.model.Session>" scope="request"/>
<jsp:useBean id="unitPrice" type="java.lang.Double" scope="request"/>

<title><%= movie.getTitle() %></title>

<div>
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/">Filmes</a></li>
            <li class="breadcrumb-item active" aria-current="page"><%= movie.getTitle()%></li>
        </ol>
    </nav>
    <div class="row col-10 offset-md-1 border rounded" style="padding: 16px; border-color: darkgray; border-width: 1px">
        <div class="col-3">
            <img src="https://placeimg.com/240/240/any" alt="Card image cap">
            <div style="padding-left: 16px; padding-top: 16px">
                <H4><%= movie.getTitle() %></H4>
                <span>Version : <b><%= movie.getVersion() %></b></span><br>
                <span>Vision : <b><%= movie.getVision() %></b></span><br>
                <span>Durée : <b><%= movie.getDuration().intValue() %></b></span><br>
            </div>
        </div>
        <div class="col-9">
            <h4>Prochaines sessions :</h4>
            <table class="table border rounded">
                <thead class="thead-light">
                <tr>
                    <th scope="col">Horaire</th>
                    <th scope="col">Prix</th>
                    <th scope="col">Place restantes</th>
                    <th scope="col">Résérver</th>
                </tr>
                </thead>
                <tbody>
                <%
                    SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE dd/MM/yyyy HH'h'mm");
                    for (Session s: sessions) {
                    	String startDate = dateFormat.format(s.getStartDate());
                %>
                <tr>
                    <th scope="row"><%= startDate %></th>
                    <td><%= unitPrice %>€</td>
                    <td><%= s.getRoom().getCapacity() - s.getTicketCount() %></td>
                    <td>+ 1</td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>
<footer class="pt-4 my-md-5 pt-md-5 border-top">
    <div class="row">
        <div class="col-12 col-md" style="margin-left: 16px">
            <img class="mb-2" src="https://cdn.worldvectorlogo.com/logos/cinemax.svg" alt="" width="96" height="96">
        </div>
        <div class="col-6 col-md">
            <h5>Features</h5>
            <ul class="list-unstyled text-small">
                <li><a class="text-muted" href="#">Cool stuff</a></li>
                <li><a class="text-muted" href="#">Random feature</a></li>
                <li><a class="text-muted" href="#">Team feature</a></li>
                <li><a class="text-muted" href="#">Stuff for developers</a></li>
                <li><a class="text-muted" href="#">Another one</a></li>
                <li><a class="text-muted" href="#">Last time</a></li>
            </ul>
        </div>
        <div class="col-6 col-md">
            <h5>Resources</h5>
            <ul class="list-unstyled text-small">
                <li><a class="text-muted" href="#">Resource</a></li>
                <li><a class="text-muted" href="#">Resource name</a></li>
                <li><a class="text-muted" href="#">Another resource</a></li>
                <li><a class="text-muted" href="#">Final resource</a></li>
            </ul>
        </div>
        <div class="col-6 col-md">
            <h5>About</h5>
            <ul class="list-unstyled text-small">
                <li><a class="text-muted" href="#">Team</a></li>
                <li><a class="text-muted" href="#">Locations</a></li>
                <li><a class="text-muted" href="#">Privacy</a></li>
                <li><a class="text-muted" href="#">Terms</a></li>
            </ul>
        </div>
    </div>
</footer>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</html>