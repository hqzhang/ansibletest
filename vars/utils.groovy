

def getvar(String myfiles){
  return myfiles.split(',').collect{ it.trim()}.collect{ it.split('/').last() }

  }

def getenv(var){

   return var;
}
