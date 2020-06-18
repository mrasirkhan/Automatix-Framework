$(document).ready(function() {
       var selTestTypeId;
       var selectedModule = [];
       var selectedFeature = [];
       var selBrowser;
       var environmentId;
       var filename;
       var xmlPath;
       var xmlFileName;
       var clientName;
       var moduleChange=false;
       var email;
       var emailId;
       var checkedmoduleSize;
       var isBrowserChange;
       var dataSourceValue;
       var environmentName;
     
       /* Set login email id session */
       $('#loginForm').on('submit', function(event) {
           emailId=document.getElementById("email");
           if (typeof(Storage) !== "undefined" && emailId!==null && emailId!==undefined) {
               localStorage.setItem("emailIdCookie", emailId.value);
           } 
           else {
              alert("Sorry, your browser does not support Web Storage. Please use Google Chrome for better compatibility.");
           }
       });
  
  $('body').on('click', '#moduleBtnId', function() 
    {		
	  var flag=isClientOrTestTypeChanged();
		selTestTypeId= $("#selTestType :selected").val();				
		selClientId = $("#selClient :selected").val();
		 if(flag){
			 	$("#moduleList").html("");
				url = "getModuleAndFeatureData";
				var posting = $.post( url, { 
							selClientId:selClientId,
							selTestTypeId:selTestTypeId,
							} );
					posting.done(function(data) {
						data=data;
						moduleAndFeatureFilteredData = JSON.parse(data);
						setModuleListData(moduleAndFeatureFilteredData);
					});					
					$("#featureList").html("");
					moduleChange=true;
					document.getElementById("featureHead").innerHTML="--Select Feature--";		
    	 	}			
    });

  $('body').on('click', '#featureBtnId', function() {		
		 selectedModuleIds=[];
		$('#selModule:checked').each(function(){
			selectedModuleIds.push($(this).val());				
	        });
		if(moduleChange==true)
		{
			$("#featureList").html("");	
			if(selectedModuleIds.length>0)	   
			{	
				var listItems="<li><input type='checkbox' class='featureCheck' name='featureCheck' id='selAllFeature'/>Select All Features</li>";
			    var data=moduleAndFeatureFilteredData.features;
			    var featureDataSet=JSON.parse(data);
			    for(var i=0;i<featureDataSet.length;i++){
			    	for(var j=0;j<=selectedModuleIds.length;j++){
			    		if(selectedModuleIds[j]==featureDataSet[i].moduleKeyId)
			    			{	
			    	            listItems = listItems+"<li><input type='checkbox' class='featureCheck' name='featureCheck' id='selFeature' value='" + featureDataSet[i].featureId + "'>" + featureDataSet[i].featureName + "</checkbox></li>";
			    			}
			    	}
			    }
			    $("#featureList").append(listItems);
			    moduleChange=false;
			}
		}
	});  
 
  
  $('body').on('click', '#submit_btn',function() {	
		selectedFeature=[];
		$('#checkAll').prop('checked', true);
		selBrowser=$("#selBrowser :selected").val();
		environmentId=$("#selEnvironment :selected").val();
		
			 $('#selFeature:checked').each(function()
				{
				 selectedFeature.push($(this).val());				
				});	
			 var messageOnSubmit;
			 var isAnyFieldEmpty= checkEmptyField();
			 if(isAnyFieldEmpty){
				 setErrorMessage();	
				 $("#testdetailsSec").addClass("hide");			
			 }
			 else{	
				 $("#errorMessage").addClass('hide');
				 callFilterTestcaseAjaxRequest();	
			 }
	 });
  
  
       $('body').on('click', '#selAllModules', function() {
           moduleChange = true;
           $('input[id=selModule]').prop('checked', this.checked);
           if (this.checked) {
               document.getElementById("moduleHead").innerHTML = "Module Selected";
           } else {
               document.getElementById("moduleHead").innerHTML = "--Select module--";
           }
       });

       $('body').on('click', '#selModule', function() {
           selectedFeature = [];
           moduleChange = true;
           var checkedmoduleSize = $('input[id=selModule]:checked').length;
           if (this.checked == false) {
               /* $('#checkAll').checked = false; */
               $('#selAllModules').prop('checked', false);
           }
           if (this.checked == true) {
               var totalTestCase = $('input[id=selModule]').length;

               var selectAllFlag = false;

               if (checkedmoduleSize == totalTestCase) {
                   $('#selAllModules').prop('checked', true);
               }
           }
           if (checkedmoduleSize <= 0) {
               document.getElementById("moduleHead").innerHTML = "--Select Module--";
           } else {
               document.getElementById("moduleHead").innerHTML = "Module Selected";
           }
       });

     
       
       
       $('body').on('click', '#selAllFeature', function() {
           $('input[id=selFeature]').prop('checked', this.checked);
           if (this.checked) {
               document.getElementById("featureHead").innerHTML = "Feature Selected";
           } else {
               document.getElementById("featureHead").innerHTML = "--Select Feature--";
           }
       });

       $('body').on('click', '#selFeature', function() {
           var checkedFeatureSize = $('input[id=selFeature]:checked').length;
           if (this.checked == false) {
               /* $('#checkAll').checked = false; */
               $('#selAllFeature').prop('checked', false);
           }
           if (this.checked == true) {
               var totalFeatures = $('input[id=selFeature]').length;
               var selectAllFlag = false;

               if (checkedFeatureSize == totalFeatures) {
                   $('#selAllFeature').prop('checked', true);
               }
           }
           if (checkedFeatureSize <= 0) {
               document.getElementById("featureHead").innerHTML = "--Select Feature--";
           } else {
               document.getElementById("featureHead").innerHTML = "Feature Selected";
           }
       });

       $('body').on('click', '#executeBtnId',function() {
           var selectedTestIds = [];
           
           var table=$('#filteredTestCaseTable').DataTable();
           var rows_selected = table.column(0).checkboxes.selected();
           
           $.each(rows_selected, function(index, rowId){
        	   selectedTestIds.push(rowId);
              } );
           var chkbox_checked    = $('tbody input[type="checkbox"]:checked', table);
    	   var chkbox_select_all  = $('thead input[name="select_all"]', table).get(0); 
           var totalRecords=table.data().count();
           var selectedRecords=selectedTestIds.length;
          if(selectedTestIds!=null && selectedTestIds!=[] && selectedTestIds!="")
          {
        	  var selectAllFlag=false;
        	  if(selectedRecords==totalRecords){
        		
        		  selectAllFlag=true;
        	  }
        	  executeButtonAjaxRequest(selectedTestIds,selectAllFlag);
          }
          else
          {
       	   	showMessagePopup("testCaseNotSelected");
          }
          
       });

       function executeButtonAjaxRequest(selectedTestIds,selectAllFlag){
    	   
           email=localStorage.getItem("emailIdCookie");
           event.preventDefault();
           url = "createXmlUrl";
           /*var isAllEmailsValid = validateEmails();
           if (isAllEmailsValid) 
           {*/
              /* var sendToEmailIds = $("#sendToEmail").val();*/
               var posting = $.post(url, {
                   selTestId: unescape(selectedTestIds),
                   selBrowser: selBrowser,
                   selEnvironment: environmentId,
                   selTestTypeId: selTestTypeId,
                   selectAllFlag: selectAllFlag,
                   selectedModuleIds: unescape(selectedModuleIds),
                   selClientId: selClientId,
                   email: email,
                   clientName:clientName,
               });
               posting.done(function(data) {
            	   showMessagePopup(data);
               });
           /*} */    
         
       }
       
       
       
       $('body').on('click', '#editTestCaseBtn', function() {
           var table = $('#allTestcaseTable').DataTable();
           dataSourceValue = table.row($(this).closest('tr')).data();
           $("#updateTestCaseId").val(dataSourceValue.id);
           $("#manualTestId").val(dataSourceValue.manualTestId);
           $("#testObjective").val(dataSourceValue.testObjective);
           $("#testDescription").val(dataSourceValue.testDescription);
           $("#scriptName").val(dataSourceValue.scriptName);
           $("#severity").val(dataSourceValue.severity);
           $("#updateTestCaseId").val(dataSourceValue.id);
           testcaseClients=dataSourceValue.clients;
           var clientList = $('input[name=editClients]');
           clientList.each(function(){ 
        	     if(testcaseClients.indexOf($(this).val())>=0){
        	    	 $(this).prop('checked',true);
        	     }
        	     else{
        	    	 $(this).prop('checked',false);
        	     }
        	});
           
           
           var objSelect = document.getElementById("editTestType");
           setSelectedValue(objSelect, dataSourceValue.testTypeId);
           
           var objSelect = document.getElementById("editTestFeature");
           setSelectedValue(objSelect, dataSourceValue.featureId);
           
           var objSelect = document.getElementById("editTestModule");
           setSelectedValue(objSelect, dataSourceValue.moduleId);
           
           var objSelect = document.getElementById("editSeverity");
           setSelectedValue(objSelect, dataSourceValue.severityId);
           var applicableForAutomationtrue  = document.getElementById("applicableForAutomationtrue");
           var applicableForAutomationfalse  = document.getElementById("applicableForAutomationfalse");
           
           if (dataSourceValue.applicableForAutomation) 
           {
        	   applicableForAutomationtrue.checked=true;
           } 
           else
           {
        	   applicableForAutomationfalse.checked=true;
           }
           
           $("#requirenmentNo").val(dataSourceValue.requirenmentNo);

           $('#edit_testcases').modal("show");

       });
   
       function getAllTestCases(){
    	   $scope=angular.element(document.getElementById('test-master')).scope();
    	   $scope.loading = true;
    	   $.ajax({
               url: 'getAllTestCasesUrl',
               method: 'POST',
               datatype: 'json',
               success: function(data) {
             	  data=JSON.parse(data);
                   setTestCaseDataTable(data);
                   setAddAndEditTestCaseModalData($scope);
                   $scope.loading = false;
               }
           });
       }
       
       $('body').on('click', '#updateTestCaseBtn',function(e){
				$.ajax({
              url: 'updateTestCaseUrl',
              type: 'POST',
              data: $('#updateTestCaseForm').serialize()
          }).done(function(data) {

              if (data == "Success") 
              {
            	  getAllTestCases();
              } 
              
          });
			var updateForm = document.getElementById("updateTestCaseForm");
			updateForm.reset();
         });     

       $('body').on('click', '.deleteTestCaseBtn', function() {
           var index = $(this).attr("value");
           $("#confirmDeleteDialog").dialog({
               title: 'Confirm Deletion',
             
               buttons: {
                   'Confirm': function() {
                       $(this).dialog('close');
                       deleteSelectedRow(index);
                   },
                   'Cancel': function() {
                       $(this).dialog('close');
                   }
               }
           });
       });

       $('body').on('click','#fileUploadBtn',function() {
           var testcaseFileName = $("#testcaseFile").val();
           var formData = new FormData(document.getElementById("fileUploadForm"));
           $.ajax({
               url: 'uploadFileUrl',
               data: formData,
               processData: false,
               contentType: false,
               type: 'POST'
           }).done(function(data) {
        	   handleFileUploadResponse(data);
           });

       });
       
       
       $('body').on('click','#uploadLinkId',function() {
    	   $("#testcaseFile").innerHTML="";
       });
       
      function  handleFileUploadResponse(data){
    	  var successMsgdiv=document.getElementById("fileUploadSuccessMsg");
    	  var errorMsgdiv=document.getElementById("fileUploadErrorMsg");
    	  if(data=="success")
    	  {
    		  successMsgdiv.innerHTML = "Successfully uploaded All Testcases";
    		  successMsgdiv.style.display = 'block';
    		  errorMsgdiv.style.display = 'none';
   	   		}
	   	   else
	   	   {
	   		   errorMsgdiv.style.display = 'block';
	   		   errorMsgdiv.innerHTML = data;
	   		   successMsgdiv.style.display = 'none';
	   	   	}
      }
       
       
       $('body').on('click', '#addTestcaseBtn',function() 
       {
    	 data=null;
    	 var successId= document.getElementById("savingsuccessMsg");
    	 var failedId= document.getElementById("savingfailedMsg");
    	 $scope=angular.element(document.getElementById('test-master')).scope();
		 $.ajax({
               url: 'addManualTestCaseUrl',
               type: 'POST',
               data: $('#addTestCaseForm').serialize()
           }).done(function(data) {
        	   if(data=="success"){
        		 
        		   successId.style.display="block";
        		   failedId.style.display="none";
        		   successId.innerHTML="Successfully Added";
        	   }
        	   else{
        		   
        		   failedId.style.display="block";
        		   successId.style.display="none";
        		   failedId.innerHTML=data;
        	   }
        	  
           });

       });

       function setSelectedValue(selectObj, valueToSet) 
       {
    	   for (var i = 0; i < selectObj.options.length; i++) 
    	   {
    		   if (selectObj.options[i].value== valueToSet) 
    		   {
    	            selectObj.options[i].selected = true;
    	           
    	       }
    		   else
    		   {
    			   selectObj.options[i].selected = false;
    			}
    	    }
    	}
       function showMessagePopup(data) {
           var message = "";

           var message_pop_up = $('#myModal');
           if (data == "success") {
               message = "Automated Execution request added in the Queue. Please check visit the Results section for status.";
           }  else if(data="testCaseNotSelected"){
               message = "Please select atleast one test case..";
           }
           message_pop_up.find('.modal-body').text(message);
           message_pop_up.modal('show');

       }

   	/*--------------Validate empty fields------------------*/
       function checkEmptyField(){
       		
       		var flag=false;
       		if(environmentId==null || undefined==environmentId || environmentId==""){		
       			
       			return true;
       		}
       		
       		else if(selTestTypeId==null || undefined==selTestTypeId || selTestTypeId==''){
       			
       			return true;
       		}
       		else if(selectedModuleIds==null || undefined==selectedModuleIds || selectedModuleIds==[] || selectedModuleIds==''){
       			return true;
       		}
       		else if(selectedFeature==null || undefined==selectedFeature || selectedFeature==[] || selectedFeature==''){
       			
       			return true;
       		}
       		
       		else if(selBrowser==null || undefined==selBrowser || selBrowser==""){		
       			
       			return true;
       		}
       		else if(undefined!=selClientId)
       			{
       				if(selClientId==null || selClientId==""){	
       					return true;
       			}
       		}
       		else{
       			return false;
       		}
       		
       	}
      
       function setFilteredTestCaseDataInTable(data){
  		if(data!=null && data!=undefined && data!="No data")
  			{
  					dataSet  = JSON.parse(data);
  			}
  			else{
  				dataSet=0;
  			}
  			$('#filteredTestCaseTable').dataTable({
  	             data: dataSet,
  	             serverside:true,
  	             destroy: true,
  	             bLengthChange: false,
  	             pageLength: 10,
  	             bDestroy: true,
  		     	 bDestroy:true,
  		     	 destroy: true,	
  	             bInfo: false,
  	             bAutoWidth: false,
  	             bJQueryUI: true,
  	             aLengthMenu: [[10, 20, 30, 40, 50, -1], [10, 20, 30, 40, 50, "All"]],
  	             sPaginationType: "full_numbers", 
             
               columns : [ 
                      {
                    	  'targets': 0,
                    	   title:"Select All","sWidth":"5%","name":"selectAll",
                          'checkboxes': {
                             'selectRow': true,
                             'value':'testId'
                          }	,
                          'data':'testId'
  	     			},
  	     			{ 
  	                       title:"Serial No","sWidth":"5%",
  	                       "mRender": function (data, type, row, meta) {
  	     						return meta.row + meta.settings._iDisplayStart + 1;
  	    					}
  	                },
                    {
  		     			title : "Test case Objective","sWidth": "20%",
  		     			'data': 'testCaseObjective'
  	     			},
                    {
  		     			title : "Feature","sWidth": "10%",
  		     			'data': 'feature'
  	     			},
                    {
  		     			title : "Severity","sWidth": "10%",
  		     			'data': 'severity'
  	     			},
                      {
  		     			title : "Module","sWidth": "10%",
  		     			'data': 'module'
  	     			},
                      {
  		     			title : "Test Type","sWidth": "10%",
  		     			'data': 'testType'
  	     			}   			
       			
       			],
               fixedHeader: {
                   header: true,
                 
               }
           });
  	 }
  	 
      
   
       
       
       
       
  	 function setErrorMessage() {
           $("#errorMessage").removeClass('hide');
       }
  	$('body').on('click', "#resetBtnId",function(){
           $("#featureList").html("");
           $("#moduleList").html("");
           document.getElementById("featureHead").innerHTML="--Select Feature--";
           document.getElementById("moduleHead").innerHTML="--Select Module--";
           $("#selBrowser").removeAttr("disabled");
           selClientId = "";
           selTestTypeId="";
           selectedModule = [];
           selectedFeature = [];
           selBrowser=null;
           environment=null;
           $("#testdetailsSec").addClass("hide");
       });
  	
  	$('body').on('change', "#selClient",function(){
  		var clientId = $(this).val();
  		if(clientId == 3){
  			$('#selBrowser').val($("#selBrowser option:contains('Web Services')").val());
  			$("#selBrowser").attr("disabled","disabled");
  		}else{
  			$("#selBrowser").removeAttr("disabled");
  		}
  	});


       function setCookie(userId) {
           if (typeof(Storage) !== "undefined") {

               localStorage.setItem("userIdCookie", userId);

           } else {
               document.getElementById("result").innerHTML = "Sorry, your browser does not support Web Storage...";
           }
       }
     function validateEmails() {
           var emails = document.getElementById("sendToEmail").value;
           var emailArray = emails.split(";");
           var isValid = true;
           var inValidEmails = "";
           var errorContent = document.getElementById("emailErrorMsg");
           var commaExist = emails.search(",");

           if (commaExist > 0) {
               errorContent.style.display = "block";
               errorContent.innerHTML = "Invalid Character (,) Enter emails separated by (;)";
               return false;
           }

           for (i = 0; i <= (emailArray.length - 1); i++) {
               if (validateEmail(emailArray[i])) {

               }
           }
           if (inValidEmails != "") {

               errorContent.style.display = "block";
               errorContent.innerHTML = "Invalid email id " + inValidEmails;
               return false;
           } else {
               errorContent.style.display = "";
               errorContent.innerHTML = "";
               return true;
           }
       }

       function validateEmail(sendToEmails) {

           var regExp = /\b[A-Z0-9._%+-]+@(seic|seix)+\.[A-Z]{2,4}\b/i;
           return regExp.test(sendToEmails);
       }

       function callFilterTestcaseAjaxRequest(){
  		 $("#errorMessage").addClass('hide');
  		 messageOnSubmit="Selected test cases are listed below...";
  		 $("#btnId").attr("href", "#testdetailsSec") ;	
  		 
  		 url = "filteredTestcaseDataUrl";			
  		    var posting = $.post( url, {
  		    	selTestTypeId :selTestTypeId,
  		    	selModuleId: unescape(selectedModuleIds) ,
  		    	selFeatureId:unescape(selectedFeature),
  		    	selClientId:selClientId,
  		    	selBrowser:selBrowser
  		    } );
  		    
  		    posting.done(function( data ) {	    
  		    	 setFilteredTestCaseDataInTable(data);
  		     	 $('#myModal1').find('.modal-body').text(messageOnSubmit);
  		 		 $('#myModal1').modal('show'); 	   
  			     setTimeout(function(){ $('#myModal').modal('hide');}, 1000);
  				 event.preventDefault();
  				 $("#testdetailsSec").removeClass("hide");
  		     	 /*$("#testdetailsSec").show("slow");?*/
  		      });
  	   
  	 }
       
    function deleteSelectedRow(id) {
           var table = $('#allTestcaseTable').DataTable();
           var posting = $.post('deleteTestCaseUrl', {
               uniqueId: id
           });
           posting.done(function(data) {
        	   if(data=="success")
                 {
        		   $("#id" + id).closest("tr").remove();
                 }
              });
       }
    
   function isClientOrTestTypeChanged(){
   		var newTestType=$("#selTestType :selected").val();
   		var newClient=$("#selClient :selected").val();
   		if( selTestTypeId!=null && selTestTypeId!=undefined && selClientId!=null && selClientId!=undefined){
   			if(newTestType!=null && newTestType!=undefined)
   				{
   					if(newTestType==selTestTypeId && newClient==selClientId)
   						{
   							return false;	
   						}					
   					}
   				}		
   			document.getElementById("featureHead").innerHTML="--Select Feature--";
   			document.getElementById("moduleHead").innerHTML="--Select Module--";
   			return true;
   		}	
   
   function setModuleListData(dataSet){
       
       	dataSet=dataSet.modules;
       	moduleSet=JSON.parse(dataSet);
       	if(moduleSet.length>0){
       	var listItems="<li><input type='checkbox' class='moduleCheck' name='moduleCheck' id='selAllModules'/>Select All Module</li>";
       	for (var i = 0; i < moduleSet.length; i++) {
               listItems = listItems+"<li><input type='checkbox' class='moduleCheck' name='moduleCheck' id='selModule' value='" + moduleSet[i].moduleId + "'>" + moduleSet[i].moduleName + "</checkbox></li>";
       		}				 	 
           $("#moduleList").append(listItems);
       	}
   		}
   });

