let writeShowIndex={
    init: function(){
        	$("#btn-delete").on("click", ()=>{
        				this.deleteById();
        			});
    },

    deleteById: function(){
    		 let id = $("#text-id").text();
    		 let category = $("#hid").val();
            let url = "/api/delete/"+category+"/"+id+"";
            alert("삭제하시겠습니까? 글 번호:"+url);
            $.ajax({
                type: "post",
                url: url,
                contentType: "application/json; charset=utf-8",

            }).done(function(result){
                location.href="/";
            }).fail(function(error){
                alert(JSON.stringify(error));
            });
    		}
}
writeShowIndex.init();