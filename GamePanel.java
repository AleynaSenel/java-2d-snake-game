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
    private final int Unit_Size = 25;

    private  int location_x = 200;
    private  int location_y = 200;

    private final int delay = 75;
    Timer timer;

    char direction = 'R';

    public GamePanel(){

        this.setPreferredSize(new Dimension(Screen_Height,Screen_Width));
        this.setBackground(new Color(6, 67, 10));
        this.setFocusable(true);

        timer = new Timer(delay, this);   //this -> Yani bu sınıf içindeki actionPerformed
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
   
      
    @Override
    public void actionPerformed(ActionEvent e){   //bizim Timer'ımızın her 75 milisaniyede bir tetikleyeceği yerdir

        System.out.println("Zaman isliyor, oyun dongusu calisiyor!");

        //location_x = location_x + Unit_Size;
        switch(direction){
            case 'R':  location_x = location_x + Unit_Size;   break;
            case 'L':  location_x = location_x - Unit_Size;   break;
            case 'U':   location_y = location_y - Unit_Size;  break;
            case 'D':   location_y = location_y + Unit_Size;  break;

        }


        repaint();     //koordinatlar değişti, paintComponent'i tekrar çalıştır der
    } 

    public void draw(Graphics g){
        g.setColor(Color.black);

        g.fillRect(location_x, location_y, Unit_Size, Unit_Size);
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

    
    
}
