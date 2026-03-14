import java.util.Random;

public class Food {
    int x;
    int y;
    
    

    public void generateNewPosition(int Screen_Width, int Screen_Height, int Unit_Size){

        int rows = Screen_Height / Unit_Size;
        int columns = Screen_Width / Unit_Size;      //burada ekrandaki toplam kare (ızgara) sayısını bulduk

        Random random = new Random();
        int randomRows = random.nextInt(rows);  
        int randomColumns = random.nextInt(columns);     //bulduğumuz sınırlar içinde random değerler atadık.

        this.x = randomColumns * Unit_Size;
        this.y = randomRows * Unit_Size;                //atadığımız random değerleri piksele çeviriyoruz.



    }
}
