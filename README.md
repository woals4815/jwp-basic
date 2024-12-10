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
* 구현
  * 1차로 Create, Update, Select Template class 분리
  * 2차로 Create Update의 같은 로직으로 구현 및 클래스 통합
    * JDBC template class로 전체 SQL 쿼리가 커버되도록 리팩토링
      * User, UserDao class 의존성 제거
        * RowMapper, PreParedStatementSetter interface 추출 -> 익명 클래스 구현체로 각 모델 타입에 맞게 매핑
  * 3차로 Select Template을 하나의 클래스에 통합
* TIL
  * 메소드 오버로딩
    * 같은 키워드의 메소드를 다른 인자들로 재선언하면 각 인자들에 매핑되는 메소드를 사용할 수 있음.
  * 클래스 내의 메소드 제네릭 타입 선언
    * <T>로 generic type 명시, 그다음 실질적인 return type 명시
      ```java
        class MyClass {
          public <T> T getT() {
           }
        }
      ```
  * 공통적으로 사용되는 로직은 따로 interface로 빼든가 class를 통해 추출
  * Exception을 extend 할 때 자동완성을 이용해서 모든 생성자들을 구현하는게 좋음
  * try catch 시에 기존 Exception 타입을 명시하고 그 다음 커스텀 Exception 인스턴스를 생성해서 throw
  * 람다 표현식을 사용하려면 함수형 인터페이스 어노테이션은 붙여야 한다
    * ```java
        @FunctionalInterface
        interface MyFunction {
            public int max(int a, int b);
        } 
      ```   