/*
 * Custom filtering function which will search data in column four between two
 * values
 */
 if(undefined != $.fn.dataTable && null != $.fn.dataTable){
 
		$.fn.dataTable.ext.search.push(
		    function( settings, data, dataIndex ) {
		    	if(isClickOnFilterBtn)
		    	{
		    		var severity = data[4];
		    		var applicationStatus = data[1];
		    		console.log("applicationStatus "+applicationStatus);
		    		var checkedValue = getSelectedCheckboxValues('filterSeverity');
		    		var filterAutomationStatus = $("select#filterAutomationStatus").val();
		    		console.log(checkedValue!=null );
		    	
		    		console.log(applicationStatus==filterAutomationStatus);
		            if((checkedValue!=null && checkedValue.includes(severity)) || (filterAutomationStatus!=null && applicationStatus==filterAutomationStatus))
		  	       	{
		  	        	return true;
		  	       	}
		  	       	else
		  	       	{
		  	    	   return false;
		  	       	}
		         }
		    	else
		    	{
		    		return true;
		    	}
		    }
		    	
		);
	}

var isClickOnFilterBtn=false;

$(document).ready(function() {
	$("#filterBtn").click(function() {
		isClickOnFilterBtn =true;
		var table = $('#allTestcaseTable').DataTable();
    	console.log("asdfsd");
        table.draw();
	});

} );

