			AmCharts.makeChart("ChartPie",
				{
					"type": "pie",
					"angle": 15.3,
					"balloonText": "[[title]]<br><span><b>[[value]]</b> ([[percents]]%)</span>",
					"depth3D": 5,
					"innerRadius": "10%",
					"labelRadius": 1,
					"alpha": 0.9,
					"colors": [
						"#04D215",
						"#FF000F",
						"#FF9E01",
						"#F8FF01"
					],
					"creditsPosition": "top-right",                
					"hoverAlpha": 0.54,
					"labelTickAlpha": 0.45,
					"marginBottom": 0,
					"marginTop": 0,
					"outlineAlpha": 0,
					"outlineThickness": 3,
					"titleField": "Status",
					"valueField": "Count",
					"borderColor": "#FFFFFF",
					"fontSize": 11,
					"handDrawScatter": 22,
					"theme": "light",
					"responsive": "True",
					"allLabels": [],
					"balloon": {
						"animationDuration": 0.96
					},
					"legend": {
						"enabled": true,
						"align": "center",
						"equalWidths": false,
						"labelText": "[[title]]:",
						"markerType": "square",
						"maxColumns": 5,
                        "valueWidth": 2
					},
					"titles": [],
					"dataProvider": [
						{
							"Status": "Pass",
							"Count": 8
						},
						
						{
							"Status": "Fail",
							"Count": 6
						},
						{
							"Status": "Skip",
							"Count": 2
						},
						{
							"Status": "Not Run",
							"Count": 2
						}						
					]
				}
			);