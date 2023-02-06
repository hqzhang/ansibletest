
properties([
   pipelineTriggers([githubPush()]),
   parameters([
              password(name: 'PasswordSet', defaultValue: 'SECRET', description: 'Enter a password'),
              text(name: 'Mytextparam', 
                 defaultValue: 'Default lines for the parameter', 
                 description: 'A description of this param'),    
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
                    def password = input message: 'Please enter the password', 
                      parameters: [string(name: 'Mypass', defaultValue: '******', description: 'input pass')]
                    echo "pass=${env.Mypass}" 
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