function getSelectedCheckboxValues(id)
{
	var checkedValue = null;
	var inputElements = document.getElementsByName(id);
	if(inputElements!=null && inputElements!=""){
		console.log("---");
    for(var i=0; inputElements[i]; ++i){
    	if(inputElements[i].checked){
    		if(checkedValue==null){
    			checkedValue = inputElements[i].value;
  		  	}
  		  	else
  		  	{
  		  		checkedValue = checkedValue + inputElements[i].value;
  		  	}
  		}
     }
	}
    return checkedValue;
}

/*
 * Include html from other pages-----------------------
 * 
 * 
 */
var app = angular.module("myApp", ["ngRoute"]);
app.config(function($routeProvider) {
    $routeProvider
    .when("/testcaseformSec", {
        templateUrl : "/resources/html/execute-testcase.html",controller:"executeTestPageCtrl"
    })
    .when("/test-master", {
        templateUrl : "/resources/html/test-master.html", controller:"testMasterCtrl"
    })
    .when("/execution_results", {
        templateUrl : "/resources/html/execution_results.html",controller:"executeResultsCtrl"
    })
     .when("/scripts", {
        templateUrl : "/resources/html/execution_results.html",controller:"executeResultsCtrl"
    })
    .otherwise({
    	templateUrl : "/resources/html/execute-testcase.html",controller:"executeTestPageCtrl"
    })
 
});
var mylinkApp=angular.module("myLinksApp",[]);
app.controller('executeTestPageCtrl', function($scope,$http) {
   $scope.loading=true;
   $http.get('getDropdownData')
   .then(function(response) {
	   setLoginUserRoleSession();
      setExecuteTestCasePageData(response.data,$scope);
      $scope.loading=false;
   }, function(response) {
   });
    
});
app.controller('testMasterCtrl', function($scope,$http) {
   
	$scope.loading=true;
   $http.get('getAllTestCasesUrl')
   .then(function(response) {
	   $scope.userRole=localStorage.getItem("userRoleSession");
	   setTestCaseDataTable(response.data);
	   setAddAndEditTestCaseModalData($scope);
	   $scope.loading=false;
	   
   }, function(response) {
       $scope.content = "Something went wrong";
   });
    
});
app.controller('executeResultsCtrl', function($scope,$http) {
	$scope.resultsDataLoading=true;
	   $http.get('getAllResultsUrl')
	   .then(function(response) {
		   setResultsDataTable(response.data);
		   $scope.resultsDataLoading=false;
	   }, function(response) {
	       $scope.content = "Something went wrong";
	   });
	});

