let btnDetails = document.getElementById('detailsBtn');

btnDetails.addEventListener('click', function() {

	let cont = document.getElementById('container');

	fetch("http://localhost:8080/rest/shark-cage/details").
		then(response => response.json()).
		then(data => {

			let h5 = document.createElement('h5');
			h5.innerHTML = "<h5 class='card-title mt-3'>" + data.title + "</h5><br>";
			cont.appendChild(h5);

			let p = document.createElement('p');
			p.className = 'card-text text-start';
			p.innerHTML = 
				data.description + "<br><br>" +
				"Diving Season - " + data.divingSeason + "<br>" +
				"Difficulty - " + data.difficulty + "<br>" +
				"Duration - " + data.duration + "h<br>" +
				"Price - " + data.price + "$<br>";

			cont.appendChild(p);
		});

});//end listener
