// <script>
//
<!-- 用户信息请求模块 -->
var UserManagement = {
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
                    alert('查询名字错误1');
                }
            },
            error: function () {
                alert('查询名字错误2');
            }
        });
        return userName;
    },
    getCurrentUser: function () {
        let currentUser = null;
        $.ajax({
            type: 'POST',
            url: '/getCurrentUser',
            success: function (user) {
                if (user) {
                    currentUser = user;
                } else
                    alert('查询错误1');
            },
            error: function () {
                alert('查询错误2');
            }
        });
        return currentUser;
    },
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
                    alert('查询错误1');
            },
            error: function (userId) {
                alert('查询错误2');
            }
        });
        return currentUserId;
    },
    detail: {
        getByUserId: function (userId) {
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
                    alert('request fail');
                }
            });
            return userDetail;
        },
    },
    addFriend: function (friendId) {
        $.ajax({
            async: false,
            type: 'POST',
            url: '/addFriend',
            data: {ida: currentUserId, idb: friendId},
            dataType: 'text',
            success: function (isSent) {
                if (isSent != null)
                    alert("Friend adding success.");
                else
                    alert("fail1");
            },
            error: function (isSent) {
                alert("fail2");
            }
        });
    },
    saveUserDetail(userDetail){
        $.ajax({
            async: false,
            url: '/saveUserDetail',
            data: {jsonUserDetail: userDetail},
            type: 'post',
            dataType: 'text',
            success: function (result) {
                if (result != null)
                    alert('Save information success!');
                else alert('!!');
            },
            error: function (result) {
                alert("!!!1")
            }
        });
    }
};
<!-- ./ 用户信息请求模块 -->
<!-- 群组信息请求模块 -->
var GroupManagement = {
    getDetailByGroupId(groupId){
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
                alert('request fail');
            }
        });
        console.log(groupDetail)
        return groupDetail;
    },
    createGroup(group){
        $.ajax({
            async: false,
            url: '/saveGroup',
            data: {jsonGroup: group},
            type: 'post',
            dataType: 'json',
            success: function (newgroup) {
                if (newgroup != null)
                    alert('Create Group Success!/nYour group Id is ['+newgroup.groupId+']');
                else alert('!!');
            },
            error: function (result) {
                alert("!!!1")
            }
        });
    },
    joinGroup(groupId){
        $.ajax({
            async: false,
            type: 'POST',
            url: '/joinGroup',
            data: {userId: currentUserId, groupId: groupId},
            dataType: 'text',
            success: function (isSent) {
                if (isSent != null)
                    layer.msg('Group joining success.',{icon:2});
                else
                    alert("fail1");
            },
            error: function (isSent) {
                alert("fail2");
            }
        });
    }
};
<!-- ./ 群组信息请求模块 -->

<!-- 全局变量与事件触发 -->
<!--    全局变量 -->
var currentUserId = UserManagement.getCurrentUserId();
var currentChatUser = null;
var currentChatGroup = null;
<!--   ./ 全局变量 -->
<!--    事件触发 -->
window.onload = function () {
    SideBarModule.Friends.load();
    SideBarModule.Groups.load();
};
$(document).on('submit', '#chatFooter form', function (e) {
    e.preventDefault();
});
$('#editCurrentUserDetail').click(function () {
    EjectModule.editProfile();
});
$('#saveUserDetail').click(function () {
    EjectModule.saveProfile();
});
$('#loadCurrentUserDetail').click(function () {
    SideBarModule.Profile.loadById(currentUserId);
});
$('#searchUserButton').click(function () {
    EjectModule.searchUser();
});
$('#createGroupButton').click(function () {
    EjectModule.createGroup();
});
$('#searchGroupButton').click(function () {
    EjectModule.searchGroup();
});
<!--   ./ 事件触发 -->
<!-- ./ 全局变量与事件触发 -->

<!-- 信息模块 -->
var Message = {
    send: function (to, content, type) {
        websocket.send(JSON.stringify({
            from: currentUserId,
            to: to,
            content: content,
            type: type,
        }))
    },
    show: function (record) {
        fromName = UserManagement.getUserNameByUserId(record.from)
        $('.layout .content .chat .chat-body .messages').append(
            '<div class="message-item ' + (record.from == currentUserId ? 'outgoing-message' : '') + '">\n' +
            '                        <div class="message-avatar">\n' +
            '                            <figure class="avatar">\n' +
            '                                <img src="./dist/media/img/man_avatar1.jpg" class="rounded-circle" alt="image">\n' +
            '                            </figure>\n' +
            '                            <div>\n' +
            '                                <h5>' + fromName + '</h5>\n' +
            '                                <div class="time">' + record.time + '</div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                        <div class="message-content">\n' +
            record.content +
            '                        </div>\n' +
            '                    </div>');

        ChatModule.Window.autoScroll();
    },
    Notice: {
        show: function (record) {
            alert(record);
        }
    },
    setReceived : function (message) {
        $.ajax({
            async: false,
            url: '/setReceived',
            type: 'post',
            data: {jsonMessage: message},
            dataType: 'json',
        });
    },
    getRecord: function (to,type) {
        var messageList = null;
        $.ajax({
            async: false, //设置同步
            type: 'POST',
            url: (type == 0 ? '/getMessageRecord' : '/getGroupMessageRecord'),
            data: {ida: currentUserId, idb: to},
            dataType: 'json',
            success: function (record) {
                if (record != null) {
                    messageList = record.record;
                } else {
                    alert('查询错误1');
                }
            },
            error: function (record) {
                alert('查询错误2');
            }
        });
        //eval方法不同于prase方法，外面加括号
        messageList = eval("(" + messageList + ")");
        return messageList;
    },
};
<!-- ./ 信息模块 -->

