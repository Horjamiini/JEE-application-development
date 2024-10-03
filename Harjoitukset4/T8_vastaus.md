1. Luo Liferay Workspace
	- File -> New -> Liferay workspace Project
	- esim. com-liferay-docs-guestbook
2. Luo Workspaceen uusi Liferay-portlet
	- File -> New -> Liferay Module Project
	- esim. guestbook

3. Etsi moduulistasi view.jsp tiedosto
	- Polku: src/main/resources/META-INF/resources

4. Tee view.jsp tiedostoon haluamiasi muutoksia
	- Lisää näppäin tai luo teksitä tms.
	- Voit myös halutettasia luoda uusia tiedostoja ja luoda linkit JSP-sivuista toiseen

5. Etsi luomastasi moduulista *Moduulisi nimi*Portlet.java tiedosto
	- Polku src/main/java/com.liferay.docs.guestbook/portlet/GuestbookPortlet.java

6. Luo Portlet tiedstoosi haluamiasi toiminnallisuuksia

7. Käynnistä Liferay-palvelin Server-paneelista, mikäli se ei ole jo käynnissä

8. Lisää Moduulisi Serverille vetämälle se Package Explorerista serverin päälle
	- Odota, että serveri julkaisee moduulisi. (Started, Synchronized)

9. Mene verkkoselaimellasi osoitteeseen localhost:8080
	- Tai muu osoite mikäli olet määritellyt portin toiseksi

10. Kirjaudu Liferay portaaliin tunnuksillasi

11. Hallinta sivulla paina Add(+) näppäintä liätäksesi portlettisi
	- Oletuksena portelttisi löytyy Widget-välilehdeltä Sample otsikon alta.

12. Vie hiirelläsi portlettisi sivulle.

13. Voit nyt testata portlettisi toimintaa. 
