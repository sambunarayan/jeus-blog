function buttonClick(){
  let selectOptions = colorSelect.selectedOptions;
  $('#colorSelect').removeClass(currColor);
  for (let i = 0 ; i < selectOptions.length ; i++){
    let color = selectOptions[i].value.replace('text', 'bg');
    $('#colorSelect').addClass(color);
    currColor = color;
  }
}

let currColor = "";
let colorSelect = document.getElementById('colorSelect');
colorSelect.addEventListener('click', buttonClick);
