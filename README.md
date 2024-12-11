# 게시판 질문 답변 기능 구현

## 요구사항
* 질문 생성
* 답변 생성
* not known: 질문 수정, 답변 수정

## 궁금한 점
* 객체를 저장하기 전에 생성자로 인스턴스를 생성하게 됨. 근데 처음에 저장하기 전에 id가 없음.
  * 근데 모종의 실수로 개발자가 getId로 접근하려 할 때 막아야 하는데 어떻게 하는거지?
  * 그리고 이런 케이스로 insert 테스트는 어떻게 하는게 좋을가

## issue
* timestamp Date conversion error
  * 각 column name 실수를 했음. typing 하는 방법이 있을까?

