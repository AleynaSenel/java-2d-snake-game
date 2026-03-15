import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JPanel;
import javax.swing.Timer;


public class GamePanel extends JPanel implements ActionListener {

    private final int Screen_Width = 600;
    private final int Screen_Height = 600;
    private final int Unit_Size = 25;      //yılan her adımda 25 piksel hareket edecek

    int ColumnsSize = Screen_Width / Unit_Size;
    int RowSize = Screen_Height / Unit_Size;
    int maxUnitSize = ColumnsSize * RowSize;

    int[] location_x = new int[maxUnitSize];
    int[] location_y = new int[maxUnitSize];

    int bodyParts = 1;
    boolean running = true;

    private final int delay = 75;    // her 75 milisaniye de actionPerformed metodu tetikleniyor
    Timer timer;

    char direction = 'R';

    Food food;
    

    public GamePanel(){

        this.setPreferredSize(new Dimension(Screen_Width,Screen_Height));
        this.setBackground(new Color(6, 67, 10));
        this.setFocusable(true);

        timer = new Timer(delay, this);   //this -> Yani bu sınıf içindeki actionPerformed
        this.addKeyListener(new MyKeyAdapter());
        this.requestFocusInWindow();
        timer.start();

        food = new Food();
        food.generateNewPosition(Screen_Width, Screen_Height, Unit_Size);
    }

    @Override
    public void paintComponent(Graphics g){   //ekranı temizle ve arka planı boya
        super.paintComponent(g);              // JPanel'in metodu o yüzden super kullandık
        draw(g);
    }
   
      
    @Override
    public void actionPerformed(ActionEvent e){   //bizim Timer'ımızın her 75 milisaniyede bir tetikleyeceği yerdir.Game Loop buradadır.
        if(running){
        
        System.out.println("Zaman isliyor, oyun dongusu calisiyor!");

        move();

        //location_x = location_x + Unit_Size;
        switch(direction){
            case 'R':  location_x[0] = location_x[0] + Unit_Size;   break;
            case 'L':  location_x[0] = location_x[0] - Unit_Size;   break;
            case 'U':  location_y[0] = location_y[0] - Unit_Size;   break;
            case 'D':  location_y[0] = location_y[0] + Unit_Size;   break;

        }

        checkFood();
        checkCollisions();
    
        }
        repaint();  //koordinatlar değişti, paintComponent'i tekrar çalıştır der
        
    } 

    public void draw(Graphics g){

        g.setColor(Color.GRAY); // Izgara çizgileri gri olsun

        for(int i = 0; i < Screen_Width / Unit_Size; i++) {
        g.drawLine(i * Unit_Size, 0, i * Unit_Size, Screen_Height); // Dikey çizgiler
        g.drawLine(0, i * Unit_Size, Screen_Width, i * Unit_Size); // Yatay çizgiler
        }



        g.setColor(Color.RED);
        g.fillOval(food.x, food.y, Unit_Size, Unit_Size);

       

        for(int i=0; i< bodyParts; i++){
            
            g.setColor(Color.black); 

            g.fillRect(location_x[i], location_y[i], Unit_Size, Unit_Size);
        }

        

    }


    public class MyKeyAdapter extends java.awt.event.KeyAdapter {

     @Override
     public void keyPressed(java.awt.event.KeyEvent e) {
            switch(e.getKeyCode()){
                case java.awt.event.KeyEvent.VK_LEFT: 
                                     //Eğer sola basıldıysa ve şu an sağa gitmiyorsam, sola dön
                  if(direction != 'R'){
                    direction = 'L';
                  }
                break;

                case java.awt.event.KeyEvent.VK_RIGHT:

                    if(direction != 'L'){
                        direction = 'R';
                    }
                break;

                case java.awt.event.KeyEvent.VK_UP:
                    
                    if(direction != 'D'){
                        direction = 'U';
                    }
                break;

                case java.awt.event.KeyEvent.VK_DOWN:

                   if(direction != 'U'){
                        direction = 'D';
                   }
                break;
            }
     } 

    }

    public void checkFood(){
        if((food.x == location_x[0]) && (food.y == location_y[0])){
            food.generateNewPosition(Screen_Width, Screen_Height, Unit_Size);
            bodyParts++;
          
        }
        
    }

    public void move(){
        for(int k = bodyParts; k > 0 ;k--){
            location_x[k] = location_x[k-1];
            location_y[k] = location_y[k-1];
        }
    }

    public void checkCollisions(){
        for(int j = 1; j < bodyParts; j++ ){
            if(location_x[j] == location_x[0] && location_y[j] == location_y[0]){   //yılan kendi vücuduna değerse oyun durur
                running = false;
            }
        }

        if(location_x[0] < 0 ) running = false;    //sol duvar
        if(location_x[0] >= Screen_Width)  running = false;  //sağ duvar
        if(location_y[0] < 0 )  running = false;    //üst duvar
        if( location_y[0] >= Screen_Height) running = false;  //alt duvar

       
        if (!running) {
        timer.stop();    //zaman durursa oyun durur
        }
    
        
    }



    
    
}
