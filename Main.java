import java.util.List;
import java.util.ArrayList;

public class Main{
	public static void main(String[] args) {
		// inisialisasi variabel yang akan terus digunakan
		int sunfPoint = 100; //sunflowerPoint
		ListMap<String> listOfPlants = new ListMap<String>();
		listOfPlants.add("PeaPlant",50);

		// pada awal permainan user memiliki 100 sunflower points


		
		// print ada key dan value apa saja
		listOfPlants.printList(sunfPoint);
	}
}