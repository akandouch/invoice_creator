package com.akandouch.invoicec.service;

import com.akandouch.invoicec.domain.UnitOfMeasure;
import com.akandouch.invoicec.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;
    @Override
    public UnitOfMeasure save(UnitOfMeasure unitOfMeasure) {
        return this.unitOfMeasureRepository.save(unitOfMeasure);
    }

    @Override
    public UnitOfMeasure findOne(String id) {
        return this.unitOfMeasureRepository.findById(id).orElse(new UnitOfMeasure());
    }

    @Override
    public List<UnitOfMeasure> findAll() {
        return this.unitOfMeasureRepository.findAll();
    }

    @Override
    public Page<UnitOfMeasure> findAllByPage(Integer pageSize, Integer pageNumber) {
        return this.unitOfMeasureRepository.findAll(PageRequest.of(pageNumber,pageSize));
    }

    @Override
    public void delete(String id) {
        this.unitOfMeasureRepository.deleteById(id);
    }
}
