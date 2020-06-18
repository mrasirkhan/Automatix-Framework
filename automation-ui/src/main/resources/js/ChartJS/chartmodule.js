			AmCharts.makeChart("ChartModule",
				{
					"type": "serial",
					"categoryField": "Module",
					"rotate": true,
					"startDuration": 1,
					"theme": "light",
					"categoryAxis": {
						"gridPosition": "start"
					},
					"chartCursor": {
						"enabled": true,
						"bulletSize": 2
					},
					"chartScrollbar": {
						"enabled": true,
						"scrollbarHeight": 5
					},
					"trendLines": [],
					"graphs": [
						{
							"balloonText": "<b>[[title]]</b>: [[value]] of [[total]] || ([[percents]]%)",
							"fillAlphas": 1,
							"id": "AmGraph-1",
							"title": "Pass",
							"type": "column",
							"valueField": "Pass"
						},
						{
							"balloonText": "<b>[[title]]</b>: [[value]] of [[total]] || ([[percents]]%)",
							"fillAlphas": 1,
							"id": "AmGraph-2",
							"title": "Fail",
							"type": "column",
							"valueField": "Fail"
						},
						{
							"balloonText": "<b>[[title]]</b>: [[value]] of [[total]] || ([[percents]]%)",
							"fillAlphas": 1,
							"id": "AmGraph-3",
							"title": "Skip",
							"type": "column",
							"valueField": "Skip"
						},
						{
							"balloonText": "<b>[[title]]</b>: [[value]] of [[total]] || ([[percents]]%)",
							"fillAlphas": 1,
							"id": "AmGraph-4",
							"title": "Not Run",
							"type": "column",
							"valueField": "Not Run"
						}
					],
					"guides": [],
					"valueAxes": [
						{
							"id": "ValueAxis-1",
							"includeAllValues": true,
							"integersOnly": true,
							"pointPosition": "middle",
							"precision": 0,
							"radarCategoriesEnabled": false,
							"stackType": "100%",
							"strictMinMax": true,
							"synchronizeWith": "ValueAxis-1",
							"title": ""
						}
					],
					"allLabels": [],
					"balloon": {},
					"legend": {
						"enabled": true,
						"align": "center",
						"maxColumns": 5,
						"switchType": "v",
						"useGraphSettings": true,
						"valueWidth": 2
					},
					"titles": [
						{
							"id": "Title-1",
							"size": 15,
							"text": "Module status view"
						}
					],
					"dataProvider": [
						{
							"Module": "Module 1",
							"Pass": 8,
							"Fail": "2",
							"Skip": "1",
							"Not Run": "1"
						},
						{
							"Module": "Module 2",
							"Pass": 6,
							"Fail": "3",
							"Skip": "1",
							"Not Run": "1"
						},
						{
							"Module": "Module 3",
							"Pass": 2,
							"Fail": "4",
							"Skip": "2",
							"Not Run": "1"
						},
						{
							"Module": "Module 4",
							"Pass": "8",
							"Fail": "3",
							"Skip": "3",
							"Not Run": "1"
						},
						{
							"Module": "Module 5",
							"Pass": "7",
							"Fail": "2",
							"Skip": "1",
							"Not Run": "1"
						},
						{
							"Module": "Module 6",
							"Pass": "7",
							"Fail": "3",
							"Skip": "2",
							"Not Run": "1"
						},
						{
							"Module": "Module 7",
							"Pass": "9",
							"Fail": "4",
							"Skip": "4",
							"Not Run": "1"
						}
					]
				}
			);