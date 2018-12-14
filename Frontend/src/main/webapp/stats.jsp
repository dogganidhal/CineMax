<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<jsp:useBean id="stats" type="fr.insta.cinemax.model.StatsModel" scope="request"/>

<title>Ciné Max Stats</title>

<div class="container">
    <div class="row">
        <div class="col-sm border rounded" style="padding: 16px 0; margin-top: 16px; margin-left: 16px; border-color: darkgray; border-width: 1px">
            <div class="border-bottom" style="text-align: center; padding-bottom: 12px"><h3>Chiffre d'affaire</h3></div>
            <div>
                <canvas id="profit-chart" width="400" height="400"></canvas>
            </div>
        </div>
        <div class="col-sm border rounded" style="padding: 16px 0; margin-top: 16px; margin-left: 16px; border-color: darkgray; border-width: 1px">
            <div class="border-bottom" style="text-align: center; padding-bottom: 12px"><h3>Chiffre d'affaire</h3></div>
            <div>
                <canvas id="sales-chart" width="400" height="400"></canvas>
            </div>
        </div>
        <div class="col-sm border rounded" style="padding: 16px 0; margin-top: 16px; margin-left: 16px; border-color: darkgray; border-width: 1px">
            <div class="border-bottom" style="text-align: center; padding-bottom: 12px"><h3>Chiffre d'affaire</h3></div>
            <div>
                <canvas id="myChart" width="400" height="400"></canvas>
            </div>
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
                label: 'Evolution du chiffre d\'affaire',
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
            labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
            datasets: [{
                label: '# of Votes',
                data: [12, 19, 3, 5, 2, 3],
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