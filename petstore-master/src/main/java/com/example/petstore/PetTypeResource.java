package com.example.petstore;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/pettypes")
@Produces("application/json")
public class PetTypeResource {
    @Inject
    PetTypeRepository repo;

    @GET
    public Response getPetTypes(){
       List list= repo.getPetTypes();
       return Response.ok(list).build();
    }

    @GET
    @Path("{id}")
    public Response getPet(@PathParam("id") int id){
       PetType petType= repo.findByPetTypeId(id);
       return Response.ok(petType).build();
    }

    @POST
    @Transactional
    public Response savePetType(@RequestBody PetType petType){
        repo.persist(petType);
        return Response.ok(petType).build();
    }

    @PUT
    @Transactional
    public Response updatePetType(@RequestBody PetType petType){

       PetType type= repo.findByPetTypeId(petType.getIdpetType());
       type.setPetType(petType.getPetType());
       repo.persist(type);

       return Response.ok(type).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deletePetType(@PathParam("id") int id){
        PetType petType=repo.findByPetTypeId(id);
        petType.setStatus(0);
        repo.persist(petType);
        return Response.ok(petType).build();
    }

    @GET
    @Path("findbyname/{petType}")
    public Response findByName(@PathParam("petType") String type){
       PetType petType= repo.findByPetType(type);
       return Response.ok(petType).build();
    }
}