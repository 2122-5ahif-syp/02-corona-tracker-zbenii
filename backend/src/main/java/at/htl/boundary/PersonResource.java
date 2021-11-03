package at.htl.boundary;

import at.htl.controller.PersonRepository;
import at.htl.entities.Person;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/api/person")
@Transactional
@RequestScoped
public class PersonResource {
    @Inject
    PersonRepository repo;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createEntry(@FormParam("fName") String firstName,
                                @FormParam("lName") String lastName,
                                @FormParam("email") String email,
                                @FormParam("telNum") String telephoneNum,
                                @Context UriInfo uriInfo) {

        Person person = new Person(firstName,lastName,email,telephoneNum);
        repo.persist(person);
        return Response
                .ok()
                .entity(person)
                .build();
    }
}
