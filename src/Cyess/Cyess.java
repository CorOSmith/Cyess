
package Cyess;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JPanel;


public class Cyess
// <editor-fold defaultstate="collapsed" desc="THE MAIN CLASS">
{   public static void main(String args[]) {

           java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CyessStart().setVisible(true);
            }});


}}

class Chess
{
    final int bs = 50;
    final int length = bs*8;
    final String kingW = "\u2654";
    final String queenW = "\u2655";
    final String rookW = "\u2656";
    final String bishW = "\u2657";
    final String knightW = "\u2658";
    final String pawnW = "\u2659";
    final String kingB = "\u265a";
    final String queenB = "\u265b";
    final String rookB = "\u265c";
    final String bishB = "\u265d";
    final String knightB = "\u265e";
    final String pawnB = "\u265f";
    int tx = -1;
    int ty = -1;
    boolean fliped= false;
    boolean blacksTurn = false;
    
    boolean autoFlip = false;
    boolean turnOrder = true;
    boolean checkMoves = true;
    
    
    ArrayList<int[][]> boardList = new ArrayList<int[][]>();
    int currentBoard = 0;
    

    private int[][] board = {
        {-2,-3,-4,-5,-6,-4,-3,-2},
        {-1,-1,-1,-1,-1,-1,-1,-1},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {1,1,1,1,1,1,1,1},
        {2,3,4,5,6,4,3,2},

    };
    
    public Chess()
    {
        int[][] t = {
        {-2,-3,-4,-5,-6,-4,-3,-2},
        {-1,-1,-1,-1,-1,-1,-1,-1},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {1,1,1,1,1,1,1,1},
        {2,3,4,5,6,4,3,2},};
         boardList.add(t);
         
        
    }
    

