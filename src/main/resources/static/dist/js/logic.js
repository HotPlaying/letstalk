// <script>
//
<!-- 用户信息请求模块 -->
var UserManagement = {
    // 通过用户编号获取用户名
    getUserNameByUserId: function (userId) {
        let userName = null;
        $.ajax({
            async: false, //设置同步
            type: 'POST',
            url: '/getUserName',
            data: {userId: userId},
            datetype: 'json',
            success: function (toUserName) {
                if (toUserName != null) {
                    userName = toUserName;
                } else {
                    layer.alert('查询名字错误1');
                }
            },
            error: function () {
                layer.alert('查询名字错误2');
            }
        });
        return userName;
    },

    // 获取当前用户
    getCurrentUser: function () {
        let currentUser = null;
        $.ajax({
            type: 'POST',
            url: '/getCurrentUser',
            success: function (user) {
                if (user) {
                    currentUser = user;
                } else
                    layer.alert('查询错误1');
            },
            error: function () {
                layer.alert('查询错误2');
            }
        });
        return currentUser;
    },

    // 获取当前用户的编号
    getCurrentUserId: function () {
        var currentUserId = null;
        $.ajax({
            async: false,
            type: 'POST',
            url: '/getCurrentUserId',
            dataType: 'text',
            success: function (userId) {
                if (userId) {
                    currentUserId = userId;
                } else
                    layer.alert('查询错误1');
            },
            error: function (userId) {
                layer.alert('查询错误2');
            }
        });
        return currentUserId;
    },

    // 通过用户编号获取用户详细信息
    getDetailByUserId: function (userId) {
            let userDetail = null;
            $.ajax({
                async: false,
                url: '/getUserDetailByUserId',
                type: 'POST',
                data: {userId: userId},
                dataType: 'json',
                success: function (jsonUserDetail) {
                    if (jsonUserDetail != null) {
                        userDetail = jsonUserDetail;
                    } else {
                        console.log('User is not exist');
                    }
                },
                error: function (jsonUserDetail) {
                    layer.alert('request fail');
                }
            });
            return userDetail;
        },

    // 请求后台系统添加好友关系记录
    addFriend: function (friendId) {
        console.log(friendId);
        $.ajax({
            async: false,
            type: 'POST',
            url: '/addFriend',
            data: {ida: currentUserId, idb: friendId},
            dataType: 'text',
            success: function (isSent) {
                if (isSent != null)
                    layer.alert("Friend adding success.");
                else
                    layer.alert("fail1");
            },
            error: function (isSent) {
                layer.alert("fail2");
            }
        });
    },

    // 保存用户详细信息
    saveUserDetail(userDetail) {
        $.ajax({
            async: false,
            url: '/saveUserDetail',
            data: {jsonUserDetail: userDetail},
            type: 'post',
            dataType: 'text',
            success: function (result) {
                if (result != null)
                    layer.alert('Save information success!');
                else layer.alert('!!');
            },
            error: function (result) {
                layer.alert("!!!1")
            }
        });
    }
};
<!-- ./ 用户信息请求模块 -->
<!-- 群组信息请求模块 -->
var GroupManagement = {
    // 通过群组编号获取群组详细信息
    getDetailByGroupId(groupId) {
        let groupDetail = null;
        $.ajax({
            async: false,
            url: '/getGroupByGroupId',
            type: 'POST',
            data: {groupId: groupId},
            dataType: 'json',
            success: function (jsonGroupDetail) {
                if (jsonGroupDetail != null) {
                    groupDetail = jsonGroupDetail;
                } else {
                    console.log('Group is not exist');
                }
            },
            error: function (jsonGroupDetail) {
                layer.alert('request fail');
            }
        });
        console.log(groupDetail)
        return groupDetail;
    },

    // 建立群组
    createGroup(group) {
        console.log(group);
        $.ajax({
            async: false,
            url: '/saveGroup',
            data: {jsonGroup: group},
            type: 'post',
            dataType: 'json',
            success: function (newgroup) {
                if (newgroup != null)
                    layer.msg('Create Group Success!\nYour group Id is [' + newgroup.groupId + ']', {icon: 1});
                else layer.alert('!!');
            },
            error: function (result) {
                layer.alert("!!!1")
            }
        });
    },

    // 请求后台系统添加群组关系记录，改变群组成员等
    joinGroup(groupId) {
        $.ajax({
            async: false,
            type: 'POST',
            url: '/joinGroup',
            data: {userId: currentUserId, groupId: groupId},
            dataType: 'text',
            success: function (isSent) {
                if (isSent != null)
                    layer.msg('Group joining success.', {icon: 1});
                else
                    layer.alert("fail1");
            },
            error: function (isSent) {
                layer.alert("fail2");
            }
        });
    }
};
<!-- ./ 群组信息请求模块 -->

