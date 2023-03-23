package com.example.groupassessment.seeds;

import com.example.groupassessment.enitity.Type;
import com.example.groupassessment.repository.TypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TypeSeed {
    private TypeRepo typeRepo;
    @Autowired
    private TypeSeed(TypeRepo typeRepo){
        this.typeRepo = typeRepo;
    }
    @EventListener
    public void seed(ContextRefreshedEvent event){
        try{
            List<String> seeds = new ArrayList<String>();
            seeds.add("other");
            seeds.add("movable");
            seeds.add("immovable");
            for (String seed: seeds){
                if (typeRepo.findByName(seed) != null){
//                logger.info();
                    System.out.println("\"" + seed + "\"" + " seed already existed!");
                    continue;
                }
                Type t = new Type();
                t.setName(seed);
                Type type = typeRepo.save(t);
                System.out.println("Type seeded " + "\"" + t.getName() + "\"");
            }
        }catch(Exception e){
            System.out.println("Seeding data ran into problem! " + e.getMessage());
        }
    }
}
