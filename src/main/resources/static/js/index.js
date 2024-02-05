let updateIndex = {

		init: function(){
//            이벤트핸들러 나열할 때 "," 안씀
			$("#btn-board").on("click", ()=>{
				this.board();
			});


		},

		board: function(){
            alert("글쓰기 이동하시겠습니까?")
			$.ajax({
				type: "get",
				url: "/api/board",
				contentType: "application/json; charset=utf-8",
//				dataType: "json"
			}).done(function(tmp){
				location.href = "/image";
			}).fail(function(error){
				alert(error.responseText);
				location.href = "/";
			});
		}
}

updateIndex.init();
