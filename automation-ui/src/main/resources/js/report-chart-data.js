$(document).ready(function() {
	$('body').on('click', '#viewResultsModalBtn', function() {

		var index = $(this).attr("value");

		callAjaxForChartData(index);
	});
});

function callAjaxForChartData(index) {
	var url = "getChartingDataUrl";
	var posting = $.post(url, {
		selExecutionId : index
	});

	posting.done(function(data) {
		dataSet = JSON.parse(data);
		setSummaryPanelData(data);
		setPieChartData(dataSet);
		setTestCaseStatusDataTable(dataSet);
		setModuleChartData(data);
		setFeatureChartingData(data);
		setScriptPanelData(dataSet);

	});
}
function setSummaryPanelData(data){
	if(data!=null)
	{
		dataSet = JSON.parse(data);
		var moduleCountDiv=document.getElementById("moduleCount");
		var featureCountDiv=document.getElementById("featureCount");
		var scriptCountDiv=document.getElementById("scriptCount");
		var testcaseCountDiv=document.getElementById("testcaseCount");
		var totalTimeDiv=document.getElementById("totalTime");
		var perTestcaseTimeDiv=document.getElementById("perTestcaseTime");
		
		moduleCountDiv.innerHTML=dataSet.executedModuleCount;
		featureCountDiv.innerHTML=dataSet.executedFeatureCount;
		scriptCountDiv.innerHTML=dataSet.executedScriptCount;
		testcaseCountDiv.innerHTML=dataSet.executedTestCaseCount;
		totalTimeDiv.innerHTML=dataSet.totalScriptTime;
		perTestcaseTimeDiv.innerHTML=dataSet.executionTimePerTestcase+" sec";

		
	}
}
function setPieChartData(dataSet) {
	AmCharts
			.makeChart(
					"ChartPie",
					{
						"type" : "pie",
						"angle" : 2,
						"balloonText" : "[[title]]<br><span><b>[[value]]</b> ([[percents]]%)</span>",
						"depth3D" : 5,
						"innerRadius" : "25%",
						"labelRadius" : 1,
						"alpha" : 0.9,
						"colors" : [ "#52be80", "#ec7063", "#5bc0de" ],
						"creditsPosition" : "top-right",
						"hoverAlpha" : 0.54,
						"labelTickAlpha" : 0.45,
						"marginBottom" : 0,
						"marginTop" : 0,
						"outlineAlpha" : 0,
						"outlineThickness" : 3,
						"titleField" : "Status",
						"valueField" : "Count",
						"borderColor" : "#FFFFFF",
						"fontSize" : 11,
						"handDrawScatter" : 22,
						"theme" : "light",
						"responsive" : "True",
						"allLabels" : [],
						"balloon" : {
							"animationDuration" : 0.96
						},
						"legend" : {
							"enabled" : true,
							"align" : "center",
							"equalWidths" : false,
							"labelText" : "[[title]]:",
							"markerType" : "square",
							"maxColumns" : 5,
							"valueWidth" : 2
						},
						"titles" : [],
						"dataProvider" : [ {
							"Status" : "Pass",
							"Count" : dataSet.PassScriptCount
						}, {
							"Status" : "Fail",
							"Count" : dataSet.FailScriptCount
						}, {
							"Status" : "Not Run",
							"Count" : dataSet.NotRunScriptCount
						} ]
					});
}
function setTestCaseStatusDataTable(data) {
	if (data != null && data != undefined && data != "No data") {
		dataSet = data.testCaseResultsData;
	} else {
		dataSet = 0;
	}
	$('#executedTestCaseStatusDataTable').dataTable(
			{
				data : dataSet,
				serverside : true,
				destroy : true,
				bLengthChange : false,
				pageLength : 20,
				bDestroy : true,
				bDestroy : true,
				destroy : true,
				bInfo : false,
				bAutoWidth : false,
				bJQueryUI : true,
				bProcessing : true,
				oLanguage : {
					"sSearch" : ""
				},
				aLengthMenu : [ [ 10, 20, 30, 40, 50, -1 ],
						[ 10, 20, 30, 40, 50, "All" ] ],
				sPaginationType : "full_numbers",

				columns : [ 
     			{ 
                       title:"Sr.No.","sWidth":"5%",
                       "mRender": function (data, type, row, meta)
                      	{
     						return meta.row + meta.settings._iDisplayStart + 1;
    					}
                },				
				{
					title : "Manual Test ID","sWidth":"10%",
					'data' : 'manualTestId'
				},
				{
					title : "Test Objective","sWidth":"40%",
					'data' : 'testObjective'
				},
				{
					title : "Module",
					'data' : 'moduleName'
				}, {
					title : "Feature",
					'data' : 'featureName'
				}, {
					title : "Severity","sWidth":"8%",
					'data' : 'severity'
				}, 
				{
					title : "Status","sWidth":"6%",
					"mRender" : function(data, type, row) {
						if (row.status == "" || row.status == null) {
							return '<center><div class="status status-info"><Strong>Not Run</Strong></div></center>';
						} 
						else if(row.status=="PASS"){
			 			return '<center><div class="status status-success"><Strong>Pass</Strong></div></center>';
			 			}
			 			else if(row.status=="FAIL"){
			 			return '<center><div class="status status-danger"><Strong>Fail</Strong></div></center>'; 
			 			}
					}
				} ],
				fixedHeader : {
					header : true,
				}
			});
}

