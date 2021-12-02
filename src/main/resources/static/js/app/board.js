var main = {
    init : function() {
        var _this = this;
        $('a').on('click', function() {
            _this.showPost();
        });
        $('table tr').hover(function() {
            $(this).addClass('table-light');
        }, function() {
            $(this).removeClass('table-light');
        });
    },
    showPost : function() {
        var id = $('input:hidden[name="id"]').val();
        $.ajax({
            type: 'GET',
            url: '/it/board/k8s/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function(json) {
            $("#post-title").text(json.title);
            $("#post-content").text(json.content);
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
        $('#blog-post').removeClass('d-lg-none');
    }
}
main.init();