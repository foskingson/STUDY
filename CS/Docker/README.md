# 도커 (Docker) 🐳
> 도커(Docker)는 컨테이너를 만들고, 실행하고, 배포할 수 있는 가상화 플랫폼이다. 도커의 컨테이너는 가상의 환경이 구축되어 있는 하나의 박스를 말한다. VirtualBox 등의 가상 머신으로 하나의 운영체제 위에 다른 운영체제 환경을 구축하는 것과 유사하지만 도커 컨테이너는 새로운 운영체제 환경을 구축할 필요 없이 하나의 분리된 프로세스처럼 작동하여 더 가볍다. 쉽게 말하면 특정한 환경을 구성하기 위해 만들어진 가상의 공간이다.
<br>
<br>
<br>

## 도커 설치
- 도커 엔진을 설치한다. `sudo docker run hello-world`를 입력했을때 Hello from Docker!가 출력되면 잘 설치 된 것이다.
``` linux
$ sudo apt-get update
$ sudo apt-get install \
    ca-certificates \
    curl \
    gnupg
 
```
<br>



<br>
<br>
<br>


## 도커 이미지
> 도커 이미지는 도커 컨테이너의 전 단계로, 컨테이너를 생성하고 실행하기 위한 모든 것을 포함한다. 예를 들어 컨테이너 생성에 필요한 파일, 환경 변수, 명령어 등과 파일 시스템이 있다. 자신만의 이미지를 만들거나 다른 사람이 만든 이미지를 사용할 수도 있다. 이미지를 생성하려면 Dockerfile을 작성하고 빌드해야 한다. Dockerfile은 이미지를 생성하는 데 단계적으로 필요한 명령을 작성하는 파일이다. 도커 이미지에는 태그(Tag)를 붙일 수 있다. 태그를 붙이는 것은 하나의 이미지에 여러 개의 별명을 붙여 주는 것과 같다. 주로 이미지의 버전을 지정하기 위해 사용한다.
<br>
<br>
<br>

## 도커 컨테이너
> 도커 컨테이너는 도커 이미지로부터 만들어진 실행 가능한 인스턴스이다. 다르게 말하면, 실행 중인 이미지를 컨테이너라고 한다. 컨테이너는 도커 이미지와 사용자가 컨테이너를 시작할 때 작성하는 옵션에 의해 정의 된다. 컨테이너를 실행하는 동안은 분리된 파일 시스템을 사용한다.
<br>
<br>
<br>

## 도커 레지스트리
> 도커 레지스트리는 도커 이미지를 저장하는 저장소이다. 도커의 공식 레지스트리로 Docker Hub가 있다. 누구나 레지스트리에 도커 이미지를 올리고, 존재하는 도커 이미지를 가져올 수 있다.


<br>
<br>
<br>

## 명령어
<br>

- docker build : Dockerfile을 이용하여 이미지를 생성한다.   
    - `docker build [옵션] [Dockerfile 경로]`   / `docker build .` : 현재 디렉토리에 있는 Dockerfile로 이미지 생성
    - `docker build -t [이미지명:태그] [Dockerfile 경로]`   / `docker build -t my-image` . : 현재 디렉토리에 있는 Dockerfile로 ‘my-image:latest’ 이미지 생성

<br>

- docker images : 도커 이미지 목록을 출력한다.

<br>

- docker run : 도커 이미지로 컨테이너를 생성하고 실행한다.
    - `docker run [옵션] [이미지명|ID] [명령어]`
    - `docker run -p [호스트 PORT]:[컨테이너 PORT] [이미지명|ID]`

<br>

- docker ps : 실행중인 컨테이너 목록 출력한다. -a 옵션은 종료된 컨테이너까지 모두 출력한다.

<br>

- docker create : 도커 이미지로 컨테이너를 생성한다.
    - `docker create [옵션] [이미지명|ID] [명령어]` 
<br>

- docker start : 중단된 컨테이너를 시작한다.
    - `docker start [옵션] [컨테이너명|ID]`

<br>

- docker exec : 실행중인 컨테이너에 접속하여 명령을 수행한다.
    - `docker exec [옵션] [컨테이너명|ID] [명령어]`
<br> 

- docker stop : 실행중인 컨테이너를 중단한다.
    - `docker stop [옵션] [컨테이너명|ID]`
<br>

- docker pull : 레지스트리에 존재하는 도커이미지를 다운받는다.
    - `docker pull [옵션] [이미지명]`
<br>

- docker rm : 도커 컨테이너를 삭제한다.
    - `docker rm [옵션] [컨테이너명|ID]`
<br>

- docker rmi : 도커 이미지를 삭제한다.
    - `docker rmi [옵션] [이미지명|ID]` 
<br>

- docker inspect : 도커 이미지 혹은 컨테이너의 자세한 정보를 출력한다.
    - `docker inspect [옵션] [이미지 혹은 컨테이너명|ID]`


<br>
<br>
<br>

## Dockerfile
> 도커 이미지를 빌드하기 위해서는 Dockerfile이 필요하다.  Dockerfile은 이미지를 생성하는데 필요한 명령어를 포함하여 모든 설정이 정의된 파일입니다. 운영체제와 버전, 환경 변수, 파일 시스템, 사용자 등을 정의한다.

<br>

### Dockerfile 구성 방법
>기본적으로 도커 파일의 파일명은 확장자 없이 Dockerfile이다. docker build 명령어를 수행하면 자동으로 이름이 Dockerfile인 파일을 찾아 이미지를 빌드한다. -f 옵션으로 원하는 이름의 도커 파일을 사용할 수 있다.
``` linux
# 주석
명령어 인자

# ---예시---
FROM ubuntu:18.04
```

<br>

