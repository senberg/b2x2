package com.b2x2.data;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ToyService {
    private final ToyPersistence toyPersistence;

    public void play(){
        Toy pluto = new Toy();
        pluto.setName("Pluto");
        toyPersistence.saveOrUpdate(pluto);

        Toy p2 = toyPersistence.get(pluto.getId());
        p2.setName("Mouse");
        toyPersistence.saveOrUpdate(p2);

        Toy p3 = toyPersistence.get(p2.getId());
        System.out.println(p3.getName());
    }
}
