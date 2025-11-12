package com.example.kmdigital.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kmdigital.Model.Region;
import com.example.kmdigital.Repository.RegionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    public Region findById(Integer id) {
        Region region = regionRepository.findById(id).orElse(null);
        return region;
    }

    public Region save(Region region) {
        return regionRepository.save(region);
    }

    public Region update(Region region) {
        return regionRepository.save(region);
    }

    public Region partialUpdate(Region region){
        Region existingRegion = regionRepository.findById(region.getId()).orElse(null);
        if (existingRegion != null) {
            if (region.getNombre() != null) {
                existingRegion.setNombre(region.getNombre());
            }
            return regionRepository.save(existingRegion);
        }
        return null;
    }

    public void deleteById(Integer id) {
        regionRepository.deleteById(id);
    }
}