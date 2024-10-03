T2

Raportoi kuinka Spring MVC toimii ja mitkä ovat sovelluksen eri tiedostojen roolit & Mistä komponenteista muodostuvat model, view ja controller?

Spring MVC on web-sovelluskehys, joka pohjautuu MVC-suunnittelumalliin: Model, View & Controller. 

Model pitää sisälläänsovelluksen tietomalleja, jotka voivat olla perus Java olioita tai luokkia, jotka käyttävät dataa tietokannoista. Esimerkin tapauksessa Widget.java tiedosto on Model-tiedosto, joka on käytännössä tavanomainen Java luokka, johon on lisätty JPA(Java Persistence API) annotaatioita esim. @Entity.

View on sovelluksen ns. näkyvä osuus, joka näyttäytyy loppukäyttäjälle ja johon sovellusksen tiedot tuodaan. Esimerkissä nämä tiedostot ovat HTML-tiedostoja: widgets.html, widget.html ja widgetform.html. Näitä tiedostoja renderöidään Thymeleaf kirjaston avulla.

Controller on ohjaus kerros(pääteltävissä myös nimestä). Controller vastaanottaa tulevat HTTP-pyynnöt ja käsiitelee ne Model-tiedostojen kanssa, jonka jälkee tuo vastausken View kerrokselle käsiteltäväksi/renderöitäväksi. Esimerkin tapauksessa WidgetController.java 

pom.xml on riippuvuuksien ja verisoden hallintaan käytettävä tiedosto.

Spring5mvcExampleApplication.java on tiedosto, joka ajaa sovelluksen.

WidgetRepository.java  tuodaan CRUD operaatiot käytettäväksi Widget.Java luokalle


Kuinka Spring-konteksti toimii Spring Boot -sovelluksessa?

	Spring-konteksti implementoi Inverion of Control(IOC) kehitysperiaattetta. Spring Boot sovelluksessa konteksti ladataan automaattisesti sovelluksen käynnistyessä.
	
	Spring-konteksti suorittaa sovelluksen beanien luomisesta, alustamisesta ja injektoinnista muihin luokkiin riippuvuuden hallinnan avulla. Eri annotaatiolla voidaan määritellä, mitä eri beanit tarvitsevat/tekevät.
	The Spring context is a registry of all available Spring beans. Classes are identified as Spring beans by annotating them with specific Spring annotations

	Once these beans are annotated they need to be registered with the Spring context, which Spring Boot does by performing a package scan of all classes in packages in 	your project. As the Spring context is being built, it implements the inversion-of-control (IoC) design pattern through dependency injection: when a Spring bean needs 	a dependency, such as a service or repository, the bean can either define a constructor that accepts the dependent bean or it can leverage the @Autowired annotation to 	tell Spring that it needs that dependency. Spring resolves all dependencies and “autowires” the application together.

Mitä Application-tiedoston @SpringBootApplication-annotaatio tekee?
	@SpringBootApplicaton sisältää kolme annotaatiota:

	@Configuration kertoo Springille, että kyseinen luokka sisältää konfiguraatio tietoja.
	@EnableAutoConfiguration kertoo Springille määrittää automaattisesti resurssit, jotka löytyvät CLASSPATHista..
	@ComponentScan käskee Springiä etsimään ja rekisteröimään sen hetkisestä paketista(package) komponentteja, jotka ovat Spring annotoituja. Esim. @Controller tai @Service

Millaisia piirteitä muiden tiedostojen annotaatiot tarjoavat tiedostoille?
	
	@Controller: Määrittelee luokan kontrolleriksi, joka käsittelee web-pyyntöjä.
	@Autowired: Spring injektoi riippuvuuksia automaattisesti, eli liittää toisen beanin luokkaan

	@GetMapping & PostMapping: käytetään yhdistämään HTTP GET & POST -pyyntöjä controllerin metodeihin.
	@PathVariable: Käytetään ottamaan muuttuja URI:sta controllerin metodin käsiteltäväksi.
