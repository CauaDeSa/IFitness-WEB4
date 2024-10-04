<%@ page contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html data-bs-theme="dark">
<head>
	<meta charset="UTF-8" lang="pt-br">
	<title>IFitness - Estatísticas</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
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
	<div class="container">
	    <div class="row my-3">
	        <div class="col">
	            <h4>Estatísticas das Atividades Físicas Realizadas</h4>
	        </div>
	    </div>
	    <div class="row my-2">
	        <div class="col-md-6 py-1">
	            <div class="card">
	            	<h5 class="card-title">Atividades por tipo</h5>
	                <div class="card-body">
	                    <canvas id="chDonut1"></canvas>
	                </div>
	            </div>
	        </div>
	    </div>
	    
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.js'></script>
	<script type="text/javascript" src="js/statistics.js"></script>
</body>
</html>