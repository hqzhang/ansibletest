
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovy.transform.Field

def copyFile(String srcFile, String destFile){
    def sourcePath = Paths.get(srcFile)
    def destinationPath = Paths.get(destFile)
    Files.copy(sourcePath, destinationPath,StandardCopyOption.REPLACE_EXISTING)
}
/*
def executeCmd(String cmd, String directory){
    ProcessBuilder procBuilder = new ProcessBuilder("bash", "-c", cmd);
    procBuilder.directory(new File(directory))
    procBuilder.redirectErrorStream(true);
    def proc = procBuilder.start()
    proc.waitFor()
    def err=proc.exitValue()
    def reader = new BufferedReader(new InputStreamReader(proc.getInputStream()))
   
    def line = null
    def output=''
    while ((line = reader.readLine()) != null) {
        output = output +line+ "\n"
    }
    
    println( "-----------------")
    println("exitValue: " + err)
    println( "-----------------")
    println output
    return output
}*/
/*
def exeCmd(String cmd){
    println cmd
    def proc=cmd.execute()
    proc.waitFor()
    def out=proc.in.text
    def err=proc.err.text
    def code=proc.exitValue()
    println ("out:$out")
    println("err:$err")
    println ("code=$code")
    return out
}*/
def exeCmd1(String cmd) {
    script {
        println "run cmd=$cmd"
        def stdout=sh (script: " $cmd && echo \"status:\$?\"  ", returnStdout: true).trim()
        print "originoutput=$stdout"
        stdout = stdout.split('\n')
        def status=stdout[-1]
        println "code=$status"
        stdout = stdout[0..-2].join('\n')
        print "onlyoutput=$stdout"
        return stdout
   }
}

def exeCmd(String cmd) {
    script {
        println "run cmd=$cmd"
        def stdout=sh (script: cmd, returnStdout: true).trim()
        print "originoutput=$stdout"
        /*stdout = stdout.split('\n')
        def status=stdout[-1]
        println "code=$status"
        stdout = stdout[0..-2].join('\n')
        print "onlyoutput=$stdout"8*/
        return stdout
   }
}

def getPrid(String repoPR){
    println("enter getPrid()")
    def cmd = "curl -u $USERNAME:$PASSWORD -X GET -H 'Content-Type: application/json'  ${repoPR}  "
    ret=null         
    def output=exeCmd(cmd)
    def obj = readJSON text: output
    //def json=new JsonSlurper()
    //def obj=json.parseText(output)
    if (obj.size!=0){
        return obj.values[0].id
    }
    return ret
}

def getMergestatus(String repoPR, int prid){
    def cmd="curl -u $USERNAME:$PASSWORD -X GET ${repoPR}/$prid/merge"
    def output=exeCmd(cmd)
    //def json=new JsonSlurper()
    //def obj=json.parseText(output)
    def obj = readJSON text: output
    return obj.canMerge
}

def uploadFile1(String fileName,String workbr, String workspace, String repo){
    println "enter uploadFile()"
    def repoUrl="https://api.bitbucket.org/2.0/repositories/${workspace}/${repo}/src"
    def cmd = "curl -u ${USERNAME}:${PASSWORD} -X POST ${repoUrl}   \
              -F ${fileName}=@${fileName}  \
              -F message=updatecurl -F branch=${workbr}"
    println cmd
    def output=exeCmd(cmd)
    return output
}

import java.text.SimpleDateFormat
def getDate(){
    def sdf = new SimpleDateFormat("yyyy-MM-dd")
    return sdf.format(new Date())
}

def createPR(String workbr, String mergebr,String workspace, String repo){
    println "enter createPR()"
    def repoPR="https://bitbucket.org/api/2.0/repositories/$workspace/$repo/pullrequests"
    def date=getDate()
    def data=[ 
        title: "Dependency Updates $date",
        description: "Automated Dependency Updates for  $date",
        source: [ branch:  [ name: "$workbr"  ],
                  repository: [ full_name: "$workspace/$repo" ] ],
        destination: [ branch:  [ name: "$mergebr"  ] ],
        close_source_branch: true ]
    
    def body=JsonOutput.toJson(data)
    def cmd="""curl -u $USERNAME:$PASSWORD -X POST -H "Content-Type: application/json" \
              ${repoPR}  --data '$body' """
       
    def output=exeCmd(cmd)
    println output
    //def json=new JsonSlurper()
    //def obj=json.parseText(output)
    def obj = readJSON text: output
    println( "create=${obj.id}")
    return obj.id
}

def mergePR(String repoPR){
    println ("enter mergePR()")

    def prid=getPrid(repoPR)
    if (prid == null ){
        println "Create PR failure"
        System.exit(1)
    }
    println("prid=$prid")
    def data=[ type: 'anytype',
                message: 'good work',
                close_source_branch: true, 
                merge_strategy: 'merge_commit' ]
    def body=JsonOutput.toJson(JsonOutput.toJson(data)) 
    def cmd="""curl -u $USERNAME:$PASSWORD -X POST -H 'Content-Type: application/json' \
        ${repoPR}/$prid/merge --data $body""" 
   
    def output=exeCmd(cmd)
    def obj = readJSON text: output
    //def json=new JsonSlurper()
    //def obj=json.parseText(output)
    println("prid=${obj.id}")
    return obj.id
}
    def workbr='workbr'
    def mergebr='master'
    def dir='/tmp/upload-test'
    def src='/tmp/CI.yml'
    def project="GRP"
    def workspace='wave-cloud'
    def repo="upload-test"
    def repoPR="https://api.bitbucket.org/2.0/repositories/$workspace/$repo/pullrequests"
    def fileName='CI.yml'

          
def uploadFile(String fileName,String workbr, String workspace, String repo){
    println "enter uploadFile()"
    def repoUrl="https://api.bitbucket.org/2.0/repositories/${workspace}/${repo}/src"
    def cmd = "curl -u ${USERNAME}:${PASSWORD} -X POST ${repoUrl}   \
              -F ${fileName}=@${fileName}  \
              -F message=updatecurl -F branch=${workbr}"
    println cmd
    def output=exeCmd(cmd)
    return output
}

println updateAll(src, workspace, repo, workbr, mergebr, directory)