app.controller('myLinksCtrl',function($scope,$http){
	 $scope.logoutAction = function() {
	        localStorage.clear();
	        document.getElementById("loginUserRole").innerHTML="";
	        $scope.logoutLink="/logoutUrl";
	    };
});

function setLoginUserRoleSession(){
	var userRole=document.getElementById("loginUserRole").innerHTML;
	if(userRole!=null){
		localStorage.setItem("userRoleSession",userRole);
		 console.log("userRole---------"+userRole);
	}
}
function setExecuteTestCasePageData(data,$scope){
	 /* Session for dropdownjson data */
	dropDownData=data;
	 if (typeof(Storage) !== "undefined" && dropDownData!==null && dropDownData!==undefined) {
		 	var dropDownData1 = JSON.stringify(dropDownData);
		    localStorage.setItem("DropdownJson",dropDownData1);
		    console.log("test---------");
	 	} 
	 	$scope.environmentList=dropDownData[0].environments;
	 	$scope.testTypeList=dropDownData[1].testTypes;
	 	$scope.browserList=dropDownData[2].browsers;
	 	$scope.clientList=dropDownData[3].clients;
	 	if($scope.clientList== null){
        	$(".client-field").addClass("hide");
        }
        else{
        	$(".client-field").removeClass("hide");
        }
}

