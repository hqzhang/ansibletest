// vars/sayHello.groovy
def call(String name = 'human') {
    // Any valid steps can be called from this code, just like in other
    // Scripted Pipeline
    echo "Hello, ${name}."
}

def getFilename(String myfiles){
   return myfiles.split(',').collect{ it.trim()}.collect{ it.split('/').last() }
}

def getFiles(String myfiles, String mypath){
    return myfiles.split(',').collect{  it.trim()}.collect{ mypath+it.split('/').last() }
}

return this
