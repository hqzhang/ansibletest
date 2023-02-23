// vars/sayHello.groovy
def call(String name = 'human') {
    // Any valid steps can be called from this code, just like in other
    // Scripted Pipeline
    echo "Hello, ${name}."
}
@NonCPS
def executeCmd(String cmd){
    def proc=cmd.execute()
    proc.waitFor()
    def out=proc.text
    def err=proc.exitValue()
    return [out,err]
}
@NonCPS
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
@NonCPS
def pwdCmd(){
    def command = "pwd"
    def proc = command.execute()
    proc.waitFor()
    println proc.text
    return proc.text
}
@NonCPS
def gitStatus(String repo){
    def cmd2="cd ${repo}; git status"
    def out=executeCmd(cmd2)
    println out
    return out
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

return this