function setAddAndEditTestCaseModalData($scope){
	dropDownDataJson=localStorage.getItem("DropdownJson");
	dropDownDataJson=JSON.parse(dropDownDataJson);
	$scope.testTypeList=dropDownDataJson[1].testTypes;
 	$scope.clientList=dropDownDataJson[3].clients;
 	$scope.moduleList=dropDownDataJson[4].modules;
	$scope.featureList=dropDownDataJson[5].features;
	$scope.severityList=dropDownDataJson[6].severities;
	
}

function setTestCaseDataTable(data) {
	   
	var userRole=localStorage.getItem("userRoleSession");
	dataset=data;
	if(dataset==null || dataset=="No data"){
		dataset=0;
	}
        $('#allTestcaseTable').dataTable({
            data: dataset,
            serverside: true,
            destroy: true,
            bLengthChange: false,
            pageLength: 25,
            bDestroy: true,
            bInfo: false,
            bAutoWidth: false,
            bJQueryUI: true,
            bProcessing: true,
            aLengthMenu: [
                [10, 20, 30, 40, 50, -1],
                [10, 20, 30, 40, 50, "All"]
            ],
            sPaginationType: "full_numbers",
            columnDefs : [
                          { targets: 11, visible: userRole == "Admin" || userRole=="Automation" },
                          { targets: 12, visible: userRole == "Admin"},
                          {targets:10, visible: false,  searchable: false},
                          { targets:9,className:"scriptWidth"}
                        ],
            columns: [
                   {
                	 title : "Serial No.","sWidth":"5%",
                	 'data': 'serialNumber',
                	 render: function(data, type, row, meta) {
                        return meta.row + meta.settings._iDisplayStart + 1;
                    }
                },   
               
                {
                	title : "Manual Test ID",
                    'data': 'manualTestId'
                },
                {
                	title : "Test Objective","sWidth":"20%",
                	render: function(data, type, row) {
                       if(row.testObjective!=null){
                    	   return row.testObjective;
                       }
                    }
                    
                },
                {
                	title : "Severity",
                	render: function(data, type, row) {
                	 if(row.severity!=null){
                		 return "<span style='white-space: nowrap;'>"+row.severity+"</span>";
                	 }
                	}
                },
                {
                	title : "Module",
                	render: function(data, type, row) {
                	 if(row.module!=null){
                		 return "<span>"+row.module+"</span>";
                	 }
                	 else{
                 		return "";
                 	}
                	}
                },
                {
                	title : "Feature",
                	render: function(data, type, row) {
                	if(row.feature!=null){
                		 return row.feature;
                	}
                	else{
                		return "";
                	}
                	}
                },
                {
                	title : "Test Type",
                	render: function(data, type, row) {
                	if(row.testType!=null){
                		 return row.testType;
                	}
                	else{
                		return "";
                	}
                	}
                },
                {
                	title : "Req. Reference",
                	render: function(data, type, row) {
                	if(row.requirenmentNo!=null){
                		 return row.requirenmentNo;
                	}
                	}
                },
                
                {
                	title : "Applicable?","sWidth":"7%",
                    "mRender": function(data, type, row) {
                        if (row.applicableForAutomation) {
                            return '<input type="checkbox" name="checkboxid" value="' + $('<div/>').text(data).html() + '" checked="checked" disabled readonly >';
                        } else {
                            return '<input type="checkbox" name="checkboxid" value="' + $('<div/>').text(data).html() + '" disabled readonly >';
                        }
                    }
                },
                {
                	title : "Script Name",
                	render: function(data, type, row) {
                	if(row.scriptName!=null){
                		 return row.scriptName;
                	}
                	}
                },
                {
                	title : "Clients",
                    'data':'clients'
                },
                
                {
                	title : "Edit",
                    "mRender": function(data, type, row) {
                        return '<center><button class="btn btn-info btn-xsm" id="editTestCaseBtn"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button></center>';
                    }
                	
                },
                {
                	title : "Delete",
                    "mRender": function(data, type, row) {
                        rowdata = row;
                        return '<center><button class="btn btn-danger btn-xsm deleteTestCaseBtn" id="id'+row.id+'" class="deleteTestCaseBtn" value="' + row.id + '" ><i class="fa fa-trash" aria-hidden="true"></i></button></center>';
                    }
                }
            ],
            'select': {
                'style': 'multi'
             },
            fixedHeader: {
                header: true,
            }
        });
    }

