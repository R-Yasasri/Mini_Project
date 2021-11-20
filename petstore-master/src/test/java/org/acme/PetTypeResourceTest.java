package org.acme;

import com.example.petstore.PetType;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PetTypeResourceTest {

    static PetType testPetType = new PetType();

    public PetTypeResourceTest() {

        testPetType = new PetType();
        testPetType.setPetType("Fish");
        testPetType.setStatus(1);
    }

    @Test
    @Order(1)
    public void testSavePettypeEndpoint() {

        PetType type = PetTypeResourceTest.testPetType;

        given()
                .body(type).contentType("Application/json").when().post("/pettypes")
                .then()
                .statusCode(200)
                .body("idpetType", isA(Integer.class))
                .body("petType", equalTo(type.getPetType()))
                .body("status", equalTo(type.getStatus()));

    }

    @Test
    @Order(2)
    public void testGetAllPettypesEndpoint() {

        List<Map<String, Object>> list = given().when().get("/pettypes")
                .then()
                .extract()
                .body()
                .as(new TypeRef<List<Map<String, Object>>>() {
                });

        for( Map<String,Object> item: list){
            assertThat(item,allOf(hasKey("idpetType"),hasKey("petType"),hasKey("status")));

            assertThat(item.get("idpetType"),isA(Integer.class));
            assertThat(item.get("petType"),notNullValue());
            assertThat(item.get("status"),equalTo(1));
        }
    }

    @Test
    @Order(3)
    public void testEditPettypeEndpoint() {

        PetType type = PetTypeResourceTest.testPetType;
        type.setPetType("Dog");
        type.setIdpetType(1);

        given().body(type).contentType("Application/json").when().put("/pettypes")
                .then()
                .statusCode(200)
                .body("idpetType", equalTo(1))
                .body("petType", equalTo(type.getPetType()))
                .body("status", equalTo(1));

    }

    @Test
    @Order(4)
    public void testGetPettypeByNameEndpoint() {
        PetType type = PetTypeResourceTest.testPetType;
        given().when().get("/pettypes/findbyname/Dog")
                .then()
                .statusCode(200)
                .body("idpetType", not(notANumber()))
                .body("petType", equalTo("Dog"))
                .body("status", equalTo(1));
    }

    @Test
    @Order(5)
    public void testGetPettypeByIdEndpoint() {
        given().when().get("/pettypes/1")
                .then()
                .statusCode(200)
                .body("idpetType", equalTo(1))
                .body("petType", notNullValue())
                .body("status", equalTo(1));
    }

    @Test
    @Order(6)
    public void testDeletePettypeEndpoint() {
        given().when().delete("/pettypes/1")
                .then()
                .statusCode(200)
                .body("idpetType", equalTo(1))
                .body("petType", notNullValue())
                .body("status", equalTo(0));
    }
}
