$(document).ready(function() {
    refresh();
    function refresh() {
        $(window).scroll(function() {
            if ($(window).scrollTop() >= $(document).height() - $(window).height() - ($('#footer').height() / 10)) {
                   // ajax call get data from server and append to the div
//                   alert("end of ");
                   addLatestPostList($("#last_post_id").val());
            }
        });
    }
    /**
     * Show post list
     */
    function addLatestPostList(lastIdx) {
//        alert($('#last_post_id').val());
        $.ajax({
            type: 'GET',
            url: '/app/v1/it/board/latest/'+lastIdx,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function(json) {
            var body = "";
            json.forEach(function(val, idx) {
                body += "<div class='row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative'>";
                body += "    <div class='col p-4 d-flex flex-column position-static'>";
                body += "        <strong class='d-inline-block mb-2 " + val.boardNameColor + "'>" + val.boardName + "</strong>";
                body += "        <h3 class='mb-0'>" + val.title + "</h3>";
                body += "        <div class='mb-1 text-muted'>" + val.createdDate+ "</div>";
                body += "        <p class='card-text mb-auto'>" + val.content + "</p>";
                body += "        <a href='/app/v1/it/board/list/" + val.boardName + "?bno=" + val.id + "' class='stretched-link'>Continue";
                body += "        reading</a>";
                body += "    </div>";
                body += "    <div class='col-auto d-none d-lg-block'>";
                body += "        <svg class='bd-placeholder-img' width='200' height='250' xmlns='http://www.w3.org/2000/svg'";
                body += "             role='img' aria-label='Placeholder: Thumbnail' preserveAspectRatio='xMidYMid slice'";
                body += "             focusable='false'><title>Placeholder</title>";
                body += "            <rect width='100%' height='100%' fill='#55595c'/>";
                body += "            <text x='50%' y='50%' fill='#eceeef' dy='.3em'>Thumbnail</text>";
                body += "        </svg>";
                body += "    </div>";
                body += "</div>";
                $('#last_post_id').val(val.id);
            });
            if (body != "") {
                $('#timeline').append(body);
            }
        }).fail(function (error) {
            alert("error ->" + JSON.stringify(error));
        });
    }
});
