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
                    def command='ls -al xyz'

                    def output=sh (script: "set +x ; $command && echo \"status:\$?\" || echo \"status:\$?\" ; exit 0", returnStdout: true).trim()
                    println "output=$output"
                    def lines = output.split('\n')
                    def status=lines.last()
                    print "status=$status"
                    lines = lines[0..-2] 
                    def stdout = lines.join('\n')
                    println "stdout=$stdout"
                   
                    sh 'exit 0'
                    def ws=env.WORKSPACE
                    def directory="$ws/$repo"
                    def workbr='feature-test'
                    def mergebr='main'
                    def workspace='wave-cloud'
                    def src="$ws/CI.yml"
                    def bbapppass='9f2d1708-aeee-449d-b133-7f094a262336'
                    def bbapitoken='7881845f-cb99-407a-8a31-ead60535fcaa'
                    def fileName='README.md'
                    
                    sh """ echo  a  >> $src  """
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

                            //def repoPR="https://api.bitbucket.org/2.0/repositories/$workspace/$repo/pullrequests"
                            println("2.   git config..")
                            sh """
                                 git remote set-url origin https://${USERNAME}:${PASSWORD}@bitbucket.org/${workspace}/${repo}.git 
                            """
                            println "3.   uploadFile().."
                            def out=gitUtils.uploadFile(src, workbr,workspace,repo)
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
