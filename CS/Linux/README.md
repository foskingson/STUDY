# 리눅스
<br>
<br>

## 명령어
<br>

- sudo apt update : apt 명령어로 설치 가능한 소프트웨어 패키지 목록을 업데이트합니다. 그리고 각 소프트웨어 패키지 별로 어떤 버전을 설치할 수 있는지도 업데이트한다
<br>

- sudo apt upgrade : 리눅스에 설치된 소프트웨어 패키지의 버전을 업그레이드 힌다. 오래된 소프트웨어 패키지에 존재하는 취약점으로 인한 보안 문제를 방지할 수 있다.
<br>

- id : 현재 유저의 유저 ID와 해당 유저가 속해있는 그룹 ID를 출력한다.
<br>

- pwd : 현재 작업 중인 디렉토리의 경로를 출력한다.
<br>

- ls : 디렉토리의 내용을 출력한다.
<br>

- cd : 작업중인 디렉토리를 변경한다.
<br>

- mkdir : 디렉토리를 생성한다.
<br>

- touch : 비어 있는 새로운 파일을 만드는 데 사용한다.
<br>

- mv : 파일이나 디렉토리의 위치를 옮길때 사용한다.
<br>

- rm : 파일이나 디렉토리를 삭제한다.
<br>

- cat : 파일의 내용을 출력한다.
<br>

- file : 파일의 유형을 출력한다.
<br>

- cp : 파일이나 디렉토리를 복사한다.
<br>

- grep : 잔체에서 특정 문자열을 찾을 때 사용한다.
<br>

- man : 특정 명령어에 대한 매뉴얼을 보여준다.
<br>

- curl : client URL의 줄임말로 서버에 데이터를 보내거나 서버로부터 데이터를 받는 데이터 전송 명령어이다.
<br>

### 리다이렉션
> 모니터에 나타나는 표준 출력 혹은 키보드로 입력하는 표준 입력을 다른 곳으로 변경하는 작업이다. 주로 어떤 명령어의 결과를 파일로 저장하거나, 다른 명령어의 입력으로 전달하는 형태로 리다이렉션한다.

<br>

- 명령어 > 파일 : 명령어 표준 출력을 파일로 변경합니다. 파일이 없으면 새로 만들고, 있으면 덮어씁니다. 다음은 ls test[0-9] 명령어 결과를 world 파일에 쓴다.
``` linux
user@user-VirtualBox:~/new_dir$ ls test[0-9]
test1  test2  test3
user@user-VirtualBox:~/new_dir$ ls test[0-9] > world
user@user-VirtualBox:~/new_dir$ cat world
test1
test2
test3
```
<br>

- 명령어 >> 파일 : 명령어 표준 출력을 파일로 변경합니다. 파일이 없으면 새로 만들고, 있으면 이어서 쓴다.
``` linux
user@user-VirtualBox:~/new_dir$ cat hello
hello
user@user-VirtualBox:~/new_dir$ cat hello >> world
user@user-VirtualBox:~/new_dir$ cat world
test1
test2
test3
hello
```
<br>

- 명령어 < 파일 : 명령어 표준 입력을 파일로 변경합니다. 파일로부터 표준 입력을 받아 명령어를 수행한다.
``` linux
user@user-VirtualBox:~/new_dir$ cat world
test1
test2
test3
hello
user@user-VirtualBox:~/new_dir$ grep test < world
test1
test2
test3
```
<br>

- 파이프(pipe) : 리다이렉션의 한 형태로, 명령어 결과 표준 출력을 다른 명령어의 표준 입력으로 보낼 때 사용한다. | 문자로 나타낸다.
``` linux
user@user-VirtualBox:~/new_dir$ ls -l
total 8
-rw-rw-r-- 1 user user 13 12월  2 13:05 hello
-rw-rw-r-- 1 user user  0 12월  2 13:10 test1
-rw-rw-r-- 1 user user  0 12월  2 13:10 test2
-rw-rw-r-- 1 user user  0 12월  2 13:10 test3
-rw-rw-r-- 1 user user 13 12월  2 13:08 world
user@user-VirtualBox:~/new_dir$ ls -l | grep hello
-rw-rw-r-- 1 user user 13 12월  2 13:05 hello
```

