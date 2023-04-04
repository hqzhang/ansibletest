def curlConfig(String fileName){
     def cmd = "curl https://raw.githubusercontent.com/hqzhang/ansibletest/main/$fileName"
     return cmd.execute().text
}
def readConfig(String fileName){
    def data=readFile file: fileName
    return data
}

def writeConfig(String data){
   def date = new Date()
   data = data + date
   writeFile file: 'solution_out.yaml', text: data
}

def getCurrent(){

   def cmd='pwd'
   return cmd.execute().text
}

return this
