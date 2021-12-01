var main = {
    init : function() {
        alert('show');
        var _this = this;
        $('#btn-write').on('click', function() {
            alert('show');
            _this.showPost();
        });
    },
    showPost : function() {
        alert('show');
        $('#blog-post').removeClass('d-lg-none');
    }
}
main.init();