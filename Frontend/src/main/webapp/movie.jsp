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
                    <td>
                        <div id="<%= s.getId()%>" class="btn-group stepper-view" role="group">
                            <button type="button" class="btn btn-primary stepper-view-subtract">-1</button>
                            <div style="margin: auto 16px;"><b class="stepper-view-value">0</b></div>
                            <button type="button" class="btn btn-primary stepper-view-add">+1</button>
                        </div>
                    </td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row col-2 offset-md-5">
        <%
            if (currentUser != null) {
        %>
        <form method="post">
            <input type="hidden" name="cart" id="cart">
            <button id="buy-button" type="submit" class="btn btn-primary btn-lg">Mettre dans mon panier</button>
        </form>
        <%
            } else {
        %>
        <a href="/auth"><button type="button" class="btn btn-primary btn-lg">Connectez vous pour acheter</button></a>
        <%
            }
        %>
    </div>
</div>
<%@include file="footer.jsp"%>
<script>
    // ES6
    let stepperViews = $(".stepper-view");
    for (let stepperView of stepperViews) {

        let stepperViewSubtractButton = stepperView.querySelector(".stepper-view-subtract");
        let stepperViewAddButton = stepperView.querySelector(".stepper-view-add");

        refreshStepperState(stepperView);

        stepperViewAddButton.onclick = () => {
            incrementStepperValue(stepperView);
        };
        stepperViewSubtractButton.onclick = ()  => {
            decrementStepperValue(stepperView);
        };
    }

    function refreshStepperState(stepperView) {
        let stepperViewSubtractButton = stepperView.querySelector(".stepper-view-subtract");
        let stepperViewAddButton = stepperView.querySelector(".stepper-view-add");
        let stepperViewValue = stepperView.querySelector(".stepper-view-value");
        let value = stepperViewValue.innerText;
        let remainingSpaces = stepperView.parentNode.previousElementSibling.innerText;

        if (value === "0")
            stepperViewSubtractButton.setAttribute("disabled", undefined);
        else
            stepperViewSubtractButton.removeAttribute("disabled");

        if (parseInt(value) >= parseInt(remainingSpaces))
            stepperViewAddButton.setAttribute("disabled", undefined);
        else
            stepperViewAddButton.removeAttribute("disabled");
        refreshCart();
    }

    function incrementStepperValue(stepperView) {
        let stepperViewValue = stepperView.querySelector(".stepper-view-value");
        stepperViewValue.innerText++;
        refreshStepperState(stepperView);
    }

    function decrementStepperValue(stepperView) {
        let stepperViewValue = stepperView.querySelector(".stepper-view-value");
        stepperViewValue.innerText--;
        refreshStepperState(stepperView);
    }

    function refreshCart() {

        let cart = [];

        for (let stepperView of stepperViews) {

            let numberOfTickets = parseInt(stepperView.querySelector(".stepper-view-value").innerText);
            if (numberOfTickets > 0) {
                cart.push({
                    movieId: <%=movie.getId()%>,
                    sessionId: stepperView.id,
                    numberOfTickets: numberOfTickets
                });
            }

        }

        console.log(cart);

        $("#cart").val(JSON.stringify(cart));

    }

</script>