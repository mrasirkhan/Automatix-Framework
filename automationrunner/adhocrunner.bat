set projectLocation=D:\Docs\Rahul\VitaminShopee\Automation_Project
cd %projectLocation%
call mvn -f adhoc_pom.xml clean -Dmaven.clean.failOnError=false install
call mvn exec:java@RunChangeExecutionStatus
call mvn exec:java@RunDumpReportToDB
call mvn exec:java@RunExcelReportGenerator
echo "Generating Report..."
timeout /t 5