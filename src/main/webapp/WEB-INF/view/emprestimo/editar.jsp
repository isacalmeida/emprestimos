<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Empréstimos</title>

<jsp:include page="../header.jsp"></jsp:include>

</head>
<body class="hold-transition skin-blue sidebar-mini">

<div class="wrapper">

<jsp:include page="../main.jsp"></jsp:include>
	
<jsp:include page="../menu.jsp"></jsp:include>

<div class="content-wrapper">
	<section class="content-header">
		<h1>
			Empréstimos
			<small>Cadastro de Empréstimos</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="<c:url value='/' />" ><i class="fa fa-dashboard"></i> Início</a></li>
			<li><a href="<c:url value='/emprestimo' />" >Empréstimos</a></li>
			<li class="active">Novo</li>
		</ol>
	</section>
	<section class="content">
		<div class="box">
			<form:form method="post" servletRelativeAction="/emprestimo/atualizar" modelAttribute="emprestimo">
				<div class="box-header with-border">
					<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<form:label path="id"> Id </form:label>
						<form:input path="id" class="form-control" readonly="true"></form:input>
					</div>
				</div>
				<div class="box-body">
					<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<form:label path="dataDaOperacao"> Data da Operação </form:label>
						<div class="input-group">
							<span class="input-group-btn">
								<button class="btn btn-default btn-flat" type="button">
									<span class="fa fa-calendar"></span>
								</button>
							</span>
							<form:input path="dataDaOperacao" class="form-control" readonly="true"></form:input>
						</div>
					</div>
					<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<form:label path="funcionario"> Funcionário </form:label>
						<div class="input-group">
							<span class="input-group-btn">
								<button class="btn btn-default btn-flat" type="button">
									<span class="fa fa-search"></span>
								</button>
							</span>
							<form:input path="funcionario.nome" class="form-control" readonly="true"></form:input>
						</div>
					</div>
					<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<form:label path="valorEmprestimo"> Valor Empréstimo </form:label>
						<div class="input-group">
							<div class="input-group-addon">
								<span class="fa fa-dollar-sign"></span>
							</div>
							<form:input path="valorEmprestimo" class="form-control" readonly="true"></form:input>
						</div>
					</div>
					<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<form:label path="qtdParcelas"> Quantidade de parcelas </form:label>
						<div class="input-group">
							<span class="input-group-btn">
								<button class="btn btn-default btn-flat" type="button" data-select2-open="funcionario">
									<span class="fa fa-search"></span>
								</button>
							</span>
							<form:input path="qtdParcelas" class="form-control" readonly="true"></form:input>
						</div>
					</div>
					<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<form:label path="dataPrimeiraParcela"> Data Primeira Parcela </form:label>
						<div class="input-group">
							<span class="input-group-btn">
								<button class="btn btn-default btn-flat" type="button">
									<span class="fa fa-calendar"></span>
								</button>
							</span>
							<form:input path="dataPrimeiraParcela" class="form-control" readonly="true"></form:input>
						</div>
					</div>
				</div>
				<div class="box-footer">
					<div class="col-xs-12 col-sm-10 col-md-8 col-lg-8">
						<a href="<c:url value='/emprestimo' />"><button type="button" class="btn btn-default btn-flat"> Voltar </button></a>
						<button type="button" class="btn btn-danger btn-flat" data-toggle="modal" data-target="#confirm">Excluir</button>
					</div>
				</div>
				<div class="modal fade" id="confirm">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span></button>
								<h4 class="modal-title">Confirmação</h4>
							</div>
							<div class="modal-body">
								<p>Tem certeza que deseja excluir?</p>
							</div>
							<div class="modal-footer">
								<button type="button" data-dismiss="modal" class="btn btn-default pull-left"> Voltar </button>
								<a href="<c:url value='/emprestimo/${emprestimo.id }/excluir'/>" ><button type="button" class="btn btn-primary" id="delete"> Confirmar </button></a>
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</section>
</div>

</div>

<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>