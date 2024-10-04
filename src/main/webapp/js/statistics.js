
"use strict";

window.onload = getActivityStatisticsByType;

function getActivityStatisticsByType(){
	const url = "activityStatistics?key=type";

	fetch(url)
	.then(response =>{
		return response.json();
	})
	.then(datalist =>{
	   	setChartDonut(datalist);
	})
	.catch(error => console.log('Erro de solicitação', error));
}

function setChartDonut(datalist){
	const colors = ['#007bff', '#28a745', '#333333', '#c3e6cb', '#dc3545', '#6c757d'];

	const donutOptions = {
		cutoutPercentage: 85,
		legend: {position: 'bottom', padding: 5, labels: {pointStyle: 'circle', usePointStyle: true}}
	};

	const chDonutData1 = {
		labels: datalist.map(data => data.type),
		datasets: [
			{
				backgroundColor: colors.slice(0, 3),
				borderWidth: 0,
				data: datalist.map(data => data.count)
			}
		]
	};

	const chDonut1 = document.getElementById("chDonut1");
	if (chDonut1) {
	  new Chart(chDonut1, {
	      type: 'pie',
	      data: chDonutData1,
	      options: donutOptions
	  });
	}
}