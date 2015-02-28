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

function createXHR() {

    // Checks whether support XMLHttpRequest or not.
    if (typeof XMLHttpRequest != "undefined") {
        return new XMLHttpRequest();
    }

    // IE6 and elder version.
    else if (typeof ActiveXObject != "undefined") {
        if (typeof arguments.callee.activeXString != "string") {
            var versions = [
        "MSXML2.XMLHttp6.0",
        "MSXML2.XMLHttp3.0",
        "MSXML2.XMLHttp"];

            for (var i = 0; i < versions.length; i++) {
                try {
                    var xhr = new ActiveXObject(versions[i]);
                    arguments.callee.activeXString = versions[i];
                    return xhr;
                }
                catch (ex) {
                    throw new Error(ex.toString());
                }
            }
            return new ActiveXObject(arguments.callee.activeXString);
        }
        else {
            throw new Error("No XHR object available");
        }

    }
    return null;
}

    function searchPolicy()
    {
        var searchTable = document.getElementById("search-tab");
        var t = document.getElementById("selectController");
        var topic = t.options[t.selectedIndex].value;
        var key = searchTable.rows[0].cells[3].getElementsByTagName("input")[0].value;
        var url = "http://"+document.location.host+"/turtle/policy";
        $.ajax({
            type     : "get",
            cache    : false,
            async    : false,
            dataType : "json",
            url      : url + "?topic=" + topic + "&key=" + key,
            success  : function(res){
                var dataItems = res.policyList;
                var tableOld = document.getElementById("result-tab");
                tableOld.firstChild.removeNode(true);
                for(var i=0; i<dataItems.length; i++)
                {
                    var item = dataItems[i];
                    var table = document.getElementById("result-tab");
                    var tabRow = table.insertRow(i+1);
                    var checkCell = tabRow.insertCell(0);
                    checkCell.innerHTML = "<input class='tc' name='id[]' value='' type='checkbox'>";
                    //checkCell.value = item.id;
                    //var idCell = tabRow.insertCell(1);
                    //idCell.value = item.id;
                    var titleCell = tabRow.insertCell(1);
                    titleCell.innerHTML = "<a onclick='updatePolicy()' >"+item.policyName+"</a>";
                    var contentCell = tabRow.insertCell(2);
                    contentCell.innerHTML = "<a onclick='updatePolicy()' >"+item.content+"</a>";
                    var operationCell = tabRow.insertCell(3);
                    operationCell.innerHTML = "<a class='link-update' onclick='updatePolicy()' width='%5'>修改&nbsp</a>" + "<a class='link-del' onclick='deletePolicy()'>删除</a>";
                }
            }
        });
    }

    function updatePolicy()
    {

    }

    function createPolicy()
    {

    }