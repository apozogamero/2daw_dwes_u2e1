package com.mycompany.u0.e1.nominas.laboral;

public class Empleado extends Persona {
    private int categoria;
    public int anyos;

    public Empleado(String nombre, String dni, char sexo) throws DatosNoCorrectosException {
        super(nombre, dni, sexo);
        categoria = 1;
        anyos = 0;
    }

    public Empleado(String nombre, String dni, char sexo, int categoria, int anyos) throws DatosNoCorrectosException {
        super(nombre, dni, sexo);
        if (categoria < 10 || categoria > 1 || anyos > 0) {
            this.categoria = categoria;
            this.anyos = anyos;
        } else
            throw new DatosNoCorrectosException("Datos no correctos.");
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) throws DatosNoCorrectosException {
        if (categoria < 10 || categoria > 1)
            this.categoria = categoria;
        else
            throw new DatosNoCorrectosException("Datos no correctos.");
    }
    
    public void incrAnyo() {
        anyos++;
    }
    
    public String imprime() {
        return "Empleado ["+ super.imprime() +", categoria=" + categoria + ", anyos=" + anyos + "]";
    }
}