
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovy.transform.Field
@NonCPS
def copyFile(String srcFile, String destFile){
    def sourcePath = Paths.get(srcFile)
    def destinationPath = Paths.get(destFile)
    Files.copy(sourcePath, destinationPath,StandardCopyOption.REPLACE_EXISTING)
}
@NonCPS
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
}
@NonCPS
def exeCmd(String cmd){
    
    def proc=cmd.execute()
    //def b = new StringBuffer()
    //proc.consumeProcessErrorStream(b)
    proc.waitFor()
    def out=proc.in.text
    def err=proc.err.text
    def code=proc.exitValue()
    println ("out:$out")
    println("err:$err")
    println ("code=$code")
    return out
}
@NonCPS
def gitPrep(String workbr, String mergebr, String directory){
    
    def command = "git branch -r ;git checkout $workbr; git branch; git pull origin $mergebr"
    def output = executeCmd(command, directory)
    
    return output
}
@NonCPS
def uploadFile(String fileName,String workbr, String workspace, String repo){
    println "enter uploadFile()"
    def cmd = "curl -u ${USERNAME}:${PASSWORD} \
              -X POST https://api.bitbucket.org/2.0/repositories/${workspace}/${repo}/src\
              -F ${fileName}=@${fileName}  \
              -F message=updatecurl -F branch=${workbr}"
    println cmd
    def output=exeCmd(cmd)
    return output
}
@NonCPS
def gitClone(String workspace, String repo, String workbr, String directory){
    println "enter gitClone()"
    //def dest="$directory/CI.yml"
    def cmd="""rm -rf upload-test/*; git clone https://${USERNAME}:$PASSWORD@bitbucket.org/$workspace/${repo}.git -b ${workbr} ."""
    println cmd
    return  executeCmd(cmd, directory)
}
@NonCPS
def getConfig(String workspace, String repo, String workbr, String directory){
    println "enter getConfig()"
    def cmd="""git remote set-url origin https://${USERNAME}:$PASSWORD@bitbucket.org/$workspace/${repo}.git """
    println cmd
    return  executeCmd(cmd, directory)
}
@NonCPS
def updateAll(String src, String workspace, String repo, String workbr, String mergebr, String directory){
    println "enter gitFinal()"
    def dest="$directory/CI.yml"
    def repoPR="https://api.bitbucket.org/2.0/repositories/$workspace/$repo/pullrequests"
    
    println("1.  git clone..")
    def out=gitClone(workspace, repo, workbr, directory)
    println out

    println("2.   git config..")
    out=getConfig(workspace, repo, workbr, directory)
    println out

    println "3.   git push ..."
    out=uploadFile(src, workbr)
    println out

    println "4.   git createPR ..."
    //out=createPR(workbr, mergebr, workspace, repo)
    println out//createPR(String workbr, String mergebr,String workspace, String repo){

     println "5.   git mergePR ..."
    out=mergePR(repoPR)
    println out

    return out
}
@NonCPS
def getPrid(String repoPR){
    println("enter getPrid()")
    def repo='upload-test'           
    def workspace='wave-cloud'
    repoPR="https://api.bitbucket.org/2.0/repositories/$workspace/$repo/pullrequests"
    def cmd = "curl -u ${USERNAME}:${PASSWORD} -X GET ${repoPR}?state=OPEN "
              
    def output=exeCmd(cmd)
    println output
    println output.getClass()
    
    def json=new JsonSlurper()
    def obj=json.parseText(output)
    return obj.values[0].id
}
@NonCPS
def getVersion(String repoPR){
    def cmd="curl -u $USERNAME:$PASSWORD -X GET ${repoPR}?state=OPEN "
    def output=exeCmd(cmd)
    def json=new JsonSlurper()
    println output
    def obj=json.parseText(output)
    return obj.valuses[0].version
}
@NonCPS
def getMergestatus(String repoPR, int prid){
    def cmd="curl -u $USERNAME:$PASSWORD -X GET ${repoPR}/$prid/merge"
    def output=exeCmd(cmd)
    def json=new JsonSlurper()
    def obj=json.parseText(output)
    return obj.canMerge
}
@NonCPS
def createPR(String workbr, String mergebr,String workspace, String repo){
    //def repoPR="https://api.bitbucket.org/2.0/repositories/$workspace/$repo/pullrequests"
    def repoPR="https://bitbucket.org/rest/api/1.0/project/$project/repos/$repo/pull-requests"
    def data=[ 
       title: 'PR-testing',
       description: null,
       state:  'OPEN', open: true,
       closed: false,
       fromRef: [ id:  "refs/heads/$workbr",
                  repository: [ slug: "$repo", name: null,
                                  project: [ key: "$project" ]
                                ]
                ],
        toRef: [ id: "refs/heads/$mergebr",
                 repository: [ slug: "$repo",
                               name: null,
                               project: [key: "$project"]
                   ]
                ],
        locked: false,
        reviewers: [] ]
        def body=JsonOutput.toJson(JsonOutput.toJson(data))
        def cmd="""curl -u $USERNAME:$PASSWORD -X POST -H "Content-Type: applicatin/json" $repoPR --data $body"""
        def output=exeCmd(cmd)
        def json=new JsonSlurper()
        def obj=json.parseText(output)
        return obj.id
}

@NonCPS
def mergePR(String repoPR){
    println ("enter mergePR()")
    def prid=getPrid(repoPR)
    println("prid=$prid")
    def version=getVersion(repoPR)
    println ("version=$version")
    def cmd="""curl -u $USERNAME:$PASSWORD -X POST -H "Content-Type: applicatin/json" $repoPR/$prid/merge?version=$version"""
    def output=exeCmd(cmd)
    println output
    def json=new JsonSlurper()
    //def obj=json.parseText(output)
    //return obj.id
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

println updateAll(src, workspace, repo, workbr, mergebr, directory)
