<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Menu </title>
</head>
<body>

<aside class="main-sidebar">
	<section class="sidebar">
		<form action="<c:url value='/' />" method="get" class="sidebar-form">
			<div class="input-group ui-widget">
				<input type="text" class="form-control" placeholder="Pesquisar ..." autocomplete="off">
				<span class="input-group-btn">
					<button id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>
				</span>
			</div>
		</form>
		<ul class="sidebar-menu" data-widget="tree">
			<li class="header">MENU PRINCIPAL</li>
			<li class="treeview menu-open">
				<a href="<c:url value='/' />" >
					<i class="fa fa-folder"></i>
					<span>Início</span>
					<span class="pull-right-container">
						<i class="fa fa-angle-left pull-right"></i>
					</span>
				</a>
				<ul class="treeview-menu" style="display: block;">
					<li><a href="<c:url value='/' />" ><i class="fa fa-tachometer-alt"></i> Dashboard</a></li>
					<li><a href="<c:url value='/funcionario' />" ><i class="fa fa-users"></i> Funcionarios</a></li>
					<li><a href="<c:url value='/emprestimo' />" ><i class="fa fa-landmark"></i> Emprestimos</a></li>
					<li><a href="<c:url value='/parcela' />" ><i class="fa fa-money-bill"></i> Parcelas</a></li>
				</ul>
			</li>
		</ul>
	</section>
</aside>

</body>
</html>