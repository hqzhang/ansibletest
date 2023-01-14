
properties([
   pipelineTriggers([githubPush()]),
   parameters([
              string(name: 'config', default: 'ansible.cfg', description: 'input ansble config '),
              string(name: 'inventory', default: 'hosts', description: 'input inventory file'),
              string(name: 'playbook', default: 'runscript.yml', description: 'input ansible playbook'),
              string(name: 'tool install', default: 'ansible', description: 'input tools ansible'),
    ])
])
pipeline {
    agent any
    
    options { timestamps () }
    environment { 
       //define global variable
       PATH="/usr/local/bin:$PATH"
       myenv='Helloworld emily!'
       private_key='afb3704a-da55-4576-9fb9-9a6265319f2b'
    }

    stages {
        stage('Stage: Run Ansible Playbook'){
            steps { 
                script {
                    echo "Stage: Run Ansible Playbook..."
                    echo "Input Parameters: ${params}"
                   
                    dir('ansible'){
                        ansiblePlaybook credentialsId: 'private_key', 
                                    inventory: 'hosts', 
                                    playbook: 'runscript.yml',
                                    installation: 'ansible'
                    }
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