### Dockerfile 명령어
> Dockerfile은 FROM 명령어로 시작해야한다. 이후에는 순서대로 명령어를 실행한다.
<br>

- FROM : 생성할 이미지의 기반이 되는 base 이미지를 지정한다. 보통 사용할 운영체제의 공식 이미지를 Dockerhub에서 가져온다. 
    - `FROM 이미지:태그`
    - `FROM ubuntu:18.04`
<br>

- ENV : Dockerfile 내에서 사용하는 환경 변수를 지정합니다. 파일 내에서 변수는 $변수명 혹은 ${변수명} 형태로 표현한다.
    - `ENV 변수명 값 or ENV 변수명=값`
    - `ENV ENV_VARIABLE=value`
<br>

- RUN : 이미지를 빌드할 때 실행할 명령어를 작성한다. 필요한 패키지를 설치하거나, 파일 권한 설정 등의 작업을 수행한다.
    - `RUN 명령어 or RUN ["명령어", "인자1", "인자2"]`
    - `RUN apt-get update`
    - `RUN ["/bin/bash", "-c", "echo hello"]`
<br>

- COPY : src 파일이나 디렉토리를 이미지 파일 시스템의 dst로 복사한다.
    - `COPY src dst`
    - `COPY . /app`
<br>

- ADD : src 파일이나 디렉토리, URL을 이미지 파일 시스템의 dst로 복사한다.
    - `ADD src dst`
    - `ADD . /app`
<br>

- WORKDIR : Dockerfile 내의 명령을 수행할 작업 디렉토리를 지정한다. 리눅스의 cd 명령어와 유사하다.
    - `WORKDIR 디렉토리`
    - `WORKDIR /home/user`
<br>

- USER : 명령을 수행할 사용자 혹은 그룹을 지정한다. 
    - `USER 사용자명|UID or USER [사용자명|UID]:[그룹명|GID]`
    - `USER $username`
<br>

- EXPOSE : 컨테이너가 실행 중일 때 들어오는 네트워크 트래픽을 listen할 포트와 프로토콜을 지정한다. 사용할 수 있는 프로토콜은 TCP와 UDP이며, 기본적으로 TCP가 지정된다.
    - `EXPOSE 포트 or EXPOSE 포트/프로토콜`
    - `EXPOSE 80/tcp`
<br>

- ENTRYPOINT : 컨테이너가 실행될 때 수행할 명령어를 지정한다.
    - `ENTRYPOINT 명령어 or ENTRYPOINT ["명령어", "인자1", "인자2"]`
    - `ENTRYPOINT ["echo", "hello"]`

<br>

- CMD : 컨테이너가 실행될 때 수행할 명령어를 지정하거나, ENTRYPOINT 명령어에 인자를 전달한다. Dockerfile 내에 CMD 명령이 여러 개 존재하면 마지막 CMD를 사용한다
    - docker run의 인자를 작성하면 CMD 명령어는 무시된다. ENTRYPOINT가 있는 경우, docker run의 인자가 ENTRYPOINT의 인자로 들어간다.
    - `CMD 명령어 or CMD ["명령어", "인자1", "인자2"] or CMD ["인자1", "인자2"]`
    - `CMD ["echo", "hello"]`

<br>

### Dockerfile 예시
``` Dockerfile
FROM ubuntu:22.04

# 사용자 이름을 'chall'로 설정
ENV user chall

# 챌린지가 동작할 포트를 2222로 설정
ENV chall_port 2222

# 우분투 패키지 업데이트
RUN apt-get update

# socat 패키지 설치
RUN apt-get install -y socat

# 'chall' 사용자 추가
RUN adduser $user

# 챌린지용 플래그와 사용자 바이너리 파일을 컨테이너에 추가
ADD ./deploy/flags/home/$user/flags
ADD ./deploy/$user/home/$user/$user

# 파일 소유자 및 권한 설정
RUN chown -R root:$user /home/$user
RUN chown root:$user /home/$user/flags
RUN chown root:$user/home/$user/$user

# 바이너리 파일 권한 설정
RUN chmod 755 /home/$user/$user
RUN chmod 440 /home/$user/flags

# 작업 디렉토리 설정
WORKDIR /home/$user

# 사용자 변경
USER $user

# 챌린지용 포트 노출
EXPOSE $chall_port

# socat을 사용하여 TCP 포트를 열고 챌린지 실행
CMD socat -T 30 TCP-LISTEN:$chall_port,reuseaddr,fork EXEC:/home/$user/$user
```

<br>
<br>
<br>

## Docker Hub
> Docker Hub는 도커의 공식 레지스트리로, 도커 이미지를 저장하는 저장소이다. 오픈 소스 컨텐츠로 누구나 이미지를 업로드하거나 다운받아 사용한다. 도커에서 제공하는 공식 이미지도 있다. 공식 이미지는 기본 OS(linux, centOS 등) 이미지나, 특정 프로그래밍 언어의 개발 환경 등을 제공한다. 비공개 이미지를 업로드하여 개인적으로 사용하거나 팀끼리 공유할 수도 있다.

<br>
<br>
<br>

## dive
>  Docker 이미지를 레이어별로 탐색하고 분석할 수 있는 Docker 이미지 분석 도구이다. 이미지의 각 레이어 내의 파일 시스템과 메타데이터에 대한 자세한 분석을 제공하여 각 레이어에서 도입된 콘텐츠, 크기 및 변경 사항을 파악할 수 있다.
<br>

``` linux
sudo snap install docker
sudo snap install dive
sudo snap connect dive:docker-executables docker:docker-executables
sudo snap connect dive:docker-daemon docker:docker-daemon
```

<br> 

- 기본적으로 필요한 사전작업을 한 후 dive <이미지id> 를 통해 접속해서 확인할 수 있다.
