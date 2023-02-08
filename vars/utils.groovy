// vars/sayHello.groovy
def call(String name = 'human') {
    // Any valid steps can be called from this code, just like in other
    // Scripted Pipeline
    echo "Hello, ${name}."
}

def getFilenames(String myfiles){
    echo  "enter getFilenames with: $myfiles"
   
    return myfiles.split(',').collect{ it.trim()}.collect{ it.split('/').last() }
}

def getFiles(String myfiles, String mypath){
    echo "enter getFiles with000: $mypath"
    def var1=myfiles.split(',').collect{ it.trim()}
    echo  "enter getFilenames with111: $var1"
    def var2=var1.collect{ mypath+it.split('/').last() }
    echo  "enter getFilenames with222: $var2"
    return var2
}

def getGlobals(){
    echo "params=$params"
    echo "Inventory=${env.Inventory}"
    echo "myFilesfull=${env.myFilesfull}"
    echo "myFilenames=${env.myFilenames}"
    echo "myFilenames=$myFilenames"
}

return this
