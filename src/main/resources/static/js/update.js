let updateIndex = {

		init: function(){
//            이벤트핸들러 나열할 때 "," 안씀
			$("#btn-save").on("click", ()=>{
				this.save();
			});


		},

		save: function(){
			let data = {
			        id: $("#id").val(),
			        prevCategory:$("#prevCategory").val(),
			        category:$("#category").val(),
			        title: $("#title").val(),
					content: $("#summernote").val()
			};
			$.ajax({
				type: "PUT",
				url: "/api/file",
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
//				dataType: "json"
			}).done(function(tmp){
				alert(" 완료되었습니다.!!");
				location.href = `/view/${tmp.category}/${tmp.id}`;
			}).fail(function(error){
				console.log(error);
			});
		}
}

updateIndex.init();
