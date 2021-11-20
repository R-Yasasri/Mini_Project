package org.acme;

import com.example.petstore.Pet;
import com.example.petstore.PetType;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PetResourceTest {

	static Pet testPet;

	public PetResourceTest(){
		testPet=new Pet();
		testPet.setPetAge(3);
		testPet.setPetName("Boola");

		PetType petType=PetTypeResourceTest.testPetType;
		petType.setIdpetType(1);

		testPet.setIdpetType(petType);
	}

	@Test
	@Order(1)
	public void testSavePetsEndpoint(){

		PetTypeResourceTest petTypeTest=new PetTypeResourceTest();
		petTypeTest.testSavePettypeEndpoint();

		Pet pet=PetResourceTest.testPet;

		given()
				.body(pet).contentType("Application/json").when().post("/pets")
				.then()
				.statusCode(200)
				.body("petId",isA(Integer.class))
				.body("idpetType", hasEntry("idpetType",pet.getIdpetType().getIdpetType()))
				.body("petAge", equalTo(pet.getPetAge()))
				.body("petName", equalTo(pet.getPetName()));
	}

	@Test
	@Order(2)
	public void testGetAllPettypesEndpoint(){
		List<Map<String, Object>> list = given().when().get("/pets")
				.then()
				.extract()
				.body()
				.as(new TypeRef<List<Map<String, Object>>>() {
				});

		for(Map<String,Object> item:list){

			assertThat(item,allOf(hasKey("petId"),hasKey("petAge"),hasKey("petName"),hasKey("idpetType")));

			assertThat(item.get("petId"),isA(Integer.class));
			assertThat(item.get("petAge"),isA(Integer.class));
			assertThat(item.get(("petName")),notNullValue());

			assertThat(item.get("idpetType"),instanceOf(Map.class));

			Map petTypeMap=(Map)item.get("idpetType");
			assertThat(petTypeMap.get("idpetType"),isA(Integer.class));
			assertThat(petTypeMap.get("petType"),notNullValue());
			assertThat(petTypeMap.get("status"),equalTo(1));
		}
	}

	@Test
	@Order(3)
	public void testEditPetEndpoint(){

		Pet pet=PetResourceTest.testPet;
		pet.setPetName("Buula");
		pet.setPetId(1);

		given().body(pet).contentType("Application/json").when().put("/pets")
				.then()
				.statusCode(200)
				.body("petId",isA(Integer.class))
				.body("idpetType", hasEntry("idpetType",pet.getIdpetType().getIdpetType()))
				.body("petAge", equalTo(pet.getPetAge()))
				.body("petName", equalTo(pet.getPetName()));
	}
	@Test
	@Order(4)
	public void testGetPetByNameEndpoint(){
		Pet pet = PetResourceTest.testPet;
		given().when().get("/pets/findbyname/Buula")
				.then()
				.statusCode(200)
				.body("petId",isA(Integer.class))
				.body("idpetType", hasEntry("idpetType",pet.getIdpetType().getIdpetType()))
				.body("petAge", equalTo(pet.getPetAge()))
				.body("petName", equalTo("Buula"));
	}
	@Test
	@Order(5)
	public void testGetPetByIdEndpoint(){
		Pet pet = PetResourceTest.testPet;
		given().when().get("/pets/1")
				.then()
				.statusCode(200)
				.body("petId",isA(Integer.class))
				.body("idpetType", hasEntry("idpetType",pet.getIdpetType().getIdpetType()))
				.body("petAge", equalTo(pet.getPetAge()))
				.body("petName", equalTo("Buula"));// Because the name is updated to Buula from Boola in testEditPetEndpoint() method
	}

	@Test
	@Order(6)
	public void testDeletePetsEndpoint(){
		Pet pet = PetResourceTest.testPet;
		given().when().delete("/pets/1")
				.then()
				.statusCode(200)
				.body("petId",isA(Integer.class))
				.body("idpetType", hasEntry("idpetType",pet.getIdpetType().getIdpetType()))
				.body("petAge", equalTo(pet.getPetAge()))
				.body("petName", equalTo("Buula"));
	}

}