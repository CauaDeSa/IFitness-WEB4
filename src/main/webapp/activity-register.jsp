<%@ page contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!doctype html>
<html data-bs-theme="dark">
<head>
	<meta charset="UTF-8" lang="pt-br">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link href="css/styles.css" rel="stylesheet">
<link href="css/errors.css" rel="stylesheet">
<title>IFitness - Página de Cadastro de Atividade</title>
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
	          <a class="nav-link" href="homeServlet">
	          	Home</a>
	        </li>
	       </ul>
	    </div>
	  </div>
	</nav>
	<div class="container  col-lg-4 offset-lg-4">
		<div class="col-lg-6 offset-lg-3 col-sm-12">
			<c:if test="${result == 'notRegistered'}">
				<div class="alert alert-danger alert-dismissible fade show"
					role="alert">
					Atividade não salva. Efetue o login.
					<button type="button" class="btn-close" data-bs-dismiss="alert"
						aria-label="Close"></button>
				</div>
			</c:if>
			<c:if test="${result == 'registered'}">
				<div class="alert alert-success alert-dismissible fade show"
					role="alert">
					Atividade salva com sucesso.
					<button type="button" class="btn-close" data-bs-dismiss="alert"
						aria-label="Close"></button>
				</div>
			</c:if>
			
			<form action="activityRegister" method="post" id="form1">
				<c:choose>
					<c:when test="${activity == null}">
						<h1 class="text-center">Nova Atividade</h1>
					</c:when>
					<c:when test="${activity != null}">
						<h1 class="text-center">Edição de Atividade</h1>
					</c:when>
				</c:choose>
				
				<c:choose>
					<c:when test="${activity == null}">
						<input type="hidden" name="id" value="0">
					</c:when>
					<c:when test="${activity != null}">
						<input type="hidden" name="id" value="${activity.id}">
					</c:when>
				</c:choose>
				
				
				
				<div class="mb-2">
					<label for="type">Tipo de atividade*</label> 
					<select class="form-select"
						name="type" id="type" required="required">
						<c:if test="${activity == null}">
							<option value="" selected>Selecione</option>
						</c:if>
						<c:choose>
							<c:when test="${activity.type != 'CAMINHADA'}">
								<option value="CAMINHADA">Caminhada</option>
							</c:when>
							<c:when test="${activity.type == 'CAMINHADA'}">
								<option value="CAMINHADA" selected>Caminhada</option>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${activity.type != 'CICLISMO'}">
								<option value="CICLISMO">Ciclismo</option>
							</c:when>
							<c:when test="${activity.type == 'CICLISMO'}">
								<option value="CICLISMO" selected>Ciclismo</option>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${activity.type != 'CORRIDA'}">
								<option value="CORRIDA">Corrida</option>
							</c:when>
							<c:when test="${activity.type == 'CORRIDA'}">
								<option value="CORRIDA" selected>Corrida</option>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${activity.type != 'NATACAO'}">
								<option value="NATACAO">Natação</option>
							</c:when>
							<c:when test="${activity.type == 'NATACAO'}">
								<option value="NATACAO" selected>Natação</option>
							</c:when>
						</c:choose>
					</select>
				</div>
				
				<div class="mb-2">
					<label for="date">Data*</label> 
					<input type="date" name="date" id="date"
						class="form-control" required="required"
						value="${activity.date}">
				</div>
				
				<div class="mb-2">
					<label for="distance">Distância (Km)*</label> 
					<input type="number"
						name="distance" id="distance" 
						class="form-control" step="0.1" min="0.1"
						required="required" value="${activity.distance}">
				</div>
				
				<div class="mb-2">
					<label for="duration">Duração (Minutos)*</label> 
					<input type="number"
						name="duration" id="duration" 
						class="form-control" step="1" min="1"
						required="required" value="${activity.duration}">
				</div>

				<div class="mb-2 d-flex gap-2">
					<button type="submit" class="btn btn-primary mt-2">Salvar</button>
					
					<c:if test="${result == 'registered'}">

						<button type="button" class="btn btn-primary  mt-2"
		                    onclick="window.location.href='homeServlet'">Voltar</button>
		
					</c:if>
				</div>
			</form>
			
			
		</div>
	</div>

	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>







