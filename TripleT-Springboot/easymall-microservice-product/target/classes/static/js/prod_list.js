
function addCartOne(productId){
    if(productId==null){
        alert("商品id错误");
        return;
    }
    $.ajax({
        url:"http://www.triplet.com/cart/save?productId="+productId+"&"+"userId"+"=6dd8674f-9ef4-4ed2-90aa-66f66bd59a2a"+"&"+"num"+"=1",
        dataType:"json",
        type:"GET",
        success:function (data) {
            if(data.status==200){
                window.location.href="http://www.triplet.com/mycart.html";
            }
            else{
                alert("新增购物车失败");
            }
        } ,
        error:function () {
            alert("发送失败");
        }

    });
}