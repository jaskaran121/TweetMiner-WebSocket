$(document).ready(function() {
	var url = $("#divForWebsocket").data("ws-url");
	websocket = new WebSocket(url);
	websocket.onmessage = function(event) {
		message= JSON.parse(event.data);
		json = message.data;
		obj = JSON.parse(json);
		keyword = obj.keyword;
		console.log(keyword);
		key= keyword.split(' ').join('-');
		if ($('#'+key).length==0){
			createNewDivforNewKeyword(keyword);
		}
		updateDivforKeyword(keyword,obj.tweet,obj.emotion);
	}

	$('#formforsearchKeyword').on('submit', function(e) {
		e.preventDefault();
		
		websocket.send(JSON.stringify({
		
			keyword : $("#searchTerm").val()
		
		}));
		$("#searchTerm").val("");
	});

});

function createNewDivforNewKeyword(keyword){
	key= keyword.split(' ').join('-');
	$(".newtweet").clone().attr('id', key).appendTo("#data");
	$('#'+key).show();
	$('#'+key).find('h4').text('Keyword: '+keyword);
	
}

function updateDivforKeyword(keyword,tweet,emotion){
	key= keyword.split(' ').join('-');
	$('#'+key).find('span').empty();
	var innerHtml="<ol>";
	innerHtml+="<a target='_blank' href='wordcount?keyword="+keyword+"'><b>"+keyword+"</b></a>";
	innerHtml+="<b> Sentiment of this search result is"+emotion+"</b>";
	tweet.forEach( function(item) {
		
		innerHtml+="<li><b>User:-</b><a target='_blank' href='user?screenName="+item.screenName+"'>"+item.screenName+"</a><div><b>Tweet:</b>"+item.tweet+"</div>";
		innerHtml+="<b>Location:-</b><a target='_blank' href='location?latitude="+item.latitude+"&longitude="+item.longitude+"'>"+item.location+"</a><div><b>HashTags:</b>";
		item.hashtags.forEach( function(hashtag){
			innerHtml+="<a target='_blank' href='hashtag?hashtag="+hashtag+"'>#"+hashtag+"</a>";
		});
		innerHtml+="</div></li>";
		});
	innerHtml+="</ol>";
	$('#'+key).find('span').append(innerHtml);
}