    public void move(int x,int y)
    {
        System.out.println("Fisrt y "+ty+", Firts x "+tx+" Second y "+y+", second x "+x);
        if(board[ty][tx] > 0 && board[y][x] <= 0 || board[ty][tx] < 0 && board[y][x] >= 0 )
        {
            int temp;
            boolean validMove = true;
            
            temp = board[ty][tx];
            if(fliped && (board[ty][tx] ==1 || board[ty][tx] == -1))
                temp = board[ty][tx] *-1;
           
            if(checkMoves)
            switch(temp)
            {
                
                
                case-1: if(!(y==ty+1 && x>=tx-1 && x<=tx+1 || y == ty+2 && x==tx)){    
                             validMove = false;
                             break;      
                         }
                
                         if(ty+1==y && x==tx && board[y][x]!=0){
                            validMove = false;
                            break; 
                         }
                
                         if( y == ty+2 && ty!=1 || (board[y][x]!=0 || board[ty+1][tx]!=0) && ty==1){
                            validMove = false;
                            break; 
                         }
                         if((tx==x-1 || tx==x+1 )&& y==ty+1 && board[y][x] == 0){
                            validMove = false;
                            break; 
                         }
                break;    
                    
                case 1:  if(!(y==ty-1 && x>=tx-1 && x<=tx+1 || y == ty-2 && x==tx)){    
                             validMove = false;
                             break;     
                         }
                         if(ty-1==y && x==tx && board[y][x]!=0){
                            validMove = false;
                            break; 
                         }
                
                         if( y == ty-2 && ty!=6 || (board[y][x]!=0 || board[ty-1][tx]!=0) && ty==6){
                            validMove = false;
                            break; 
                         }
                         if((tx==x-1 || tx==x+1 )&& y==ty-1 && board[y][x] == 0){
                            validMove = false;
                            break; 
                         }
                break;
                    
                case -2:
                case 2: if(tx != x && ty != y){
                            validMove = false;
                            break;
                        }
                
                        for(int z = ty+1; z<y;z++)
                        if(board[z][x]!= 0)
                            validMove = false;
                        
                        for(int z = tx+1; z<x;z++)
                        if(board[y][z]!= 0)
                            validMove = false;
                                                           
                        for(int z = ty-1; z>y;z--)
                        if(board[z][x]!= 0)
                            validMove = false;
                        
                        for(int z = tx-1; z>x;z--)
                        if(board[y][z]!= 0)
                            validMove = false;
                        
                break;
                
                case -3:
                case 3: if(!((y==ty+2 && (x==tx-1 || x==tx+1)) || (y==ty+1 && (x==tx-2 || x==tx+2)) ||
                        (y==ty-1 && (x==tx-2 || x==tx+2)) || (y==ty-2 && (x==tx-1 || x==tx+1)) ))
                        validMove = false;
                        break;
                        
                case -4:
                case 4: if(!(ty-tx == y-x || ty+tx == y+x))
                        {
                            validMove = false;
                            break;
                        }
                        
                        int change = Math.abs(tx-x);
                        System.out.println("CHange:"+ change);

                        if(x>tx)
                        {
                            if(y>ty)
                                for(int z = 1; z<change;z++)
                                {
                                    if(board[ty+z][tx+z]!= 0)
                                    validMove = false;
                                    
                                    System.out.println("loop test"+z);
                                }
                            else
                                for(int z2 = 1; z2<change;z2++)
                                if(board[ty-z2][tx+z2]!= 0)
                                    validMove = false;
                            
                            System.out.println("x if test");
                        }
                        else 
                        {    if(y>ty)
                            {      for(int z = 1; z<change;z++)
                                if(board[ty+z][tx-z]!= 0)
                                    validMove = false;
                            }
                             else
                             {   for(int z2 = 1; z2<change;z2++)
                                {
                                if(board[ty-z2][tx-z2]!= 0)
                                    validMove = false;  
                                System.out.println("loop test2"+z2);
                                }
                            }
                        
                        }
                
            break;
            
            
            case -5:
            case 5: if(tx != x && ty != y && !(ty-tx == y-x || ty+tx == y+x)){
                            validMove = false;
                            break;
                        }
                       
                     if(x==tx || y==ty)
                     {  
                        for(int z = ty+1; z<y;z++)
                        if(board[z][x]!= 0)
                            validMove = false;
                        
                        for(int z = tx+1; z<x;z++)
                        if(board[y][z]!= 0)
                            validMove = false;
                                                           
                        for(int z = ty-1; z>y;z--)
                        if(board[z][x]!= 0)
                            validMove = false;
                        
                        for(int z = tx-1; z>x;z--)
                        if(board[y][z]!= 0)
                            validMove = false;
                        
                     }
                     else{
                     
                        int changeQ = Math.abs(tx-x);
                        System.out.println("CHange:"+ changeQ);

                        if(x>tx)
                        {
                            if(y>ty)
                                for(int z = 1; z<changeQ;z++)
                                {
                                    if(board[ty+z][tx+z]!= 0)
                                    validMove = false;
                                    
                                    System.out.println("loop test"+z);
                                }
                            else
                                for(int z2 = 1; z2<changeQ;z2++)
                                if(board[ty-z2][tx+z2]!= 0)
                                    validMove = false;
                            
                            System.out.println("x if test");
                        }
                        else 
                        {    if(y>ty)
                            {      for(int z = 1; z<changeQ;z++)
                                if(board[ty+z][tx-z]!= 0)
                                    validMove = false;
                            }
                             else
                             {   for(int z2 = 1; z2<changeQ;z2++)
                                {
                                if(board[ty-z2][tx-z2]!= 0)
                                    validMove = false;  
                                System.out.println("loop test2"+z2);
                                }
                            }
                        
                        }
                     }  
            break;
                        
            case -6:
            case 6: if(!(x>=tx-1 && x<=tx+1&& y>=ty-1 && y<=ty+1))
                        {
                            validMove = false;
                            break;
                        }
            break;
            }
            
            if(validMove)
            {
                board[y][x] = board[ty][tx];           
                board[ty][tx] = 0;
                blacksTurn = !blacksTurn;
                
                ty = -1;
                tx = -1;
                
                int[][] btemp = new int[8][8];
                
                for(int i =0;i<board.length;i++)
                    for(int o=0;o<board[i].length;o++)
                        btemp[i][o] = board[i][o];
                
                boardList.add( btemp);
                currentBoard++;
                
                for(int q = boardList.size()-1;q>currentBoard;q--)
                    boardList.remove(q);
                
                if(autoFlip)
                    flip();
                
                showBoardList();
                System.out.println(currentBoard);
            }
            else
                showMessageDialog(null,"Invalid move","Error",2);
        }
     
        ty = -1;
        tx = -1;
    }
    
    public void showBoardList()
    {
        System.out.println("NEw");
       for(int t =0;t<boardList.size();t++)
       {
           System.out.println("--------------------");
           for(int x =0;x<board.length;x++){
               
                for(int y=0;y<board[x].length;y++)
                   System.out.print(boardList.get(t)[x][y]+",");
                System.out.println();
           }
       }
        
    }

    public boolean setFirstClickCords(int x, int y)
    {
        if(board[y][x]!=0)
        {
            if(turnOrder){
            if(board[y][x]>0 && blacksTurn)
            {
                showMessageDialog(null,"It is Blacks turn!","Error",2);
                return false;
            }
            
            if( board[y][x]<0 && !blacksTurn)
            {
                showMessageDialog(null,"It is White's turn!","Error",2);
                return false;
            }
            }
           
            
                tx =x;
                ty = y;
                System.out.println("You have selected the piece at "+ty+" , "+tx );
                return true;
            
            
        }
        return false;
    }
    
