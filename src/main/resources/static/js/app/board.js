$(document).ready(function(){
    list();
    function list() {
        var regForm = $("#hidden_form");
        var tableBody = $("#tbody");
        var name = document.getElementById("board_name").innerHTML;
        var name2 = $(this).data("board_name");
        var name3 = '${board_name}';

        alert(name);
        alert(name2);
        alert(name3);
        alert(regForm.find('#hidden_board_name').val());

        var body = "";

        body += "<tr id='tr id'>";
        body += "<td>1</td>";
        body += "<td><a id='1' href='/it/board/list/kubernetes?bno=1'>test</a></td>";
        body += "<td>a</td>";
        body += "<td>b</td>";
        body += "</tr>";
        alert(body);
        tableBody.html(body);
    }
});