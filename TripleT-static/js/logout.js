function logout(){
	var _ticket = $.cookie("EM_TICKET");
	//alert("退出的ticket",_ticket);
    $.ajax({
       url:"http://www.triplet.com/user/logout/"+ _ticket,
       dataType:"json",
       type:"GET",
	   data:{"ticket":_ticket},
       success:function (data) {
           if(data.status==200){
               window.location.href="http://www.triplet.com/";
			   window.location.reload();
               return;
           }else{
               alert("登出失败");
               return;
           }
       },
       error:function () {
           alert("登出请求发送失败");
       }
    });
}