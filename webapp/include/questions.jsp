<%--
  Created by IntelliJ IDEA.
  User: jayden
  Date: 2024. 12. 11.
  Time: 오전 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="list">
  <c:forEach items="${questions}" var="question">
    <li>
      <div class="wrap">
        <div class="main">
          <strong class="subject">
            <a href="qna/detail?questionId=${question.questionId}">${question.title}</a>
          </strong>
          <div class="auth-info">
            <i class="icon-add-comment"></i>
            <span class="time">${question.createdDate}</span>
            <a href="./user/profile.html" class="author">${question.writer}</a>
          </div>
          <div class="reply" title="댓글">
            <i class="icon-reply"></i>
            <span class="point">${question.countOfAnswer}</span>
          </div>
        </div>
      </div>
    </li>
  </c:forEach>
</ul>
