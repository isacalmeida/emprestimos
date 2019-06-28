<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Footer </title>
</head>
<body>

<script src="<c:url value='/static/lib/jquery-1.12.4.min.js' />"></script>
<script src="<c:url value='/static/bootstrap-3.3.7/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/static/defaults/js/adminlte.min.js'/>"></script>
<script src="<c:url value='/static/defaults/js/demo.js'/>"></script>
<script src="<c:url value='/static/bootstrap-datepicker-1.6.4/js/bootstrap-datepicker.min.js'/>"></script>
<script src="<c:url value='/static/bootstrap-datepicker-1.6.4/locales/bootstrap-datepicker.pt-BR.min.js'/>" charset="UTF-8"></script>
<script src="<c:url value='/static/jquery-mask-1.14.15/jquery.mask.min.js'/>"></script>
<script src="<c:url value='/static/select2-4.0.6/js/select2.min.js'/>"></script>
<script src="<c:url value='/static/select2-4.0.6/js/i18n/pt-BR.js'/>"></script>

<script>
$('.datepicker').datepicker({
	autoclose: true,
	calendarWeeks: true,
	format: 'dd/mm/yyyy',
	language: 'pt-BR',
	showOnFocus: false,
	todayBtn: 'linked',
	todayHighlight: true
});
</script>

<script>
$('#btnDataFim').click(function(){
	$('#divDataFim').datepicker('hide');
});
</script>

<script>
$(document).ready(function(){
	var optionsData = {
		clearIfNotMatch: true
	};
	$('input[name*=\'data\']').mask('00/00/0000', optionsData);
	
	var optionsCpf = {
		clearIfNotMatch: true
	};
	$('input[name=\'cpf\']').mask('000.000.000-00', optionsCpf);
	
	var optionsDinheiro = {
		clearIfNotMatch: true,
		reverse: true
	};
	$('.mask_dinheiro').mask("##0.00", optionsDinheiro);
});
</script>

<script>
$(function () {
	//Initialize Select2 Elements
	$('.select2').select2({
		placeholder: "",
		language: "pt-BR",
		width: '100%'
	});
});
$("button[data-select2-open]").click(function() {
	$("#" + $(this).data("select2-open")).select2("open");
});
</script>

</body>
</html>