package jpajsf;


import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationPath("jsonapi")
@Path("asiakasjson") // polku on: localhost:8080/T3_03/jsonapi/asiakasjson
public class AsiakasApiJson extends Application {

	@EJB
	private AsiakasService as;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAsiakkaat() {
		List<Asiakas> asiakkaat = as.getAllAsiakkaat();
		GenericEntity<List<Asiakas>> list = new GenericEntity<List<Asiakas>>(asiakkaat) {
		};
		return Response.ok(list).build();
	}
	
	@GET
	@Path("{id:}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAsiakasById(@PathParam("id") int id) {
		Asiakas asiakas = as.getAsiakasById(id);
		return Response.ok(asiakas).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveAsiakas(Asiakas asiakas) {
		asiakas = as.saveAsiakas(asiakas);
		return Response.ok(asiakas).build();
	}
	
	@DELETE
	@Path("{id:}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAsiakas(@PathParam("id") int id) {
		Asiakas asiakas = as.deleteAsiakas(id);
		return Response.ok(asiakas).build();
	}
}
