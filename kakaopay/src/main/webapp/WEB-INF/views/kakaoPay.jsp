<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>


<script>
$(function(){
	$('#apibtn').click(function(){
		$.ajax({
			url : '/cls/jq/kakaopay.cls',
			dataType : 'json',
			success : function(data){
				aleat(data);
			},
			error : function(error){
				aleat(error);
			}
		});
	});
});

</script>

	<h1>
		카카오페이 테스트
	</h1>
	
	<form method ="post" action="/kakaoPay">
		<button class="apibtn" id="apibtn">카카오페이 결제</button>
	</form>
