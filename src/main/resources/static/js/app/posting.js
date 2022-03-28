function onChange(evt) {
    var files = evt.target.files;
    for (var i = 0, f; f = files[i]; i++) {
        // Code to execute for every file selected
        alert(files[i].name);
//        var data = new FormData(files[i]);
        $.ajax({
            type: 'POST',
            enctype: 'multipart/form-data',
            url: '/app/v1/RestImage/upload',
            processData: false,
            contentType: 'multipart/form-data',
            cache: false,
            timeout: 600000,
            data: files[i],
        }).done(function(data) {
            alert(data);
        }).fail(function (error) {
            alert(error);
        });
    }
}
document.getElementById("uploadFile").addEventListener("change", onChange, false);