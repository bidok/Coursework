package com.example.demo.service.model.impls;

import com.example.demo.data.FakeData;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.CarClass;
import com.example.demo.model.Marka;
import com.example.demo.model.Modell;
import com.example.demo.repository.car.CarRepository;
import com.example.demo.repository.model.ModelRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.model.interfaces.IModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : bidok
 * @created : 29.04.2021, четверг
 * @className : ModelServiceImpl
 **/
@Service
public class ModelServiceImpl implements IModelService, IGenericService<Modell> {
    @Autowired
    private ModelRepository repository;
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private FakeData fakeData;

    @Override
    public Modell getById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("model with id: [" + id + "] not found"));
    }

    @Override
    public List<Modell> getAll() {
        return repository.findAll();
    }

    @Override
    public Modell save(Modell modell) {
        Marka[] markas = Marka.values();
        CarClass[] carClasses = CarClass.values();

        if(this.getAll().stream().anyMatch(item -> item.getId().equals(modell.getId()))){
            modell.setUpdateTime(LocalDateTime.now());
            modell.setCreateTime(getById(modell.getId()).getCreateTime());
        }

        if(Arrays.stream(markas).filter(item -> item.equals(modell.getMarka())).count() == 0){
            throw new InvalidDataException("incorrect [" + modell.getMarka() + "] marka");
        }
        else if (Arrays.stream(carClasses).filter(item -> item.equals(modell.getCarClass())).count() == 0){
            throw new InvalidDataException("incorrect [" + modell.getMarka() + "] car class");
        }
        else return repository.save(modell);
    }

    @Override
    public Modell deleteById(String id) {
        Modell modell = this.getById(id);
        carRepository.saveAll(carRepository.findAllByModellId(id).stream()
                .peek(item -> {
                    item.setModell(this.getById("60928ca1ad2dd355bc15f7a3"));
                }).collect(Collectors.toList()));
        repository.deleteById(id);
        return modell;
    }

}
