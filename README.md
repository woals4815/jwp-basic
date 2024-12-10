## 1차
* UserDAO 내에 findAll 구현
* JDBC 로직 중복 제거
  * SQLException 처리
  * Compile Exception
    * api가 사용되는 모든 곳에서 예외 처리 해야 하는가? && 예외가 반드시 메소드에 대한 반환값이 돼야 하는가 ->  컴파일 타입 exception 사용
    * api 를 사용하는 소수 중 이 예외를 처리해야 하는가? -> 런타임 exception
    * 큰 문제 발생 && 문제 복구 방법 emtpy -> runtime exception
* UserDAO refactor 요구사항
  * SQL query, query 인자, select 구문의 경우 조회한 데이터를 추출하는 3가지 구현에만 집중