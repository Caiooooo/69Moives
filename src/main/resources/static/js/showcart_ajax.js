$(function(){
    sum();
    total();
    chk();
    $(".goto_order").click(function(){
    //    alert("aaba");
        var arr = [];//初始化一个数组arr，用于保存获得多选框的值。
        $('input:checkbox:checked').each(function(){
            if($(this).attr("class") != "allC"){
                arr.push($(this).attr('value'));
            }
        });
        var cartIds=arr.toString();
        //使用Ajax进行跳转，并带参数
        $.post("/order/order",{"cartIds":cartIds},function(){
            window.location="/order/order?cartIds="+cartIds;
        });
    });
});

function sum(){
    $('#goods tr').each(function(){
        var price = parseFloat($(this).find("td:eq(4)").text());
        var num	= parseInt($(this).find("input[name='num']").val());
        $(this).find("td:eq(6)").text(price * num);
    })
}
function total(){
    var s=0;
    $('#goods tr').each(function(){
        if($(this).find("input[type='checkbox']").attr('checked')){
            var prices = parseFloat($(this).find("td:eq(6)").text());
            s+=prices;
        }
    })
    $("#total").text(s);
}

function change(btn, n,cartId) {
    var t = $(btn).next().length==0? $(btn).prev() : $(btn).next();
    var num = parseInt(t.val());
    if(num<=1 && n<0) {
        return;
    }

    t.val(num+n);
    num = t.val();

    var tds=$(btn).parent().siblings();
    var yourprice = tds.eq(4).text();
    tds.eq(5).text(yourprice * num);
    total();
    $.post("/cart/updateCart",{"cartId":cartId,"num":num});
}

function del(btn,cid){
    $(btn).parent().parent().remove();
    total();
    $.post("/cart/deleteCart",{"cartId":cid});
}

function chk(){
    total();
}