<!-- 全局变量与事件触发 -->

<!--    全局变量 -->
// 当前用户
var currentUserId = UserManagement.getCurrentUserId();
var currentUserName = UserManagement.getUserNameByUserId(currentUserId);

// 当前窗口的聊天用户对象，初次载入时为空，未打开任何窗口
var currentChatUser = null;

// 当前窗口的聊天群组对象，初次载入时为空，未打开任何窗口
var currentChatGroup = null;
<!--   ./ 全局变量 -->

<!--    事件触发 -->
// 用户主界面载入时加载的内容
window.onload = function () {
    // 加载好友列表
    SideBarModule.Friends.load();
    // 加载群组列表
    SideBarModule.Groups.load();
};
// 屏蔽聊天输入框表单的默认操作
$(document).on('submit', '#chatFooter form', function (e) {
    e.preventDefault();
});
// 点击 编辑个人信息 按钮
$('#editCurrentUserDetail').click(function () {
    EjectModule.editProfile();
});
// 点击 保存个人信息 按钮
$('#saveUserDetail').click(function () {
    EjectModule.saveProfile();
});
// 点击 查看个人信息 按钮
$('#loadCurrentUserDetail').click(function () {
    SideBarModule.Profile.loadById(currentUserId);
});
// 点击 查找用户 按钮
$('#searchUserButton').click(function () {
    EjectModule.searchUser();
});
// 点击 创建群组 按钮
$('#createGroupButton').click(function () {
    EjectModule.createGroup();
});
// 点击 查找群组 按钮
$('#searchGroupButton').click(function () {
    EjectModule.searchGroup();
});
<!--   ./ 事件触发 -->
<!-- ./ 全局变量与事件触发 -->

<!-- 信息模块 -->
var Message = {
    // 将消息交由后台服务器处理
    send: function (to, content, type) {
        websocket.send(JSON.stringify({
            from: currentUserId,
            to: to,
            content: content,
            type: type,
        }))
    },

    /**
     * 显示单条消息气泡到聊天窗口
     * @param record 待显示的消息；
     */
    show: function (record) {
        let toName = arguments[1] ? arguments[1] : 0;
        $('.layout .content .chat .chat-body .messages').append(
            '<div class="message-item ' + (record.from == currentUserId ? 'outgoing-message' : '') + '">\n' +
            '                        <div class="message-avatar">\n' +
            '                            <figure class="avatar">\n' +
            '                                <img src="./dist/media/img/man_avatar1.jpg" class="rounded-circle" alt="image">\n' +
            '                            </figure>\n' +
            '                            <div>\n' +
            '                                <h5>' + (toName === 0 ? UserManagement.getUserNameByUserId(record.from) : toName) + '</h5>\n' +
            '                                <div class="time">' + record.time + '</div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                        <div class="message-content">\n' +
            record.content +
            '                        </div>\n' +
            '                    </div>');

        ChatModule.Window.autoScroll();
    },

    // 用于一对一聊天，设置离线消息为已读，防止再次通知
    setReceived: function (message) {
        $.ajax({
            async: false,
            url: '/setReceived',
            type: 'post',
            data: {jsonMessage: message},
            dataType: 'json',
        });
    },

    // 获取聊天记录
    getRecord: function (to, type) {
        var messageList = null;
        $.ajax({
            async: false, //设置同步
            type: 'POST',
            url: (type == 0 ? '/getMessageRecord' : '/getGroupMessageRecord'),
            data: (type == 0 ? {ida: currentUserId, idb: to} : {groupId: to}),
            dataType: 'json',
            success: function (record) {
                if (record != null) {
                    messageList = record.record;
                } else {
                    layer.alert('查询错误1');
                }
            },
            error: function (record) {
                layer.alert('查询错误2');
            }
        });
        //eval方法不同于prase方法，外面加括号
        messageList = eval("(" + messageList + ")");
        return messageList;
    },

    // 清理已经查看了的群组离线消息，防止再次通知
    cleanGroupReceived(groupId) {
        $.ajax({
            url: '/cleanGroupReceived',
            data: {groupId: groupId, userId: currentUserId},
            type: 'post',
        });
    }
};
<!-- ./ 信息模块 -->

