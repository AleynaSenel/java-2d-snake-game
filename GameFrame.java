  import javax.swing.JFrame;

public class GameFrame extends JFrame {

    public GameFrame() {
        this.add(new GamePanel()); // Tuvalimizi pencereye ekliyoruz (Birazdan bunu sen yazacaksın)
        this.setTitle("Snake Game"); // Pencere başlığı
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Çarpıya basınca program dursun
        this.setResizable(false); // Pencere boyutu fareyle değiştirilemesin
        this.pack(); // Pencereyi içindeki panele göre otomatik boyutlandır
        this.setVisible(true); // Pencereyi görünür yap
        this.setLocationRelativeTo(null); // Pencereyi ekranın tam ortasında aç
    }
}

