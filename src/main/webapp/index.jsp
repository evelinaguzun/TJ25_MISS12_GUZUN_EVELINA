<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome Page</title>
</head>
<body>
    <h1>Welcome</h1>
    <form action="controller" method="get">
        <label>Select your destination:</label>
        <select name="page">
            <option value="1">Page 1</option>
            <option value="2">Page 2</option>
        </select>
        <input type="submit" value="Go">
    </form>
</body>
</html>
    