<br>
<br>
<br>

## 권한

### 유저/그룹
> 리눅스의 각 유저는 이름과 고유한 사용자 ID(UID) 를 가지고 있다. 그룹은 말 그대로 여러 유저가 속할 수 있는 그룹으로, 이 역시 그룹 이름과 고유한 그룹 ID(GID) 를 가지고 있다. 파일이나 디렉토리와 같은 시스템 자원에 유저가 접근하면 유저의 UID와 해당 유저가 속한 그룹의 GID를 확인하여 권한을 가지고 있는지 보고 접근을 제어한다. /etc/passwd 는 리눅스의 유저 정보를 담고 있는 텍스트 파일로 각 사용자의 이름, 사용자 ID, 속해있는 그룹 ID 등의 정보가 나와있다. /etc/group 은 리눅스의 그룹 정보를 저장하는 텍스트 파일입니다. 각 그룹의 이름, 그룹 ID, 그룹에 속한 유저 목록 등의 정보를 포함한다. 

<br>

### 파일 및 디렉토리 권한
> 리눅스는 사용자가 파일과 디렉토리에 접근하는 행위에 대해 권한으로 제어한다. 각 파일과 디렉토리는 소유자/소유 그룹/다른 사용자를 가지고 있다. 소유자는 파일 또는 디렉토리 권한을 수정할 수 있고 이를 통해 소유자/소유 그룹/다른 사용자의 파일 또는 디렉토리에 접근 권한을 설정 할 수 있다.

<br>

- 읽기 : 파일 또는 디렉토리의 내용을 볼 수 있게 허용한다. r로 표시된다.
- 쓰기 : 파일 또는 디렉토리의 내용을 수정하거나 삭제하는 것을 허용한다. w로 표시된다.
- 실행 : 파일이 프로그램이 경우 실행할 수 있게 허용하고 디렉토리의 경우 디렉토리에 접근하는 것을 허용한다. x로 표시된다.
> 아래와 같은 상황에서 study는 맨 앞의 권한 플래그를 확인했을 때 d를 통해 디렉토리인걸 알 수 있고 이후로 3개씩 각각 사용자/소유 그룹/다른 사용자의 권한을 확인하면 소유자와 소유 그룹은 모든 권한을 가지고 있고 다른 사용자는 읽기와 실행 권한만 가지고 있다. 또 뒤에 나오는 user user를 통해 소유자는 user이고 소유 그룹 또한 user임을 알 수 있다.
``` linux
user@user-VirtualBox:~/바탕화면$ ls -l
합계 4
drwxrwxr-x 2 user user 4096  2월 17 17:59 study
```

<br>

### 권한 명령어
- chmod : 파일 권한을 변경하는 명령어로 root 혹은 소유자만 실행할 수 있다. chmod 권한 파일명 형식으로 사용한다.
    - 권한은 십진수로 표현해 줄 수 있다. 읽기권한은 4, 쓰기 권한은 2, 소유 권한은 1로 만약 모든 권한을 준다면 chmod 777 파일명 을 사용하면 된다.
    - 문자로도 줄 수 있다. 소유자 u, 그룹 g, 다른 사용자 o로 만약 소유자에게 읽기 권한을 추가하려면 chmod u+r 파일명 을 사용하면 된다.