<!-- 全局窗口层次定义 -->

<!--    弹出层模块 -->
var EjectModule = {
    // 查找用户，弹出用户信息窗口
    searchUser: function () {
        var userId = $(" input[ id='userId']").val();
        if (userId !== null) {
            SideBarModule.Profile.loadById(userId, 1);
            $('#sendAddFriendRequestButton').click(function () {
                EjectModule.addFriendMessage(userId);
            });
        }
    },

    // 添加好友请求消息的发送弹出窗口
    addFriendMessage: function (userId) {
        var content = $(" textarea[ id='requestContent']").val();
        Message.send(userId, content, 2);
    },

    // 编辑用户信息窗口
    editProfile: function () {
        var userDetail = UserManagement.getDetailByUserId(currentUserId);
        var html = `<div class="tab-content">
                    <div class="tab-pane show active" id="personal" role="tabpanel">
                        <form>
                            <div class="form-group">
                                <label for="fullname" class="col-form-label">UserName</label>
                                <div class="input-group">
                                    <input type="text" readonly class="form-control"  value="` + UserManagement.getUserNameByUserId(currentUserId) + `">
                                    <div class="input-group-append">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="introduction" class="col-form-label">Introduction</label>
                                <div class="input-group">
                                    <input type="text" class="form-control" id="introduction" value="` + userDetail.userIntro + `">
                                    <div class="input-group-append">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="address" class="col-form-label">Address</label>
                                <div class="input-group">
                                    <input type="text" class="form-control" id="address" value="` + userDetail.userAddr + `">
                                    <div class="input-group-append">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="email" class="col-form-label">Email</label>
                                <div class="input-group">
                                    <input type="text" class="form-control" id="email" value="` + userDetail.userEmail + `">
                                    <div class="input-group-append">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="gender" class="col-form-label">Gender</label>
                                <div class="input-group">
                                      <select class="form-control" id="gender">
                                           <option>Male</option>
                                           <option>Female</option>
                                      </select>
                                    <div class="input-group-append">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>`;
        $('#profileEditBody').html(html);
    },

    // 获取用户信息并交由后台保存
    saveProfile: function () {
        let jsonUserDetail = {
            userId: currentUserId,
            userIntro: $(" input[ id='introduction' ] ").val(),
            userAddr: $(" input[ id='address' ] ").val(),
            userEmail: $(" input[ id='email' ] ").val(),
            userGender: $(" select[ id='gender']").val()
        };
        console.log("1:   " + jsonUserDetail);
        jsonUserDetail = JSON.stringify(jsonUserDetail);
        console.log(jsonUserDetail);
        UserManagement.saveUserDetail(jsonUserDetail);
    },

    // 填写群组信息创建群组
    createGroup() {
        let jsonGroup = {
            creatorId: currentUserId,
            groupIntro: $(" textarea[ id='groupIntro' ] ").val(),
            groupName: $(" input[ id='groupName' ] ").val(),
        };
        GroupManagement.createGroup(JSON.stringify(jsonGroup));
        SideBarModule.Groups.load();
    },

    // 查找群组并选择是否添加
    searchGroup() {
        var groupId = $(" input[ id='groupId']").val();
        if (groupId !== null) {
            SideBarModule.Profile.loadByGroupId(groupId);
            $('#joinGroupButton').click(function () {
                GroupManagement.joinGroup(groupId);
                SideBarModule.Groups.load();
            });
        }
    }
};
<!--    ./ 弹出层模块 -->

