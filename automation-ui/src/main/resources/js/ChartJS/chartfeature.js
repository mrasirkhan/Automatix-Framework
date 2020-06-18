			AmCharts.makeChart("ChartFeature",
				{
					"type": "serial",
					"categoryField": "Feature",
					"columnSpacing": 0,
					"synchronizeGrid": true,
					"angle": 10,
					"autoMarginOffset": 5,
					"depth3D": 3,
					"plotAreaBorderAlpha": 0.5,
					"colors": [
						"#52be80",
						"#ec7063",
						"#f7dc6f",
						"#b2babb"
					],
					"startDuration": 1,
					"addClassNames": true,
					"autoDisplay": true,
					"backgroundAlpha": 0.8,
					"creditsPosition": "top-right",
					"panEventsEnabled": false,
					"theme": "light",
					"categoryAxis": {
						"autoRotateAngle": 0,
						"autoWrap": true,
						"gridPosition": "start",
						"boldLabels": true,
						"centerLabels": true,
						"centerRotatedLabels": true,
						"titleBold": false,
						"titleColor": "#000000"
					},
					"chartCursor": {
						"enabled": true,
						"categoryBalloonEnabled": false,
						"categoryBalloonText": "",
						"leaveAfterTouch": false,
						"zoomable": false
					},
					"chartScrollbar": {
						"enabled": true,
						"graphType": "column",
						"scrollbarHeight": 1,
						"scrollDuration": 0
					},
					"trendLines": [],
					"graphs": [
						{
							"balloonText": "<b>[[title]]</b>: [[value]] ([[percents]]%)",
							"fillAlphas": 1,
							"id": "Pass",
							"title": "Pass",
							"type": "column",
							"valueField": "Pass"
						},
						{
							"balloonText": "<b>[[title]]</b>: [[value]] ([[percents]]%)",						
							"fillAlphas": 1,
							"id": "Fail",
							"title": "Fail",
							"type": "column",
							"valueField": "Fail"
						},
						{
							"balloonText": "<b>[[title]]</b>: [[value]] ([[percents]]%)",						
							"fillAlphas": 1,
							"id": "Skip",
							"title": "Skip",
							"type": "column",
							"valueField": "Skip",
						},
						{
							"balloonText": "<b>[[title]]</b>: [[value]] ([[percents]]%)",	
							"alphaField": "Feature",
							"fillAlphas": 1,
							"id": "NotRun",
							"title": "Not Run",
							"type": "column",
							"valueField": "Not Run"
						}
					],
					"guides": [],
					"valueAxes": [
						{
							"axisTitleOffset": 16,
							"id": "ValueAxis-1",
							"stackType": "regular",
							"title": "No. of tests",
							"titleFontSize": 12
						}
					],
					"allLabels": [],
					"balloon": {
						"borderColor": "#000000",
						"borderThickness": 1,
						"fixedPosition": false,

						"offsetX": 0,
						"offsetY": 0,
						"showBullet": true
					},
					"legend": {
						"enabled": true,
						"markerSize": 14,
						"useGraphSettings": true,
                        "valueWidth": 2,
                        "align": "center"
					},
					"titles": [
						{
							"id": "Title-1",
							"size": 15,
							"text": "Feature wise view"
						}
					],
					"dataProvider": [
						{
							"Pass": "5",
							"Fail": "8",
							"Skip": 2,
							"Not Run": 1,
							"Feature": "Feature 1"
						},
						{
							"Pass": "2",
							"Fail": "6",
							"Skip": 1,
							"Not Run": 1,
							"Feature": "Feature 2"
						},
						{
							"Pass": "1",
							"Fail": "2",
							"Skip": 1,
							"Not Run": 1,
							"Feature": "Feature 3"
						},
						{
							"Pass": "4",
							"Fail": "2",
							"Skip": "2",
							"Not Run": "2",
							"Feature": "Feature 4"
						}
					]
				}
			);