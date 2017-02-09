        var stompClient = null;
        var token = null;

        function showRegistrOrLogin(showReg) {
            document.getElementById('name').style.visibility= showReg ? 'visible' : 'hidden';
            document.getElementById('sendRegistr').disabled = !showReg;
            document.getElementById('sendLogin').disabled = showReg;
            document.getElementById('menu').style.visibility = 'hidden';
            document.getElementById('chat').style.visibility = 'hidden';
            document.getElementById('loginForm').style.visibility ='visible';
            document.getElementById('chats').style.visibility= 'hidden';
        }

        function showRegistr() {showRegistrOrLogin(true);}

        function showLogin() {showRegistrOrLogin(false);}

        function registration() {
            var xhr = new XMLHttpRequest();
            xhr.open('POST', 'http://localhost:8081/newuser', true);
            xhr.setRequestHeader('Accept', 'application/json');
            xhr.setRequestHeader('Content-Type', 'application/json');
            var login = document.getElementById('login').value;
            var password = document.getElementById('password').value;
            var name = document.getElementById('name').value;
            xhr.send(JSON.stringify({'login':login, 'password':password, 'name':name}));
            xhr.onreadystatechange=function() {
            	if (xhr.status === 201) {
            		showLogin();
            	} else {
            		showRegistr();
            	}
                $("#login").val('');
                $("#password").val('');
                $("#name").val('');
	        }
        }

        function login() {
            var xhr = new XMLHttpRequest();
            xhr.open('POST', 'http://localhost:8081/login', true);
            var login = document.getElementById('login').value;
            var password = document.getElementById('password').value;
            xhr.setRequestHeader('login', login);
            xhr.setRequestHeader('password', password);
            xhr.send();
 			xhr.onreadystatechange=function() {
 				if (xhr.status == 200) {
            		token = xhr.getResponseHeader('Auth-Token');
                    showMenu(true);
                    $("#login").val('');
                    $("#password").val('');
            	} else {
            		showLogin();
            	}
            }
        }

        function showMenu(showMenu) {
        	document.getElementById('menu').style.visibility= showMenu ? 'visible' : 'hidden';
        	document.getElementById('loginForm').style.visibility = !showMenu ? 'visible' : 'hidden';
        }

        function start() {
        	showMenu(false);
        	showLogin();
            if(stompClient != null) {
                stompClient.disconnect();
            }
        }

        function logout() {
        	var xhr = new XMLHttpRequest();
            xhr.open('GET', 'http://localhost:8081/signout', true);
            xhr.setRequestHeader('Auth-Token', token);
            xhr.send();
            showLogin();
            if(stompClient != null) {
                stompClient.disconnect();
            }
        }

        function showChats() {
            document.getElementById('chatName').style.visibility= 'hidden';
            document.getElementById('addChat').style.visibility= 'hidden';
            document.getElementById('showChats').style.visibility= 'hidden';
            document.getElementById('chats').style.visibility= 'visible';
            $.ajax({
                url:'http://localhost:8081/chats',
                type: 'GET',
                headers: {'Auth-Token': token},
                dataType: 'json',
                success: function(data) {
                    for(var i = 0; i < data.length; i++){
                        $("#chats").append("<button onclick=\"enterToChat("+data[i].id+")\">"+data[i].name+"</button>");
                    }
                }
            });
        }

        function enterToChat(chatId) {
            $.ajax({
                url: 'http://localhost:8081/chats/' + chatId + '/members',
                type: 'POST',
                headers: {'Auth-Token': token},
                success: function() {
                    chatOn(chatId);
                }
            });
        }

        function addChat() {
            $.ajax({
                url: 'http://localhost:8081/chats',
                type: 'POST',
                headers: {'Auth-Token': token},
                data: $("#chatName").val(),
                success: function(data) {
                    chatOn(data);
                }
            });
            $("#chatName").val('');
        }

        function chatOn(chatId) {
            $("#chat").attr("style","visibility: visible;");
            $("#menu").attr("style","visibility: hidden;");
            connect(chatId);
            $.ajax({
                url: 'http://localhost:8081/chats/'+chatId+'/messages',
                type: 'GET',
                headers: {'Auth-Token': token},
                data: {get:"new"},
                dataType: 'json',
                success: function(data) {
                    $("#chatArea").empty();
                    for(var i = 0; i < data.length; i++){
                        $("#chatArea").append(data[i].userName+": "+data[i].text+"\n");                    }
                }
            });
            $("#sendMessage").click(function() {
                $.ajax({
                    url: 'http://localhost:8081/chats/' + chatId + '/messages',
                    type: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json',
                        'Auth-Token': token
                    },
                    data: JSON.stringify({text:$("#text").val()}),
                    dataType: 'json'
                });
                $("#text").val('');
            });
            $("#lastMessages").click(function () {
                $.ajax({
                    url: 'http://localhost:8081/chats/'+chatId+'/messages',
                    type: 'GET',
                    headers: {'Auth-Token': token},
                    data: {get:"last"},
                    dataType: 'json',
                    success: function(data) {
                        $("#chatArea").empty();
                        for(var i = 0; i < data.length; i++){
                            $("#chatArea").append(data[i].userName+": "+data[i].text+"\n");                    }
                    }
                });
            });
            $("#allMessages").click(function () {
                $.ajax({
                    url: 'http://localhost:8081/chats/'+chatId+'/messages',
                    type: 'GET',
                    headers: {'Auth-Token': token},
                    data: {get:"all"},
                    dataType: 'json',
                    success: function(data) {
                        $("#chatArea").empty();
                        for(var i = 0; i < data.length; i++){
                            $("#chatArea").append(data[i].userName+": "+data[i].text+"\n");                    }
                    }
                });
            });
        }

        function connect(chatId) {
            var socket = new SockJS('http://localhost:8081/chat');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function(frame) {
                console.log('Connected: ' + frame);
                stompClient.subscribe("/topic/"+chatId+"/messages", function(messageOutput) {
                    showMessageOutput(JSON.parse(messageOutput.body));
                });
            });
        }

        function showMessageOutput(messageOutput) {
           $("#chatArea").append(messageOutput.userName+": "+messageOutput.text+"\n");
           var svuk = $("#messageNotify")[0];
           svuk.play();
        }

