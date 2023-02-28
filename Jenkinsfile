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
mylist=[ 'aa','bb']
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
                    //sh "rm -rf $repo; git clone https://hqzhang@bitbucket.org/hqzhang/myrepo.git"
                    //sh "ls -al test1"
                    def ws=env.WORKSPACE
                    def directory="$ws/$repo"
                    def workbr='test-pr'
                    def mergebr='master'
                    def workspace='wave-cloud'
                    def src="$ws/CI.yml"
                    def bbapppass='9f2d1708-aeee-449d-b133-7f094a262336'
                    
                    //gitUtils(src, workbr, mergebr, dir) 
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
                            //gitUtils.updateAll(src, workspace, repo, workbr, mergebr, directory) 
                            //println "enter gitFinal()"
                            def dest="$directory/CI.yml"
                            //def repoPR="https://api.bitbucket.org/2.0/repositories/$workspace/$repo/pullrequests"
                            
                            //println("1.  git clone..")
                            //def out=gitClone(workspace, repo, workbr, directory)
                            //println out

                            println("2.   git config..")
                            //out=getConfig(workspace, repo, workbr, directory)
                            //println out
                            sh """
                                 git remote set-url origin https://${USERNAME}:${PASSWORD}@bitbucket.org/${workspace}/${repo}.git 
                            """


                           /* println "3.   git push ..."
                            out=uploadFile(src, workbr)
                            println out

                            println "4.   git createPR ..."
                            //out=createPR(workbr, mergebr, workspace, repo)
                            println out//createPR(String workbr, String mergebr,String workspace, String repo){

                            println "5.   git mergePR ..."
                            out=mergePR(repoPR)
                            println out
                            */
                            
                         }
                }
            }
        }
         stage('Stage: anoter test'){
            steps { 
                script {
                    mylist.each { println it}
                }
            }
         }

    }
}
