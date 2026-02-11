// Define variables at the top so they are accessible globally in the pipeline
def smokeStats = [passed: 0, failed: 0, skipped: 0]
def regStats = [passed: 0, failed: 0, skipped: 0]

pipeline {
    agent any
    tools { maven 'Maven-3' }
    
    stages {
        stage('Smoke Test') {
            steps {
                dir('smoke-repo') {
                    git 'https://github.com/ragav002-rs/PlaywrightCode'
                    bat "mvn -f PlaywrightPOMSession clean test -Dsurefire.suiteXmlFiles=src/test/resources/TestRunners/testng.xml"
                }
            }
            post {
                always {                 
                    script {
                        def results = junit testResults: '**/target/surefire-reports/TEST-*.xml'
                       
                       	smokeStats.total = results.totalCount
                        smokeStats.passed = results.passCount
                        smokeStats.failed = results.failCount
                        smokeStats.skipped = results.skipCount
                    }
                }	
            }
        }

        // ... Build Validation and Deploy stages ...

        stage('Regression Test') {
            steps {
                catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                    dir('selenium-repo') {
                        git 'https://github.com/ragav002-rs/SeleniumCode'
                        bat "mvn -f SeleniumCode clean test -Dsurefire.suiteXmlFiles=src/test/resources/TestRunners/testng.xml"
                    }
                }
            }  
            post {
                always {				
                    script {
                        def results = junit testResults: '**/target/surefire-reports/*.xml'                      
						
						regStats.total = results.totalCount
                        regStats.passed = results.passCount
                        regStats.failed = results.failCount
                        regStats.skipped = results.skipCount
                    }
                }
            }  
        }
    }			
    post {
        always {
            script {
                // Determine color based on result
                def statusColor = "#28a745" // Success
                if (currentBuild.currentResult == 'UNSTABLE') statusColor = "#fb6f00"
                if (currentBuild.currentResult == 'FAILURE') statusColor = "#ff0033"

                emailext(
                    subject: "Pipeline ${currentBuild.currentResult}: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                    mimeType: 'text/html',
                    to: 'ragav002@gmail.com',
                    body: """
                        <html>
                        <body style="font-family: Arial; padding: 20px; background:#f5f5f5;">
                            <div style="background: ${statusColor}; color: white; padding: 15px; border-radius: 6px;">
                                <h2 style="margin:0;">Pipeline ${currentBuild.currentResult}: ${env.JOB_NAME} #${env.BUILD_NUMBER}</h2>
                            </div>
                            <br/>
                            
                           <!-- BOX 1 : PIPELINE SUMMARY -->
								<div style="background:white; border:2px solid #ddd; padding:20px; border-radius:6px;">
					    		<h3 style="margin-top:0; text-align: center;">Pipeline Summary</h3>
					
					    <table cellpadding="10" style="width:100%; border-collapse: collapse;">
					        <tr>
					            <td style="border: 2px solid #ddd;"><b>Overall Status</b></td>
					            <td style="color:${statusColor}; border: 2px solid #ddd; font-weight:bold;">
					                ${currentBuild.currentResult}
					            </td>
					        </tr>
					        <tr>
					            <td style="border: 2px solid #ddd;"><b>Job Name</b></td>
					            <td style="border: 2px solid #ddd;">${env.JOB_NAME}</td>
					        </tr>
					        <tr>
					             <td style="border: 2px solid #ddd;"><b>Build Number</b></td>
					             <td style="border: 2px solid #ddd;">#${env.BUILD_NUMBER}</td>
					        </tr>
					    </table>
					
					    <br/>
					    <a href="${env.BUILD_URL}console"
					       style="background:#007bff; color:white; padding:10px 15px;
					              text-decoration:none; border-radius:4px; display:inline-block;">
					        View Console Output
					    </a>
					    <a href="${env.BUILD_URL}/pipeline-overview"
					       style="background:#007bff; color:white; padding:10px 15px;
					              text-decoration:none; border-radius:4px; display:inline-block;">
					        Pipeline Overview
					</div>
					
					<br/>
                            
                         <!-- BOX 2 : TEST EXECUTION SUMMARY -->                          
                            <div style="background:white; border:1px solid #ddd; padding:20px; border-radius:6px;">
                                <h3 style="text-align: center;">Test Execution Summary</h3>
                                <table border="1" cellpadding="10" style="border-collapse: collapse; width:100%;">
                                    <tr style="background:#f2f2f2;">
                                        <th>Test Suite</th>
                                        <th>Passed</th>
                                        <th>Failed</th>
                                        <th>Skipped</th>
                                        <th>TotalTests</th>
                                    </tr>
                                    <tr>
                                        <td><b>Smoke Tests</b></td>
                                        <td style="color:green;">${smokeStats.passed}</td>
                                        <td style="color:red;">${smokeStats.failed}</td>
                                        <td>${smokeStats.skipped}</td>
                                         <td>${smokeStats.total}</td>
                                    </tr>
                                    <tr>
                                        <td><b>Regression Tests</b></td>
                                        <td style="color:green;">${regStats.passed}</td>
                                        <td style="color:red;">${regStats.failed}</td>
                                        <td>${regStats.skipped}</td>
                                        <td>${regStats.total}</td>
                                    </tr>                                          
                                </table>
                                
                                <br/>
					    		<a href="${env.BUILD_URL}testReport"
					       			style="background:#28a745; color:white; padding:10px 15px;
					             	text-decoration:none; border-radius:4px; display:inline-block;">
					        		View Test Results
					   		 	</a>
                                
                            </div>
                        </body>
                        </html>
                    """
                )
            }
        }
    }
}