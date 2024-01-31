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
            alert(JSON.stringify(data))
			$.ajax({
				type: "POST",
				url: "/api/file/update",
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
//				dataType: "json"
			}).done(function(tmp){
				alert(" 완료되었습니다.!!"+JSON.stringify(tmp));
				location.href = `/view/${tmp.category}/${tmp.id}`;
			}).fail(function(error){
				console.log(error);
			});
		}
}

updateIndex.init();
