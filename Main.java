import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		// inisialisasi variabel yang akan terus digunakan
		int sunfPoint = 100; //sunflowerPoint
		ListMap<String> listOfPlants = new ListMap<String>();
		listOfPlants.add("PeaPlant",50);
		boolean isGameOver = false;
		//inisialisasi field


		// gamenya mulai
		while (!isGameOver) {

			// ditanya program mau beli tumbuhan apa ngga
			int pilihanUser = 99;
			System.out.println("Sunflower Point yang kamu miliki sekarang: " + sunfPoint);
			listOfPlants.printList(sunfPoint); //kasi list tumbuhan berdasarkan sunfPoint yang dimiliki
			System.out.print("Pilih salah satu: ");
			int banyakPilihan = listOfPlants.banyakPilihan(sunfPoint);
			pilihanUser = input.nextInt();
			while (pilihanUser < 0 || (pilihanUser > banyakPilihan)) {
				System.out.println("Tolong masukkan input sesuai pilihan");
				System.out.print("Pilih salah satu: ");
				pilihanUser = input.nextInt();
			}
			while (pilihanUser !=0) {
				boolean inputPosisiBenar = false;
				int posX;
				int posY;
				while (!inputPosisiBenar) {
					System.out.print("Masukkan posisi x y: ");
					posX = input.nextInt();
					posY = input.nextInt();
					if ((posX < 0) || (posX > 10) || (posY < 0) || (posY > 4)) {
						System.out.println("Masukan salah. Ulangi: ");
					} else {
						System.out.println(listOfPlants.namaTumbuhanTerpilih(pilihanUser, sunfPoint) + " siap ditanam pada (" + posX + "," + posY + ")");
						inputPosisiBenar = true;
					} // masih ada kondisi else if dimana posisinya udah ditempati di field
				}

				// tambahin tumbuhan baru ke field

				sunfPoint = sunfPoint - listOfPlants.hargaTumbuhanTerpilih(pilihanUser, sunfPoint); //kurangi sunfPoint

				// user memilih lagi
				System.out.println("Silahkan pilih lagi");
				System.out.println("Sunflower Point yang kamu miliki sekarang: " + sunfPoint);
				listOfPlants.printList(sunfPoint);
				System.out.print("Pilih salah satu: ");
				banyakPilihan = listOfPlants.banyakPilihan(sunfPoint);
				pilihanUser = input.nextInt();
				while (pilihanUser < 0 || (pilihanUser > banyakPilihan)) {
					System.out.println("Tolong masukkan input sesuai pilihan");
					System.out.print("Pilih salah satu: ");
					pilihanUser = input.nextInt();
				}
			}
			


			isGameOver = true;
		}


		
	}
}