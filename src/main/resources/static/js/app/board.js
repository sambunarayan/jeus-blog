$(document).ready(function(){
    list();
    function list() {
        var regForm = $("#hidden_form");
        var tableBody = $("#tbody");
        showList(regForm.find('#hidden_board_name').val(), 1);
    }
    function showList(boardName, page) {
        $.ajax({
            type: 'GET',
            url: '/it/board/' + boardName +'/page/'+page,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function(json) {
            var tableBody = $("#tbody");
            var body = "";
            json.posts.forEach(function(val, idx) {
                body += "<tr>";
                body += "<td>"+ val.id +"</td>";
                body += "<td><a id='"+ val.id +"' href='/it/board/list/" + val.boardName + "?bno="+ val.id +"'>"+ val.title +"</a></td>";
                body += "<td>"+ val.author +"</td>";
                body += "<td>"+ val.createdDate +"</td>";
                body += "</tr>";
            });
            tableBody.html(body);
            showPageList(json.totalPostNum);
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

    function showPageList(totalNum) {
        alert("totalNum = " + totalNum);
        var currPageNum = 1;
        var endNum = Math.ceil(totalNum / 10.0) * 10;
        if (endNum * 10 >= totalNum) {
            endNum = Math.ceil(totalNum / 10.0);
        }
        if (endNum * 10 < totalNum) {
            next = true;
        }
        var startNum = endNum - 9;
        if (startNum < 1) {
            startNum = 1;
        }
        var prev = startNum != 1;
        var next = false;

        alert(startNum + " -> " + endNum);
        var page = "<ul class='pagination pull-right'>";
        if (prev) {
            page += "<li class='page-item'><a class='page-link' href='" + (startNum - 1) + "'>Previous</a></li>";
        }
        for (var i = startNum; i <= endNum; i++) {
            var active = currPageNum == i? "active":"";
            page+="<li class='page-item " + active +" '><a class='page-link' href='" + i + "'>" + i + "</a></li>";
        }
        if (next) {
            page+="<li class='page-item'><a class='page-link' href='" + (endNum + 1) + "'>Next</a></li>";
        }
        page += "</ul></div>";
        $("#pageDiv").html(page);
    }
});