var main = {
    init : function() {
        $(window).scroll(function() {
//            alert("end of " + $(window).scrollTop() + "," + $(document).height() + "," + $(window).height());
            if($(window).scrollTop() >= $(document).height() - $(window).height() - $('#footer').height()) {
                   // ajax call get data from server and append to the div
                   alert("end of");
            }
        });
    }
}
main.init();
