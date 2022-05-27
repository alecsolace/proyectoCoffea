<%-- 
    Document   : processRegistration
    Created on : 16-may-2022, 18:35:51
    Author     : aaguirrem
--%>
<%@page import="com.sanvalero.coffea.domain.Customer"%>
<%@page import="com.sanvalero.coffea.dao.CustomerDAO"%>  
<jsp:useBean id="customer" class="com.sanvalero.coffea.domain.Customer"></jsp:useBean>
<jsp:setProperty property="*" name="obj"/> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            int status = CustomerDAO.addUser(customer);
            if (status > 0) {
                out.print("You are successfully registered");
            }

        %>  
    </body>
</html>
