def getFile(String wksp){
   def str=new File("${wksp}/solution.yaml").text
   return str
}
