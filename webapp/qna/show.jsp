<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>SLiPP Java Web Programming</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <link href="../css/styles.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-fixed-top header">
    <div class="col-md-12">
        <div class="navbar-header">

            <a href="/" class="navbar-brand">SLiPP</a>
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse1">
                <i class="glyphicon glyphicon-search"></i>
            </button>

        </div>
        <div class="collapse navbar-collapse" id="navbar-collapse1">
            <form class="navbar-form pull-left">
                <div class="input-group" style="max-width:470px;">
                    <input type="text" class="form-control" placeholder="Search" name="srch-term" id="srch-term">
                    <div class="input-group-btn">
                        <button class="btn btn-default btn-primary" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                    </div>
                </div>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-bell"></i></a>
                    <ul class="dropdown-menu">
                        <li><a href="https://slipp.net" target="_blank">SLiPP</a></li>
                        <li><a href="https://facebook.com" target="_blank">Facebook</a></li>
                    </ul>
                </li>
                <li><a href="../user/list.html"><i class="glyphicon glyphicon-user"></i></a></li>
            </ul>
        </div>
    </div>
</nav>
<%@ include file="/include/navigation.jspf" %>
<div class="container" id="main">
    <div class="col-md-12 col-sm-12 col-lg-12">
        <div class="panel panel-default">
          <header class="qna-header">
              <h2 class="qna-title">${question.title}</h2>
          </header>
          <div class="content-main">
              <article class="article">
                  <div class="article-header">
                      <div class="article-header-thumb">
                          <img src="https://graph.facebook.com/v2.3/100000059371774/picture" class="article-author-thumb" alt="">
                      </div>
                      <div class="article-header-text">
                          <a href="/users/92/kimmunsu" class="article-author-name">kimmunsu</a>
                          <a href="/questions/413" class="article-header-time" title="퍼머링크">
                              ${question.createdDate}
                              <i class="icon-link"></i>
                          </a>
                      </div>
                  </div>
                  <div class="article-doc">
                      <p>A 에 의존성을 가지는 B라는 클래스가 있습니다.</p><p>B라는 클래스는 InitializingBean 을 상속하고 afterPropertiesSet을 구현하고 있습니다.
                      서버가 가동되면서 bean들이 초기화되는 시점에 B라는 클래스의 afterPropertiesSet 메소드는</p><p>A라는 클래스의 특정 메소드인 afunc()를 호출하고 있습니다.</p>
                  </div>
                  <div class="article-util">
                      <ul class="article-util-list">
                          <li>
                              <a class="link-modify-article" href="/questions/423/form">수정</a>
                          </li>
                          <li>
                              <form class="form-delete" action="/questions/423" method="POST">
                                  <input type="hidden" name="_method" value="DELETE">
                                  <button class="link-delete-article" type="submit">삭제</button>
                              </form>
                          </li>
                          <li>
                              <a class="link-modify-article" href="/">목록</a>
                          </li>
                      </ul>
                  </div>
              </article>
              <div class="qna-comment">
                  <div class="qna-comment-slipp">
                      <p class="qna-comment-count"><strong>2</strong>개의 의견</p>
                      <div class="qna-comment-slipp-articles">

                          <article class="article" id="answer-1405">
                              <div class="article-header">
                                  <div class="article-header-thumb">
                                      <img src="https://graph.facebook.com/v2.3/1324855987/picture" class="article-author-thumb" alt="">
                                  </div>
                                  <div class="article-header-text">
                                      <a href="/users/1/자바지기" class="article-author-name">자바지기</a>
                                      <a href="#answer-1434" class="article-header-time" title="퍼머링크">
                                          2016-01-12 14:06
                                      </a>
                                  </div>
                              </div>
                              <div class="article-doc comment-doc">
                                  <p>이 글만으로는 원인 파악하기 힘들겠다. 소스 코드와 설정을 단순화해서 공유해 주면 같이 디버깅해줄 수도 있겠다.</p>
                              </div>
                              <div class="article-util">
                                  <ul class="article-util-list">
                                      <li>
                                          <a class="link-modify-article" href="/questions/413/answers/1405/form">수정</a>
                                      </li>
                                      <li>
                                          <form class="form-delete" action="/questions/413/answers/1405" method="POST">
                                              <input type="hidden" name="_method" value="DELETE">
                                              <button type="submit" class="link-delete-article">삭제</button>
                                          </form>
                                      </li>
                                  </ul>
                              </div>
                          </article>
                          <article class="article" id="answer-1406">
                              <div class="article-header">
                                  <div class="article-header-thumb">
                                      <img src="https://graph.facebook.com/v2.3/1324855987/picture" class="article-author-thumb" alt="">
                                  </div>
                                  <div class="article-header-text">
                                      <a href="/users/1/자바지기" class="article-author-name">자바지기</a>
                                      <a href="#answer-1434" class="article-header-time" title="퍼머링크">
                                          2016-01-12 14:06
                                      </a>
                                  </div>
                              </div>
                              <div class="article-doc comment-doc">
                                  <p>이 글만으로는 원인 파악하기 힘들겠다. 소스 코드와 설정을 단순화해서 공유해 주면 같이 디버깅해줄 수도 있겠다.</p>
                              </div>
                              <div class="article-util">
                                  <ul class="article-util-list">
                                      <li>
                                          <a class="link-modify-article" href="/questions/413/answers/1405/form">수정</a>
                                      </li>
                                      <li>
                                          <form class="form-delete" action="/questions/413/answers/1405" method="POST">
                                              <input type="hidden" name="_method" value="DELETE">
                                              <button type="submit" class="link-delete-article">삭제</button>
                                          </form>
                                      </li>
                                  </ul>
                              </div>
                          </article>
                          <form class="submit-write" id="answerForm" method="post" action="/qna/answer">
                              <div class="form-group" style="padding:14px;">
                                  <textarea class="form-control" form="answerForm" id="answer" name="contents" placeholder="Update your status"></textarea>
                              </div>
                              <button class="btn btn-success pull-right" type="button">Post</button>
                              <div class="clearfix" />
                          </form>
                      </div>
                  </div>
              </div>
          </div>
        </div>
    </div>
</div>

<!-- script references -->
<script src="../js/jquery-2.2.0.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/scripts.js"></script>
	</body>
</html>