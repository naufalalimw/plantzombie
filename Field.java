//Field.java

public class Field{
    //atribut Field
    private final int x_min_view=0;//koordinat x minimal yang terlihat
    private final int x_max_view=9;//koordinat x maksimal yang terlihat
    private final int y_min_view=0;//koordinat y minimal yang terlihat
    private final int y_max_view=4;//koordinat y maksimal yang terlihat
    private final int x_max=10;//koordinat x maksimal, tidak terlihat, untuk spawn zombie
    private final int jmlh_petak=55;
    private Petak[] petaknya;

    //Petak
    public class Petak{
        int x; //posisi x
        int y; //posisi y
        boolean isNull;
        boolean isPlanted; //bernilai true jika sudah tertanam
        boolean isZombie; //bernilai true jika ada zombie di petak
        boolean isShot;   //bernilai true jika bullet ada di petak
        Integer n_bullet_damage; //damage bullet yg ada di petak jika di petak terdapat bullet
        String view; //ini adalah yang ditampilkan nantinya
        Creature creature;
        
        //konstruktor petak
        public Petak(int x, int y){
            this.x=x;
            this.y=y;
            isNull=true;
            isPlanted=false;
            isZombie=false;
            isShot = false;
            n_bullet_damage=0;
            this.view="  ";
            this.creature = null;
        }

        //ada zombie di petak, tapi gimana bedain print untuk zombie1 dan 2?
        public void adaZombie(){
            this.isNull=false;
            this.isZombie=true;
            if (this.creature.getClass().getName().equals("Zombie1")) {
                this.view = "\u01791";
            } else if (this.creature.getClass().getName().equals("Zombie2")) {
                this.view = "\u01792";
            }
        }

        //ada plant di petak
        public void adaPlant(){
            this.isNull=false;
            this.isPlanted=true;
            if (this.creature.getClass().getName().equals("PeaPlant")) {
                this.view="\u00B6P";
            } else if (this.creature.getClass().getName().equals("MushroomPlant")) {
                this.view="\u00B6M";
            }
        }

        //ada shot di petak
        public void adaShot(){
            this.isShot = true;
            this.view = n_bullet_damage.toString();
        }
        //membuat petak yang tadinya terisi jadi kosong
        public void setNull(){
            if(!isPetakKosong()){
                isNull=true;
                isPlanted=false;
                isZombie=false;
                isShot = false;
                n_bullet_damage=0;
                this.view="  ";
                this.creature = null;
            }
        }
        // mengembalikan true jika petak kosong
        public boolean isPetakKosong() {
            return isNull;
        }

        public void isiPlant(String namaTanaman) {
            if (namaTanaman.equals("PeaPlant")) {
                Plant tanaman = new PeaPlant();
                this.creature = tanaman;
            } else if (namaTanaman.equals("MushroomPlant")) {
                Plant tanaman = new MushroomPlant();
                this.creature = tanaman;
            }
        }

        public void isiZombie(int tipeZombie) {
            if (tipeZombie == 0) {
                Zombie zombie = new Zombie1();
                this.creature = zombie;
            } 
            else if (tipeZombie == 1) {
                Zombie zombie = new Zombie2();
                this.creature = zombie;
            }
        }

        public void translation(int x, int y) {
            this.x += x;
            this.y += y;
    
        }
    } 
    
    //Konstruktor Field
    //membuat array of reference ke petak
    public Field(){
        Petak[] petaks = new Petak[jmlh_petak];
        int n=0;
        for (int i=x_min_view; i<=x_max; i++){
            for (int j=y_min_view; j<=y_max_view; j++){
                petaks[n]=new Petak(i,j);
                n++;
            }
        }
        this.petaknya = petaks;
    }

    //getter petaknya
    public Petak[] getPetak() {
        return this.petaknya;
    }

