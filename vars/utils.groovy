// vars/sayHello.groovy
def call(String name = 'human') {
    // Any valid steps can be called from this code, just like in other
    // Scripted Pipeline
    echo "Hello, ${name}."
}
def getMyFiles(String myfiles){
    echo  "enter getMyFiles with111: $myfiles"
    def str=new String(myfiles)
    List tmp=[]
    str.split(',').each {
      tmp.add(it.trim() )
    }
    return str.split(',').collect{ it.trim() }
    //eturn myfiles
}

def getFileNames(String myfiles){
    echo  "enter getFileNames with222: $myfiles"
   def str=new String(myfiles)
    List tmp=[]
    str.split(',').each {
      tmp.add(it.trim() )
    }
    return str.split(',').collect{ it.trim().split('/').last() }
    //return myfiles
}


def getNewFiles(String myfiles, String mypath){
    echo "enter getNewFiles with333: $mypath"
    def str=new String(myfiles)
    List tmp=[]
    str.split(',').each {
      tmp.add(it.trim() )
    }
  
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
