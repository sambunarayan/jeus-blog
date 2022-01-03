$(document).ready(function(){
    list();
    function list() {
        var regForm = $("#hidden_form");
        showPostList(regForm.find('#hidden_board_name').val(), regForm.find('#hidden_current_page').val());
    }
    /**
     * Show post list
     */
    function showPostList(boardName, page) {
        $.ajax({
            type: 'GET',
            url: '/app/v1/it/board/' + boardName +'/page/'+page,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function(json) {
            var tableBody = $("#tbody");
            var body = "";
            var no = json.totalPostNum - ((page - 1) * 10);
            json.posts.forEach(function(val, idx) {
                body += "<tr>";
                body += "<td>"+ no-- +"</td>";
                body += "<td><a id='"+ val.id +"' href='/app/v1/it/board/list/" + val.boardName
                        + "?bno="+ val.id +"&page=" + page + "'>"+ val.title +"</a></td>";
                body += "<td>"+ val.author +"</td>";
                body += "<td>"+ val.createdDate +"</td>";
                body += "</tr>";
            });
            tableBody.html(body);
            showPageList(boardName, json.totalPostNum, page);
        }).fail(function (error) {
            alert("error ->" + JSON.stringify(error));
        });
    }

    function showPageList(boardName, totalNum, currPageNum) {
        var endNum = Math.ceil(totalNum / 10.0) * 10;
        var next = false;
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
        var prev = currPageNum != 1;
        var page = "<ul class='pagination pull-right'>";
        if (prev) {
            page += "<li class='page-item'><a class='page-link' href='/app/v1/it/board/list/" + boardName + "?page=" + (startNum - 1) + "'>Previous</a></li>";
        }
        for (var i = startNum; i <= endNum; i++) {
            var active = currPageNum == i? "active":"";
            page+="<li class='page-item " + active +" '><a class='page-link' href='/app/v1/it/board/list/" + boardName + "?page=" + i + "'>" + i + "</a></li>";
        }
        if (next) {
            page+="<li class='page-item'><a class='page-link' href='/app/v1/it/board/list/" + boardName + "?page=" + (endNum + 1) + "'>Next</a></li>";
        }
        page += "</ul></div>";
        $("#pageDiv").html(page);
    }
});

var main = {
    init : function() {
        var _this = this;
        $('#modal-btn-delete').on('click', function() {
            _this.delete();
        });
    },
    delete : function() {
        var id = $('#hidden_id').val();
        var boardName = $('#hidden_board_name').val();
        var page = $('#hidden_current_page').val();

        $.ajax({
            type: 'DELETE',
            url: '/app/v1/it/board/delete/post/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function() {
            window.location.href='/app/v1/it/board/list/' + boardName + '?page=' + page;
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    }
}
main.init();