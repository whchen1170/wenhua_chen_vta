$(function() {
  $('#buttonSave').click(function(){
    var name = $('#name').val();
    var place = $('#place').val();

    $.ajax({
      url: '/api/save?name=' + name + '&place='+place,
      success: function(data) {
        alert("saved user and location? " + data);
      }
    });
  });
});

$(function() {
  $('#buttonSearch').click(function(){
    var user = $('#user').val();

    $.ajax({
      url: '/api/search?user=' + user,
      success: function(data) {
        //$('#location').text(data);
        var base = "https://www.google.com/maps/embed/v1/place?key=AIzaSyDDPPjTUEdMkA7JQMTep77x3d5pWiWdZrs";
        var url = base + '&q=' + data;
        alert(url);
        $('#myframe').attr('src',url);
      }
    });
  });
});

$( document ).ready(function() {
    if (document.location.hostname !== "localhost") {
        host = "http://" + document.location.host;
    } else {
        host = "http://localhost:4567"
    }
});