function filterTestCases()
{
	$('#allTestcaseTable tfoot th').each( function () {
        var title = $(this).text();
        console.log("title "+title);
        $(this).html( '<input type="text" placeholder="" />' );
        
    } ).toggle();
 
    var table = $('#allTestcaseTable').DataTable();
        
    table.columns().every( function () {
        var that = this;
        $( 'input', this.footer() ).on( 'keyup change', function () {
        	console.log(this.value);
        	console.log("dfsdf"+that.search());
            if ( that.search() !== this.value ) {
                that.search( this.value ).draw();
            }
        } );
    } );
    $('.ignoreHeader').html('');
}


function setResultsDataTable(data) {
	 var result = 0;
	 if (data != null && data != undefined && data != "No data") {
         result = data;
     } else {
         result = 0;
     }
    $('#allResultsTable').dataTable({
        data: result,
        serverside: true,
        destroy: true,
        bLengthChange: false,
        pageLength: 20,
        bDestroy: true,
        bInfo: false,
        bAutoWidth: false,
        bJQueryUI: true,
        bProcessing: true,
         "ordering": false,
        aLengthMenu: [
            [10, 20, 30, 40, 50, -1],
            [10, 20, 30, 40, 50, "All"]
        ],
        sPaginationType: "full_numbers",
        oLanguage: {
            "sSearch": "Wild Filter: "
        },
        columns: [
            {
                title : "Execution No.",
                'data': 'executionId'
            },
            {
                title : "Initated by",                       
                'data': 'initiatorId'
            },
            {
                title : "Execution Date",                   
                'data': 'date'
            },
            {
                title : "Environment \\ AUT",
                'data': 'environment'
            },
            {
                title : "Browser \\ Platform",                       
                'data': 'browser' 
            },
            {
                title : "Test Type",                  
                'data': 'testType'
            },
            {
					title : "Automation Type",
                'data': 'client'
            },
			{
				title : "Status","sWidth": "8%",	
				 "mRender": function(data, type, row) 
					{
					if(row.status=="Completed"){
		 			return '<center><button class="btn btn-success btn-xsm" data-toggle="modal" data-target="#viewResultsModal" 
		 			id="viewResultsModalBtn" value="' + row.executionId + '"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i>&nbsp;
		 			'+row.status+'</button></center>';
		 			}
		 			else if(row.status=="In Progress"){
		 			return '<center><button class="btn btn-warning disabled"><i class="fa fa-hourglass-half" aria-hidden="true"></i>&nbsp;'+row.status+'</button></center>';
		 			}
		 			else if(row.status=="Not Started"){
		 			return '<center><button class="btn btn-info disabled"><i class="fa fa-hourglass-start" aria-hidden="true"></i>&nbsp;'+row.status+'</button></center>'; 
		 			}	
				}
            }	
			],
        fixedHeader: {
            header: true,
        }
    });
    
  
}  
