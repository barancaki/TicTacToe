package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	
	static ArrayList<Integer> oyuncu_konumu = new ArrayList<Integer>();
	static ArrayList<Integer> bilgisayar_konumu = new ArrayList<Integer>();
	static boolean aktiflik = true;

	public static void main(String[] args) {
		char[][] gorunus = { { ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-', '+' }, { ' ', '|', ' ', '|', ' ' },
				{ '-', '+', '-', '+', '-', '+' }, { ' ', '|', ' ', '|', ' ' } };
		printGorunus(gorunus);
		
		while(aktiflik) {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Bir konum giriniz (1-9):");		
			int girilen_konum = scanner.nextInt();
			while(oyuncu_konumu.contains(girilen_konum) || bilgisayar_konumu.contains(girilen_konum)) {
				System.out.println("Yanlis poziyson , doğru pozisyon giriniz : ");
				girilen_konum=scanner.nextInt();
			}
			
			konumAtama(gorunus, girilen_konum, "oyuncu");
			String sonuc = kazanan_oyuncu();
			if(sonuc.length()>0) {
				System.out.println(sonuc);
				
			}
			Random random = new Random();
			int bilgisayar_konum = random.nextInt(9) + 1;
			while(oyuncu_konumu.contains(bilgisayar_konum) || bilgisayar_konumu.contains(bilgisayar_konum)) {
				bilgisayar_konum = random.nextInt(9) + 1;
			}
			
			konumAtama(gorunus, bilgisayar_konum, "bilgisayar");
			printGorunus(gorunus);
			
			sonuc = kazanan_oyuncu();
			if(sonuc.length()>0) {
				System.out.println(sonuc);
				
			}

		}
		
	}

	public static void printGorunus(char[][] gorunus) {
		for (char[] sira : gorunus) {
			for (char c : sira) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	public static void konumAtama(char[][] gorunus,int girilen_konum,String kullanici) {
		
		char sembol = 'X';
		
		if(kullanici.equals("oyuncu")) {
			sembol = 'X';
			oyuncu_konumu.add(girilen_konum);
		}else if(kullanici == "bilgisayar") {
			sembol = 'O';
			bilgisayar_konumu.add(girilen_konum);
		}
		
		
		switch (girilen_konum) {
		case 1:
			gorunus[0][0] = sembol;
			break;
		case 2:
			gorunus[0][2] = sembol;
			break;
		case 3:
			gorunus[0][4] = sembol;
			break;
		case 4:
			gorunus[2][0] = sembol;
			break;
		case 5:
			gorunus[2][2] = sembol;
			break;
		case 6:
			gorunus[2][4] = sembol;
			break;
		case 7:
			gorunus[4][0] = sembol;
			break;
		case 8:
			gorunus[4][2] = sembol;
			break;
		case 9:
			gorunus[4][4] = sembol;
			break;
		default:
			break;
		}
		
		
	}
	public static String kazanan_oyuncu() {

		List ust_Sira = Arrays.asList(1, 2, 3);
		List orta_Sira = Arrays.asList(4, 5, 6);
		List alt_Sira = Arrays.asList(7, 8, 9);
		List ilk_Sutun = Arrays.asList(1 ,4 , 7);
		List ikinci_Sutun = Arrays.asList(2 ,5 , 8);
		List ucuncu_Sutun = Arrays.asList(3 ,6 , 9);
		List capraz_ust_Sira = Arrays.asList(1 ,5 , 9);
		List capraz_alt_Sira = Arrays.asList(3 ,5 , 7);
		
		List<List> kazanan = new ArrayList<List>();
		kazanan.add(ust_Sira);
		kazanan.add(orta_Sira);
		kazanan.add(alt_Sira);
		kazanan.add(ilk_Sutun);
		kazanan.add(ikinci_Sutun);
		kazanan.add(ucuncu_Sutun);
		kazanan.add(capraz_alt_Sira);
		kazanan.add(capraz_ust_Sira);
		
		for(List l : kazanan) {
			if(oyuncu_konumu.containsAll(l)) {
				aktiflik = false;
				return "Tebrikler , kazandiniz.";
				
			}else if (bilgisayar_konumu.containsAll(l)) {
				aktiflik = false;
				return "Maalesef , kaybettiniz.";
			}else if (oyuncu_konumu.size() + bilgisayar_konumu.size() == 9) {
				aktiflik = false;
				return "Berabere!";
			}
		}
		
		return " ";
	}
}