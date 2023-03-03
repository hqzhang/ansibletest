set -e
repoUrl='https://bitbucket.org/api/2.0/repositories/wave-cloud/upload-test'
prid=''
update-file(){
    echo "update File"
    res=`curl -u $USERNAME:$PASSWORD -X POST ${repoUrl}/src    \
        -F uploadFile=@uploadFile         \
        -F message=updatecurl -F branch=test-pr `
    echo "uploadFile =$res"
}
create-pr(){
    res=`curl -u $USERNAME:$PASSWORD -X POST -H 'Content-Type: application/json' \
        ${repoUrl}/pullrequests \
        -d '{ "title": "Dependency Updates 2023-03-02", \
            "description": "Automated Dependency Updates for 2023-03-02", \
            "source": { "branch": { "name": "test-pr" }, \
            "repository": { "full_name": "wave-cloud/upload-test" } }, \
            "destination": { "branch": { "name": "main" } }, \
            "close_source_branch": true }' | jq -r '.id' `
    echo "$res"
}
get-prid(){
    res=`curl -u $USERNAME:$PASSWORD -X GET -H 'Content-Type: application/json' \
        ${repoUrl}/pullrequests  | jq -r '.values[].id' `
    echo "$res"
}
merge-pr(){
    prid=`get-prid`
    res=`curl -u $USERNAME:$PASSWORD -X POST -H 'Content-Type: application/json' \
        ${repoUrl}/pullrequests/$prid/merge \
        --data '{ "type": "anytype", "message": "good work", "close_source_branch": true, "merge_strategy": "merge_commit" }' | jq -r '.id' ` 
    echo $res
}

update-file
 
prid=`create-pr`
echo "create prid=$prid"

prid=`merge-pr`
echo "merge prid=$prid"

