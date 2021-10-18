package com.mycompany.u0.e1.nominas.laboral;

public class Persona {

    public String nombre,dni;
    public char sexo;

    public Persona(String nombre, String dni, char sexo) throws DatosNoCorrectosException {
        this.nombre = nombre;
        this.dni = dni;
        if (sexo == 'M' || sexo == 'F')
            this.sexo = sexo;
        else
            throw new DatosNoCorrectosException("Datos no correctos.");
    }

    public Persona(String nombre, char sexo) throws DatosNoCorrectosException {
        this.nombre = nombre;
        if (sexo == 'M' || sexo == 'F')
            this.sexo = sexo;
        else
            throw new DatosNoCorrectosException("Datos no correctos.");
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
    public String imprime() {
        return "nombre=" + nombre + ", dni=" + dni;
    }
}
