function serverPath() {
    var urlPath = window.document.location.href;  //浏览器显示地址 http://10.10.10.10:7788/login
    var docPath = window.document.location.pathname; //文件在服务器相对地址 /user/id
    var index = urlPath.indexOf(docPath);
    var serverAddress = urlPath.substring(7, index).split(':')[0]; //服务器地址 10.10.10.10:7788
    var serverPort = '7788';
    var serverAddrAndPort = serverAddress + ':' + serverPort;
    return serverAddrAndPort;//服务器地址 10.10.10.10:7788
}
const WSURL = 'ws://' + serverPath() + '/' + currentUserId;
// var wsurl = 'ws://192.168.0.102:7788/' + currentUserId;

const websocket = new WebSocket(WSURL);

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
    const record = JSON.parse(message);
    //type == 0，一对一消息
    if (record.type === 0) {
        // 当前聊天窗口为此好友，直接显示
        if (currentChatUser === record.to || currentChatUser === record.from) {
            Message.show(record);
        } // 当前聊天窗口非此好友，通知
        else if (record.isReceived === 0) {
            SideBarModule.Friends.notice(record.from);
        } // 其它情况，通报异常
        else {
            console.log('exception in handleReceivedMessage (type === 0)');
        }
    } //type == 1，群组消息
    else if (record.type === 1) {
        // 收到群组消息，且当前窗口为该群组，直接显示消息
        if (currentChatGroup === record.to) {
            Message.show(record);
        }
        // 收到的群组消息不是当前窗口，改为通知
        else {
            SideBarModule.Groups.notice(record.to);
        }
    } // type == 2，好友添加请求
    else if (record.type === 2) {
        SideBarModule.Friends.friendAddNotice(record);
    } // type == 3，离线未读群组消息
    else if (record.type === 3){
        SideBarModule.Groups.notice(record.from);
    }
}


// //连接发生错误的回调方法
// websocket.onerror = function () {
// };
