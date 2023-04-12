package jee.master.models.service;

import jee.master.models.dao.DivisionRepository;
import jee.master.models.dao.IGenericRepository;
import jee.master.models.entity.Division;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DivisionService implements IDivisionService{

    public DivisionService(Connection connection) {
        this.divisionRepository = new DivisionRepository(connection);
    }

    @Override
    public List<Division> divisionsList() {
        try {
            return divisionRepository.list();
        } catch (SQLException e) {
            throw new ExceptionJdbcService(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Division> getDivisionById(Long id) {
        try {
            return Optional.ofNullable(divisionRepository.getById(id));
        } catch (SQLException e) {
            throw new ExceptionJdbcService(e.getMessage(),e.getCause());
        }
    }

    private IGenericRepository<Division> divisionRepository;
}
