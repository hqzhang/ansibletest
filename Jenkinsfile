def version = 'main'
library("my-shared-lib@$version") _
println "wksp=${env.WORKSPACE}"
def readConfig(){
     def data = readFile(file: 'solution.yaml')
     return data
}
def writeConfig(){
   def date = new Date()
   def data = "Hello World\nSecond line\n" + date
   writeFile(file: 'solution_out.yaml', text: data)
}
properties([
   pipelineTriggers([githubPush()]),
   parameters([
             
              string(name: 'UpDirs', defaultValue: 'my_var_params', description: 'input ansble config '),
              string(name: 'MY_VAR_P', defaultValue: '/tmp/file1,     /tmp/file2', description: 'input ansble config '),
              string(name: 'myPath', defaultValue: './ansible/', description: 'input ansble config '),
              text(name: 'CONFIG', defaultValue: 'OK',description: 'input read file'),
              
    ])
])
//println readConfig()
println writeConfig()
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
                   con.writeConfig()
               }
           }
       }
       stage('read') {
           steps {
               script {
                   echo "READ FILE8888888888"
                   con.readConfig()
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
