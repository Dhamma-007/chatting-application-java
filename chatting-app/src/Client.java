import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Client  implements ActionListener {
    int  x=450;
    int y=700;
    static JFrame f=new JFrame();
    JTextField text;
    static JPanel chat;
    static Box vertical=Box.createVerticalBox();
    static DataOutputStream dout;
    Client(){
        //header
        f.setLayout(null);
        JPanel p1=new JPanel();
        p1.setBackground(new Color(93, 99, 99));
        p1.setBounds(0,0,x,70);
        p1.setLayout(null);
        f.add(p1);

        //back button
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2=i1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel back=new JLabel(i3);
        back.setBounds(5,20,25,25);
        p1.add(back);
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        //profile image
        ImageIcon pf1=new ImageIcon(ClassLoader.getSystemResource("icons/fem.png"));
        Image pf2=pf1.getImage().getScaledInstance(70,80,Image.SCALE_DEFAULT);
        ImageIcon pf3=new ImageIcon(pf2);
        JLabel profile=new JLabel(pf3);
        profile.setBounds(40,10,50,50);
        p1.add(profile);

        //video icon
        ImageIcon vc1=new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image vc2=vc1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon vc3=new ImageIcon(vc2);
        JLabel video=new JLabel(vc3);
        video.setBounds(300,20,30,30);
        p1.add(video);

        //call icon
        ImageIcon cl1=new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image cl2=cl1.getImage().getScaledInstance(25,30,Image.SCALE_DEFAULT);
        ImageIcon cl3=new ImageIcon(cl2);
        JLabel call=new JLabel(cl3);
        call.setBounds(360,20,25,30);
        p1.add(call);

        //more
        ImageIcon mr1=new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image mr2=mr1.getImage().getScaledInstance(10,25,Image.SCALE_DEFAULT);
        ImageIcon mr3=new ImageIcon(mr2);
        JLabel more=new JLabel(mr3);
        more.setBounds(410,22,10,25);
        p1.add(more);

        //name
        JLabel name=new JLabel("User 2");
        name.setBounds(110,15,100,18);
        name.setForeground(Color.white);
        name.setFont(new Font("SAN_SERIF", Font.BOLD,18));
        p1.add(name);

        //active status
        JLabel active=new JLabel("Active Now");
        active.setBounds(110,35,100,18);
        active.setForeground(Color.white);
        active.setFont(new Font("SAN_SERIF", Font.BOLD,10));
        p1.add(active);

        //chat area
        chat=new JPanel();
        chat.setBounds(5,75,440,570);
        f.add(chat);

        text=new JTextField();
        text.setBounds(5,655,310,40);
        text.setFont(new Font("SAN_FERIF",Font.PLAIN,16));
        f.add(text);

        JButton send = new JButton("Send");
        send.setBounds(320,655,123,40);
        send.setBackground(new Color(42, 70, 167));
        send.setForeground(Color.white);
        text.setFont(new Font("SAN_FERIF",Font.PLAIN,16));
        send.addActionListener(this);
        f.add(send);

        f.setSize(x,y);
        f.setLocation(850,100);
        f.getContentPane().setBackground(Color.WHITE);
        f.setUndecorated(true);
        f.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String msg=text.getText();
        JPanel p2=formatLabel(msg) ;

        chat.setLayout(new BorderLayout());

        JPanel right=new JPanel(new BorderLayout());
        right.add(p2,BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createHorizontalStrut(15));

        chat.add(vertical,BorderLayout.PAGE_START);
        try {
            dout.writeUTF(msg);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        text.setText("");
        f.repaint();
        f.invalidate();
        f.validate();

    }
    public  static JPanel formatLabel(String msg){
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(
                panel,BoxLayout.Y_AXIS
        ));
        JLabel op=new JLabel( "<html><p style=\"width: 150px\">"+msg+"</p></html> " );
        op.setFont(new Font("Tahoma",Font.PLAIN,16));
        op.setBackground(new Color(147, 148, 227));
        op.setOpaque(true);
        op.setBorder(new EmptyBorder(15,15,15,50));
        panel.add(op);

        Calendar cal=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");

        JLabel time=new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        return panel;
    }
    public static void main(String[] args) {
//        System.out.println("hello");
        new Client();
        try {
            Socket s=new Socket("127.0.0.1",6001);
            DataInputStream din=new DataInputStream(s.getInputStream());
            dout=new DataOutputStream(s.getOutputStream());
            while(true) {
                chat.setLayout(new  BorderLayout());

                String msg=din.readUTF();
                JPanel panel=formatLabel(msg);
                JPanel left=new JPanel(new BorderLayout());
                left.add(panel,BorderLayout.LINE_START);
                vertical.add(left);
                vertical.add(Box.createVerticalStrut(15));
                chat.add(vertical,BorderLayout.PAGE_START);


                f.validate();

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
