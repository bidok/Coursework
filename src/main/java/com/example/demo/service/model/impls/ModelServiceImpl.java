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
import com.example.demo.service.customer.impls.CustomerServiceImpl;
import com.example.demo.service.model.interfaces.IModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private static  final Logger LOGGER = LoggerFactory.getLogger(ModelServiceImpl.class);

    @Override
    public Modell getById(String id) {
        LOGGER.info("method get by id [" + id + "] was called");
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("model with id: [" + id + "] not found"));
    }

    @Override
    public List<Modell> getAll() {
        LOGGER.info("method get all was called");
        return repository.findAll().stream()
                .filter(Objects::nonNull)
                .filter(item -> item.getName() != null && !item.getName().equals("undefined"))
                .collect(Collectors.toList());
    }

    @Override
    public Modell save(Modell modell) {
        LOGGER.info("method save was called ");
        Marka[] markas = Marka.values();
        CarClass[] carClasses = CarClass.values();

        if(this.getAll().stream().anyMatch(item -> item.getId().equals(modell.getId()))){
            LOGGER.info("object with id: [" + modell.getId() + "] was updated");
            modell.setUpdateTime(LocalDateTime.now());
            modell.setCreateTime(getById(modell.getId()).getCreateTime());
        }
        else LOGGER.info("object was created");
        if(Arrays.stream(markas).filter(item -> item.equals(modell.getMarka())).count() == 0){
            throw new InvalidDataException("incorrect [" + modell.getMarka() + "] marka");
        }
        if (Arrays.stream(carClasses).filter(item -> item.equals(modell.getCarClass())).count() == 0){
            throw new InvalidDataException("incorrect [" + modell.getMarka() + "] car class");
        }
        if(Stream.of(modell.getMarka(), modell.getName(), modell.getCarClass(), modell.getDate()).anyMatch(Objects::isNull)){
            throw new InvalidDataException("some fieal in object are null");
        }
        return repository.save(modell);
    }

    @Override
    public Modell deleteById(String id) {
        LOGGER.info("method delete was called");
        Modell modell = this.getById(id);
        carRepository.saveAll(carRepository.findAllByModellId(id).stream()
                .peek(item -> {
                    item.setModell(this.getById("60928ca1ad2dd355bc15f7a3"));
                }).collect(Collectors.toList()));
        repository.deleteById(id);
        LOGGER.info("object with id:[" + id +"] was deleted");
        return modell;
    }

}
