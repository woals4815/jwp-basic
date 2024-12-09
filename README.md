# 요구사항

* 모든 요청은 하나의 디스패처 서블릿이 받게 한다
* Controller interface를 추가한다.
* Servlet으로 구현돼 있는 각 Controller들을 앞서 추가한 Controller 인터페이스로 재구현
  * execute 반환 값은 redirect일 경우, redirect: 로 시작하고 포워드 방식의 경우 JSP경로를 반환
* RequestMapping 클래스를 추가해 요청 URL과 컨트롤러 매핑을 설정
* 회원가입 화면, 로그인 화면과 같이 특별한 로직을 구현할 필요가 없는 경우는 뷰에 대한 이동만을 담당하는 ForwardController를 추가한다
* DispatcherServlet에 요청 url에 해당하는 Controller를 찾아 excecute() 메소드를 호출해 실질적인 작업을 위임한다
* Controller의 execute() 메소드 반환 값 string을 받아 서블릿에서 jsp로 이동할 때의 중복을 제거한다
