var requestPre = 'http://localhost:8080';
var mytoken = localStorage.getItem('token');
// var requestPre = 'http://192.168.1.242:8080/zyportal_dxwlserver';
// var requestPre = 'http://118.31.64.193:8080/zyportal_dxwlserver';
var socket;
var str;
function takeUuid(len, radix) {
    var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
    var uuid = [], i;
    radix = radix || chars.length;

    if (len) {
        for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random() * radix];
    } else {
        var r;
        uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
        uuid[14] = '4';
        for (i = 0; i < 36; i++) {
            if (!uuid[i]) {
                r = 0 | Math.random() * 16;
                uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
            }
        }
    }

    return uuid.join('');
}

$(function () {
    webSockets();
})


function webSockets() {
    getposition();
    if (typeof(WebSocket) == "undefined") {
        console.log("您的浏览器不支持WebSocket");
    } else {
        console.log("您的浏览器支持WebSocket");
        //实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
        //等同于socket = new WebSocket("ws://localhost:8083/checkcentersys/websocket/20");
        socket = new WebSocket(("http://" + window.location.host + "/websocket/" + mytoken).replace("http", "ws"));
        //打开事件
        socket.onopen = function () {
            console.log("Socket 已打开");
            $.toptip('已连接服务器,当前城市：' + returnCitySN['cname']);
            //socket.send("这是来自客户端的消息" + location.href + new Date());
        };
        //获得消息事件
        socket.onmessage = function (msg) {
            var json = $.parseJSON(msg.data);
            if (json.type == "sys") {
                online(json);
            } else if (json.type == "area") {
                online(json);
            } else if (json.type == "error") {
                window.location.href = '/html/login';
            } else {
                reMsg(json);
            }

            clearTimeout(disConnect);
            //发现消息进入    开始处理前端触发逻辑
        };
        //关闭事件
        socket.onclose = function () {
            console.log("Socket已关闭");
            disConnect();
            $.toptip('服务器断开连接');

        };
        //发生了错误事件
        socket.onerror = function () {
            $.toptip('连接服务器发生错误');
            //此时可以尝试刷新页面
        }
    }
}


var disConnect = function () {
    setTimeout(function () {
        webSockets();
    }, 5000);
}


/*
 function getposition() {
 var map = new BMap.Map("allmap");
 var geolocation = new BMap.Geolocation();
 geolocation.getCurrentPosition(function (r) {
 if (this.getStatus() == BMAP_STATUS_SUCCESS) {
 var mk = new BMap.Marker(r.point);
 map.addOverlay(mk);
 map.panTo(r.point);
 //经度
 var longitude = r.point.lng;
 //纬度
 var latitude = r.point.lat;
 var geoc = new BMap.Geocoder();
 var point = new BMap.Point(longitude, latitude);
 geoc.getLocation(point, function (rs) {
 var addComp = rs.addressComponents;
 str = addComp.province + "" + addComp.city + "" + addComp.district + "" + addComp.street + "" + addComp.streetNumber;
 socket.send("地理位置：" + str + ",经度:" + longitude + ",纬度:" + latitude);
 });
 }
 else {
 }
 }, {enableHighAccuracy: true})


 }


 var infoWindow = new BMap.InfoWindow(appendDiv);  // 创建信息窗口对象
 infoWindow.addEventListener('open',function(type, target, point){ //窗口打开是，隐藏自带的关闭按钮
 $('.BMap_pop>img').hide();
 })*/


function getposition() {
    var mapObj = new AMap.Map('iCenter');
    mapObj.plugin('AMap.Geolocation', function () {
        geolocation = new AMap.Geolocation({
            enableHighAccuracy: true, // 是否使用高精度定位，默认:true
            timeout: 10000,           // 超过10秒后停止定位，默认：无穷大
            maximumAge: 0,            // 定位结果缓存0毫秒，默认：0
            convert: true,            // 自动偏移坐标，偏移后的坐标为高德坐标，默认：true
            showButton: true,         // 显示定位按钮，默认：true
            buttonPosition: 'LB',     // 定位按钮停靠位置，默认：'LB'，左下角
            buttonOffset: new AMap.Pixel(10, 20), // 定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
            showMarker: true,         // 定位成功后在定位到的位置显示点标记，默认：true
            showCircle: true,         // 定位成功后用圆圈表示定位精度范围，默认：true
            panToLocation: true,      // 定位成功后将定位到的位置作为地图中心点，默认：true
            zoomToAccuracy: true       // 定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
        });
        mapObj.addControl(geolocation);
        geolocation.getCurrentPosition();
        AMap.event.addListener(geolocation, 'complete', onComplete); // 返回定位信息
        AMap.event.addListener(geolocation, 'error', onError);       // 返回定位出错信息

    });
}

function onComplete(obj) {
    var param = {};
    param.type = "area";
    param.msg = "地理位置：" + obj.formattedAddress + ",经纬度:" + obj.position;
    param.toUser = "";
    param.fromUser = "";
    socket.send(JSON.stringify(param));
}
function onError(obj) {
    /*    window.location.href = "https://www.baidu.com/";*/
}

//公共AJAX
function getData(type, url, data, dataType, success) {
    $.ajax({
        type: type,
        url: url,
        cache: false,
        data: data,
        dataType: dataType,
        success: success,
        headers: {
            "Authorization": localStorage.getItem('token')
        }
    });
}
function getData_async(type, url, data, dataType, success) {
    $.ajax({
        type: type,
        url: url,
        cache: false,
        data: data,
        dataType: dataType,
        success: success,
        async: false,
        headers: {
            "Authorization": localStorage.getItem('token')
        }
    });
}