<!--    侧边列表模块 -->
var SideBarModule = {
    // 好友列表侧边栏
    Friends: {
        // 载入好友列表视图
        load: function () {
            var allFriends = SideBarModule.Friends.get();
            var listDiv = document.getElementById("friendList");
            listDiv.innerHTML = '';
            var html = '';
            for (var i = 0; i < allFriends.length; i++) {
                html += `<li class="list-group-item" id="friendId` + allFriends[i].userId + `" onclick=ChatModule.Chat.withUser(` + allFriends[i].userId + `)>
                            <div>
                                <figure class="avatar" id="avatarId` + allFriends[i].userId + `">
                                    <img src="./dist/media/img/man_avatar1.jpg" class="rounded-circle" alt="image">
                                </figure>
                            </div>
                            <div class="users-list-body">
                                <div>
                                    <h5>` + allFriends[i].userName + `</h5>
                                    <p></p>
                                </div>
                            </div>
                        </li>`;
            }
            console.log("Friends loads");
            listDiv.innerHTML = html;
        },

        // 从后台系统获取好友列表
        get: function () {
            var allFriends = null;
            $.ajax({
                async: false, //设置同步
                type: 'POST',
                url: '/getFriends',
                success: function (friends) {
                    if (friends != null) {
                        allFriends = friends.friendList;
                    } else {
                        layer.alert('查询错误1');
                    }
                },
                error: function (friends) {
                    layer.alert('查询错误2');
                }
            });
            //划重点划重点，这里的eval方法不同于prase方法，外面加括号
            allFriends = eval("(" + allFriends + ")");
            return allFriends;
        },

        // 好友消息通知（在好友头像旁添加红点）
        notice: function (userId) {
            $('#avatarId' + userId).addClass('avatar-state-danger');
        },

        // 好友添加请求通知（好友列表视图添加一行好友添加信息）
        friendAddNotice: function (message) {
            $('#friendList').append(`<li class="list-group-item" >
                            <div id="fI` + message.id + `" class="users-list-body" data-toggle="modal" data-target="#addFriendInvitationAcceptor">
                                <div>
                                    <span class="badge badge-danger"></span>
                                    <h5>New friend invitation</h5>
                                    <p>from <b>` + UserManagement.getUserNameByUserId(message.from) + `</b></p>
                                </div>
                            </div>
                        </li>`);
            $('#fI' + message.id).click(function () {
                $('#invitationBody').html(`<div class="mt-4 mb-4">
                                    <h6>` + UserManagement.getUserNameByUserId(message.from) + `</h6>
                                    <hr>
                                    <p class="text-muted">` + message.content + `</p>`);
                $('#agreeAddingInvitaion').click(function () {
                    console.log("friendId from : " + message.from);
                    UserManagement.addFriend(message.from);
                    Message.setReceived(JSON.stringify(message));
                    SideBarModule.Friends.load();
                });
                $('#rejectAddingInvitaion').click(function () {
                    Message.setReceived(JSON.stringify(message));
                    SideBarModule.Friends.load();
                });

            });
        },
    },

    // 个人信息侧边栏
    Profile: {
        // 通过个人编号载入个人信息
        loadById: function (userId, type) {
            var userDetail = UserManagement.getDetailByUserId(userId);
            var html;
            if (userDetail == null) {
                html = `<h3>User is not exist, try to search another Id</h3>`;
            } else
                html = `<div class="pl-4 pr-4">
                        <div class="text-center">
                            <h5 class="mb-1">` + UserManagement.getUserNameByUserId(userId) + `</h5>
                            <p class="text-muted">Gender: ` + (userDetail.userGender == null ? 'null' : userDetail.userGender) + `</p>
                        </div>
                        <div class="tab-content" id="myTabContent">
                            <div class="tab-pane fade active show" id="home" role="tabpanel" aria-labelledby="home-tab">
                                <div class="mt-4 mb-4">
                                    <h6>Introduction</h6>
                                    <p class="text-muted">` + (userDetail.userIntro == null ? 'null' : userDetail.userIntro) + `</p>
                                </div>
                                <div class="mt-4 mb-4">
                                    <h6>Address</h6>
                                    <p class="text-muted">` + (userDetail.userAddr == null ? 'null' : userDetail.userAddr) + `</p>
                                </div>
                                <div class="mt-4 mb-4">
                                    <h6>Email</h6>
                                    <p class="text-muted">` + (userDetail.userEmail == null ? ' ' : userDetail.userEmail) + `</p>
                                </div>
                            </div>
                        </div>
                    </div>`;
            $('#profileBody').html(html);
            if (type == 1)
                $('#profileBody').append('<div class="text-center">' +
                    '<a class="btn btn-primary" id="addFriendButton"  href="#" data-toggle="modal" data-target="#addFriendMessage">add friend</a>' +
                    '</div>');
        },

        // 通过群组编号载入群组信息
        loadByGroupId(groupId) {
            let group = GroupManagement.getDetailByGroupId(groupId)
            let html;
            if (group == null) {
                html = `<h3>Group is not exist, try to search by another Id</h3>`;
            } else
                html = `<div class="pl-4 pr-4">
                        <div class="text-center">
                            <h5 class="mb-1">` + group.groupName + `</h5>
                            <p class="text-muted">group id: ` + (groupId) + `</p>
                        </div>
                        <div class="tab-content" id="myTabContent">
                            <div class="tab-pane fade active show" id="home" role="tabpanel" aria-labelledby="home-tab">
                                <div class="mt-4 mb-4">
                                    <h6>Members Count<p class="text-muted">` + (group.membersCount) + `</p></h6>
                                </div>
                                <div class="mt-4 mb-4">
                                    <h6>Creator<p class="text-muted">` + (UserManagement.getUserNameByUserId(group.creatorId)) + `</p></h6>
                                </div>
                                <div class="mt-4 mb-4">
                                    <h6>Created Time<p class="text-muted">` + (group.createdTime) + `</p></h6>                                    
                                </div>
                                <div class="mt-4 mb-4">
                                    <h6>Introduction</h6>
                                    <p class="text-muted">` + (group.groupIntro) + `</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="text-center">
                        <a class="btn btn-primary" id="joinGroupButton" href="#"">join Group</a>
                    </div>`;
            $('#profileBody').html(html);
        }
    },

    // 群组列表侧边栏
    Groups: {
        // 载入群组列表视图
        load: function () {
            var allGroups = SideBarModule.Groups.get();
            var listDiv = document.getElementById("groupList");
            listDiv.innerHTML = '';
            var html = '';
            for (var i = 0; i < allGroups.length; i++) {
                html += `<li class="list-group-item" id="groupId` + allGroups[i].groupId + `" onclick=ChatModule.Chat.withGroup(` + allGroups[i].groupId + `)>
                            <div>
                                <figure class="avatar" id="groupAvatarId` + allGroups[i].groupId + `">
                                    <i data-feather="users"></i>
                                </figure>
                            </div>
                            <div class="users-list-body">
                                <div>
                                    <h5>` + allGroups[i].groupName + `</h5>
                                    <p></p>
                                </div>
                            </div>
                        </li>`;
            }
            listDiv.innerHTML = html;
        },

        // 从后台系统获取群组列表
        get: function () {
            var allGroups = null;
            $.ajax({
                async: false, //设置同步
                type: 'POST',
                url: '/getGroupsByUserId',
                success: function (groups) {
                    if (groups != null) {
                        allGroups = groups.groupList;
                    } else {
                        layer.alert('查询错误1');
                    }
                },
                error: function (groups) {
                    layer.alert('查询错误2');
                }
            });
            //划重点划重点，这里的eval方法不同于prase方法，外面加括号
            allGroups = eval("(" + allGroups + ")");
            return allGroups;
        },

        // 群组消息通知（在群组标志旁添加红点）
        notice: function (groupId) {
            $('#groupAvatarId' + groupId).addClass('avatar-state-danger');
        },
    },
};
<!--    ./ 侧边列表模块 -->

