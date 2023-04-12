package jee.master.models.service;

import jee.master.models.entity.Division;

import java.util.List;
import java.util.Optional;

public interface IDivisionService {

    List<Division> divisionsList();
    Optional<Division> getDivisionById(Long id);

}
