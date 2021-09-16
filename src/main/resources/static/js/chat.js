/*发送消息*/
function send(man, str) {
    var date = new Date().format("yyyy-MM-dd hh:mm:ss");
    var html = "<div class='send'>   <div class='time'>" + date + "</div> <div class='name'>" + man + "</div><div class='msg'><img src='/static/img/avatar2.jpg' />" +
        "<p><i class='msg_input'></i>" + str + "</p></div></div>";
    upView(html);
}
/*接受消息*/
function show(headSrc, str) {
    var date = new Date().format("yyyy-MM-dd hh:mm:ss");
    var html = "<div class='show'><div class='time'>" + date + "</div><div class='msg'><img src='/static/img/avatar3.jpg'/>" +
        "<p><i class='msg_input'></i>" + str + "</p></div></div>";
    upView(html);
}

function sysonline(str) {
    var html = ' <div class="chat-notice">' +
        '<span>' + str + '上线</span>' +
        '</div>';
    upView(html);
}
/*更新视图*/
function upView(html) {
    $('.message').append(html);
    $('main').scrollTop($('main')[0].scrollHeight);

}
function sj() {
    return parseInt(Math.random() * 10)
}
$(function () {
    $('.footer').on('keyup', 'input', function () {
        if ($(this).val().length > 0) {
            $(this).next().css('background', '#114F8E').prop('disabled', true);
        } else {
            $(this).next().css('background', '#ddd').prop('disabled', false);
        }
    })
    $('.footer p').click(function () {
        if ($(this).prev().val().length > 0) {
            show("/static/img/avatar3.jpg", $(this).prev().val());
            var param = {};
            param.type = "msg";
            param.msg = $(this).prev().val();
            param.toUser = "";
            param.fromUser = "";
            var a = JSON.stringify(param);
            parent.sendSysInfo(a);
            $(this).prev().val('');
            $(this).css('background', '#ddd').prop('disabled', false);
        }
    })
})

/*
 /!*测试数据*!/
 var arr = ['사랑해요사랑해요사랑해요사랑해요사랑해요사랑해요사랑해요사랑해요사랑해요사랑해요사랑해요사랑해요사랑해요사랑해요사랑해요사랑해요사랑해요사랑해요사랑해요사랑해요사랑해요', '천송이사랑해요사랑해요사랑해요사랑해요사랑해요사랑해요'];
 var imgarr = ['/static/img/touxiang.png', 'images/touxiangm.png'];

 function test() {
 $(arr).each(function (i) {
 setTimeout(function () {
 send("/static/img/avatar2.jpg", arr[i])
 }, sj() * 500)
 })
 }*/
Date.prototype.format = function (format) {
    var args = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(format))
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var i in args) {
        var n = args[i];
        if (new RegExp("(" + i + ")").test(format))
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? n : ("00" + n).substr(("" + n).length));
    }
    return format;
};

function preNew() {
    parent.reNew();
}