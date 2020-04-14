<html>
	<head>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="alert alert-danger" role="alert">
						Su sesión ha finalizado, por favor vuelva a iniciar sesión.
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-12">	
					<form action="<%=request.getContextPath()%>">
						<button type="submit" class="btn btn-primary">Volver</button>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>
