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

    //
    public void zombieWalk(){
        boolean maju=true;
            for (int i=0;i<=44;i+=11){
                for (int j=i;j>=j-this.petaknya[j].creature.getStep() ;i--){
                    if ((j-1) % 11==0){ //udah diujung
                        //gameOver
                        break;
                    }else if(petaknya[j-1].isPlanted){
                        //kirim damage ke plant
                        petaknya[j-1].creature.takeDamage(petaknya[j].creature.getAttack());
                        maju=false;
                    }
                    if(petaknya[j].isShot){
                        //memberikan damage ke zombie
                        petaknya[j].creature.takeDamage(petaknya[j].n_bullet_damage);
                        petaknya[j].isShot=false;
                    }
                    //selama di petak akhir zombie terdapat zombie lain, petak akhirnya dikurangi
                    while(petaknya[j-this.petaknya[j].creature.getStep()].isZombie){
                        petaknya[j].creature.setStep(j-1);
                    }
                    //selama maju (depannya tidak ada plant), zombie maju
                    while(maju){
                        petaknya[j-1]=petaknya[j];
                        petaknya[j].setNull();
                    }

                    }
                }
            }
        //jika di posisi akhir ada zombie, mundur selangkah, (khusus untuk zombie yg gerak 2 langkah)
        //jika di belakangnya posisi akhir ada zombie, tidak perlu gerak
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
        System.out.println("--------------------------------------------------------");
        for (int i=0;i<=44;i+=11){
            System.out.println("| "+this.petaknya[i+0].view+" | "+this.petaknya[i+1].view+" | "+this.petaknya[i+2].view+" | "+this.petaknya[i+3].view+" | "+this.petaknya[i+4].view+" | "+this.petaknya[i+5].view+" | "+this.petaknya[i+6].view+" | "+this.petaknya[i+7].view+" | "+this.petaknya[i+8].view+" | "+this.petaknya[i+9].view+" | "+this.petaknya[i+10].view+" |");
            System.out.println("----------------------------------------------------");
        }
    }

    
}