    public String turnToPiece(int ident)
    {
        switch(ident)
                {
                    case 1: return pawnW;
                    case 2: return rookW;
                    case 3: return knightW;
                    case 4: return bishW;
                    case 5: return queenW;
                    case 6: return kingW;

                    case -1: return pawnB;
                    case -2: return rookB;
                    case -3: return knightB;
                    case -4: return bishB;
                    case -5: return queenB;
                    case -6: return kingB;

                    default: break;
                }
        return "";
    }

    public void drawPieces(Graphics g)
    {
        g.setFont(new Font("",0,50));


        for(int x = 0; x<board.length;x++)
            for(int y = 0 ; y< board[0].length;y++)
            {
                String thing = "";
                
                thing += turnToPiece(board[x][y]);

                g.drawString(thing,bs*y,bs*x+42);

            }
    }

    public void drawBoard(Graphics g){



        for(int r=0; r<8; r++)
            for(int c=0; c<8; c++)
            {

                if(fliped)
                {
                    if( !(r%2==0 && c%2==0  || r%2==1 && c%2==1))
                        g.setColor( Color.LIGHT_GRAY );
                    else
                        g.setColor( Color.GRAY );
                }
                else
                {
                    if( (r%2==0 && c%2==0  || r%2==1 && c%2==1))
                        g.setColor( Color.LIGHT_GRAY );
                    else
                        g.setColor( Color.GRAY );
                }
                
                if(r==ty && c==tx)
                    g.setColor(Color.CYAN);
                g.fillRect(c*bs,r*bs,(c+1)*bs,(r+1)*bs);
            }

        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(4,1,0));
        g2d.setColor(Color.BLACK);
        for(int x=0; x<length; x+=bs)
            g2d.drawLine(x,0,x,length);
        g2d.drawLine(0, length-1, length, length-1);
        for(int y=0; y<length; y+=bs)
            g2d.drawLine(0,y,length,y);
        g2d.drawLine(length-1, 0, length-1, length);


    }

    public void flip()
    {
        if(ty ==-1 &&tx==-1)
        {
        fliped = !fliped;


        System.out.println("fliped");
        int[][] temp = new int[8][8];

         for(int x = 0; x<board.length;x++)
            for(int y = 0; y< board[0].length;y++)
            {
                temp[x][y] = board[x][y];
            }

        for(int x = 0; x<board.length;x++)
            for(int y = 0; y< board[0].length;y++)
            {
                board[x][y] = temp[7-x][y];
            }
        }
        else
            showMessageDialog(null,"You have allready selected a piece, please move first","Error",2);

    }
    
    public void back()   
    {
        if(currentBoard!=0){
        currentBoard--;        
        
            for(int x =0;x<board.length;x++)
                for(int y=0;y<board[x].length;y++)
                   board[x][y] = boardList.get(currentBoard)[x][y];
                
        blacksTurn = !blacksTurn;

        
        showBoardList();
        System.out.println(currentBoard);
        }
        else
        showMessageDialog(null,"You can not go back any further","Error",2);
    }
    
    public void forward()   
    {
        if(currentBoard!=boardList.size()-1){
        currentBoard++;        
        
            for(int x =0;x<board.length;x++)
                for(int y=0;y<board[x].length;y++)
                   board[x][y] = boardList.get(currentBoard)[x][y];
                
        blacksTurn = !blacksTurn;
            
        showBoardList();
        System.out.println(currentBoard);
        }
        else
        showMessageDialog(null,"You can not go forward any further","Error",2);
    }
    


}

class CyessStart extends CyessGui implements Runnable
{
    Chess game = new Chess();
    boolean clicked = false;
    Sound bgm;
    boolean bgmon = true;


    public CyessStart()
    {
        bgm = new Sound("209_-_Tazer.wav");
        bgm.play();
    }
    
    @Override
    public void paintSurface(Graphics g)
    {
        game.drawBoard(g);
        game.drawPieces(g);
    }

    @Override
    public void flip()
    {
        game.flip();
        repaint();
    }
    
    @Override
    public void back()
    {
        game.back();
        repaint();
    }
    
    @Override
    public void forward()
    {
        game.forward();
        repaint();
    }
    
    public void toggleMusic()
    {
        if(bgmon){
            bgm.stop();
            bgmon = false;
        }
        else{
            bgm.play();
            bgmon = true;
        }
        
    }
    
    @Override
    public void changeAutoFlip(boolean change){
        game.autoFlip = change;
    }
    
