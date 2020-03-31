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
            this.view="       ";
            this.creature = null;
        }

        //ada zombie di petak, tapi gimana bedain print untuk zombie1 dan 2?
        public void adaZombie(){
            this.isNull=false;
            this.isZombie=true;
            this.view="¬[º-°¬]";
            //"⊂(◑▃◑⊂)"
        }

        //ada plant di petak
        public void adaPlant(){
            this.isNull=false;
            this.isPlanted=true;
            this.view="(＾┌＾)┌ ";
            //"༼ ಠل͟ಠ༽ "
        }

        //ada shot di petak
        public void adaShot(){
            this.isNull = false;
            this.isShot = true;
            this.view = n_bullet_damage.toString();
        }

        // mengembalikan true jika petak kosong
        public boolean isPetakKosong() {
            return isNull;
        }

        public void isiPlant(String namaTanaman) {
            if (namaTanaman.equals("PeaPlant")) {
                Plant tanaman = new PeaPlant();
                this.creature = tanaman;
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
    /*
    public void zombieWalk(){
        for (int i=0;i<=44;i+=11){
            for (int j=i;j>=j-this.petaknya[j].creature.step ;i--){// ini gimana caranya akses stepnya 
                if ((j-1) % 11==0){ //udah diujung
                    //gameOver
                    break;
                }else if(petaknya[j-1].isPlanted){
                    //kirim damage ke plant
                    petaknya[j-1].creature.takeDamage(petaknya[j].creature.getAttack());
                    //gimana caranya biar si plant ini dapet damagenya ya
                }else{
                    if(petaknya[j].isShot){
                        //gimana cara kasih damagenya ke zombie ya
                        petaknya[j].creature.takeDamage(petaknya[j].n_bullet_damage);
                        petaknya[j].isShot=false;
                    }
                }
            }
        }
        //jika di posisi akhir ada zombie, mundur selangkah, (khusus untuk zombie yg gerak 2 langkah)
        //jika di belakangnya posisi akhir ada zombie, tidak perlu gerak
    }
    */
    public void gameOver(){

    }

    //Mencetak field
    public void printField(){
        System.out.println("###########################################################################");
        System.out.println("---------------------------------------------------------------------------");
        for (int i=0;i<=44;i+=11){
            System.out.println("| "+this.petaknya[i+0].view+" | "+this.petaknya[i+1].view+" | "+this.petaknya[i+2].view+" | "+this.petaknya[i+3].view+" | "+this.petaknya[i+4].view+" | "+this.petaknya[i+5].view+" | "+this.petaknya[i+6].view+" | "+this.petaknya[i+7].view+" | "+this.petaknya[i+8].view+" | "+this.petaknya[i+9].view+" | "+this.petaknya[i+10].view+" |");
            System.out.println("---------------------------------------------------------------------------");
        }
    }

    
}
