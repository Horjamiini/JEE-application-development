<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="papu" class="Luokat.AsiakasBean"
             scope="session">
</jsp:useBean>
<html>
    <head>
        <title>
            vastaus.jsp
        </title>
    </head>
    <body>
        <p>
            Vastaus hakuun:
        </p>
        Id: <jsp:getProperty name="papu" property="id"/>
        <br />
        Nimi: <jsp:getProperty name="papu" property="nimi"/>
        <br />
        Osoite: <jsp:getProperty name="papu" property="osoite"/>
        <br />
        Puhelin: <jsp:getProperty name="papu" property="puhelin"/>
        <br />
        Email: <jsp:getProperty name="papu" property="email"/>
        <br />
        Salasana: <jsp:getProperty name="papu" property="salasana"/>
        <br />
        <p>
            <a href="lomake.html">Palaa hakulomakkeeseen</a>
        </p>
    </body>
</html>