    @Override
    public void changeTurnOrder(boolean change){
        game.turnOrder = change;
    }
    
    @Override
    public void changeCheckMoves(boolean change){
        game.checkMoves = change;
    }

    @Override
    public void click(int x, int y)
    {

       // System.out.println("cords: "+x+" , "+y);

        if(!clicked)
        {
            if(game.setFirstClickCords(x/50,y/50))
            clicked = true;
        }
        else
        {
            game.move(x/50,y/50);
            clicked = false;
          
        }
        
        repaint();
    }

    @Override
    public void run(){

    }
}

class CyessGui extends javax.swing.JFrame {



    public CyessGui() {

        setResizable(false);
        initComponents();
        setLocationRelativeTo(this);
        setVisible(true);
    }

    public void click(int x ,int y){}

    public void flip(){}
    
    public void back(){}
    
    public void forward(){}
    
    public void changeAutoFlip(boolean change){}
    
    public void changeTurnOrder(boolean change){}
    
    public void changeCheckMoves(boolean change){}

    public void paintSurface(Graphics g){}
    
    public void toggleMusic(){}

    class CustomPanel extends JPanel
    {
        @Override
        public void paintComponent(Graphics g)
        {

            paintSurface(g);

        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new CustomPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        AutoFlip = new javax.swing.JCheckBoxMenuItem();
        TurnOrder = new javax.swing.JCheckBoxMenuItem();
        checkmoveitem = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jMenu2 = new javax.swing.JMenu();
        backItem = new javax.swing.JMenuItem();
        forwardItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cyess");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("resources/pawn_icon.png")));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 400));
        jPanel1.setVerifyInputWhenFocusTarget(false);
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        jMenu1.setText("Options");

        AutoFlip.setText("Auto Flip");
        AutoFlip.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                AutoFlipStateChanged(evt);
            }
        });
        jMenu1.add(AutoFlip);

        TurnOrder.setSelected(true);
        TurnOrder.setText("Turn Order");
        TurnOrder.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                TurnOrderStateChanged(evt);
            }
        });
        jMenu1.add(TurnOrder);

        checkmoveitem.setSelected(true);
        checkmoveitem.setText("Check Moves");
        checkmoveitem.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                checkmoveitemStateChanged(evt);
            }
        });
        jMenu1.add(checkmoveitem);

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("Toggle Music");
        jCheckBoxMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jCheckBoxMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText(" Control");

        backItem.setText("Back");
        backItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                backItemMousePressed(evt);
            }
        });
        jMenu2.add(backItem);

        forwardItem.setText("Forward");
        forwardItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                forwardItemMousePressed(evt);
            }
        });
        jMenu2.add(forwardItem);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.getAccessibleContext().setAccessibleParent(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
       if(evt.getKeyChar() == 'f')
       flip();
       
    }//GEN-LAST:event_formKeyPressed

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        click(evt.getX(),evt.getY());
    }//GEN-LAST:event_jPanel1MousePressed

    private void AutoFlipStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_AutoFlipStateChanged
        this.changeAutoFlip(AutoFlip.getState());
    }//GEN-LAST:event_AutoFlipStateChanged

    private void TurnOrderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_TurnOrderStateChanged
        this.changeTurnOrder(TurnOrder.getState());
    }//GEN-LAST:event_TurnOrderStateChanged

    private void backItemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backItemMousePressed
        back();
    }//GEN-LAST:event_backItemMousePressed

    private void forwardItemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forwardItemMousePressed
        forward();
    }//GEN-LAST:event_forwardItemMousePressed

    private void checkmoveitemStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_checkmoveitemStateChanged
        this.changeCheckMoves(checkmoveitem.getState());
    }//GEN-LAST:event_checkmoveitemStateChanged

    private void jCheckBoxMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1ActionPerformed
        this.toggleMusic();
    }//GEN-LAST:event_jCheckBoxMenuItem1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem AutoFlip;
    private javax.swing.JCheckBoxMenuItem TurnOrder;
    private javax.swing.JMenuItem backItem;
    private javax.swing.JCheckBoxMenuItem checkmoveitem;
    private javax.swing.JMenuItem forwardItem;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    protected javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}


class Sound 
{
    String name;
    Clip clip;
    
    public Sound(String path)
    {      
        try {
          
        clip = AudioSystem.getClip();
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/resources/"+path));
        clip.open(inputStream);   
        
        name = path;
        
      } catch (Exception e) {
        System.err.println(e.getMessage());
      }
        
    }
    
    public void play()
    {
        clip.start();
        
    }
    
    public void pause()
    {
        clip.stop();
    }
    
    public void stop()
    {
        clip.stop();
        clip.setFramePosition(0);
    }
    
    
}