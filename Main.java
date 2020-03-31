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
		System.out.println(" $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$	");
        System.out.println("    ___ _          _             ____             __   _                   	");
        System.out.println("   | _ \\ |__ _ _ _| |_  __ __   /_  / ___  __ _  / /  (_)__ 	");
        System.out.println("   |  _/ / _` | \' \\  _| \\ V /   / /_/ _ \\/  ' \\/ _ \\/ / -_)	");
        System.out.println("   |_| |_\\__,_|_||_\\__|  \\_/   /___/\\___/_/_/_/_.__/_/\\__/    ver 1.0	        ");
        System.out.println(" $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$	");
        System.out.println("    _|___|___|___|___|__   ---------------------	_|___|___|___|___|__");
        System.out.println("    _|___|___|___|___|__   |    1. Play Game   |	_|___|___|___|___|__");
        System.out.println("    _|___|___|___|___|__   |    2. Exit        |	_|___|___|___|___|__");
        System.out.println("    _|___|___|___|___|__   ---------------------	_|___|___|___|___|__");
		System.out.println(" ^^^|^^|^^_^^|_^^├^^_|^^__^^|^^_^^_|^^|^^|_^^|^^|__^^|__^^_^^|_^^|_^^|__^^^");    
		
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
							field.getPetak()[posX+posY*11].isiPlant(listOfPlants.namaTumbuhanTerpilih(pilihanUser, sunfPoint));
							field.getPetak()[posX+posY*11].adaPlant();
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

			// fungsi zombienya muncul
			int posisiZombie = rand.nextInt(5);
			posisiZombie = posisiZombie*11 + 10;
			int tipeZombie = rand.nextInt(2);
			field.getPetak()[posisiZombie].isiZombie(tipeZombie);
			field.getPetak()[posisiZombie].adaZombie();

			field.printField(); // untuk print field sebelum bullet sama zombienya jalan
			
			// fungsi untuk zombie jalan dan bullet nyerang dan regenerate
			field.shotMaju();
			field.zombieWalk();

			field.printField(); // untuk print field setelah bullet sama zombienya jalan


			// Mengecek dulu apakah ada zombie yang sampe ujung kiri
			int n = 0;
			while (n <= 44 && !isGameOver){
				if(field.getPetak()[n].isZombie){
					isGameOver = true;
				}
				n+=11;
			}
			
		}
		//Game over ditampilkan di layar
		System.out.println("                                            	   		           .-'''-.                                              ");
		System.out.println("                                                        		  '   _    \\                                                ");
		System.out.println("                       	__  __   ___         __.....__                  /   /` '.   \\.----.     .----.   __.....__                                 ");
		System.out.println("  	.--./)           |  |/  `.'   `.   .-''         '.             .   |     \\  ' \\    \\   /    /.-''         '.                               ");
		System.out.println(" 	/.\'\'\\\\           |   .-.  .-.   \' /     .-\'\'\"\'-.  `.           |   \'      |  \' \'   \'. /\'   //     .-\'\'\"\'-.  `. .-,.--.                     ");
		System.out.println("	| |  | |    __   |  |  |  |  |  |/     /________\\   \\          \\    \\     / /  |    |'    //     /________\\   \\|  .-. |                    ");
		System.out.println("	\\`-\' /   .:--.\'. |  |  |  |  |  ||                  |           `.   ` ..\' /   |    ||    ||                  || |  | |                    ");
		System.out.println("	/(\"\'`   / |   \\ ||  |  |  |  |  |\\    .-------------\'              '-...-'`    \'.   `\'   .'\\    .-------------'| |  | |                    ");
		System.out.println("	\\'---.  `\" __ | ||  |  |  |  |  | \\    \'-.____...---.                           \\        /  \\    '-.____...---.| |  '-,.--.  ,.--.  ,.--.  ");
		System.out.println("	/'\"\"\'.\\  .\'.\'\'| ||__|  |__|  |__|  `.             .\'                             \\      /    `.             .\' | |   //    \\//    \\//    \\ ");
		System.out.println("	||     ||/ /   | |_                   `\'\'-...... -\'                               \'----\'       `\'\'-...... -\'   | |   \\\\    /\\\\    /\\\\    / ");
		System.out.println("	\\\'. __// \\ \\._,\\ \'/                                                                                            |_|    `\'--\'  `\'--\'  `\'--\'  ");
		System.out.println("	`\'---\'   `--\'  `\"                                                                                                                        ");


		
	}
}