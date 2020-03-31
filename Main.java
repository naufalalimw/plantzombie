import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Main{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Random rand = new Random();
		// inisialisasi variabel yang akan terus digunakan
		int sunfPoint = 100; //sunflowerPoint
		ListMap<String> listOfPlants = new ListMap<String>(); //list Plant yang ada di game
		listOfPlants.add("MushroomPlant",25);
		listOfPlants.add("PeaPlant",50); // add semua plant
		int n_zombies = 2;
		boolean isGameOver = false;
		Field field = new Field(); // inisialisasi field


		// gamenya mulai
		while (!isGameOver) {
			//Menambah sunflower point tiap turn sebanyak 15
			System.out.println("+15 Sunflower Point");
			sunfPoint = sunfPoint + 15;

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

			// fungsi zombienya muncul
			int posisiZombie = rand.nextInt(5);
			posisiZombie = posisiZombie*11 + 10;
			field.getPetak()[posisiZombie].adaZombie();
			int tipeZombie = rand.nextInt(2);
			field.getPetak()[posisiZombie].isiZombie(tipeZombie);

			// fungsi untuk zombie jalan dan bullet nyerang dan regenerate

			field.printField(); // untuk print field setelah bullet sama zombienya jalan


			// Mengecek dulu apakah ada zombie yang sampe ujung kiri
			int n = 1;
			while (n <= 40 && !isGameOver){
				if(field.getPetak()[n].x == 1){
					if(field.getPetak()[n].isZombie){
						isGameOver = true;				//jika ada maka game over
					}
				}
				n++;
			}
			
		}
		//Game over ditampilkan di layar
		System.out.println("GAME OVER");


		
	}
}