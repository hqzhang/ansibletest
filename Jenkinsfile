@Library('my-shared-lib') _
properties([
   pipelineTriggers([githubPush()]),
   parameters([
              string(name: 'MY_VAR', defaultValue: 'my_var_params', description: 'input ansble config '),
              string(name: 'myFiles', defaultValue: '/tmp/file1,     /tmp/file2', description: 'input ansble config '),
              string(name: 'myPath', defaultValue: './ansible/', description: 'input ansble config '),
              string(name: 'Config', defaultValue: 'ansible.cfg', description: 'input ansble config '),
              string(name: 'Inventory', defaultValue: 'hosts', description: 'input inventory file'),
              string(name: 'Playbook', defaultValue: 'runscript.yml', description: 'input ansible playbook'),
              string(name: 'Tool_install', defaultValue: 'ansible', description: 'input tools ansible'),
    ])
])

pipeline {
    agent any
    
    options { timestamps () }
    environment { 
        //Define Gobal Variables can be used name, env.name or params.name
        MY_VAR='my-var-env'
        PATH="/usr/local/bin:$PATH"
        myenv='Helloworld emily!'
        private_key='afb3704a-da55-4576-9fb9-9a6265319f2b'
        //myFiles='/tmp/file1,     /tmp/file2'
        //myPath='./ansible/'
        myFilesOrig=utls.getMyFiles(env.myFiles)
        myFilenames=utils.getFileNames(env.myFiles)
        myFilesfull=utils.getNewFiles(env.myFiles, env.myPath)
       
    }

    stages {
        stage('Stage: Run Ansible Playbook'){
            steps { 
                script {
                    echo "Stage: Run Ansible Playbook..."
                    echo "Input Parameters: ${params}"
                    echo "myFiles=$myFiles1"
                    echo "myFilenames=$myFilenames"
                    echo "myFilesfull=$myFilesfull"
                    echo "Global Environmet:"
  
                    sh ' printenv'

                    utils.getGlobals()
                    sh ' ./test.sh '

                    sh ' python3 test.py'
                    
                    echo  "myFilenames =$myFilenames"
                    echo  "myFilesfull =$myFilesfull"
                    //echo "mynames=$mynames"
                        //mypaths=myfiles.split(',').collect{ it.trim()}.collect{ mypath+it.split('/').last() }
                    //echo "mypaths=$mypaths"
                   /* dir('ansible'){
                        ansiblePlaybook credentialsId: 'private_key', 
                                    inventory: 'hosts', 
                                    playbook: 'runscript.yml',
                                    installation: 'ansible'
                    }*/
                    sh """
                        whoami
                        pwd
                        ls -al ~/.ssh
                        cd ansible
                        ansible-playbook runscript.yml
                    """
                }
            }
        }

    }
}
