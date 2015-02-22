    function login(){
		
			var name = document.getElementById("user").value;
			var passwd = hex_md5(document.getElementById("pwd").value);
			var url = "http://"+document.location.host+"/turtle/admin";
			$.ajax({
                type     : "post",
                cache    : false,
                async    : false,
                datatype : "json",
                data     : {"account": name,
                            "passwd":passwd},
                url      : url,
                success  : function(res){
                    //$.cookie("SID", null, {'path':'/'});
                    //$.cookie("account", null, {'path':'/'});
                    if(res){
                        //window.location.href = "index.html?timestamp";
                        document.forms[0].action = "index.html";
                        document.forms[0].submit();
                    }else{
                    	document.forms[0].action = "login.html";
                        document.forms[0].submit();
                    }
                }
            });
		
	}