    public void zombieWalk() {
        int i;
        for (i=1;i<=54;i++) {
            if (petaknya[i].isZombie) {
                int j;
                int langkah = petaknya[i].creature.getStep();
                for (j=1;j<=langkah;j++) {
                    // cek apakah dia udah di ujung
                    int q = i-j+1;
                    if (petaknya[q].x == 0) {
                        break;
                    }
                    // cek apakah ada shot di tempatnya berdiri
                    if (petaknya[q].isShot) {
                        petaknya[q].creature.takeDamage(petaknya[q].n_bullet_damage);
                        petaknya[q].isShot = false;
                        petaknya[q].n_bullet_damage = 0;
                        if (petaknya[q].creature.isDead()) {
                            petaknya[q].isNull = true;
                            petaknya[q].isZombie = false;
                            petaknya[q].creature = null;
                            petaknya[q].view = "  ";
                            break;
                        }
                    }

                    // cek di depannya ada apa
                    if (petaknya[q-1].isPlanted) { // kalo depannya Plant
                        petaknya[q-1].creature.takeDamage(petaknya[q].creature.getAttack());
                        if (petaknya[q-1].creature.isDead()) {
                            petaknya[q-1].isNull = true;
                            petaknya[q-1].isPlanted = false;
                            petaknya[q-1].isZombie = true;
                            petaknya[q-1].view = petaknya[q].view;
                            petaknya[q-1].creature = petaknya[q].creature;
                            petaknya[q].isNull = true;
                            petaknya[q].isZombie = false;
                            petaknya[q].view = "  ";
                            petaknya[q].creature = null;
                        }
                    } 
                    else if (petaknya[q-1].isZombie) {
                        //mencari lokasi tempat dia mendarat
                        int banyakSkip = j-petaknya[q].creature.getStep();
                        int k=1;
                        boolean kosong = false;
                        while (k<=banyakSkip && !kosong && ((q%11-k)>=0)) {
                            if (petaknya[q-k].isNull) {
                                kosong = true;
                            } else {
                                k++;
                            }
                        }
                        if (kosong) {
                            if (petaknya[q-k].isShot) {
                                petaknya[q].creature.takeDamage(petaknya[q-k].n_bullet_damage);
                                petaknya[q-k].n_bullet_damage = 0;
                                petaknya[q-k].isShot = false;
                                petaknya[q-k].view = "  ";
                            }
                            if (petaknya[q].creature.isDead()) {
                                petaknya[q].isNull = true;
                                petaknya[q].isZombie = false;
                                petaknya[q].view = "  ";
                                petaknya[q].creature = null;
                                break;
                            } else {
                                petaknya[q-k].isNull = false;
                                petaknya[q-k].isZombie = true;
                                petaknya[q-k].view = petaknya[i].view;
                                petaknya[q-k].creature = petaknya[q].creature;
                                petaknya[q].isNull = true;
                                petaknya[q].isZombie = false;
                                petaknya[q].view = "  ";
                                petaknya[q].creature = null;
                                j = j + k;
                            }
                        } else { // ga kosong
                            break;
                        }
                    }
                    else { // gaada apa2
                        if (petaknya[q-1].isShot) {
                            petaknya[q].creature.takeDamage(petaknya[q-1].n_bullet_damage);
                            petaknya[q-1].isShot = false;
                            petaknya[q-1].n_bullet_damage = 0;
                        }
                        if (petaknya[q].creature.isDead()) {
                            petaknya[q].isNull = true;
                            petaknya[q].isZombie = false;
                            petaknya[q].view = "  ";
                            petaknya[q].creature = null;
                        }   
                        else { // masih hidup
                            petaknya[q-1].isNull = false;
                            petaknya[q-1].isZombie = true;
                            petaknya[q-1].view = petaknya[q].view;
                            petaknya[q-1].creature = petaknya[q].creature;
                            petaknya[q].isNull = true;
                            petaknya[q].isZombie = false;
                            petaknya[q].view = "  ";
                            petaknya[q].creature = null;
                        }
                    }
                }
            }
        }
    }

    public void shotMaju() {
        int i;
        for (i=54;i>=0;i--) {
            if (petaknya[i].isPlanted) { // liat apakah dia Plant atau tidak
                petaknya[i].isShot = true;
                petaknya[i].n_bullet_damage = petaknya[i].n_bullet_damage + petaknya[i].creature.getAttack();
            }
            if (petaknya[i].isShot) { // liat apakah dia shot atau bukan
                if (i%11 == 9) {
                    petaknya[i].isShot = false;
                    petaknya[i].n_bullet_damage = 0;
                    if (!petaknya[i].isPlanted) {
                        petaknya[i].view = petaknya[i].n_bullet_damage.toString();
                    }
                }
                else {
                    petaknya[i+1].isShot = true;
                    petaknya[i+1].n_bullet_damage = petaknya[i+1].n_bullet_damage + petaknya[i].n_bullet_damage;
                    petaknya[i].isShot = false;
                    petaknya[i].n_bullet_damage = 0;
                    if (!petaknya[i+1].isPlanted && !petaknya[i+1].isZombie){
                        petaknya[i+1].view = petaknya[i+1].n_bullet_damage.toString();
                    }
                    if (!petaknya[i].isPlanted) {
                        petaknya[i].view = petaknya[i].n_bullet_damage.toString();    
                    }
                    if (petaknya[i+1].isZombie) { //kalau depannya ada zombie
                        petaknya[i+1].creature.takeDamage(petaknya[i+1].n_bullet_damage); // kasi damage ke zombie
                        petaknya[i+1].isShot = false; // shot jadi false
                        petaknya[i+1].n_bullet_damage = 0; // damage udah diterima zombie
                        if (petaknya[i+1].creature.isDead()) {
                            petaknya[i+1].view = "       ";
                            petaknya[i+1].creature = null;
                            petaknya[i+1].isNull = true;
                            petaknya[i+1].isZombie = false;
                        }
                    }
                }
            }
        }
    }
    

    //Mencetak field
    public void printField(){
        System.out.println("######################## FIELD #########################");
        System.out.println(" * 0  * 1  * 2  * 3  * 4  * 5  * 6  * 7  * 8  * 9  * 10 *");
        System.out.println("---------------------------------------------------------");
        int j = 0;
        for (int i=0;i<=44;i+=11){
            System.out.println(j + "| "+this.petaknya[i+0].view+" | "+this.petaknya[i+1].view+" | "+this.petaknya[i+2].view+" | "+this.petaknya[i+3].view+" | "+this.petaknya[i+4].view+" | "+this.petaknya[i+5].view+" | "+this.petaknya[i+6].view+" | "+this.petaknya[i+7].view+" | "+this.petaknya[i+8].view+" | "+this.petaknya[i+9].view+" | "+this.petaknya[i+10].view+" |");
            System.out.println("---------------------------------------------------------");
            j++;
        }
    }

    
}
