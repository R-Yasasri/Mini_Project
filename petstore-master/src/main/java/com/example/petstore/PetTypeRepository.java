package com.example.petstore;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PetTypeRepository implements PanacheRepository<PetType> {

    public PetType findByPetType(String petType){
        PetType p=find("petType",petType).firstResult();
        return checkStatus(p);
    }

    public PetType findByPetTypeId(Integer id){
        PetType p= find("idpetType",id).firstResult();
        return checkStatus(p);
    }

    public List<PetType> getPetTypes(){
       return find("status",1).list();
    }

    private PetType checkStatus(PetType petType){
        if (petType.getStatus()==0){
            return null;
        }else{
            return petType;
        }
    }
}
