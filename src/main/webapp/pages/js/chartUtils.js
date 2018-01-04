/**
 *  <!-- D3 graphics -->
	<script src="https://d3js.org/d3.v4.min.js"></script>
	<script language="Javascript" src="/stock-alerts/pages/js/chartUtils.js"> </script>
 */

// parse the date / time
var parseTime = d3.timeParse("%d/%m/%Y");

// helper function
function getDate(d, dateParam) {
	if (typeof d[dateParam] == "string") {
		return parseTime(d[dateParam]);
	} else {
		// Date has come in "long" format
		return new Date(d[dateParam]);
	}
}
function getPriceDomain(data, priceParams) {
	var max = 0;
	var min = data[0][priceParams[0]];
	for (var i = 0; i < data.length; i++) {
		for( var j = 0; j < priceParams.length; j++){
			var price = data[i][priceParams[j]];
			if (price > max) {
				max = price;
			}
			if (price < min) {
				min = price;
			}
		}
	}

	return [ min * 0.7, max * 1.3 ];
}

function createLineGenerator(dateParam, priceParam, xScale, yScale){
	return d3.line().x(function(d) {
		// console.log(d[dateParam] + " = " + getDate(d, dateParam));
		return xScale(getDate(d, dateParam));
	}).y(function(d) {
		return yScale(d[priceParam]);
	}).curve(d3.curveBasis)
}

function createChart(data, containerId, dateParam, priceParams, labels) {
	if( !labels ){
		labels = [];
	}
	var colors = ["green", "blue", "red" ];
	document.getElementById(containerId).innerHTML = "";
	// get max and min dates - this assumes data is sorted
	var maxDate = getDate(data[0], dateParam), minDate = getDate(data[data.length - 1], dateParam);
	
	if( maxDate < minDate ){
		var auxDate = minDate;
		minDate = maxDate;
		maxDate = auxDate;
	}


	var vis = d3.select("#" + containerId), WIDTH = 1000, HEIGHT = 500, MARGINS = {
		top : 20,
		right : 20,
		bottom : 20,
		left : 50
	}, xScale = d3.scaleTime().range([ MARGINS.left, WIDTH - MARGINS.right ])
			.domain([ minDate, maxDate ]), yScale = d3.scaleLinear().range(
			[ HEIGHT - MARGINS.top, MARGINS.bottom ]).domain(
			getPriceDomain(data, priceParams)), xAxis = d3.axisBottom().scale(
			xScale), yAxis = d3.axisLeft().scale(yScale);

	vis.append("svg:g").attr("class", "x axis").attr("transform",
			"translate(0," + (HEIGHT - MARGINS.bottom) + ")").call(xAxis);
	vis.append("svg:g").attr("class", "y axis").attr("transform",
			"translate(" + (MARGINS.left) + ",0)").call(yAxis);

//	var lineGen = d3.line().x(function(d) {
//		// console.log(d.date + " = " + getDate(d));
//		return xScale(getDate(d, dateParam));
//	}).y(function(d) {
//		return yScale(d[priceParam]);
//	}).curve(d3.curveBasis)
	
	for( var i = 0; i < priceParams.length ; i++){ 
		vis.append('svg:path').attr('d', createLineGenerator(dateParam, priceParams[i],xScale, yScale)(data)).attr('stroke', colors[i])
		.attr('stroke-width', 2).attr('fill', 'none')

		vis.append("text")
		.attr('text-anchor', 'end')
		.attr("transform", "translate(" + (WIDTH - MARGINS.right) + "," + (10 + MARGINS.top + ( 20 * i)) + ")")
		.attr("dy", ".35em")
		.style("fill", colors[i])
		.text(labels[i]? labels[i] : '');
	}
	 
}