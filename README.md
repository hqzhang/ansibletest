#Ansible Tutorial
```
under ansible directory
config file: ansible.cfg define log path and inventory path
hosts file: inventory is redirect to hosts
run playbook file: myscript.yml

ansible-playbook myscripts.yml
```
that copy bash file:test.sh to remote
and run test.sh on remote
and print(debug) output.
```
PLAY [Shell Examples] *****************************************************************************************************************
TASK [Gathering Facts] ****************************************************************************************************************
ok: [192.168.2.38]
TASK [include_vars] *******************************************************************************************************************
ok: [192.168.2.38]
TASK [Transfer the script] ************************************************************************************************************
ok: [192.168.2.38]
ASK [debug] **************************************************************************************************************************

TASK [Execute the script] *************************************************************************************************************
changed: [192.168.2.38]
TASK [debug] **************************************************************************************************************************
ok: [192.168.2.38] => {
    "listfile.stdout_lines": [
        "Hello Hongqi!!!!!",
        "total 0",
        "drwxr-xr-x.  2 root root   6 Jan 11 15:30 .",
        "drwxrwxrwt. 10 root root 256 Jan 11 17:23 .."
    ]
}
PLAY RECAP ****************************************************************************************************************************
192.168.2.38               : ok=6    changed=1    unreachable=0    failed=0    skipped=0    rescued=0    ignored=0   
```




