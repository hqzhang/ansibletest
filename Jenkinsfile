
properties([
   pipelineTriggers([githubPush()]),
   parameters([
           choice(name: 'choice1', choices: ['dev','qa','prod'], description: 'input cluster'),
           choice(name: 'ocversion', choices: ['oc-3.9.0','oc-3.10.0'], description: 'input oc version'),

					 ])
		])
pipeline {
    agent any
    
    options { timestamps () }
    environment { 
       //define global variable
       PATH="/Users/hongqizhang/Library/Python/3.9/bin:$PATH"
       myvar='helloworld'
    }

    stages {
        stage('Stage: Run Ansible Playbook'){
            steps { 
                script {
                    echo "Stage: Initial and Clean..."
                    echo "Input Parameters: ${params}"
                    sh """
                        pip3 install ansible
                        cd ansible
                        ansible-playbook runscript.sh
                    """
									}
								}
							}

			}
		}
