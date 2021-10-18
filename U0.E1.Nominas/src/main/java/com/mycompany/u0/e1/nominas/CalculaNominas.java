package com.mycompany.u0.e1.nominas;

import com.mycompany.u0.e1.nominas.laboral.*;
import java.sql.*;
import java.io.*;
import java.util.Scanner;


public class CalculaNominas {
    
    /*private static void escribe(Empleado e1, Empleado e2) {
        Nomina n = new Nomina();
        e1.Imprime();
        System.out.println(n.sueldo(e1));
        e2.Imprime();
        System.out.println(n.sueldo(e2));
    }
    
    public static void main(String[] args) throws DatosNoCorrectosException {
        Empleado e1 = new Empleado("James Cosling", "32000032G", 'M', 4, 7);
        Empleado e2 = new Empleado("Ada Lovelace", "32000031R", 'F');
        
        escribe(e1,e2);
        e2.incrAnyo();
        e2.incrAnyo();
        e1.setCategoria(9);
        escribe(e1,e2);
    }
}*/

    public static void main( String[] args ){
		boolean salir = false;		
		
		Scanner numeros = new Scanner(System.in);
		Scanner letras = new Scanner(System.in);
		String dni;
				
		while (!salir) {
		
			System.out.println("1. Ver todos los empleados \n"
					+ "2. Ver salario de un empleado especifico \n"
					+ "3. Submenu de edicion \n"
					+ "4. Recalcular y actualizar sueldo de un empleado \n"
					+ "5. Recalcular y actualizar sueldos de todos los empleados \n"
					+ "6. Realizar copia de seguridad de la BBDD en fichero \n"
					+ "7. Alta de nuevos empleados \n"
					+ "8. Dar de baja empleado \n"
					+ "9. Cargar backup-empleados.txt a la base de datos \n"
					+ "0. Salir ");
			String menuprincipal = letras.nextLine();
		
			switch (menuprincipal) {
			case "1": 
				for(Empleado e : EmpleadosDAO.getEmpleados()) {
					System.out.println(e.imprime());
				}
				break;
			
			case "2":
				System.out.println("Introduce el DNI del empleado del que quieres ver el sueldo");
				dni = letras.nextLine();
				int sueldo=EmpleadosDAO.getSueldo(dni);
				
				if( sueldo > 0) {
					System.out.println("El salario del empleado con DNI: "+dni+" es de "+sueldo+"€.");
				}else{
					System.out.println("El empleado con ese DNI no existe");
				}
				break;
				
			case "3":
				System.out.println("Introduce el DNI del empleado a editar");
				dni = letras.nextLine().toUpperCase();
				
				if(EmpleadosDAO.exists(dni)) {
					System.out.println("Que campos deseas editar? \n"
							+ "1. Nombre \n"
							+ "2. Sexo \n"
							+ "3. Categoria \n"
							+ "4. Anyos \n"
							+ "5. Todos los campos \n"
							+ "Introduce cualquier otra tecla para salir.");
					String opcioneditar = letras.nextLine();
					
					Empleado e = EmpleadosDAO.getEmpleado(dni);
					
					switch (opcioneditar) {
					case "1":
						System.out.println("Introduce el nuevo nombre");
						e.nombre = letras.nextLine();
						EmpleadosDAO.updateEmpleado(e);
						break;
					case "2":
						System.out.println("Introduce el nuevo sexo F/M");
						e.sexo = letras.nextLine().charAt(0);
						EmpleadosDAO.updateEmpleado(e);
						break;
					case "3":
						System.out.println("Introduce la nueva categoria");
						try {
							e.setCategoria(numeros.nextInt());
							EmpleadosDAO.updateEmpleado(e);
						}catch (DatosNoCorrectosException ex) {
							ex.printStackTrace();
						}
						break;
					case "4":
						System.out.println("Introduce el nuevo anyo");
						e.anyos = numeros.nextInt();
						EmpleadosDAO.updateEmpleado(e);
						break;
					case "5":
						System.out.println("Introduce el nuevo nombre");
						e.nombre = letras.nextLine();
						System.out.println("Introduce el nuevo sexo F/M");
						e.sexo = letras.nextLine().charAt(0);
						System.out.println("Introduce la nueva categoria");
						try {
							e.setCategoria(numeros.nextInt());
						}catch (DatosNoCorrectosException ex) {
							ex.printStackTrace();
						}						
						System.out.println("Introduce el nuevo anyo");
						e.anyos = numeros.nextInt();
						EmpleadosDAO.updateEmpleado(e);
						break;
					default:
						System.out.println("OK");
						break;
					}
					
				}
				
				break;
				
			case "4":
				
				System.out.println("Introduce el DNI del empleado a recalcular el sueldo");
				dni = letras.nextLine();
				
				if(EmpleadosDAO.exists(dni)) {
					EmpleadosDAO.updateSueldo(dni);
					System.out.println("Consulta enviada");
				}else {
					System.out.println("Empleado inexistente");
				}
								
				break;
				
			case "5":
				
				for(Empleado e : EmpleadosDAO.getEmpleados()) {
					EmpleadosDAO.updateSueldo(e.dni);
				}
				System.out.println("Consulta enviada");
				
				break;
				
			case "6":
				
				for(Empleado e : EmpleadosDAO.getEmpleados()) {
					EmpleadosDAO.backup(e);
				}
				System.out.println("Hecho :)");
				
				break;
				
			case "7":
				System.out.println("Alta manual o mediante fichero empleadosNuevos.txt? \n"
						+ "1. Manual \n"
						+ "2. Fichero \n");
				String opcionesalta = letras.nextLine();
				String nombre, dnie;
				char sexo;
				int categoria, anyos;
				
				switch (opcionesalta) {
				case "1":
					System.out.println("Introduce el nombre del empleado");
					nombre = letras.nextLine();
					System.out.println("Introduce el DNI del empleado");
					dnie = letras.nextLine().trim().toUpperCase();
					System.out.println("Introduce el Sexo del empelado F/M");
					sexo = letras.nextLine().charAt(0);
					System.out.println("Introduce la categoria del empleado");
					categoria=numeros.nextInt();
					System.out.println("Introduce los anyos trabajados del empleado");
					anyos = numeros.nextInt();
					
					EmpleadosDAO.altaEmpleado(nombre, dnie, sexo, categoria, anyos);
					System.out.println("Consulta Enviada");
									
					break;
				
				case "2":
					System.out.println("Introduce el nombre del fichero con la información de los empleados");
					EmpleadosDAO.altaEmpleado(letras.nextLine());
					System.out.println("Consulta Enviada");
					break;

				default:
					System.out.println("Operación cancelada");
					break;
				}
				
				break;
				
			case "8":
				
				System.out.println("Introduce el DNI del empleado a dar de baja");
				dni = letras.nextLine();
				EmpleadosDAO.bajaEmpleado(dni);
				System.out.println("Consulta enviada");
				
				break;
				
			case "9":
				
				EmpleadosDAO.altaEmpleado("backup-empleados.txt");
				System.out.println("Hecho :)");
				
				break;
				
			case "0":
				
				System.out.println("Hasta otra :)");
				EmpleadosDAO.cerrarConexion();
				salir=true;
				break;
			}
		
		};
			
		letras.close();
		numeros.close();
	}
    }