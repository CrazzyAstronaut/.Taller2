package Logica;

import java.sql.Date;
import java.util.Scanner;

public class TestDate {

	private static Scanner s;
	
	public static void main(String[] args) {
		while(true) {
		int Nmenu = ingresarFecha();
		System.out.println(Nmenu);
		}
	}
	
	public static int ingresarFecha() {
		s = new Scanner(System.in);
		System.out.println("dia: ");
		String dia = s.nextLine();
		System.out.println("mes: ");
		String mes = s.nextLine();
		while(diaCorrecto(Integer.parseInt(dia),Integer.parseInt(mes))==false) {
			System.out.println("dia: ");
			dia = s.nextLine();
			System.out.println("mes: ");
			 mes = s.nextLine();
		}
		int m = Integer.parseInt(mes);
		int d = Integer.parseInt(dia);
		Date fI = new Date(121,m-1,d);
		Date fi1 = new Date(121,2,8);
		Date ff1 = new Date(121,4,2);
		Date fi2 = new Date(121,4,3);
		Date ff2 = new Date(121,6,11);
		Date fi3 = new Date(121,6,12);
		Date ff3 = new Date(121,6,25);
		Date f4 = new Date(121,6,26);
		System.out.println(fI);
		System.out.println(fi1);
		System.out.println(ff1);
		if((fI.after(fi1)&&fI.before(ff1))||fI.equals(fi1)||fI.equals(ff1)) {
			System.out.println("Inicio Semestre");
			return 0;
		}
		if((fI.after(fi2)&&fI.before(ff2))||fI.equals(fi2)||fI.equals(ff2)) {
			System.out.println("Mitad Semestre");
			return 1;
		}
		if((fI.after(fi3)&&fI.before(ff3))||fI.equals(fi3)||fI.equals(ff3)) {
			System.out.println("Final Semestre");
			return 2;
		}
		if(fI.equals(f4)) {
			System.out.println("Cierre Semestre");
			return 3;
		}
		else {
			System.out.println("No hay menú");
			return 4;
		}
	}
	
 	public static boolean diaCorrecto(int dia, int mes) {
		//comprobar si los numeros son correctos
		if(mes==2) {
			if(dia<29&&dia>0) {
				return true;
			}
		}
		if(mes==4||mes==6||mes==9||mes==11) {
			if(dia<31&&dia>0) {
				return true;
			}
		}
		if(mes==1||mes==3||mes==5||mes==7||mes==8||mes==10||mes==12) {
			if(dia<32&&dia>0) {
				return true;
			}
		}
		return false;
	}
}
