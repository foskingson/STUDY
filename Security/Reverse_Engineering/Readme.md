# 리버스 엔지니어링 (Reverse Engineering) ⚙
> 줄여서 리버싱으로도 불리는 리버스 엔지니어링은 무언가 설계하고 제작하는 엔지니어링의 과정을 정반대로 수행하는 것이다. 컴퓨터 프로그래밍적 관점에서는 개발자가 소스 코드를 작성하고, 컴파일한 산출물에서부터 시작하는 것이다. 프로그램의 동작을 직접 실행 시켜보면서 소스 코드의 내용을 추측하는 것도 가능한 방법이고, 컴파일된 결과물의 데이터를 분석하여 소스 코드의 내용을 유추, 복구 해볼 수도 있다. 여기서 언급한 산출물은 프로그램 혹은 이진파일이라고 부른다. 컴파일 되지 않은 코드 역시 '프로그램'이라고 부르는 경우가 있어 '이진 파일'이 조금 더 보편적으로 사용된다. 이진 파일은 사람이 눈으로 식별하기 어려운 데이터로 이루어져 있어, 단순히 이 데이터 값을 본다고 해서 어떤 동작을 하는지 알아내기가 어렵다. 이를 도와주는 것이 '디스어셈블러(disassembler)', '디컴파일러(decompiler)'와 같은 도구이다.

<br>
<br>
<br>

## 레지스터와 메모리의 차이
> 레지스터는 CPU에서 직접 액세스할 수 있는 작은 단위의 데이터를 저장하는 데 사용되며 계산 중에 빠른 액세스를 제공한다. 반면 메모리는 레지스터에 맞지 않는 데이터와 명령을 저장하기 위해 더 큰 공간을 제공하지만 레지스터에 비해 액세스 시간이 느리다.
<br>

- 예를 들어 간단하게 3이랑 5를 더할 때는 A

<br>
<br>
<br>

## 명령어 집합 구조(Instruction Set Architecture, ISA)
> 마이크로프로세서(CPU)가 인식해서 기능을 이해하고 실행할 수 있는 기계어 명령어의 모음이다.