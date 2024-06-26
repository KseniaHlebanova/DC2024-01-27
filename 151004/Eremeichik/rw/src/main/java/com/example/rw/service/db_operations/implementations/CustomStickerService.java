package com.example.rw.service.db_operations.implementations;

import com.example.rw.exception.model.not_found.EntityNotFoundException;
import com.example.rw.model.entity.implementations.News;
import com.example.rw.model.entity.implementations.Sticker;
import com.example.rw.model.entity.implementations.User;
import com.example.rw.repository.interfaces.StickerRepository;
import com.example.rw.service.db_operations.interfaces.StickerService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomStickerService implements StickerService {

    private final StickerRepository stickerRepository;

    @Override
    @Cacheable(value = "sticker", key = "#id")
    public Sticker findById(Long id) throws EntityNotFoundException {
        return stickerRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public List<Sticker> findAll() {
        return stickerRepository.findAll();
    }

    @Override
    public void save(Sticker entity) {
        stickerRepository.save(entity);
    }

    @Override
    @CacheEvict(value = "sticker", key = "#id")
    public void deleteById(Long id) throws EntityNotFoundException {
        boolean wasDeleted = stickerRepository.findById(id).isPresent();
        if(!wasDeleted){
            throw new EntityNotFoundException(id);
        } else{
            stickerRepository.deleteById(id);
        }
    }

    @Override
    @CachePut(value = "sticker", key="#entity.id")
    public Sticker update(Sticker entity) {
        boolean wasUpdated = stickerRepository.findById(entity.getId()).isPresent();
        if(!wasUpdated){
            throw new EntityNotFoundException();
        } else{
            stickerRepository.save(entity);
        }
        return entity;
    }
}
