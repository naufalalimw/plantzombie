//Field.java

public class Field{
    //atribut Field
    private final int x_min_view=0;//koordinat x minimal yang terlihat
    private final int x_max_view=9;//koordinat x maksimal yang terlihat
    private final int y_min_view=0;//koordinat y minimal yang terlihat
    private final int y_max_view=4;//koordinat y maksimal yang terlihat
    private final int x_max=10;//koordinat x maksimal, tidak terlihat, untuk spawn zombie
    private final int jmlh_petak=55;
    
    //Petak
    public class Petak{
        int x; //posisi x
        int y; //posisi y
        boolean isNull;
        boolean isPlanted; //bernilai true jika sudah tertanam
        boolean isZombie; //bernilai true jika ada zombie di petak
        int n_bullet_damage; //damage bullet yg ada di petak jika di petak terdapat bullet
            
        public Petak(int x, int y){
            this.x=x;
            this.y=y;
            isNull=true;
            isPlanted=false;
            isZombie=false;
            n_bullet_damage=0;

        }
    } 
    
    //Konstruktor
    public Field(){
        Petak[] petaks = new Petak[jmlh_petak];
        int n=0;
        for (int i=x_min_view; i<=x_max; i++){
            for (int j=y_min_view; j<=y_max_view; j++){
                petaks[n]=new Petak(i,j);
                n++;
            }
        }
    }

    //
    //public void printField(){
    //    for
    //}
}
