// vars/sayHello.groovy
def call(String name = 'human') {
    // Any valid steps can be called from this code, just like in other
    // Scripted Pipeline
    echo "Hello, ${name}."
}
def xxx(String myfiles){
   return myfiles.split(',').collect{ it.trim()}.collect{ it.split('/').last() }
}
return this
