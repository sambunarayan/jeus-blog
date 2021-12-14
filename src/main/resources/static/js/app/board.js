$(document).ready(function(){ 
  var template = $('#test').html();
  var output = $('#output');

  var data = {
      "board_name": "Template Engines"
  };

  var result = Mustache.render(template, data);
  
  output.append(result);

});