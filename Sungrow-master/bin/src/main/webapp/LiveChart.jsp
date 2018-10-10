
<%@page import="com.tutorial.App.EstruturaDeDados.Fila"%>
<%@page import="com.tutorial.App.mqtt.CallbackMQTT"%>
<%@page import="com.tutorial.App.EstruturaDeDados.TipoFila"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
<script type="text/javascript">
	
   window.onload = function () {
      // var dps = [{ x: 1, y: 10 }, { x: 2, y: 13 }, { x: 3, y: 18 }, { x: 4, y: 20 }, { x: 5, y: 17 }, { x: 6, y: 10 }, { x: 7, y: 13 }, { x: 8, y: 18 }, { x: 9, y: 20 }, { x: 10, y: 17 }];   //dataPoints.
    var dataPoints = [];
       var chart = new CanvasJS.Chart("chartContainer", {
           title: {
               text: "Temperatura"
           },
           data: [{
               type: "line",
               dataPoints: dataPoints
           }]
       });

       chart.render();

       var xVal = 1;
       var yVal = 15;
       var updateInterval =1000;

        var updateChart = function () {
        	
        	
           yVal = ${message};
           dataPoints.push({ x: xVal, y: yVal });
           xVal++;
           chart.render();
           
           
       };
       
       var starTimer = function () {
           new Ajax.PeriodicalUpdater('/graficos/temperatura', {
              method: 'GET', frequency: 2, decay: 1
           });
        }
       setInterval(function () { starTimer() }, updateInterval);
       setInterval(function () { updateChart() }, updateInterval);
       
       
       
      
   }
</script>
</head>
<body>
<div id="chartContainer" style="height: 300px; width: 100%;"></div>
</body>
</html>