function setModuleChartData(data) {
	if (data != null) {
		dataSet = JSON.parse(data);
		allModuleStatusData = dataSet.AllModuleStatusData;

	}
	AmCharts
			.makeChart(
					"ChartModule",
					{
						"type" : "serial",
						"categoryField" : "Module",
						"rotate" : true,
						"startDuration" : 1,
						"theme" : "light",
						"colors" : [ "#52be80", "#ec7063","#5bc0de" ],
						"categoryAxis" : {
							"gridPosition" : "start"
						},
						"chartCursor" : {
							"enabled" : true,
							"bulletSize" : 2
						},
						"trendLines" : [],
						"graphs" : [
								{
									"balloonText" : "<b>[[title]]</b>: [[value]] of [[total]] || ([[percents]]%)",
									"fillAlphas" : 1,
									"id" : "AmGraph-1",
									"title" : "Pass",
									"type" : "column",
									"valueField" : "Pass"
								},
								{
									"balloonText" : "<b>[[title]]</b>: [[value]] of [[total]] || ([[percents]]%)",
									"fillAlphas" : 1,
									"id" : "AmGraph-2",
									"title" : "Fail",
									"type" : "column",
									"valueField" : "Fail"
								},
								{
									"balloonText" : "<b>[[title]]</b>: [[value]] of [[total]] || ([[percents]]%)",
									"fillAlphas" : 1,
									"id" : "AmGraph-4",
									"title" : "Not Run",
									"type" : "column",
									"valueField" : "Not Run"
								} ],
						"guides" : [],
						"valueAxes" : [ {
							"id" : "ValueAxis-1",
							"includeAllValues" : true,
							"integersOnly" : true,
							"pointPosition" : "middle",
							"precision" : 0,
							"radarCategoriesEnabled" : false,
							"stackType" : "100%",
							"strictMinMax" : true,
							"synchronizeWith" : "ValueAxis-1",
							"title" : "% of tests"		
						} ],
						"allLabels" : [],
						"balloon" : {},
						"legend" : {
							"enabled" : true,
							"align" : "center",
							"equalWidths" : false,
							"labelText" : "[[title]]",
							"markerType" : "square",
							"maxColumns" : 5,
							"valueWidth" : 2
						},
						"titles" : [ {
							"id" : "Title-1",
							"size" : 15,
							"text" : "Module view"
						} ],
						"dataProvider" : allModuleStatusData
					});
}

