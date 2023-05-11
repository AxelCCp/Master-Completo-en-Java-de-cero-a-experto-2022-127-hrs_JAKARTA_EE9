package jee.master.jsf.converters;

//561  - SE NECESITARÁ UN CONVERTIDOR POR CADA CAMPO O DEPENDENCIA Q SE TENGA EN EL FORMULARIO. POR CADA LISTA. POR EJEMPLO PRODUCTO DEPENDE DE CATEGORIAS.
//ESTA CLASE SE ASIGNA EN EL FORM.XHTML CON <f:converter binding="#{categoriaConverter}"/>

//1 - DEBE SER UN COMPONENTE CDI DEL TIPO DEL REQUEST.

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jee.master.jsf.entities.Categoria;
import jee.master.jsf.services.ProductoService;

import java.lang.annotation.Annotation;
import java.util.Optional;

@RequestScoped
@Named("categoriaConverter")
public class CategoriaConverter implements Converter<Categoria> {

    @Inject
    private ProductoService service;

    @Override
    public Categoria getAsObject(FacesContext context, UIComponent component, String id) {
        if(id == null){
            return  null;
        }
        Optional<Categoria>categoriaOptional = service.porIdCategoria(Long.valueOf(id));

        if(categoriaOptional.isPresent()){
            return categoriaOptional.get();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Categoria categoria) {
        if(categoria == null){
            return "0";
        }
        return categoria.getId().toString();
    }
}
