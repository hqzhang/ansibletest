def readConfig(){
     def data = readFile(file: 'solution.yaml')
     return data
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
