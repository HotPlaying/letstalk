var wsurl = 'ws://192.168.0.102:7788/' + currentUserId;

var websocket = new WebSocket(wsurl);

//连接成功建立的回调方法
websocket.onopen = function () {
};

//接收到消息的回调方法
websocket.onmessage = function (event) {
    handleReceiveMessage(event.data);
};

//连接关闭的回调方法
websocket.onclose = function () {
};

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
    closeWebSocket();
};

//关闭WebSocket连接
function closeWebSocket() {
    websocket.close();
}

//处理收到的消息
function handleReceiveMessage(message) {
    var record = JSON.parse(message);
    if (record.type === 0) {
        if (currentChatObject === record.to) {
            Message.show(record);
            ChatModule.Window.autoScroll();
        }
        else if (record.isReceived === 0 || currentChatObject !== record.to) {
            SideBarModule.Friends.notice(record.from);
        } else {
            console.log('el');
        }
    } else if (record.type === 2) {
        SideBarModule.Friends.friendAddNotice(record);
    } else if (record.type === 3){
        console.log('else00');
    }
}


// //连接发生错误的回调方法
// websocket.onerror = function () {
// };
