@Library('my-shared-lib') _
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
                    def repo='myrepo'
                    //sh "rm -rf $repo; git clone https://hqzhang@bitbucket.org/hqzhang/myrepo.git"
                    //sh "ls -al test1"
                    def ws=env.WORKSPACE
                    def dir="$ws/$repo"
                    def workbr='test-pr'
                    def mergebr='master'
                 
                    def src="$ws/CI.yml"
                    def credid='19935bd0-e469-48b6-b5f3-5865a12607d2'
                    
                    sh """ echo  a  >> $src  """
                    withCredentials([usernamePassword(credentialsId: credid, \
                            usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                            sh """rm -rf $repo; git clone https://${USERNAME}:${PASSWORD}@bitbucket.org/hqzhang/myrepo.git -b $workbr
                                               
                                  git remote set-url origin https://${USERNAME}:${PASSWORD}@bitbucket.org/hqzhang/myrepo.git 
                            """
                            echo """git remote set-url origin https://${USERNAME}:${PASSWORD}@bitbucket.org/hqzhang/myrepo.git """
                            gitUtils(src, workbr, mergebr, dir) 

                            //println("output:$output")
                            
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
