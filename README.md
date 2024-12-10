# 요구사항

* 모든 요청은 하나의 디스패처 서블릿이 받게 한다
* Controller interface를 추가한다.
* Servlet으로 구현돼 있는 각 Controller들을 앞서 추가한 Controller 인터페이스로 재구현
  * execute 반환 값은 redirect일 경우, redirect: 로 시작하고 포워드 방식의 경우 JSP경로를 반환
* RequestMapping 클래스를 추가해 요청 URL과 컨트롤러 매핑을 설정
* 회원가입 화면, 로그인 화면과 같이 특별한 로직을 구현할 필요가 없는 경우는 뷰에 대한 이동만을 담당하는 ForwardController를 추가한다
* DispatcherServlet에 요청 url에 해당하는 Controller를 찾아 excecute() 메소드를 호출해 실질적인 작업을 위임한다
* Controller의 execute() 메소드 반환 값 string을 받아 서블릿에서 jsp로 이동할 때의 중복을 제거한다


## ETC Learn
```shell
    mvn compile # 메이븐으로 컴파일 하는 명령어
    
    mvn test # 메이븐으로 테스트코드 컴파일과 테스트 하는 명령어
    
    mvn package # target -> war(Web archive) 파일을 만든다.  
```

* 메이븐의 플러긴이란?

메이븐의 중심이다. source, target의 버전으로 jdk 버전을 변경할 수 있음.
Run configuration -> 각 컴파일러들의 goals를 저장할 수 있음.
메이븐은 Phases라는 미리 정해진 단계가 있음. 각 phase에다가 다른 플러긴의 goal을 연결시킬 수 있음

* Tip
종속성의 자바 소스코드를 보는 방법 
```xml
  <downloadsource>true</downloadsource>
```


## 서블릿 컨테이너 및 Tomcat 디렉토리 구조
* Tomcat은 오픈소스 서블릿 컨테이너
* 서블릿 표준은 웹앱을 디렉토리 구조에 따라 개발 할 수 있음. 이렇게 개발한 프로젝트는 서블릿 컨테이너에 배포 가능
* 톰캣의 설치 경로를 확인한 후 해당 경로에서 설정을 진행해야 개발서버에서 IDE 없이 서버 시작 가능
* 서버 로그는 catalina log directory 의 catalina.out 파일에서 확인 가
* 실시간 로그 변화 확인은 
```shell
    tail -f
```


## IDE 없이 빌드하기
* maven을 설치하고 .zshrc 나 shell 설정 파일에 명시해야 함

```shell

  export MAVEN_HOME=/Users/유저네임/workspace/apache-maven-{versionId}
```


## pom.xml 설정

scope 태그를 이용해서 자료 파일 포함 유무 결정할 수 있음
scope -> test, provide 넣으면 빌드 때 안들어감