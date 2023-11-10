package com.example.Rent_a_Car.services.impl;

import com.example.Rent_a_Car.model.dto.BrandDTO;
import com.example.Rent_a_Car.model.dto.ModelDTO;
import com.example.Rent_a_Car.repository.BrandRepository;
import com.example.Rent_a_Car.services.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BrandDTO> getAllBrands() {
        List<BrandDTO> brands = this.brandRepository
                .getAll()
                .stream()
                .map(brandEntity -> {
                    BrandDTO brandDTO = modelMapper.map(brandEntity, BrandDTO.class);
                    brandDTO.setModel(brandEntity.getModels().stream()
                            .map(modelEntity -> modelMapper.map(modelEntity, ModelDTO.class)).toList());
                    return brandDTO;
                }).toList();

        return brands;
    }
}