function setFeatureChartingData(data) {
	if (data != null) {
		var dataSet = JSON.parse(data);
		allFeatureStatusData = dataSet.AllFeatureStatusData;

	}
	AmCharts.makeChart("ChartFeature", {
		"type" : "serial",
		"categoryField" : "Feature",
		"columnSpacing" : 0,
		"synchronizeGrid" : true,
		"angle" : 10,
		"autoMarginOffset" : 5,
		"depth3D" : 3,
		"plotAreaBorderAlpha" : 0.5,
		"colors" : [ "#52be80", "#ec7063", "#5bc0de" ],
		"startDuration" : 1,
		"addClassNames" : true,
		"autoDisplay" : true,
		"backgroundAlpha" : 0.8,
		"creditsPosition" : "top-right",
		"panEventsEnabled" : false,
		"theme" : "light",
		"categoryAxis" : {
			"autoRotateAngle" : 45,
			"autoRotateCount" : 10,
			"autoWrap" : true,
			"gridPosition" : "start",
			"centerLabels" : true,
			"labelOffset" : 1,
			"labelRotation" : 0,
			"minHorizontalGap" : 0,
			"titleBold" : false,
			"titleColor" : "#000000",
			"titleFontSize" : 0,
			"titleRotation" : 0
		},

		"chartCursor" : {
			"enabled" : true,
			"categoryBalloonEnabled" : false,
			"categoryBalloonText" : "",
			"leaveAfterTouch" : false,
			"zoomable" : false,
			"bulletSize" : 2
		},
		"trendLines" : [],
		"graphs" : [ {
			"balloonText" : "<b>[[title]]</b>: ([[percents]]%)",
			"fillAlphas" : 1,
			"id" : "Pass",
			"title" : "Pass",
			"type" : "column",
			"valueField" : "Pass"
		}, {
			"balloonText" : "<b>[[title]]</b>: ([[percents]]%)",
			"fillAlphas" : 1,
			"id" : "Fail",
			"title" : "Fail",
			"type" : "column",
			"valueField" : "Fail"
		},

		{
			"balloonText" : "<b>[[title]]</b>: ([[percents]]%)",
			"alphaField" : "Feature",
			"fillAlphas" : 1,
			"id" : "NotRun",
			"title" : "Not Run",
			"type" : "column",
			"valueField" : "Not Run"
		} ],
		"guides" : [],
		"valueAxes" : [ {
			"axisTitleOffset" : 16,
			"id" : "ValueAxis-1",
			"stackType" : "regular",
			"title" : "No. of tests",
			"titleFontSize" : 12,
			"integersOnly": true			
		} ],
		"allLabels" : [],
		"balloon" : {
			"borderColor" : "#000000",
			"borderThickness" : 1,
			"fixedPosition" : false,
			"offsetX" : 0,
			"offsetY" : 0,
			"showBullet" : true
		},
		"legend" : {
			"enabled" : true,
			"align" : "center",
			"equalWidths" : false,
			"labelText" : "[[title]]",
			"markerType" : "square",
			"maxColumns" : 5,
			"valueWidth" : 2
		},
		"titles" : [ {
			"id" : "Title-1",
			"size" : 15,
			"text" : "Feature view"
		} ],
		"dataProvider" : allFeatureStatusData
	});
}

function setScriptPanelData(data){
	
	if (data != null && data != undefined && data != "No data") {
		dataSet = data.ReportScriptData;
	} else {
		dataSet = 0;
	}
	$('#reportScriptDataTable').dataTable(
			{
				data : dataSet,
				serverside : true,
				destroy : true,
				bLengthChange : false,
				pageLength : 25,
				bDestroy : true,
				bDestroy : true,
				destroy : true,
				bInfo : false,
				bAutoWidth : false,
				bJQueryUI : true,
				bProcessing : true,
				oLanguage : {
					"sSearch" : ""
				},
				aLengthMenu : [ [ 10, 20, 30, 40, 50, -1 ],
						[ 10, 20, 30, 40, 50, "All" ] ],
				sPaginationType : "full_numbers",

				columns : [ 
     			{ 
                       title:"Sr.No.","sWidth":"3%",
                       "mRender": function (data, type, row, meta)
                      	{
     						return meta.row + meta.settings._iDisplayStart + 1;
    					}
                },				
				{
					title : "Script Name","sWidth":"20%",
					'data' : 'scriptName'
				},
				{
					title : "Start Time","sWidth":"7%",
					'data' : 'startTime'
				}, 
				{
					title : "End Time","sWidth":"7%",
					'data' : 'endTime'
				},
				{
					title : "Total Time","sWidth":"6%",
					"mRender" : function(data, type, row) {
					return row.totalScriptTime + " sec";
					}

				},
				{
					title : "Status","sWidth":"4%",
					"mRender" : function(data, type, row) {
						if (row.status == "" || row.status == null) {
							return '<center><Strong><div class="status status-info">Not Run</div></Strong></center>';
						} 
						else if(row.status=="PASS"){
			 			return '<center><Strong><div class="status status-success">Pass</div></Strong></center>';
			 			}
			 			else if(row.status=="FAIL"){
			 			return '<center><Strong><div class="status status-danger">Fail</div></Strong></center>'; 
			 			}
					}
				},				
				{
					title : "Test OutCome","sWidth":"20%",
					'data' : 'testOutcome'
				}, 
				{
					title : "Screenshot","sWidth":"15%",
					'data' : 'screenshot'
				}				
				],
				fixedHeader : {
					header : true,
				},
				
			});


}

