<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core"prefix="c"%> 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>나의 홈페이지</title>
    <style>
      * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
      }
      body,
      html {
        width: 100%;
        height: 100%;
      }
      body {
        display: flex;
        flex-direction: column;
      }

      header {
       position: relative;
  height: 400px;
  color: white;

  background-image: url("${rootPath}/static/images/냥이.jpg");
  background-position: center;
  background-repeat: repeat;
  
  background-attachment: fixed;

  justify-content: center;
  align-items: center;
      }
      
      section#content{
      
      /*
      html, body의 height: 100%로 설정하고, 
      body를 flex-direction: column;,flex로 설정하고 
      header, nav, content, footer 를 각각 배열하고
      content가 있는 박스에만 flex값을 1로 설정하면 height 가 전체 가득 찬 레이아웃이 만들어진다
      overflow 는 내용이 넘치면 자동으로 스크롤바생성*/
      	flex:1;
      	overflow:auto;
      	
      }

      nav#main-nav ul {
        background: linear-gradient(to right, purple, orange);
        list-style: none;
        display: flex;
      }

      nav#main-nav li {
        padding: 8px 16px;
        color: white;
        margin: 0px 5px;
        border-bottom: 3px solid transparent;
        cursor:pointer;
        transition:border-color 0.3s linear;
      }

      nav#main-nav li:nth-child(3) {
        margin-left: auto;
      }

      nav#main-nav li:hover {
        border-bottom: 3px solid black;
        cursor: pointer;
      }

      footer {
        background: linear-gradient(to left, pink, black);
        color: white;
        text-align: center;
        padding: 0.7rem;
      }
    </style>
  </head>
  <body>
    <tiles:insertAttribute name="header" />
    <tiles:insertAttribute name="menu" />
    <section id="content">
    <tiles:insertAttribute name="content" />
    </section>
    <tiles:insertAttribute name="footer" />
  </body>
</html>