``` linux
user@user-VirtualBox:~/바탕화면/study$ ls -la
합계 8
drwxrwxr-x 2 user user 4096  2월 17 20:42 .
drwxr-xr-x 3 user user 4096  2월 17 17:58 ..
-rwxr--r-- 1 user user    0  2월 17 20:42 test
user@user-VirtualBox:~/바탕화면/study$ chmod 764 test
user@user-VirtualBox:~/바탕화면/study$ ls -la
합계 8
drwxrwxr-x 2 user user 4096  2월 17 20:42 .
drwxr-xr-x 3 user user 4096  2월 17 17:58 ..
-rwxrw-r-- 1 user user    0  2월 17 20:42 test
user@user-VirtualBox:~/바탕화면/study$ chmod g-w test
user@user-VirtualBox:~/바탕화면/study$ ls -la
합계 8
drwxrwxr-x 2 user user 4096  2월 17 20:42 .
drwxr-xr-x 3 user user 4096  2월 17 17:58 ..
-rwxr--r-- 1 user user    0  2월 17 20:42 test
```
<br>

- chown : chown은 파일 소유자 혹은 소유 그룹을 변경하는 명령어로 root만 실행 할 수 있다. chown 사용자명[.그룹명] 파일명 형식으로 사용한다.  소유 그룹만 변경하고 싶은 경우 chgrp 명령어를 사용한다.
``` linux
user@user-VirtualBox:~/바탕화면/study$ ls -l
합계 0
-rwxr--r-- 1 user user 0  2월 17 20:42 test
user@user-VirtualBox:~/바탕화면/study$ sudo chown root test
[sudo] user 암호: 
user@user-VirtualBox:~/바탕화면/study$ ls -l
합계 0
-rwxr--r-- 1 root user 0  2월 17 20:42 test
```

<br>

### 특수 권한
- setuid : 일반 사용자가 파일을 실행하면 파일 소유자 권한으로 실행된다. 예를 들어, /bin/passwd 파일은 소유자가 root이지만 setuid가 설정되어 있어 일반 사용자가 root 권한으로 실행하고 비밀번호도 변경할 수 있다. setuid는 소유자의 실행 권한에 x 대신 s 문자로 나타낸다. 대문자 S로 표시되는 경우에는 setuid가 걸려 있으나, 실행 권한이 없는 경우이다.
<br>

- setgid : 일반 사용자가 파일을 실행하면 파일 소유 그룹 권한으로 실행된다. setgid는 소유 그룹의 실행 권한에 x 대신 s 문자로 나타낸다. 마찬가지로 실행 권한이 없으나 setgid가 걸려 있는 경우 대문자 S로 표시된다.
<br>

- sticky bit : 디렉토리에 sticky bit를 설정하면 파일 및 디렉토리 소유자와 root 사용자 외에 일반 사용자가 파일을 삭제할 수 없다. 주로 공용 디렉토리에 사용한다. 일반 사용자의 실행 권한에 x 대신 t 문자로 나타낸다. 이 역시 마찬가지로 실행 권한이 없는 경우에는 대문자 T로 표시힌디.


<br>
<br>
<br>

## 중요 디렉토리
<br>

- /bin : 일반 유저가 사용할 수 있는 기본적인 명령어나 프로그램을 담고 있는 디렉토리이다.
<br>

- /boot : 시스템 부팅에 필요한 파일들을 담고 있는 디렉토리이다.
<br>

- /dev : 리눅스에서는 컴퓨터에 부착된 물리적인 장치들을 디바이스 드라이버를 거쳐 파일 형태로 접근 가능하다. 그런 장치들을 나타내는 파일들을 담은 디렉토리이다.
<br>

- /etc : 운영체제나 운영체제 위에서 동작하는 서비스의 설정 파일들을 담고 있는 디렉토리이다.
<br>

- /home : 각 일반 유저의 홈 디렉토리를 담고 있는 디렉토리이다.
<br>

- /lib : 시스템에 필요한 라이브러리 파일들을 담은 디렉토리이다. /bin 이나 /sbin 에 존재하는 프로그램이 필요로 하는 동적 라이브러리 파일이 /lib 디렉토리에 존재한다.
<br>

- /opt : 소프트웨어 패키지들을 담는 디렉토리이다.
<br>

- /proc : 리눅스 커널 자원에 접근할 수 있는 파일과 프로세스를 나타내는 파일을 담고 있다.
<br>

- /root : 루트유저의 홈 디렉토리이다.
<br>

