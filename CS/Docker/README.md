# 도커 (Docker) 🐳
> 도커(Docker)는 컨테이너를 만들고, 실행하고, 배포할 수 있는 가상화 플랫폼이다. 도커의 컨테이너는 가상의 환경이 구축되어 있는 하나의 박스를 말한다. VirtualBox 등의 가상 머신으로 하나의 운영체제 위에 다른 운영체제 환경을 구축하는 것과 유사하지만 도커 컨테이너는 새로운 운영체제 환경을 구축할 필요 없이 하나의 분리된 프로세스처럼 작동하여 더 가볍다. 쉽게 말하면 특정한 환경을 구성하기 위해 만들어진 가상의 공간이다.
<br>
<br>
<br>

## 도커 설치
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

