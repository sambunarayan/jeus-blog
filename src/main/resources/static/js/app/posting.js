function onChange(evt) {
    var files = evt.target.files;
    var data = new FormData();
    for (var i = 0, f; f = files[i]; i++) {
        // Code to execute for every file selected
        alert(files[i].name);
//        data.append('files['+i+']', files[i]);
    }
    data.append("file", files[0]);
    $.ajax({
        type: 'POST',
        url: '/app/v1/RestImage/upload',
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        data: data,
    }).done(function(data) {
        console.log("success");
        console.log(data);
        alert(data.imageName);
    }).fail(function (error) {
        console.log(error);
    });
}
document.getElementById("uploadFile").addEventListener("change", onChange, false);