def version = 'main'
library("my-shared-lib@$version") _
properties([
   pipelineTriggers([githubPush()]),
   parameters([
              string(name: 'UpDirs', defaultValue: 'my_var_params', description: 'input ansble config '),
              string(name: 'MY_VAR_P', defaultValue: '/tmp/file1,     /tmp/file2', description: 'input ansble config '),
              string(name: 'myPath', defaultValue: './ansible/', description: 'input ansble config '),
              
    ])
])

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
    
    }

    stages {
        stage('Stage: Run Ansible Playbook'){
            steps { 
                script {
                    echo "Stage: Run Ansible Playbook..."
                    echo "Input Parameters: ${params}"
                    def repo='upload-test'
                  
                    def ws=env.WORKSPACE
                    def directory="$ws/$repo"
                    def workbr='test-pr'
                    def mergebr='master'
                    def workspace='wave-cloud'
                    def src="$ws/CI.yml"
                    def bbapppass='9f2d1708-aeee-449d-b133-7f094a262336'
                    def bbapitoken='7881845f-cb99-407a-8a31-ead60535fcaa'
                    def fileName='README.md'
                    
                    sh """ echo  a  >> $src  """
                    println("1.  git clone..")
                    checkout([
                        $class: 'GitSCM', 
                        branches: [[name: '*/main']], 
                        doGenerateSubmoduleConfigurations: false, 
                        extensions: [[$class: 'CleanCheckout']], 
                        submoduleCfg: [], 
                        userRemoteConfigs: [[credentialsId: bbapppass, url: 'https://fredzhang123@bitbucket.org/wave-cloud/upload-test.git']]
                   ])
                    withCredentials([usernamePassword(credentialsId: bbapppass, \
                           usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                           
                            def dest="$directory/CI.yml"
                            def project='GRP'
                            def repoPR="https://bitbucket.org/rest/api/1.0/project/$project/repos/$repo/pull-requests"
                            //def repoPR="https://api.bitbucket.org/2.0/repositories/$workspace/$repo/pullrequests"
                            println("2.   git config..")
                            sh """
                                 git remote set-url origin https://${USERNAME}:${PASSWORD}@bitbucket.org/${workspace}/${repo}.git 
                            """
                            println "3.   git push ..."
                            def out=gitUtils.uploadFile(src, workbr,workspace,repo)
                        
                            println out

                            println "4.   git createPR ..."
                            out=gitUtils.createPR(workbr, mergebr, workspace, repo)
                            //println out//createPR(String workbr, String mergebr,String workspace, String repo){

                            //println "5.   git mergePR ..."
                            //out=gitUtils.mergePR(repoPR)
                            //println out
                            
                         }
                }
            }
        }
        
    }
}
