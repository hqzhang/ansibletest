//////
def getEnvList(){
    return ['DEV','BAT']
}

properties([
    pipelineTriggers([githubPush()]),
    parameters([
            choice( name: 'SolutionDetail', description: '', choices: getEnvList() ),
           
])])
def map
def list
pipeline {
    agent any
    stages {
        stage('Create List') {
            steps {
                script {
                    echo "STAGE: create List..."
                    echo "params=$params"
                    echo "ansible playbook"
                    sh """
                        cat main.yaml
                        cat hosts
                        /usr/local/bin/ansible-playbook main.yaml
                    """
                    
                    /*def targtServer=params.servers
                    echo "params=$params"
                    if ( targtServer.equals('ERROR') ) 
                    {
                        targtServer='s23'
                    }
                    
                    echo "targtServer=$targtServer"
                    workspace=WORKSPACE
                    println "WS=${env.WORKSPACE}"
                    println "WS=${WORKSPACE}"
                    println "pwd="+pwd()
                    println "workspace=$workspace"
                    // you may create your list here, lets say reading from a file after checkout
                    //list = ["Test-1", "Test-2", "Test-3", "Test-4", "Test-5"]
                    list = readXMLList("${workspace}/manifest_Lynx.xml")
                    echo "***************"
                    def jsonText = parseXML("${workspace}/manifest_Lynx.xml")
                    map = readJSON text: jsonText
                    echo "#################"
                     def remote = [:]
                    remote.name = 'test'
                    remote.host = '192.168.2.27'
                    remote.user = 'root'
                    remote.password = 'password'
                    remote.allowAnyHosts = true
    
                    sshCommand remote: remote, command: "ls -lrt"*/
    
                }
            }
        }
   }
}


