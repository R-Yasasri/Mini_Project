package com.example.petstore;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/pets")
@Produces("application/json")
public class PetResource {

	@Inject
	PetRepository repo;

	@Inject
	PetTypeRepository petTypeRepo;

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	public Response getPets() {
		List<Pet> pets = repo.listAll();
		return Response.ok(pets).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@GET
	@Path("{petId}")
	public Response getPet(@PathParam("petId") int petId) {
		if (petId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		Pet pet = repo.findByPetId(petId);
		return Response.ok(pet).build();
		
	}

	@POST
	@Transactional
	public Response savePet(@RequestBody(name="pet") Pet pet){

		repo.persist(pet);
		return Response.ok(pet).build();
	}

	@PUT
	@Transactional
	public Response updatePet(@RequestBody Pet pet){

		Pet p=repo.findByPetId(pet.getPetId());
		p.setPetAge(pet.getPetAge());
		p.setPetName(pet.getPetName());
		p.setIdpetType(pet.getIdpetType());
		repo.persist(p);
		return Response.ok(p).build();
	}

	@DELETE
	@Path("{id}")
	@Transactional
	public Response deletePet(@PathParam("id") int id){
		Pet pet=repo.findByPetId(id);
		repo.delete(pet);
		return Response.ok(pet).build();
	}

	@GET
	@Path("findbyname/{name}")
	public Response findByName(@PathParam(("name"))String name){
		Pet pet=repo.findByName(name);
		return Response.ok(pet).build();
	}
}
