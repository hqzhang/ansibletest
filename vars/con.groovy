def readFile(String wksp){
   def str=new File("${wksp}/solution.yaml").text
   return str
}

def writeFile(){

   writeFile file: "output/usefulfile.txt", text: "This file is useful, need to archive it."
}
def getCurrent(){

   def cmd='pwd'
   return cmd.execute().text
}
