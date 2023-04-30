package org.aguzman.apiservlet.webapp.headers.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.aguzman.apiservlet.webapp.headers.configs.ProductoServicePrincipal;
import org.aguzman.apiservlet.webapp.headers.configs.Service;
import org.aguzman.apiservlet.webapp.headers.interceptors.Logging;
import org.aguzman.apiservlet.webapp.headers.interceptors.TransactionalJpa;
import org.aguzman.apiservlet.webapp.headers.models.entities.Categoria;
import org.aguzman.apiservlet.webapp.headers.models.entities.Producto;
import org.aguzman.apiservlet.webapp.headers.repositories.Repository;
import org.aguzman.apiservlet.webapp.headers.repositories.RepositoryJpa;

import java.util.List;
import java.util.Optional;

//@Logging                  EL INTERCEPTOR TAMBN PUEDE IR A NIVEL DE CLASE E INTERCEPTA TODOS LOS METODOS DE LA CLASE.
@Service
@ProductoServicePrincipal //ESTA ES OTRA MANERA DE DEFINIR UN SERVICIO PRINCIPAL.           //@Named("defecto")
@TransactionalJpa //534
public class ProductoServiceImpl implements ProductoService{

    @Inject
    @RepositoryJpa //SE INDICA Q INYECTE EL REPOSITORIO Q TIENE ESTA ANOTACIÓN.
    private Repository<Producto> repositoryJdbc;

    @Inject
    @RepositoryJpa
    private Repository<Categoria> repositoryCategoriaJdbc;


    @Override
    @Logging                                                                                                        //SE APLICA INTERCEPTOR
    public List<Producto> listar() {
        try {
            return repositoryJdbc.listar();
        } catch (Exception throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    @Logging
    public Optional<Producto> porId(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbc.porId(id));
        } catch (Exception throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            repositoryJdbc.guardar(producto);
        } catch (Exception throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            repositoryJdbc.eliminar(id);
        } catch (Exception throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategoria() {
        try {
            return repositoryCategoriaJdbc.listar();
        } catch (Exception throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        try {
            return Optional.ofNullable(repositoryCategoriaJdbc.porId(id));
        } catch (Exception throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
