<%@ page import="javafx.util.Pair" %>
<%@ page import="fr.insta.cinemax.model.Movie" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<jsp:useBean id="stats" type="fr.insta.cinemax.model.StatsModel" scope="request"/>

<title>Ciné Max Stats</title>

<div class="container">
    <div class="row">
        <div class="col-sm border rounded" style="padding: 16px 0; margin-top: 16px; margin-left: 16px; border-color: darkgray; border-width: 1px">
            <div class="border-bottom" style="text-align: center; padding-bottom: 12px"><h3>Evolution du chiffre d'affaire</h3></div>
            <div>
                <canvas id="profit-chart" width="400" height="400"></canvas>
            </div>
            <div style="padding-top: 12px; margin-left: 12px; font-size: xx-small"><p>Sur une semaine *</p></div>
        </div>
        <div class="col-sm border rounded" style="padding: 16px 0; margin-top: 16px; margin-left: 16px; border-color: darkgray; border-width: 1px">
            <div class="border-bottom" style="text-align: center; padding-bottom: 12px"><h3>Evolution des ventes</h3></div>
            <div>
                <canvas id="sales-chart" width="400" height="400"></canvas>
            </div>
            <div style="padding-top: 12px; margin-left: 12px; font-size: xx-small"><p>Sur une semaine *</p></div>
        </div>
        <div class="col-sm border rounded" style="padding: 16px 0; margin-top: 16px; margin-left: 16px; border-color: darkgray; border-width: 1px">
            <div class="border-bottom" style="text-align: center; padding-bottom: 12px; padding-left: 16px; padding-right: 16px"><h3>Membres les plus actifs</h3></div>
            <%
                int index = 0;
                for (Pair<User, Integer> topUser: stats.getTopUsers()) {
                	index++;
            %>
                <div class="<% if (index < stats.getTopUsers().size()) {%>border-bottom<%}%>" style="padding: 16px">
                    <p>Nom et Prénom : <b><%=topUser.getKey().getFirstName() + " " + topUser.getKey().getLastName()%></b></p>
                    <p>Nombre de tickets : <b><%=topUser.getValue()%></b></p>
                </div>
            <%}%>
        </div>
        <div class="col-sm border rounded" style="padding: 16px 0; margin-top: 16px; margin-left: 16px; border-color: darkgray; border-width: 1px">
            <div class="border-bottom" style="text-align: center; padding-bottom: 12px; padding-left: 16px; padding-right: 16px"><h3>Films Best Seller</h3></div>
            <%
                index = 0;
                for (Pair<Movie, Double> bestSellerMovie: stats.getBestSellerMovies()) {
                    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
                    String generatedRevenue = decimalFormat.format(bestSellerMovie.getValue());
                    index++;
            %>
            <div class="<% if (index < stats.getBestSellerMovies().size()) {%>border-bottom<%}%>" style="padding: 16px">
                <p>Titre : <b><%= bestSellerMovie.getKey().getTitle() %></b></p>
                <p>Revenus générés : <b><%= generatedRevenue %>€</b></p>
            </div>
            <%}%>
        </div>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.bundle.min.js"></script>
<script>
    let ctx = document.getElementById("profit-chart").getContext('2d');
    let myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: <%=stats.getDaysOfWeek()%>,
            datasets: [{
                label: 'Recette',
                data: <%=stats.getLastWeekProfit()%>,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255,99,132,1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero:true
                    }
                }]
            }
        }
    });
    ctx = document.getElementById("sales-chart").getContext('2d');
    let salesChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: <%= stats.getDaysOfWeek()%>,
            datasets: [{
                data: <%= stats.getSalesPerDayOfWeek()%>,
                label: 'Tickets',
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255,99,132,1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero:true
                    }
                }]
            }
        }
    })
</script>

<%@include file="footer.jsp"%>