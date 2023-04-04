def readConfig(){
     def cmd = "curl https://raw.githubusercontent.com/hqzhang/ansibletest/main/solution.yaml"
     return cmd.execute().text
}
def writeConfig(){
   def date = new Date()
   def data = "Hello World\nSecond line\n" + date
   writeFile(file: 'solution_out.yaml', text: data)
}

def getCurrent(){

   def cmd='pwd'
   return cmd.execute().text
}

return this
