import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import groovy.json.JsonSlurper
import groovy.transform.Field
@groovy.transform.Field
def repoPR="https://bitbucket.org/rest/api/1.0/project/myproject/repos/myrepo/pull-requests"
@NonCPS
def call(String src,  String workbr, String mergebr, String dir) {
    echo "enter gitUpdate()"
    // Any valid steps can be called from this code, just like in other
    // Scripted Pipeline
   
    echo  "workbr=$workbr"
    echo  "mergebr=$mergebr"
    echo  "dir=$dir"
    echo  "src=$src"
    gitUpdate(src, workbr, mergebr, dir)
}
@NonCPS
def copyFile(String srcFile, String destFile){
    def sourcePath = Paths.get(srcFile)
    def destinationPath = Paths.get(destFile)
    Files.copy(sourcePath, destinationPath,StandardCopyOption.REPLACE_EXISTING)
}

@NonCPS
def exeCmd(String cmd, String directory){
    println cmd
    def command = cmd.split()
    def procBuilder = new ProcessBuilder(command)
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
def gitCmd(String cmd, String directory){
    println directory
    def out=exeCmd(cmd,directory)
    println out
    return out
}
@NonCPS
def gitStatus(String directory){
    def cmd="git status";
    def output=exeCmd(cmd,directory)
    return output
}
@NonCPS
def gitAddall(String directory){
    def cmd="git add -u .";
    def output=exeCmd(cmd,directory)
    return output
}
@NonCPS
def gitCheckout(String branch,String directory){
    println directory
    def cmd="git checkout $branch"
    def output=exeCmd(cmd,directory)
    return output
}
@NonCPS
def gitBranch(String directory){
    def cmd="git branch"
    def output=exeCmd(cmd,directory)
    return output
}
@NonCPS
def gitBranchrem(String directory){
    def cmd="git branch -r"
    def output=exeCmd(cmd,directory)
    return output
}
@NonCPS
def gitPullbr(String branch, String directory){
    println directory
    def cmd="git pull origin $branch"
    def output=exeCmd(cmd,directory)
    return output
}


@NonCPS
def gitCommit(String directory, String msg='update'){
    println directory
    def cmd = "git commit -m $msg"
    def output=exeCmd(cmd,directory)
    return output
}
@NonCPS
def gitPushf(String directory){
    println directory
    def cmd="git push -f"
    def output=exeCmd(cmd,directory)
    return output
}
@NonCPS
def gitUpdate(String src, String workbr, String mergebr, String dir){
    def dest="$dir/CI.yml"

    println "git status"
    gitStatus(dir)
    
    println "git branch -r"
    gitBranchrem( dir)

    println "git checkout $workbr"
    gitCheckout(workbr,dir )
   
    println"git branch"
    gitBranch(dir)
  
    println "git pull.."
    gitPullbr(mergebr,dir)

    println "git copy update.."
    copyFile(src, dest)

    println "git add .."
    gitAddall(dir)
    
    println "git commit.."
    gitCommit(dir)

    println "git push"
    gitPushf(dir)

}
/*project="hqzhang"
repo="ansibletest"
workbr="new-branch"
mergebr="master"*/
//def repoPR="https://bitbucket.org/rest/api/1.0/project/$project/repos/$repo/pull-requests"

@NonCPS
getPrid(){
    def cmd="curl -u $USERNAME:$PASSWORD -X GET ${repoPR}?state=OPEN "
    def output=exeCmd(cmd,directory)
    def json=new JsonSlurper()
    def obj=json.parseText(output)
    println obj.values[0].id
}
@NonCPS
getVersion(){
    def cmd="curl -u $USERNAME:$PASSWOR -X GET ${repoPR}?state=OPEN "
    def output=exeCmd(cmd,directory)
    def json=new JsonSlurper()
    def obj=json.parseText(output)
    return obj.values[0].version
}
@NonCPS
getMergestatus(){
    def cmd="curl -u $USERNAME:$PASSWOR -X GET ${repoPR}/$prid/merge"
    def output=exeCmd(cmd,directory)
    def json=new JsonSlurper()
    def obj=json.parseText(output)
    return obj.canMerge
}


createPR(){
    
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
        def cmd="""curl -u $USERNAME:$PASSWOR -X POST -H "Content-Type: applicatin/json" $repoPR --data $body"""
        def output=exeCmd(cmd,directory)
        def json=new JsonSlurper()
        def obj=json.parseText(output)
        return obj.id
}


mergePR(){
    def prid=getPrid()
    println prid
    def version=getVersion()
    println version
    def cmd="""curl -u $USERNAME:$PASSWOR -X POST -H "Content-Type: applicatin/json" $repoPR/$prid/merge?version=$version"""
   
    def output=exeCmd(cmd,directory)
    println output
}
