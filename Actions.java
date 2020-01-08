import javax.swing.*;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.prefs.Preferences;

 class Actions extends JFrame implements ActionListener
 {
 JPanel pnl = new JPanel();
 JLabel lbl = new JLabel();
 Timer tm;
 int x = 0;
 

 
 public static void main( String[] args)
 {
  Actions gui = new Actions();
 }
 
 JButton goBtn = new JButton("Start");
 
 ImageIcon duke = new ImageIcon(getClass().getResource("thumbnail_logo.jpg"));
 
 
 JLabel lbl1 = new JLabel(duke);
 JLabel lbl2 = new JLabel("ya boi");
 JLabel lbl3 = new JLabel("ya boiiiiii", duke, JLabel.CENTER);
 
 
 
 JTextField txt1 = new JTextField(getPref(), 38);
 JTextField txt2 = new JTextField("Defult Text", 38);
 
 JRadioButton rad1 = new JRadioButton("Normal");
 JRadioButton rad2 = new JRadioButton("Big one");
 JRadioButton radJPG = new JRadioButton("JPG");
 JRadioButton radPNG = new JRadioButton("PNG");
// 
 ButtonGroup sizes = new ButtonGroup();
 ButtonGroup format = new ButtonGroup();
 
 String location;
 Preferences prefs = Preferences.userRoot().node(this.getClass().getName());  
   
    public Actions()
    {
     super("yay prizes wooooo");
     lbl = new JLabel();
     int width = 1000;
     int height = 1000;
     setSize( width, height);
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     add(pnl);
     setVisible(true);
     
     
     
//      int numberOfDicks = dickPics.size();
//         Collections.shuffle(dickPics);
//         Collections.shuffle(dickPics);
//      
     
     pnl.add(radJPG);
     pnl.add(radPNG);
     radJPG.setSelected(true);
   
     pnl.add(goBtn);
     
     lbl3.setHorizontalTextPosition(JLabel.CENTER);
     lbl3.setVerticalTextPosition(JLabel.CENTER);
     
     
     sizes.add(rad1);
     sizes.add(rad2);
//     sizes.add(rad3);
     format.add(radJPG);
     format.add(radPNG);
//     
     pnl.add(rad1);
     pnl.add(rad2);
//     pnl.add(rad3);
     rad1.setSelected(true);
     
     
     pnl.add(txt1);
     //pnl.add(txt2);
         
     pnl.add(lbl1);
     //pnl.add(lbl2);
     //pnl.add(lbl3);

     
     goBtn.addActionListener(this);
     rad1.addActionListener(this);
     rad2.addActionListener(this);
     radJPG.addActionListener(this);
     radPNG.addActionListener(this);
    
    }
   
    List<String> dickPics;
   
    public void actionPerformed(ActionEvent event)
    {
    if(event.getSource() == rad1)
    {
       	 //System.out.println("test");
       	 setMaxPhoto(6);
//       	System.out.println(getMaxPhoto());
    }
     if(event.getSource() == rad2)
     {
    	 //System.out.println("test");
    	 setMaxPhoto(getMaxPhoto()+10);
//    	 System.out.println(getMaxPhoto());
     }
     if(event.getSource() == radJPG)
     {
    	 //System.out.println("test");
    	 setExtension(".JPG");
    	 System.out.println(getExtension());
     }
     if(event.getSource() == radPNG)
     {
    	 //System.out.println("test");
    	 setExtension(".PNG");
    	 System.out.println(getExtension());
     }
    	
    	
     if(event.getSource() == goBtn)
     {
   
    location = txt1.getText();
    //System.out.println(location);
    setPref(location);
      File dir = new File(location);
//      if (!dir.isDirectory())
//      {
//      System.out.println(location + "not a dir");
//      }
//      if (dir.listFiles().length<1)
//      {
//      System.out.println(location + "has no files in ");
//      }
      dickPics = Arrays.asList(dir.list(
         new FilenameFilter() {
            @Override public boolean accept(File dir, String name) {
               return name.endsWith(getExtension());
            }
         
         }
      ));
     lbl1.setVisible(false);
     pnl.setVisible(false);
     Project();
//     int response = JOptionPane.showConfirmDialog(resetBtn, "ok");
//     if (response == 0){
//    	 lbl1.setVisible(true);
//         pnl.setVisible(true);
//    	 
//     }

     
     }
     
     
    }

public void setPref(String location) {
// Preference key name
prefs = Preferences.userRoot().node(this.getClass().getName());
   final String PREF_NAME = "loc";
   prefs.put(PREF_NAME, location);
}

public String getPref() {
// Preference key name
prefs = Preferences.userRoot().node(this.getClass().getName());
   final String PREF_NAME = "loc";
   return prefs.get(PREF_NAME, "C:/pics");
}
   

   
    public void SetImageSize(int i){
     
      //JPanel imagePanel = new JPanel( new BorderLayout() );
     
        ImageIcon icon = new ImageIcon(location+ "/"+dickPics.get(i));
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_FAST);
        ImageIcon newImc = new ImageIcon(newImg);
        lbl.setIcon(newImc);
    }
   
   int maxPhoto = 6;
   
   public int getMaxPhoto(){
	   return this.maxPhoto;
   }
    
   public void setMaxPhoto(int num){
        	this.maxPhoto = num;
        }
   
   String extension = ".JPG";
   
   public String getExtension(){
	   return this.extension;
   }
    
   public void setExtension(String str){
        	this.extension = str;
        }
    public void Project(){
        //super("Who gun win?");
        lbl.setBounds(pnl.getBounds());
        int numberOfDicks = dickPics.size();
        Collections.shuffle(dickPics);
        Collections.shuffle(dickPics);
       
       
        //Call The Function SetImageSize
        Random rand = new Random();
        SetImageSize(rand.nextInt(numberOfDicks));
//        int maxPhoto = rand.nextInt(4)+ 5 ;
        setMaxPhoto((rand.nextInt(5))+ getMaxPhoto());
        
       
               //set a timer
        tm = new Timer(1500,new ActionListener() { //Time between photos

            @Override
            public void actionPerformed(ActionEvent e) {
               
               
                if(x >= getMaxPhoto()){
                    tm.stop();
                    //getContentPane().setBackground(Color.decode("#17ebcb"));
                    try {
            Thread.sleep(30000);
            } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            }
                    final JOptionPane pane = new JOptionPane("click ok to resart");
                    final JDialog d = pane.createDialog((JFrame)null, "beep boop");
                    d.setLocation(10,10);
                    d.setVisible(true);
                    
                    int response = JOptionPane.showConfirmDialog(null, 
                            "click ok to resart", "beep boop", JOptionPane.DEFAULT_OPTION);
                    
//                    System.out.println(response);
                    if (response == 0){
                    	lbl1.setVisible(true);
                        pnl.setVisible(true);
                        x=0;
                        setMaxPhoto(5);
                   	 
                    }
                }
                else {
                x += 1;
                    SetImageSize(rand.nextInt(numberOfDicks));
                }
            }
        });
       
        add(lbl);
        tm.start();
        
        getContentPane().setBackground(Color.decode("#bdb67b"));
            
    }

     }