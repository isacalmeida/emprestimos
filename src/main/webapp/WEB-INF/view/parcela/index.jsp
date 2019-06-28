<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="br.edu.unoesc.util.Constants" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Parcelas</title>

<jsp:include page="../header.jsp"></jsp:include>

</head>
<body class="hold-transition skin-blue sidebar-mini">

<div class="wrapper">

<jsp:include page="../main.jsp"></jsp:include>
	
<jsp:include page="../menu.jsp"></jsp:include>

<div class="content-wrapper">
	<section class="content-header">
		<h1>
			Parcelas
			<small>Cadastro de Parcelas</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="<c:url value='/' />" ><i class="fa fa-tachometer-alt"></i> Início</a></li>
			<li class="active">Parcelas</li>
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
					<a href="<c:url value='/' />"><button type="button" class="btn btn-info btn-flat">Voltar</button></a>
				</div>
			</div>
			<div class="box-body">
				<div class="box box-solid">
					<div class="box-header with-border">
						<form:form method="post" servletRelativeAction="/parcela/filtrar" modelAttribute="parcelaFilter">
							<div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-6">
								<form:label path="dataInicio"> Data de Vencimento: </form:label>
								<div class="input-group date datepicker" data-provide="datepicker">
									<span class="input-group-btn">
										<button class="btn btn-default btn-flat" type="button">
											<span class="fa fa-calendar"></span>
										</button>
									</span>
									<form:input path="dataInicio" class="form-control"></form:input>
								</div>
							</div>
							<div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-6">
								<form:label path="dataFim"> Até: </form:label>
								<div class="input-group date datepicker" data-provide="datepicker" id="divDataFim">
									<span class="input-group-btn">
										<button class="btn btn-default btn-flat" type="button">
											<span class="fa fa-calendar"></span>
										</button>
									</span>
									<form:input path="dataFim" class="form-control"></form:input>
									<span class="input-group-btn">
										<button class="btn btn-default btn-flat" type="submit" id="btnDataFim">
											<span class="fa fa-search"> Pesquisar </span>
										</button>
									</span>
								</div>
							</div>
						</form:form>
					</div>
					<div class="box-body table-responsive no-padding">
						<table id="tabela_principal" class="table table-hover">
							<thead>
								<tr>
									<th>Id Parcela</th>
									<th>Id Emprestimo</th>
									<th>Funcionário</th>
									<th>Parcela</th>
									<th>Vencimento</th>
									<th>Valor da Parcela</th>
									<th>Valor Pago</th>
									<th>Opções</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="p" items="${parcelas }">
									<c:if test="${!empty(p)}">
										<tr>
											<td>${p.id }</td>
											<td>${p.emprestimo.id }</td>
											<td>${p.emprestimo.funcionario.nome }</td>
											<td>${p.parcela }</td>
											<td>${p.vencimento }</td>
											<td>${p.valorParcela }</td>
											<td>${p.valorPago }</td>
											<td>
												<a href="" data-toggle="modal" data-target="#confirm${p.id }">Baixar</a>
											</td>
										</tr>
									</c:if>
								</c:forEach>
							</tbody>
						</table>
						<c:forEach var="p" items="${parcelas }">
							<c:if test="${!empty(p)}">
								<div class="modal fade" id="confirm${p.id }">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal" aria-label="Close">
												<span aria-hidden="true">&times;</span></button>
												<h4 class="modal-title">Confirmação</h4>
											</div>
											<div class="modal-body">
												<p>Tem certeza que deseja baixar a parcela?</p>
											</div>
											<div class="modal-footer">
												<button type="button" data-dismiss="modal" class="btn btn-default pull-left"> Voltar </button>
												<a href="<c:url value='/parcela/${p.id }/baixar'/>" ><button type="button" class="btn btn-primary" id="baixar"> Confirmar </button></a>
											</div>
										</div>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>

</div>

<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>