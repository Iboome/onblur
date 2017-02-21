var stompClient = null;
var pageW=parseInt($(document).width());
var pageH=parseInt($(document).height());
var Top;

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $(".barrage-text").val()}));
    $(".barrage-text").val('');
}

function showGreeting(message) {
    var creSpan=$("<span class='barrage'></span>");
    creSpan.text(message);
    Top=parseInt(pageH*(Math.random()));
    if(Top>pageH-90){
        Top=pageH-90;
    }
    creSpan.css({"top":Top,"right":-300,"color":getRandomColor()});
    $(".barrage-main").append(creSpan);
    var spanDom=$(".barrage-main>span:last-child");
    spanDom.stop().animate({"right":pageW+300},10000,"linear",function(){
        $(this).remove();
    });
}

function getRandomColor(){
    return '#' + (function(h){
            return new Array(7 - h.length).join("0") + h
        })((Math.random() * 0x1000000 << 0).toString(16));
}

$(function () {
    $( "#send" ).click(function() { sendName(); });
    document.onkeydown=function(e){
        if(e.keyCode == 13){
            sendName();
        }
    };
    $("#barrage_switch").bind("click",function(){
        var flag = $("#barrage_switch").is(':checked');
        if(flag){
            connect();
        }else{
            disconnect();
        }
    });
});