<!--    聊天模块 -->
var ChatModule = {
    // 聊天窗口的操作
    Window: {
        // 打开聊天窗口加载内容
        open: function (to, type) {
            var chatHeaderUser = document.getElementById("chatHeaderUser");
            $("#chatHeaderAction .dropdown-menu .dropdown-menu-right a").attr('id', type + ':' + to);
            var chatBody = document.getElementById("chatBody");
            chatBody.innerHTML = '';
            if (type == 0) {
                let toName = UserManagement.getUserNameByUserId(to);
                chatHeaderUser.innerHTML = '                    <figure class="avatar">\n' +
                    '                        <img src="./dist/media/img/man_avatar1.jpg" class="rounded-circle" alt="image">\n' +
                    '                    </figure>\n' +
                    '                    <div>\n' +
                    '                        <h5>' + toName + '</h5>\n' +
                    '                        <small class="text-success">\n' +
                    '                            <i></i>\n' +
                    '                        </small>\n' +
                    '                    </div>\n';
            } else if (type == 1) {
                let group = GroupManagement.getDetailByGroupId(to);
                let toName = group.groupName;
                chatHeaderUser.innerHTML = `                    <figure class="avatar">
                                            <img alt="image" src="./dist/icons/users.svg">
                                        </figure>
                                        <div>
                                            <h5>` + toName + `(` + group.membersCount + `)</h5>
                                        </div>`
            }
        },

        // 用于动态调整聊天窗口底下消息输入栏
        messageForm: function (to, type) {
            $('#chatFooter form .btn-primary').attr('id', to);
            $('#chatFooter input').attr('id', type)
            $('#chatFooter form').on('submit', function (e) {
                e.preventDefault();
                var to = $(this).find('button[type=submit]').attr('id');
                var type = $(this).find('input[type=text]').attr('id');
                var input = $(this).find('input[type=text]');
                var message = input.val();

                message = $.trim(message);
                if (message) {
                    console.log('Sending to: ' + to);
                    Message.send(to, message, type);
                    input.val('');
                } else {
                    input.focus();
                }
                return false;
            });
        },

        // 用于收到聊天消息时将窗口滚动到底部
        autoScroll: function () {
            var chat_body = $(".layout .content .chat .chat-body");
            setTimeout(function () {
                chat_body.scrollTop(chat_body.get(0).scrollHeight, -1).niceScroll({
                    cursorcolor: 'rgba(66, 66, 66, 0.20)',
                    cursorwidth: "4px",
                    cursorborder: '0px'
                }).resize();
            }, 200);
        }
    },

    // 聊天相关的操作
    Chat: {
        // 选择好友后，加载的与好友聊天方法
        withUser: function (to) {
            currentChatUser = to;
            currentChatGroup = null;
            let toName = UserManagement.getUserNameByUserId(to);
            ChatModule.Window.messageForm(to, 0);
            ChatModule.Window.open(to, 0);
            $('.list-group-item').attr('class', 'list-group-item');
            $('#avatarId' + to).attr('class', 'avatar');
            $('#friendId' + to).addClass('open-chat');
            $('.sidebar-group').attr('class', 'sidebar-group');
            var messageRecord = Message.getRecord(to, 0);
            ChatModule.Window.autoScroll();
            for (let i = 0; i < messageRecord.length; i++) {
                const jsonMessage = {
                    id: messageRecord[i].id,
                    from: messageRecord[i].from,
                    to: messageRecord[i].to,
                    content: messageRecord[i].content,
                    type: messageRecord[i].type,
                    time: messageRecord[i].time,
                    isReceived: messageRecord[i].isReceived
                };
                Message.show(jsonMessage, (jsonMessage.from==currentUserId ? currentUserName : toName));
                if (messageRecord[i].isReceived === 0 && messageRecord[i].to == currentUserId) {
                    Message.setReceived(JSON.stringify(messageRecord[i]));
                }
            }
        },

        // 选择群组后，加载的与群组聊天方法
        withGroup: function (to) {
            currentChatUser = null;
            currentChatGroup = to;
            ChatModule.Window.messageForm(to, 1);
            // ChatModule.Window.open(to);
            $('.list-group-item').attr('class', 'list-group-item');
            $('#groupAvatarId' + to).attr('class', 'avatar');
            $('#groupId' + to).addClass('open-chat');
            $('.sidebar-group').attr('class', 'sidebar-group');
            ChatModule.Window.open(to, 1);
            var messageRecord = Message.getRecord(to, 1);
            for (let i = 0; i < messageRecord.length; i++) {
                const jsonMessage = {
                    id: messageRecord[i].id,
                    from: messageRecord[i].from,
                    to: messageRecord[i].to,
                    content: messageRecord[i].content,
                    type: messageRecord[i].type,
                    time: messageRecord[i].time,
                    isReceived: messageRecord[i].isReceived
                };
                Message.show(jsonMessage);
            }
            Message.cleanGroupReceived(to);
        },
    }
};
<!--    ./ 聊天模块 -->

<!-- ./ 全局窗口层次定义 -->
