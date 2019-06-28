<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ page import="br.edu.unoesc.util.Constants" %>
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
			<li><a href="<c:url value='/' />" ><i class="fa fa-tachometer-alt"></i> Início</a></li>
			<li class="active">Empréstimos</li>
		</ol>
	</section>
	<section class="content">
		<c:if test="${status != null }">
			<div class="box">
				<div class="box-header">
					<i class="fa fa-bullhorn"></i>
					<h3 class="box-title">Alertas</h3>
					<div class="box-tools pull-right">
						<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
						<button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
					</div>
				</div>
				<div class="box-body">
					<c:if test="${status == Constants.FALHA }">
						<div class="callout callout-danger">
							<h4><i class="icon fa fa-ban"></i> Ops</h4>
							<p>${message }</p>
						</div>
					</c:if>
					<c:if test="${status == Constants.SUCESSO }">
						<div class="callout callout-success">
							<h4><i class="icon fa fa-check"></i> Sucesso</h4>
							<p>${message }</p>
						</div>
					</c:if>
				</div>
			</div>
		</c:if>
		<div class="box">
			<div class="box-header with-border">
				<div class="col-xs-10 col-sm-6 col-md-4 col-lg-4">
					<a href="<c:url value='/emprestimo/novo'/>" ><button type="button" class="btn btn-primary btn-flat">Novo</button></a>
					<a href="<c:url value='/' />"><button type="button" class="btn btn-info btn-flat">Voltar</button></a>
				</div>
			</div>
			<div class="box-body table-responsive">
				<table id="tabela_principal" class="table table-hover">
					<thead>
						<tr>
							<th>#</th>
							<th>Funcionário</th>
							<th>Data da Operação</th>
							<th>Valor do Empréstimo</th>
							<th>Qtd de Parcelas</th>
							<th>Primeiro Vencimento</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="e" items="${emprestimos }">
							<c:if test="${!empty(e)}">
								<tr>
									<td>${e.id }</td>
									<td><a href="<c:url value='/emprestimo/${e.id }/editar' />"  >${e.funcionario.nome }</a></td>	
									<td>${e.dataDaOperacao }</td>
									<td>${e.valorEmprestimo }</td>
									<td>${e.qtdParcelas }</td>
									<td>${e.dataPrimeiraParcela }</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</section>
</div>

</div>

<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>