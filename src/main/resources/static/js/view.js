let writeShowIndex={
    init: function(){
        	$("#btn-delete").on("click", ()=>{
        				this.deleteById();
        			});
            $("#btn-update").on("click", ()=>{
                this.update();
            });
    },

    deleteById: function(){
    		 let id = $("#text-id").text();
    		 let category = $("#hid").val();
            let url = "/api/file/"+category+"/"+id;
            var result = confirm("삭제하시겠습니까?");
           if(result){
            alert("삭제완료");
           }else {
            alert("삭제취소");
            return;
           }
            $.ajax({
                type: "delete",
                url: url,
                contentType: "application/json; charset=utf-8"
            }).done(function(result){
                location.href="/";
            }).fail(function(error){
                alert(error.responseText);
            });
    		},
    update: function(){
    		 let id = $("#text-id").text();
    		 let category = $("#hid").val();
            $.ajax({
                type: "get",
                url: "/api/file/"+category+"/"+id,
                contentType: "application/json; charset=utf-8"
            }).done(function(result){
                 console.log(result);
                location.href="/update/"+category+"/"+id
            }).fail(function(error){
             alert(error.responseText);
            });
    		}
}
writeShowIndex.init();