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
			<form:form method="post" servletRelativeAction="/emprestimo/salvar" modelAttribute="emprestimo">
				<div class="box-header with-border">
					<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<form:label path="id"> Id </form:label>
						<form:input path="id" class="form-control" readonly="true"></form:input>
					</div>
				</div>
				<div class="box-body">
					<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<form:label path="dataDaOperacao"> Data da Operação </form:label>
						<div class="input-group date datepicker" data-provide="datepicker" data-date-start-date="-0d">
							<span class="input-group-btn">
								<button class="btn btn-default btn-flat" type="button">
									<span class="fa fa-calendar"></span>
								</button>
							</span>
							<form:input path="dataDaOperacao" class="form-control"></form:input>
						</div>
					</div>
					<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<form:label path="funcionario"> Funcionário </form:label>
						<div class="input-group">
							<span class="input-group-btn">
								<button class="btn btn-default btn-flat" type="button" data-select2-open="funcionario">
									<span class="fa fa-search"></span>
								</button>
							</span>
							<form:select path="funcionario" class="form-control select2">
								<form:option value="null"> Selecione </form:option>
								<form:options items="${funcionarios}" itemLabel="nome" itemValue="id" />
							</form:select>
						</div>
					</div>
					<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<form:label path="valorEmprestimo"> Valor Empréstimo </form:label>
						<div class="input-group">
							<div class="input-group-addon">
								<span class="fa fa-dollar-sign"></span>
							</div>
							<form:input path="valorEmprestimo" class="form-control mask_dinheiro"></form:input>
						</div>
					</div>
					<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<form:label path="qtdParcelas"> Quantidade de parcelas </form:label>
						<div class="input-group">
							<span class="input-group-btn">
								<button class="btn btn-default btn-flat" type="button" data-select2-open="qtdParcelas">
									<span class="fa fa-search"></span>
								</button>
							</span>
							<form:select path="qtdParcelas" class="form-control select2">
								<form:option value="null"> Selecione </form:option>
								<form:option value="3"> 3x </form:option>
								<form:option value="6"> 6x </form:option>
								<form:option value="12"> 12x </form:option>
								<form:option value="24"> 24x </form:option>
							</form:select>
						</div>
					</div>
					<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<form:label path="dataPrimeiraParcela"> Data Primeira Parcela </form:label>
						<div class="input-group date datepicker" data-provide="datepicker">
							<span class="input-group-btn">
								<button class="btn btn-default btn-flat" type="button">
									<span class="fa fa-calendar"></span>
								</button>
							</span>
							<form:input path="dataPrimeiraParcela" class="form-control"></form:input>
						</div>
					</div>
				</div>
				<div class="box-footer">
					<div class="col-xs-12 col-sm-10 col-md-8 col-lg-8">
						<button name="submit" value="1" type="submit" class="btn btn-success btn-flat">Salvar</button>
						<a href="<c:url value='/emprestimo' />"><button type="button" class="btn btn-default btn-flat">Voltar</button></a>
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