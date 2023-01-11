#Ansible Tutorial

git clone https://github.com/hqzhang/ansisble.git

goto ansible directory

config file: ansible.cfg define log path and inventory path
```
[defaults]
inventory = ./hosts
host_key_checking = false
log_path=./ansible.log
```
hosts file: inventory is redirect to hosts
```
[web]
192.168.2.38
```
playbook file: runscript.yml
```
---
  - name: Shell Examples
    hosts: remoteservers
    tasks:
      - include_vars: vars/values.yml
      - name: Transfer the script
        register: copyfile
        copy: src=test.sh dest=/root mode=0777
      - debug:
          var: copyfile.stdout_lines
      - name: Execute the script
        register: listfile
        command: sh /root/test.sh {{ myvar }}
      - debug:
          var: listfile.stdout_lines
```
that copy bash file:test.sh to remote
and run test.sh on remote
and print(debug) output.

run playbook
```
ansible-playbook myscripts.yml
```
output
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