<!-- 全局窗口层次定义 -->

<!--    弹出层模块 -->
var EjectModule = {
    searchUser: function () {
        var userId = $(" input[ id='userId']").val();
        if (userId !== null) {
            SideBarModule.Profile.loadById(userId, 1);
            $('#sendAddFriendRequestButton').click(function () {
                EjectModule.addFriendMessage(userId);
            });
        }
    },
    addFriendMessage: function (userId) {
        var content = $(" textarea[ id='requestContent']").val();
        Message.send(userId, content, 2);
    },
    editProfile: function () {
        var userDetail = UserManagement.detail.getByUserId(currentUserId);
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
    createGroup(){
        let jsonGroup = {
            creatorId: currentUserId,
            groupIntro: $(" input[ id='groupIntro' ] ").val(),
            groupName: $(" input[ id='groupName' ] ").val(),
        };
        GroupManagement.createGroup(jsonGroup);
    },
    searchGroup(){
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
    Friends: {
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
                        alert('查询错误1');
                    }
                },
                error: function (friends) {
                    alert('查询错误2');
                }
            });
            //划重点划重点，这里的eval方法不同于prase方法，外面加括号
            allFriends = eval("(" + allFriends + ")");
            return allFriends;
        },
        notice: function (userId) {
            $('#avatarId' + userId).addClass('avatar-state-danger');
        },
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
                                    <h6>`+ UserManagement.getUserNameByUserId(message.from) +`</h6>
                                    <hr>
                                    <p class="text-muted">` + message.content + `</p>`);
                message = JSON.stringify(message);
                $('#agreeAddingInvitaion').click(function () {
                    UserManagement.addFriend(message.from);
                    Message.setReceived(message);
                    SideBarModule.Friends.load();
                });
                $('#rejectAddingInvitaion').click(function () {
                    Message.setReceived(message);
                    SideBarModule.Friends.load();
                });

            });
        },
    },
    Profile: {
        loadById: function (userId, type) {
            var userDetail = UserManagement.detail.getByUserId(userId);
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
        loadByGroupId(groupId){
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
    Groups: {
        load : function () {
            var allGroups = SideBarModule.Groups.get();
            var listDiv = document.getElementById("groupList");
            listDiv.innerHTML = '';
            var html = '';
            for (var i = 0; i < allGroups.length; i++) {
                html += `<li class="list-group-item" id="groupId` + allGroups[i].groupId + `" onclick=ChatModule.Chat.withGroup(` + allGroups[i].groupId + `)>
                            <div>
                                <figure class="avatar" id="groupAvatarId` + allGroups[i].userId + `">
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
        get : function () {
            var allGroups = null;
            $.ajax({
                async: false, //设置同步
                type: 'POST',
                url: '/getGroupsByUserId',
                success: function (groups) {
                    if (groups != null) {
                        allGroups = groups.groupList;
                    } else {
                        alert('查询错误1');
                    }
                },
                error: function (groups) {
                    alert('查询错误2');
                }
            });
            //划重点划重点，这里的eval方法不同于prase方法，外面加括号
            allGroups = eval("(" + allGroups + ")");
            return allGroups;
        },
        notice: function (groupId) {
            $('#groupAvatarId' + groupId).addClass('avatar-state-danger');
        },
    },
};
<!--    ./ 侧边列表模块 -->

<!--    聊天模块 -->
var ChatModule = {
    Window: {
        open: function (to, type) {
            var chatHeaderUser = document.getElementById("chatHeaderUser");
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
            }
            else if (type == 1) {
                let group = GroupManagement.getDetailByGroupId(to);
                let toName = group.groupName;
                chatHeaderUser.innerHTML = `                    <figure class="avatar">
                                            <h6>Group chat</h6>
                                        </figure>
                                        <div>
                                            <h5>` + toName + `</h5><i>(`+ group.membersCount +`)</i>
                                        </div>`
            }
        },
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
    Chat: {
        withUser: function (to) {
            currentChatUser = to;
            currentChatGroup = null;
            ChatModule.Window.messageForm(to, 0);
            ChatModule.Window.open(to, 0);
            $('.list-group-item').attr('class', 'list-group-item');
            $('#avatarId' + to).attr('class', 'avatar');
            $('#friendId' + to).addClass('open-chat');
            $('.sidebar-group').attr('class', 'sidebar-group');
            var messageRecord = Message.getRecord(to, 0);
            ChatModule.Window.autoScroll();
            for (var i = 0; i < messageRecord.length; i++) {
                var jsonMessage = {
                    id:messageRecord[i].id,
                    from: messageRecord[i].from,
                    to: messageRecord[i].to,
                    content: messageRecord[i].content,
                    type: messageRecord[i].type,
                    time: messageRecord[i].time,
                    isReceived : messageRecord[i].isReceived
                };
                Message.show(jsonMessage);
                if (messageRecord[i].isReceived === 0 && messageRecord[i].to == currentUserId) {
                    Message.setReceived(JSON.stringify(messageRecord[i]));
                }
            }
        },
        withGroup: function(to) {
            currentChatUser = null;
            currentChatGroup = to;
            ChatModule.Window.messageForm(to, 1);
            // ChatModule.Window.open(to);
            $('.list-group-item').attr('class', 'list-group-item');
            $('#groupAvatarId' + to).attr('class', 'avatar');
            $('#groupId' + to).addClass('open-chat');
            $('.sidebar-group').attr('class', 'sidebar-group');
            ChatModule.Window.open(to, 1);
        },
    }
};
<!--    ./ 聊天模块 -->

<!-- ./ 全局窗口层次定义 -->
