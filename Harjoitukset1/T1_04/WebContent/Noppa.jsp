<%-- Noppa.jsp ottaa käyttön olion joka luodaan 
   tavallisesta luokasta. 
   jsp:usebean ei vaadi että luokan tarvitsee olla papu

   Tehtävä: Täydennä sovellusta niin että se ilmoittaa
   heittojen lukumäärän ja nollaa sen. Se myös ilmoittaa
   heittojen keskiarvon ja nollaa sen. Tee sovelluslogiikka
   eli tarvittavat attribuutit ja metodit Noppa -luokkaan.

   Muuta Noppa.jsp:n koodi sellaiseksi että siinä käytetetään
   normaalin JSP:n sijasta mahdollisimman paljon JSTL-
   tagikieltä.

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--Otetaan jsp:useBean-actionilla Noppa.java käyttöön.
Scope on nyt session, joten Noppa.java on käytettävissä 
tälle sivulle ja kaikille muille sivuille jotka ovat
samassa sessiossa.
--%>
<jsp:useBean id="noppa" scope="session" class="luokat.Noppa" />
<!DOCTYPE HTML>
<html>
    <head><title>Noppa.jsp</title></head>
    <body>
    
    
    <c:set var="i" value="${param.heita}" scope="page" />
    <c:set var="j" value="${param.nollaa}" scope="page" />
	<c:choose>
		<c:when test="${i!=null}">
			${noppa.heita()}
			<p>Silmäluvuksi tuli: <%=noppa.getTulos() %></p><br>
		</c:when>
		<c:when test="${j!=null}">
			${noppa.nollaa()}
			<p>Noppaa ei ole heitetty</p><br>
		</c:when>
		<c:otherwise>
		<p>Noppaa ei ole heitetty</p>
		</c:otherwise>
	</c:choose>
    
    <%-- 
        <%
            if (request.getParameter("heita") != null) {
                //Käytetään noppa-olion heita() -metodia
                noppa.heita();
        %>
        
        Silmäluvuksi tuli: <%= noppa.getTulos()%><br>

        <%
            } else if (request.getParameter("nollaa") != null) {
                noppa.nollaa();
                out.println("Noppaa ei ole heitetty<br>");
            } else {
                out.println("Noppaa ei ole heitetty<br>");

            }
        %> 
        
        --%>
        Heittojen summa on nyt: <%= noppa.getSumma()%><br>
        Heittojen lukumäärä on nyt: <%= noppa.getHeittolkm()%><br>
        Heittojen keskiarvo on nyt: <%= noppa.getKeskiarvo()%><br>

        <%--Luodaan URL:it joiden avulla noppa saadaan toimimaan--%>
        <%
            String heitaURL = response.encodeURL("Noppa.jsp?heita=ok");
            String nollaaURL = response.encodeURL("Noppa.jsp?nollaa=ok");

        //Nopan heitto
            out.println("<a href='" + heitaURL + "'>Heitä noppaa</a><br>");
            out.println("<a href='" + nollaaURL + "'>Nollaa summa</a><br>");
        %>   

    </body>
</html>