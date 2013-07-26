var display_container;
var request;

function showPosition(position) {
	var x = "latitude=" + position.coords.latitude + "&longitude="
			+ position.coords.longitude;
	// display_container.empty().append('<h1>' + x + '</h1>');

	if (position.coords.latitude) {

		request = $.ajax({
			url : "rest/search",
			type : "GET",
			data : x,
			dataType : "json"
		});

		// callback handler that will be called on success
		request.done(function(response, textStatus, jqXHR) {
			var json = JSON.parse(jqXHR.responseText);
			var result = "<h1>" + json.name + "</h1>";
			result+="<img alt=\"rating\" src=\""+json.rating_img_url_small+"\" />";
			result+="<h5>Location: "+json.location.address[0]+"</h5>";
			result+="<h5><a href=\""+json.url+"\">check in Yelp.com</a></h5>";
			display_container.empty().append(result);
		});

		request.fail(function(xhr, textStatus) {
			alert("???!!!!!! Request failed: " + xhr.responseText);
		});

	}
}

function getLocation() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(showPosition);

	} else {
		display_container.innerHTML = "Geolocation is not supported by this browser.";
	}
}

function searchResturant(displayContainer, description) {
	display_container = displayContainer;
	getLocation();
	description.empty().append('hummmmm ... That\'s it.');
}