import groovy.yaml.YamlSlurper
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.DumperOptions

String buildScript(List values){
    def ret=values.collect { '"'+it+'"' }
    return "return ${ret}"
}

String convertScript( String str){
    def ret='"""'+str.replaceAll('"', '\\\\"')+'"""'
    return  "return $ret"
}

def getFileContent(String SolutionDetail){
    def mf ="ls /var/root/.jenkins/workspace/workspace/ansibletest/releases  ".execute().text
    def out=mf.readLines().collect{ it.split("\\.")[0]}
    def map=[:]
    out.each { 
        map[it]="curl -k https://raw.githubusercontent.com/hqzhang/ansibletest/main/releases/${it}.xml".execute().text 
        map[it]="cat /var/root/.jenkins/workspace/workspace/ansibletest/releases/${it}.xml"
    }
    def ret= "<textarea name=\"value\"  value  class=\"setting-input  \" type=\"text\">${map[SolutionDetail]}</textarea>"
    convertScript(ret)
}
println getFileContent('dev')

System.exit(1)

def getFileList(){
    def test=''
    def mf ="ls /var/root/.jenkins/workspace/workspace/ansibletest/releases ".execute().text
    def out=mf.readLines().collect{ it.split("\\.")[0]}
    return out
}
