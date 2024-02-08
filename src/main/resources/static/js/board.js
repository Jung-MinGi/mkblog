let index = {

		init: function(){
//            이벤트핸들러 나열할 때 "," 안씀
			$("#btn-save").on("click", ()=>{
				this.save();
			});


		},

		save: function(){

			let data = {
			        category:$("#tableNameSelect").val(),
			        title: $("#title").val(),
					content: $("#summernote").val()
			};

			$.ajax({
				type: "POST",
				url: "/api/file",
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
			}).done(function(resp){
				alert("글 작성 완료되었습니다."+JSON.stringify(resp));
//				location.href = `/view/${resp.category}/${resp.title}`;
				location.href = `/view/${resp.category}/${resp.id}`;
			}).fail(function(resp){
			    const error = resp.responseJSON;
                let defaultMessage = error[0].defaultMessage;
                alert(defaultMessage+"!");
			});
		}
}

index.init();
