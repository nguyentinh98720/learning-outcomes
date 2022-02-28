<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin</title>
    <link href="../css/dashboardcss.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <div class="leftnav">
        <h2>Vati Team</h2>
        <hr>
        <div id="dashboard" onclick="open_dashboard()"><span>📑</span><p>Dashboard</p></div>
        <div id="staffmanager" onclick="open_staffmanager()"><span>📝</span><p>Staff Manager</p></div>
    </div>
    <div class="topnav">
        <div>
            <h3>Welcome <%= session.getAttribute("email") %></h3>
        </div>
        <div>
            <a href="/PRJ321x_A3/login?action=logout">
	            <span>🧑‍💼</span>
	            <span>Logout</span>
            </a>
        </div>
    </div>
    <div class="body">
        <div id="div1">
            <span>Member of the team</span>
            <table>
                <tr></tr>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Mission</th>
                    <th>Department</th>
                </tr>
                <tr>
                    <td>1</td>
                    <td>Name 1</td>
                    <td>Mission 1</td>
                    <td>Department 1</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Name 2</td>
                    <td>Mission 2</td>
                    <td>Department 2</td>
                </tr>
                <tr></tr>
            </table>
        </div>
        <div id="div2">
            <span>Updating...</span>
        </div>
    </div>
    <script src="../javaScript/dashboardjs.js" type="text/javascript"></script>
</body>
</html>