import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BankomatOkno extends JFrame {
    JButton wplac,wyplac,doladuj,drukuj,wyloguj;
    Font font = new Font("Geneva",Font.ITALIC, 18);

    Konto osoba;
    Bankomat bankomat;

    public BankomatOkno(Konto osoby,Bankomat bankomat){

        this.bankomat = bankomat;
        this.osoba = osoby;

        //odstep
        setTitle("Bankomat");
        setSize(380,500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(0,40,30));


        //wplac
        wplac = new JButton("Wpłać");
        wplac.setPreferredSize(new Dimension(300,30));
        wplac.setFont(font);
        add(wplac);


        //wyplac
        wyplac = new JButton("Wypłać");
        wyplac.setPreferredSize(new Dimension(300,30));
        wyplac.setFont(font);
        add(wyplac);


        //doladuj
        doladuj = new JButton("Doładuj telefon");
        doladuj.setPreferredSize(new Dimension(300,30));
        doladuj.setFont(font);
        add(doladuj);


        //drukuj
        drukuj = new JButton("Drukuj stan konta");
        drukuj.setPreferredSize(new Dimension(300,30));
        drukuj.setFont(font);
        add(drukuj);



        //wyloguj
        wyloguj = new JButton("Wyloguj");
        wyloguj.setPreferredSize(new Dimension(300,30));
        wyloguj.setFont(font);
        add(wyloguj);



        wplac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame wplataOkno = new WplataOkno(osoba,bankomat);
            }
        });

        wyplac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame wyplataOkno = new WyplataOkno(osoba,bankomat);
            }
        });

        doladuj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame doladuj = new DoladowanieOkno(osoba,bankomat);
            }
        });

        wyloguj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame bank = new LogowanieOkno(bankomat);
            }
        });

        drukuj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = new Date(); // This object contains the current date value
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String dataWydruku =  formatter.format(date);
                JOptionPane.showMessageDialog(null, "Data: "+dataWydruku+"\nImie: "+osoba.getImie()+"\nStan konta: "+osoba.getStanKonta(),"Wydruk",JOptionPane.INFORMATION_MESSAGE);
            }
        });

    }
}
