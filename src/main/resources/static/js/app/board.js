$(document).ready(function(){

        showList();
        function showList() {

            var tableBody = $("#tbody");
            alert(tableBody);
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