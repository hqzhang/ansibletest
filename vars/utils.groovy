// vars/sayHello.groovy
def call(String name = 'human') {
    // Any valid steps can be called from this code, just like in other
    // Scripted Pipeline
    echo "Hello, ${name}."
}

def getMyFiles(String myfiles){
    echo  "enter getMyFiles with111: $myfiles"
   
    //return myfiles.split(',').collect{ it.trim() }
    return "getMyFiles"
}

def getFileNames(String myfiles){
    echo  "enter getFileNames with222: $myfiles"
   
    //return myfiles.split(',').collect{ it.trim() }.collect{ it.split('/').last() }
    return "getFileNames"
}


def getNewFiles(String myfiles, String mypath){
    echo "enter getNewFiles with333: $mypath"
    def ret=myfiles.split(',').collect{ it.trim()}.collect{ mypath+it.split('/').last() }.join(' ')
    //return "\"$ret\""
    return "getNewFiles"

}
def getGlobals(){
    echo "Enter getGlobal variables:"
    echo "params1=$params"
    echo "params_env=${env.Tool_install}"
    echo "params_direct=${Tool_install}"
    echo "params_with=${params.MY_VAR}"
    echo "MY_VAR_ENV=${env.MY_VAR}"
    echo "MY_VAR=${MY_VAR}"
    echo "myFilenames4=${env.myFilenames}"
    echo "myFilenames5=$myFilenames"
    echo "Tool_install6=$Tool_install"
}

return this
