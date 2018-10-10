
<!DOCTYPE HTML>
<html>
<head>

<script>
window.onload = function() {

var dataPoints = [];

var chart = new CanvasJS.Chart("chartContainer1", {
	theme: "light2",
	title: {
		text: "Temperatura da Solução"
	},
	data: [{
		type: "line",
		dataPoints: dataPoints
	}]
});

var chart1 = new CanvasJS.Chart("chartContainer2", {
	theme: "light2",
	title: {
		text: "Temperatura Ambiente"
	},
	data: [{
		type: "line",
		dataPoints: dataPoints
	}]
});
updateData();

var xValue = 0;
var yValue = 10;
var newDataCount = 6;

function addDataTemperaturaDaAgua(data) {
	
	dataPoints.length = 0;
	$.each(data, function(key, value) {
		dataPoints.push({x: xValue , y: value});
		xValue++;
	
	
});
	chart.render();
	setTimeout(updateData1, 1000);

}

function addDataTemperaturaAmbiente(data) {
	
	dataPoints.length = 0;
	$.each(data, function(key, value) {
		dataPoints.push({x: xValue , y: value});
		xValue++;
	
	
});
	chart1.render();
	setTimeout(updateData, 1000);

}

function updateData() {
	
	$.getJSON("http://localhost:8080/json1/temperaturaagua", addDataTemperaturaDaAgua);
	
}

function updateData1() {
	
	
	$.getJSON("http://localhost:8080/json1/temperaturaambiente", addDataTemperaturaAmbiente);
}



}
</script>
</head>
<body>
<!-- <div id="chartContainer" style="height: 300px; width: 100%;"></div> -->
<div id='chartContainer1' style='width: 49%; height: 300px;display: inline-block;'></div>
<div id='chartContainer2' style='width: 49%; height: 300px;display: inline-block;'></div>
<script src="https://canvasjs.com/assets/script/jquery-1.11.1.min.js"></script>
<script src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script>
</body>
</html>