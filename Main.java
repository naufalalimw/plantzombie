import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		// inisialisasi variabel yang akan terus digunakan
		int sunfPoint = 100; //sunflowerPoint
		ListMap<String> listOfPlants = new ListMap<String>(); //list Plant yang ada di game
		listOfPlants.add("MushroomPlant",25);
		listOfPlants.add("PeaPlant",50); // add semua plant
		boolean isGameOver = false;
		Field field = new Field(); // inisialisasi field


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
					if ((posX < 0) || (posX > 9) || (posY < 0) || (posY > 4)) {
						System.out.println("Masukan salah. Ulangi: ");
					} else {
						if (field.getPetak()[posX+posY*11].isPetakKosong()) {
							field.getPetak()[posX+posY*11].adaPlant();
							System.out.println(listOfPlants.namaTumbuhanTerpilih(pilihanUser, sunfPoint));
							field.getPetak()[posX+posY*11].isiPlant(listOfPlants.namaTumbuhanTerpilih(pilihanUser, sunfPoint));
							System.out.println(listOfPlants.namaTumbuhanTerpilih(pilihanUser, sunfPoint) + " siap ditanam pada (" + posX + "," + posY + ")");
							inputPosisiBenar = true;
						}
						else {
							System.out.println("Tidak dapat menanam tumbuhan pada kotak tersebut. Ulangi!");
						}
					}
				}

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
			field.printField(); // untuk print field sebelum bullet sama zombienya jalan

			// fungsi untuk zombie jalan dan bullet nyerang dan regenerate

			field.printField(); // untuk print field setelah bullet sama zombienya jalan


			// cek dulu apakah ada zombie yang sampe ujung kiri

			isGameOver = true;
		}


		
	}
}