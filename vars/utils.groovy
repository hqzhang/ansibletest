// vars/sayHello.groovy
def call(String name = 'human') {
    // Any valid steps can be called from this code, just like in other
    // Scripted Pipeline
    echo "Hello, ${name}."
}

def getMyFiles(String myfiles){
    echo  "enter getMyFiles with111: $myfiles"
    def str='/tmp/file1, /tmp/file2'
    List tmp=[]
    str.split(',').each {
      tmp.add(it.trim() )
    }
    //return tmp
    return myfiles
}

def getFileNames(String myfiles){
    echo  "enter getFileNames with222: $myfiles"
    def str='/tmp/file1, /tmp/file2'
    List tmp=[]
    getMyFiles(str).each {
      tmp.add(it.split('/').last()  )
    }
    //return tmp
    return myfiles
}


def getNewFiles(String myfiles, String mypath){
    echo "enter getNewFiles with333: $mypath"
    def str='/tmp/file1, /tmp/file2'
    List tmp=[]
    //getMyFiles(str).each {
     // tmp.add( mypath + it.split('/').last() )
    //}
    //return """+tmp.join(' ')+"""
    return myfiles
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
