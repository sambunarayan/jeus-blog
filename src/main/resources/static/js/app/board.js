var main = {
    init : function() {
        var _this = this;
        $('a').on('click', function() {
            if ($(this).hasClass('nav-link')) {
                return;
            } else if ($(this).hasClass('btn')) {
                return true;
            }
            var tr = $("table tr");
            for (var i=0; i < tr.length; i++) {
                tr.removeClass('table-secondary');
            }
            $(this).closest('tr').addClass('table-secondary');
            _this.showPost($(this).attr('id'));
        });
        $('table tr').hover(function() {
            if (!$(this).hasClass('table-primary')) {
                $(this).addClass('table-light');
            }
        }, function() {
            $(this).removeClass('table-light');
        });
    },
    showPost : function(id) {
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
        window.scrollTo({ top: 0, behavior: 'smooth' });
    }
}
main.init();