import groovy.json.JsonSlurper
// vars/sayHello.groovy
def call(String name = 'human') {
    // Any valid steps can be called from this code, just like in other
    // Scripted Pipeline
    echo "Hello, ${name}."
}

def exeCmd(String cmd, String directory){
    def command = cmd.split()
    def processBuilder = new ProcessBuilder(command)
   
    processBuilder.directory(new File(directory))
    def process = processBuilder.start()
    process.waitFor()

    def reader = new BufferedReader(new InputStreamReader(process.getInputStream()))
    def line = null
    def output=''
    while ((line = reader.readLine()) != null) {
        output = output +line+ "\n"
    }
    
    return output
}

def gitCmd(String cmd, String directory){
    println directory
    def output=exeCmd(cmd,directory)
    return output
}

def shellCommand(String cmd){
    println "define command to nexus/github/bitbucket"
    def data=[ version: "$version"]
    def boday=JsonOutput.toJson(JsonOutput.toJson(data))
    def cmd1="""curl http://google.com --data $body"""

    println "execute and parse"
    def out=executeCmd(cmd1)
    def json=new JsonSlurper()
    def obj=json.parseText(out)
    println obj.values1
    return [out,err]
}

def gitStatus(String directory){
    def cmd="git status";
    def output=exeCmd(cmd,directory)
    return output
}

def gitAddall(String directory){
    def cmd="git add -u .";
    def output=exeCmd(cmd,directory)
    return output
}

def gitCheckout(String directory){
    println directory
    def cmd="git checkout"
    def output=exeCmd(cmd,directory)
    return output
}

def gitBranch(String directory){
    def cmd="git branch"
    def output=exeCmd(cmd,directory)
    return output
}

def gitPull(String directory){
    println directory
    def cmd="git pull"
    def output=exeCmd(cmd,directory)
    return output
}
def gitSync(String directory){
    println directory
    def cmd="git sync"
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
def getMyFiles(String myfiles){
    echo  "enter getMyFiles with111: $myfiles"
    def str=new String(myfiles)
    return str.split(',').collect{ it.trim() }
}

def getFileNames(String myfiles){
    echo  "enter getFileNames with222: $myfiles"
    def str=new String(myfiles)
    return str.split(',').collect{ it.trim().split('/').last() }
}


def getNewFiles(String myfiles, String mypath){
    echo "enter getNewFiles with333: $mypath"
    def str=new String(myfiles)
    def var=str.split(',').collect{ mypath + it.trim().split('/').last() }.join(' ')
    return "\"$var\""
}
/*def getMyFiles(String myfiles){
    echo  "enter getMyFiles with111: $myfiles"
    def str=new String(myfiles)
    List tmp=[]
    str.split(',').each {
      tmp.add(it.trim() )
    }
    return tmp
    //eturn myfiles
}

def getFileNames(String myfiles){
    echo  "enter getFileNames with222: $myfiles"
   def str=new String(myfiles)
    List tmp=[]
    str.split(',').each {
      tmp.add(it.trim() )
    }
  
    return tmp.collect{ it.split('/').last() }
    //return myfiles
}


def getNewFiles(String myfiles, String mypath){
    echo "enter getNewFiles with333: $mypath"
    def str=new String(myfiles)
    List tmp=[]
    str.split(',').each {
      tmp.add(it.trim() )
    }
    def var=tmp.collect{ mypath + it.split('/').last() }.join(' ')
    return "\"${var}\""
    //return myfiles
}*/
def getGlobals(){
         
    echo "Enter getGlobal variables:"
    echo "UpDirs=$UpDirs"
    echo "params_env=${env.Tool_install}"
    echo "params_direct=${Tool_install}"
   
    echo "MY_VAR_ENV=${MY_VAR_ENV}"
    echo "MY_VAR_P=${MY_VAR_P}"
    echo "myFilenames4=${env.myFilenames}"
    echo "myFilenames5=$myFilenames"
    echo "Tool_install6=$Tool_install"
}

def graceExit(){
    println( "I want to jump out and exit")
    error( "I want to jump out")
    return
}
return this
