function onChange(evt) {
    var files = evt.target.files;
    for (var i = 0, f; f = files[i]; i++) {
        // Code to execute for every file selected
        alert(files[i].name);
    }
}
document.getElementById("uploadFile").addEventListener("change", onChange, false);