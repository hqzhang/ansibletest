//////

library("ansibletest-shared-lib@main") _

println menu.getFileList()
println menu.getFileContent('config')
println menu.getFileContent('solution')
properties([
    pipelineTriggers([githubPush()]),
    parameters([
            choice( name: 'SolutionDetail', description: '', choices: menu.getFileList() ),
           [$class: 'DynamicReferenceParameter', choiceType: 'ET_FORMATTED_HTML', name: 'services', omitValueField: false, 
           randomName: 'choice-parameter-138673186839723', referencedParameters: 'SolutionDetail', 
           script: [$class: 'GroovyScript', fallbackScript: [classpath: [], oldScript: '', sandbox: false, script: ''], script: [classpath: [], oldScript: '', sandbox: false, 
           script: '''
            def mf ="ls /var/root/.jenkins/workspace/workspace/ansibletest".execute().text
            def myls = mf.readLines().collect{ it.split()["\\."] }
            def map=[:]
            myls.each { map[it]="curl -k https://raw.githubusercontent.com/hqzhang/ansibletest/main/releases/${it}.xml".execute().text }
            return """<textarea name=\"value\"  value  class=\"setting-input  \" type=\"text\">${map[SolutionDetail]}</textarea> """
            ''']]],

             string(name: 'Backup', defaultValue: 'backupFile.xml', description: 'A file for record'),
])
])
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
                    echo "parsing yaml"
                    def read = readYaml text: env.services
                    sh """rm -f ${env.Backup}"""
                    writeYaml file: "releases/${env.Backup}", data: read
                    
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


