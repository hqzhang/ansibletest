def readConfig(String wksp){
     echo "READ FILE8888888888"
     def data = readFile(file: 'solution.yaml')
     println(data)
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
