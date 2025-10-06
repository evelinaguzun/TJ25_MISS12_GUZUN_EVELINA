<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome Page</title>
    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea, #764ba2);
            color: white;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .container {
            background: rgba(255, 255, 255, 0.1);
            padding: 40px 60px;
            border-radius: 20px;
            text-align: center;
            box-shadow: 0 8px 20px rgba(0,0,0,0.3);
        }
        h1 {
            font-size: 2.2em;
            margin-bottom: 20px;
        }
        label {
            font-size: 1.1em;
        }
        select, input[type="submit"] {
            margin-top: 15px;
            padding: 10px 15px;
            border: none;
            border-radius: 8px;
            font-size: 1em;
            outline: none;
        }
        select {
            background: rgba(255,255,255,0.2);
            color: white;
        }
        input[type="submit"] {
            background: rgba(255,255,255,0.3);
            color: white;
            cursor: pointer;
            transition: background 0.3s;
        }
        input[type="submit"]:hover {
            background: rgba(255,255,255,0.5);
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome</h1>
        <form action="controller" method="get">
            <label for="page">Select your destination:</label><br><br>
            <select name="page" id="page">
                <option value="1">Page 1</option>
                <option value="2">Page 2</option>
            </select><br><br>
            <input type="submit" value="Go">
        </form>
    </div>
</body>
</html>

    