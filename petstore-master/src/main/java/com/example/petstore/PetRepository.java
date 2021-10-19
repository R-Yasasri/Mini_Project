package com.example.petstore;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PetRepository implements PanacheRepository<Pet> {

    public Pet findByName(String name){
        return find("petName",name).firstResult();
    }

    public Pet findByPetId(int id){
        return find("petId",id).firstResult();
    }
}