- /sbin : /bin 디렉토리와 마찬가지로 기본적인 유저 명령어나 프로그램을 가지고 있는 디렉토리이다. /sbin은 root 유저가 사용할 수 있는 명령어나 프로그램을 가지고 있다.
<br>

- /tmp : 유저나 프로그램이 임시로 파일을 생성해야할 때 사용할 수 있는 디렉토리이다. 오래된 파일은 삭제된다.
<br>

- /usr : 사용자 바이너리, 문서, 라이브러리, 헤더 파일 등을 담고 있는 디렉토리이다.
<br>

- /var : 프로그램이나 시스템이 실시간으로 가변적인 파일을 사용하고 저장해야 할 때 활용하는 디렉토리입니다. 예를 들어 /var/log에는 다양한 로그 파일이 저장된다.


<br>
<br>
<br>

## nc (netcat)
> netcat은 TCP/IP 프로토콜을 사용하여 네트워크 연결을 통해 데이터를 읽고 쓰는 다목적 명령줄 네트워킹 유틸리티이다. 보안을 공부하는 많은 실습의 경우 서버에서 특정 포트를 통해 서비스를 동작시키는 환경을 구성한다. 이런 경우에 클라이언트가 이 프로그램과 통신하기 위해서 사용하는 것이 nc(netcat)라는 도구이다. . 웹 서비스에 접속해서 푸는 형태의 워게임에서 많이 사용된다.

<br>

- nc 설치 `sudo apt update && sudo apt install netcat`
<br>

- nc 사용 옵션
``` linux
usage: nc [-46CDdFhklNnrStUuvZz] [-I length] [-i interval] [-M ttl]
	  [-m minttl] [-O length] [-P proxy_username] [-p source_port]
	  [-q seconds] [-s sourceaddr] [-T keyword] [-V rtable] [-W recvlimit]
	  [-w timeout] [-X proxy_protocol] [-x proxy_address[:port]]
	  [destination] [port]
```

<br>

- 간단한 사용방식은 `nc hostname(ip) port` 이다. 아래는 google.com 에 80번 포트로 연결을 요청한 후 GET / HTTP/1.1 을 입력한 후 내 입력이 끝났다는 뜻으로 엔터키를 한번 더 입력해보면, 서버에서 이 요청에 해당하는 response를 전송 받을 수 있다.
    - 다만 연결이 안되는 경우 방화벽 혹은 네트워크, 포트가 열려있는지 등을 확인해 봐야한다.
``` linux
user@user-VirtualBox:~/바탕화면$ nc google.com 80

GET / HTTP/1.1



HTTP/1.1 200 OK

Date: Sun, 18 Feb 2024 04:39:13 GMT

Expires: -1

Cache-Control: private, max-age=0
...
```

<br>
<br>
<br>

## SSH
> 원격 Linux 서버에 연결하는 가장 일반적인 방식은 SSH 명령어를 사용하는 것이다. SSH (Secure Shell, Secure Socket Shell)는 원격 서버(컴퓨터)에 연결할 수 있도록 해 주는 암호화된 네트워크 프로토콜이다. 암호화를 통해 호스트와 클라이언트가 안전하게 통신할 수 있다.

<br>

- 윈도우에서 리눅스 SSH를 통해 원격접속 할 경우
    1. 윈도우에서 설정 - 앱 - 선택적 기능 - OpenSSH 클라이언트가 설치되어 있는지 확인 없다면 설치한다. 
    2. 윈도우에서 cmd창에 들어가 ssh명령어를 작성한다. `ssh user@HOST -p PORT -i [개인 키 파일 경로]` , 만약 포트 설정안하면 defualt포트인 22번으로 연결을 시도한다.
        - 패스워드로 접속 : `ssh bguser@host3.dreamhack.games -p 11051` 수행 후 패스워드 입력
        - 개인 키로 접속 :  `ssh bguser@host3.dreamhack.games -p 11051 -i [다운받은 개인 키 파일 경로]`

        