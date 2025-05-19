package com.b2x2.data;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ToyPersistence {
    private final ToyRepository toyRepository;

    @Transactional
    public void saveOrUpdate(Toy toy) {
        if(toy.getId() == null) {
            ToyEntity toyEntity = new ToyEntity();
            BeanUtils.copyProperties(toy, toyEntity);
            toyEntity = toyRepository.save(toyEntity);
            BeanUtils.copyProperties(toyEntity, toy);
        } else {
            ToyEntity toyEntity = toyRepository.getReferenceById(toy.getId());
            BeanUtils.copyProperties(toy, toyEntity);
        }
    }

    public Toy get(Long id) {
        ToyEntity toyEntity = toyRepository.getReferenceById(id);
        Toy toy = new Toy();
        BeanUtils.copyProperties(toyEntity, toy);
        return toy;
    }
}
