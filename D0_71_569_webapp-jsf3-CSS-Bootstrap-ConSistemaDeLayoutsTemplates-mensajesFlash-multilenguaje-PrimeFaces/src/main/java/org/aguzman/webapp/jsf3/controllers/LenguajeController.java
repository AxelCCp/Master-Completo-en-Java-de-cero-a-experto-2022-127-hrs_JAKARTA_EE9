package org.aguzman.webapp.jsf3.controllers;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

//567
//1 - COMO ESTE OBJ SE GUARDA EN LA SESION HTTP, HAY Q IMPLEMENTAR SERIALIZABLE, PARA QUE SE SERIALICE ENTRE REQUEST Y REQUEST. (ESCUCHA LA EXPLICACIÓN AL INICIO DE LA CLASE).
//2 - SE CREA UN MÉTODO PARA INICIALIZAR EL BEANS. CADA VEZ Q SE CREA ESTE OBJ EN LA SESSION HTTP, HAY Q INICIALIZARLO. SE USA POSTCONSTRUCT PARA Q SE EJECUTE DESPUES DE LA INICIALIZACIÓN.
//3 - SE INICIALIZAN LOS LENGUAJES SOPORTADOS Y SE INICIALIZA LA LOCALIZACIÓN.
    //SE OBTIENE LA LOCALIZACIÓN POR DEFECTO. EL MESSAGE.PROPERTIES.
//4 - METODO DE EVENTO PARA CAMBIAR EL LENGUAJE SELECCIONADO EN EL FRONT.

// ..... LUEGO SE VA A CONFIGURAR EN EL MAIN.XHTML

@Named
@SessionScoped
public class LenguajeController implements Serializable {

    private static final long serialVersionUID = 1237486L;
    private Locale locale;
    private String lenguaje;
    private Map<String, String>lenguajesSoportados;

    //2
    @PostConstruct
    public void init(){
        locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();    //3
        lenguajesSoportados = new HashMap<>();
        lenguajesSoportados.put("Ingles", "en");
        lenguajesSoportados.put("Español", "es");
    }

    //4
    public void seleccionar(ValueChangeEvent e){
        String nuevoLenguaje = e.getNewValue().toString();
        lenguajesSoportados.values().forEach(v -> {
            if(v.equals(nuevoLenguaje)){
                this.locale = new Locale(nuevoLenguaje);
                FacesContext.getCurrentInstance().getViewRoot().setLocale(this.locale);     //SE CAMBIA EL IDIOMA EN EL CONTEXTO DE JSF
            }
        });
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public Map<String, String> getLenguajesSoportados() {
        return lenguajesSoportados;
    }

    public void setLenguajesSoportados(Map<String, String> lenguajesSoportados) {
        this.lenguajesSoportados = lenguajesSoportados;
    }
}
