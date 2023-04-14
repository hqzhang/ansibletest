def version = 'main'
library("my-shared-lib@$version") _
println "wksp=${env.WORKSPACE}"

properties([
   pipelineTriggers([githubPush()]),
   parameters([ choice(name: 'choice1', choices: ['solution.yaml', 'config.yaml'], description: 'input cluster'),
                text(name: 'CONFIG', defaultValue: con.curlConfig(env.choice1),description: 'input read file'),
                [$class: 'ChoiceParameter', choiceType: 'PT_SINGLE_SELECT', 
                filterLength: 1, filterable: false, name: 'tier', 
                randomName: 'choice-parameter-116988809562567', 
                script: [$class: 'GroovyScript', fallbackScript: [classpath: [], oldScript: '', sandbox: false, script: ''], 
                        script: [classpath: [], oldScript: '', sandbox: false, script: 'return [ \'web\',\'backend\',\'database\']']]
                [$class: 'DynamicReferenceParameter', 
                choiceType: 'ET_FORMATTED_HTML', name: 'tier', omitValueField: false, 
                randomName: 'choice-parameter-116430624038785', referencedParameters: 'choice1', 
                script: [$class: 'GroovyScript', fallbackScript: [classpath: [], oldScript: '', sandbox: false, script: ''],
                        script: [classpath: [], oldScript: '', sandbox: false, 
                                  script: '''service_tier_map = [
  "web": [
    ["service_name": "user_frontend", "release_tag": "1.0.0" ],
    ["service_name": "admin_frontend", "release_tag": "1.0.2" ],
  ],
  "backend": [
    ["service_name": "admin_service", "release_tag": "2.1.0" ],
    ["service_name": "finance_service", "release_tag": "2.2.0" ],
    ["service_name": "payment_service", "release_tag": "3.2.0" ],
  ],
  "database": [
    ["service_name": "dynamo_db", "release_tag": "5.4.1"],
    ["service_name": "mysql", "release_tag": "3.2.1"],
    ["service_name": "postgresql", "release_tag": "1.2.3"],
  ],
]
html_to_be_rendered = "<table><tr>"
service_list = service_tier_map[tier]
service_list.each { service ->
  html_to_be_rendered = """
    ${html_to_be_rendered}
    <tr>
    <td>
    <input name=\\"value\\" alt=\\"${service.service_name}\\" json=\\"${service.service_name}\\" type=\\"checkbox\\" class=\\" \\">
    <label title=\\"${service.service_name}\\" class=\\" \\">${service.service_name}</label>
    </td>
    <td>
    <input type=\\"text\\" class=\\" \\" name=\\"value\\" value=\\"${service.release_tag}\\"> </br>
    </td>
    </tr>
"""
}
return "${html_to_be_rendered}</tr></table>"''']]],     
              
    ])
])
println con.curlConfig('solution.yaml')
//println writeConfig()
pipeline {
    agent any
    
    options { timestamps () }
    environment { 
        //Define Gobal Variables can be used name, env.name or params.name
        UpDirs="/usr/local/bin:"
        MY_VAR_ENV="uiuiu"
        PATH="/usr/local/bin:$PATH"
        myenv='Helloworld emily!'
        private_key='afb3704a-da55-4576-9fb9-9a6265319f2b'
        //myFiles='/tmp/file1,     /tmp/file2'
        //myPath='./ansible/'
        HOME='/var/root'
    }

    stages {
        stage('write') {
           steps {
               script {
                   echo "WRITE FILE8888888888"
                   writeFile file: 'solution_out.yaml', text: env.CONFIG
               }
           }
       }
       stage('read') {
           steps {
               script {
                   echo "READ FILE8888888888"
                   println con.readConfig('solution.yaml')
               }
           }
       }
        stage('Stage: Testing grace exit'){
            steps { 
                script {
                    echo "Stage: Testing grace exit"
                    println "wksp=${env.WORKSPACE}"
                    utils.graceExit()
                    echo "End: Testing grace exit*********"
                }
            }
        }
        
        stage('Stage: Run Ansible Playbook'){
            steps { 
                script {
                    echo "Stage: Run Ansible Playbook..."
                    echo "Input Parameters: ${params}"
                    def repo='upload-test'
                    def cmd='pwd'
                    def op = sh (script: cmd , returnStdout: true, returnStatus:true)
                    println op
                    def std=gitUtils.exeCmd(cmd)
                    println "outputstd=$std"
                   
                    def ws=env.WORKSPACE
                    def directory="$ws/$repo"
                    def workbr='feature-test'
                    def mergebr='main'
                    def workspace='wave-cloud'
                    def src="$ws/CI.yml"
                    def bbapppass='9f2d1708-aeee-449d-b133-7f094a262336'
                    def bbapitoken='7881845f-cb99-407a-8a31-ead60535fcaa'
                    def fileName='CI.yml'
                    
                    sh """ echo  a  >> $fileName  """
                    println("1.  git clone..")
                    sh 'pwd; ls -al'
                    checkout([
                        $class: 'GitSCM', 
                        branches: [[name: '*/main']], 
                        doGenerateSubmoduleConfigurations: false, 
                        extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: repo ]], 
                        submoduleCfg: [], 
                        userRemoteConfigs: [[credentialsId: bbapppass, url: 'https://fredzhang123@bitbucket.org/wave-cloud/upload-test.git']]
                    ])
                   
                    sh 'pwd; ls -al'
                    withCredentials([usernamePassword(credentialsId: bbapppass, \
                           usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                            
                            def dest="$directory/CI.yml"
                            def project='GRP'
                            def repoPR="https://api.bitbucket.org/2.0/repositories/$workspace/$repo/pullrequests"

                            println("2.   git config..")
                            
                            println "3.   uploadFile().."
                            def out=gitUtils.uploadFile(fileName, workbr,mergebr,workspace,repo)
                            println out

                            println "4.   getPrid createPR createPR()..."

                            sh """
                                pwd
                                ls -al vars/
                                chmod a+x vars/createMerge.sh
                                #./vars/createMerge.sh
                            """
                            println gitUtils.getPrid(repoPR)
                            out=gitUtils.createPR(workbr, mergebr, workspace, repo)
                            println out

                            println "5.   mergePR ()..."
                            println gitUtils.mergePR(repoPR)
                         }
                }
            }
            
        }
        
    }
}
