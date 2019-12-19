<%--
  Created by IntelliJ IDEA.
  User: n2god
  Date: 19/12/2019
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"
         language="java"
        pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Strona główna</title>
  </head>
  <body>
  <h1>Przeglądarka miast</h1>
  <form action="ControllerServlet" method="post">
    <input type="text" placeholder="miasto" name="city">
    <br>
    <input type="text" placeholder="kod państwa" name="country">
    <br>
    <input type="text" placeholder="region" name="district">
    <br>
    <input type="number" placeholder="populacja" name="population">
    <br>
    Dodaj<input type="radio" name="option" value="add">
    <br>
    Usuń<input type="radio" name="option" value="delete">
    <br>
    <input type="submit" value="Wyślij">
  </form>
  </body>
</html>
