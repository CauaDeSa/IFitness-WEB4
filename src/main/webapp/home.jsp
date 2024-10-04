<%@ page contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html data-bs-theme="dark">
<head>
	<meta charset="UTF-8" lang="pt-br">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<link rel="stylesheet" href="css/home.css">
	<title>IFitness - Página Principal</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="homeServlet">IFitness</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	        <li class="nav-item">
	          <a class="nav-link" href="activity-register.jsp">
	          	Nova Atividade</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="statistics.jsp">Estatísticas</a>
	        </li>
	      </ul>
	      <form class="d-flex">
	        <input class="form-control me-2" type="search" 
	        	placeholder="Pesquisar" aria-label="Search">
	        <button class="btn btn-outline-success" type="submit">
	        	Pesquisar</button>
	      </form>
	      
	      <div class="dropdown-center">
	      	<a class="nav-link dropdown-toggle ms-3" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	            ${sessionScope.user.name}
	          </a>
	          <ul class="dropdown-menu mt-3" style="right: 0; left: unset;" aria-labelledby="navbarDropdown">
	            <li><a class="dropdown-item" href="#">Minha Conta</a></li>
	            <li><hr class="dropdown-divider"></li>
	            <li><a class="dropdown-item" href="logout">Sair</a></li>
	          </ul>
	      </div>
	    </div>
	  </div>
	</nav>
	<div class="container d-flex flex-column justify-content-center align-items-center">
		<div class="center col-lg-10 col-sm-12">
			<h1 class="text-center">Listagem de Atividades</h1>
			<form action="activitySearch" method="post">
				<div class="row">
					<div class="col-12 col-lg-3">
					  	<div class="mb-2">
							<label for="type">Tipo</label> 
							<select class="form-select"
								name="type" id="type">
								<option value="" selected>Selecione</option>
								<option value="CAMINHADA">Caminhada</option>
								<option value="CICLISMO">Ciclismo</option>
								<option value="CORRIDA">Corrida</option>
								<option value="NATACAO">Natação</option>
							</select>
						</div>
					</div>
					<div class="col-12 col-lg-3">
						<div class="mb-2">
							<label for="initial-date">Data inicial</label> 
							<input type="date" name="initial-date" id="initial-date"
								class="form-control">
						</div>
					</div>  
					<div class="col-12 col-lg-3">
						<div class="mb-2">
							<label for="final-date">Data final</label>
							<input type="date" name="final-date" id="final-date"
								class="form-control">
						</div>
					</div>
					<div class="col-12 col-lg-3 mt-4">
						<button type="submit" class="btn btn-primary">Filtrar</button>
					</div>  
				</div>
			</form>
			<c:choose>
				<c:when test="${fn:length(userActivities) > 0}">
					<table class="table table-striped table-hover table-responsive">
						<tr>
							<th>#</th>
							<th>Tipo</th>
							<th>Data</th>
							<th>Distância (km)</th>
							<th>Duração (minutos)</th>
							<th>Ações</th>
						</tr>
						<c:forEach var="activity" 
							items="${userActivities}" varStatus="index">
							<tr>
								<td>${index.count}</td>
								<td>
									<c:choose>
										<c:when test="${activity.type == 'CORRIDA'}">
											<img src="img/running_icon.png" alt="Corrida">
										</c:when>
										<c:when test="${activity.type == 'CAMINHADA'}">
											<img src="img/walking_icon.png" alt="Caminhada">
										</c:when>
										<c:when test="${activity.type == 'CICLISMO'}">
											<img src="img/cycling_icon.png" alt="Ciclismo">
										</c:when>
										<c:when test="${activity.type == 'NATACAO'}">
											<img src="img/swimming_icon.png" alt="Natação">
										</c:when>
									</c:choose>
								
								</td>
								<td><fmt:parseDate value="${activity.date}"
											pattern="yyyy-MM-dd" var="parsedDate" type="date" /> <fmt:formatDate
											value="${parsedDate}" var="formattedDate" type="date"
											pattern="dd/MM/yyyy" /> ${formattedDate}</td>
									<td><fmt:formatNumber value="${activity.distance}"
											var="formattedDistance" type="number" maxFractionDigits="1" />
										${formattedDistance} km</td>
									<td><fmt:formatNumber value="${activity.duration}"
											var="formattedDuration" type="number" maxFractionDigits="1" />

										<c:if test="${activity.duration < 60}">
                                            ${formattedDuration} min
                                         </c:if> <c:if
											test="${activity.duration >= 60}">
											<fmt:formatNumber value="${activity.duration/60}"
												var="formattedHours" type="number" maxFractionDigits="0" />
                                            ${formattedHours}h
                                            <fmt:formatNumber
												value="${activity.duration%60}" var="formattedMinutes"
												type="number" maxFractionDigits="1" />
                                            ${formattedMinutes}min
                                           </c:if></td>
								<td>
									<span data-bs-toggle="tooltip" data-bs-placement="top" title="Editar">
										<a class="btn" href="activityRegister?action=update&activity-id=${activity.id}">
	                						<img src="img/pencil-square.svg" alt="Editar">
	                					</a>
									</span>

            							<span data-bs-toggle="tooltip" data-bs-placement="top" title="Excluir">
            								<a type="button" class="btn" data-bs-toggle="modal" data-bs-target="#myModal" 
            								data-bs-id="${activity.id}">
             								<img src="img/trash.svg" alt="Excluir">
             							</a>
            							</span>
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<c:out value="Nenhuma atividade cadastrada."></c:out>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	
	<div class="modal" tabindex="-1" id="myModal">
		<div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title">Exclusão</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        <p>Tem certeza que deseja excluir a atividade?</p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
		        <button type="button" id="delete" class="btn btn-danger">Excluir</button>
		      </div>
		    </div>
	  	</div>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	<script type="text/javascript" src="js/home.js"></script>
</body>
</html>