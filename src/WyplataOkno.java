import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class WyplataOkno extends JFrame {


    JButton wyplac,powrot;
    JLabel JLkwota,JLinfo;
    JTextField JTkwota;
    JRadioButton n20,n50,n100,n200,n500,brak;

    Font font = new Font("Geneva",Font.ITALIC, 18);



    int  n = 5;
    int numerObslugiwanegoUzytkownika = 0;
    ArrayList<String>[] al = new ArrayList[n];

    int kwota;
    Konto osoba;
    Bankomat bankomat;

    public WyplataOkno(Konto osoby,Bankomat Bankomat){
        this.bankomat = Bankomat;
        this.osoba = osoby;

        setTitle("Wyplata");
        setSize(380,350);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(0,20,20));

        ButtonGroup gRadio = new ButtonGroup();

        JLkwota = new JLabel("Kwota:");
        JTkwota = new JTextField("");
        wyplac = new JButton("Wypłać");
        powrot = new JButton("Powrót");
        JLinfo = new JLabel("       Opcjonalne - wybierz nominał");

        n20 = new JRadioButton("20");
        n50 = new JRadioButton("50");
        n100 = new JRadioButton("100");
        n200 = new JRadioButton("200");
        n500 = new JRadioButton("500");
        brak = new JRadioButton("Brak");

        gRadio.add(n20);
        gRadio.add(n50);
        gRadio.add(n100);
        gRadio.add(n200);
        gRadio.add(n500);
        gRadio.add(brak);


        JLkwota.setPreferredSize(new Dimension(75,30));
        JTkwota.setPreferredSize(new Dimension(225,30));

        JLinfo.setPreferredSize(new Dimension(300,30));

        powrot.setPreferredSize(new Dimension(150,30));
        wyplac.setPreferredSize(new Dimension(150,30));
        brak.setPreferredSize(new Dimension(340,30));


        JLinfo.setFont(font);
        JLkwota.setFont(font);
        JTkwota.setFont(font);

        n20.setFont(font);
        n50.setFont(font);
        n100.setFont(font);
        n200.setFont(font);
        n500.setFont(font);
        brak.setFont(font);

        powrot.setFont(font);
        wyplac.setFont(font);

        add(JLkwota);
        add(JTkwota);
        add(JLinfo);

        add(n20);
        add(n50);
        add(n100);
        add(n200);
        add(n500);
        add(brak);
        add(wyplac);

        add(powrot);

        n20.setActionCommand(n20.getText());
        n50.setActionCommand(n50.getText());
        n100.setActionCommand(n100.getText());
        n200.setActionCommand(n200.getText());
        n500.setActionCommand(n500.getText());
        brak.setActionCommand(brak.getText());

        wyplac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nominaly;

                float zmiennaPomFLOAT;
                int zmiennaPomINT,Ilosc20 = 0, Ilosc50 = 0 ,Ilosc100 = 0,Ilosc200 =0 ,Ilosc500 = 0;

                try{
                    nominaly = gRadio.getSelection().getActionCommand();
                    kwota = Integer.parseInt(JTkwota.getText());
                    if(kwota/10 == 1 || kwota/10 == 3 || kwota < 20 || kwota%10 > 0){
                        JOptionPane.showMessageDialog(null,"Bankomat nie wydaje takich nominałów.","Błąd.",JOptionPane.INFORMATION_MESSAGE);
                        throw new IllegalArgumentException("Bankomat nie dysponuje takimi nominałami.");
                    } else {

                        System.out.println(nominaly);
                        //jezeli nie ma preferencji wyplaty w nominalach
                        if(nominaly.equals("Brak")){
                            int zmienna = kwota;
                            zmiennaPomFLOAT = osoba.getStanKonta();
                            if(kwota/500 > 0){
                                Ilosc500 = kwota/500;
                                kwota -= Ilosc500*500;
                            }
                            if(kwota/200 > 0){
                                Ilosc200 = kwota/200;
                                kwota -= Ilosc200*200;
                            }

                            if(kwota/100 > 0){
                                Ilosc100 = kwota/100;
                                kwota -= Ilosc100*100;
                            }

                            if((kwota/50 > 0  && kwota%50 != 10 && kwota%50 != 30 && kwota%50 !=20) || kwota == 70){
                                Ilosc50 = kwota/50;
                                kwota -= Ilosc50*50;
                            }

                            if(kwota/20 > 0){
                                Ilosc20 = kwota/20;
                                kwota -= Ilosc20*20;
                            }

                            kwota = zmienna;
                            osoba.Wyplac(kwota);
                            if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                bankomat.b500.zmniejszIlosc(Ilosc500);
                                bankomat.b200.zmniejszIlosc(Ilosc200);
                                bankomat.b100.zmniejszIlosc(Ilosc100);
                                bankomat.b50.zmniejszIlosc(Ilosc50);
                                bankomat.b20.zmniejszIlosc(Ilosc20);
                                bankomat.setBanknotyIloscList();
                                System.out.println(bankomat.toString());
                            }


                        }else{
                                /// rozmienianie 20
                                if(nominaly.equals("20")){

                                    Ilosc20 = kwota/20;
                                    zmiennaPomINT = kwota%20;

                                    if(zmiennaPomINT == 10){
                                        Ilosc20 -= 2;
                                        Ilosc50 += 1;
                                        System.out.println(Ilosc20);
                                        System.out.println(Ilosc50);


                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b20.zmniejszIlosc(Ilosc20);
                                            bankomat.b50.zmniejszIlosc(Ilosc50);
                                            bankomat.setBanknotyIloscList();
                                            System.out.println(bankomat.toString());
                                        }
                                    } else {

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b20.zmniejszIlosc(Ilosc20);
                                            bankomat.setBanknotyIloscList();
                                            System.out.println(bankomat.toString());
                                        }
                                    }
                                    System.out.println(zmiennaPomINT);
                                }
                                ///rozmienianie 50
                                if(nominaly.equals("50")){

                                    Ilosc50 = kwota/50;
                                    zmiennaPomINT = kwota%50;

                                    if(zmiennaPomINT == 40){
                                        Ilosc20 += 2;
                                        System.out.println(Ilosc20);
                                        System.out.println(Ilosc50);

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b20.zmniejszIlosc(Ilosc20);
                                            bankomat.b50.zmniejszIlosc(Ilosc50);
                                            bankomat.setBanknotyIloscList();
                                            System.out.println(bankomat.toString());
                                        }
                                    } else if(zmiennaPomINT == 20 ){
                                        Ilosc20 += 1;
                                        System.out.println(Ilosc20);
                                        System.out.println(Ilosc50);

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b20.zmniejszIlosc(Ilosc20);
                                            bankomat.b50.zmniejszIlosc(Ilosc50);
                                            bankomat.setBanknotyIloscList();
                                            System.out.println(bankomat.toString());
                                        }
                                    } else {

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b50.zmniejszIlosc(Ilosc50);
                                            bankomat.setBanknotyIloscList();
                                            System.out.println(bankomat.toString());
                                        }
                                    }
                                    System.out.println(zmiennaPomINT);
                                }
                                /// rozmienianie setek
                                if(nominaly.equals("100")){

                                    Ilosc100 = kwota/100;
                                    zmiennaPomINT = kwota%100;

                                    if(zmiennaPomINT == 90){

                                        Ilosc50 += 1;
                                        Ilosc20 += 2;

                                        System.out.println(Ilosc100);
                                        System.out.println(Ilosc50);
                                        System.out.println(Ilosc20);

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b100.zmniejszIlosc(Ilosc100);
                                            bankomat.b50.zmniejszIlosc(Ilosc50);
                                            bankomat.b20.zmniejszIlosc(Ilosc20);
                                            bankomat.setBanknotyIloscList();
                                            System.out.println(bankomat.toString());
                                        }
                                    } else

                                    if(zmiennaPomINT == 80){

                                        Ilosc20 += 4;

                                        System.out.println(Ilosc100);
                                        System.out.println(Ilosc20);

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b100.zmniejszIlosc(Ilosc100);
                                            bankomat.b20.zmniejszIlosc(Ilosc20);
                                            bankomat.setBanknotyIloscList();
                                            System.out.println(bankomat.toString());
                                        }
                                    } else

                                    if(zmiennaPomINT == 70){

                                        Ilosc50 += 1;
                                        Ilosc20 += 1;

                                        System.out.println(Ilosc100);
                                        System.out.println(Ilosc50);
                                        System.out.println(Ilosc20);

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b100.zmniejszIlosc(Ilosc100);
                                            bankomat.b50.zmniejszIlosc(Ilosc50);
                                            bankomat.b20.zmniejszIlosc(Ilosc20);
                                            bankomat.setBanknotyIloscList();
                                            System.out.println(bankomat.toString());
                                        }
                                    } else

                                    if(zmiennaPomINT == 60){

                                        Ilosc20 += 3;

                                        System.out.println(Ilosc100);
                                        System.out.println(Ilosc20);

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b100.zmniejszIlosc(Ilosc100);
                                            bankomat.b50.zmniejszIlosc(Ilosc20);
                                            bankomat.setBanknotyIloscList();
                                            System.out.println(bankomat.toString());
                                        }
                                    } else

                                    if(zmiennaPomINT == 50){

                                            Ilosc50 += 1;

                                            System.out.println(Ilosc100);
                                            System.out.println(Ilosc50);

                                            zmiennaPomFLOAT = osoba.getStanKonta();
                                            osoba.Wyplac(kwota);
                                            if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                                bankomat.b100.zmniejszIlosc(Ilosc100);
                                                bankomat.b50.zmniejszIlosc(Ilosc50);
                                                bankomat.setBanknotyIloscList();
                                                System.out.println(bankomat.toString());
                                            }
                                    } else

                                    if(zmiennaPomINT == 40){

                                        Ilosc20 += 2;
                                        System.out.println(Ilosc20);
                                        System.out.println(Ilosc100);

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b20.zmniejszIlosc(Ilosc20);
                                            bankomat.b100.zmniejszIlosc(Ilosc100);
                                            bankomat.setBanknotyIloscList();
                                            System.out.println(bankomat.toString());
                                        }
                                    } else if(zmiennaPomINT == 20 ){

                                        Ilosc20 += 1;
                                        System.out.println(Ilosc20);
                                        System.out.println(Ilosc100);

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b20.zmniejszIlosc(Ilosc20);
                                            bankomat.b100.zmniejszIlosc(Ilosc100);
                                            bankomat.setBanknotyIloscList();
                                            System.out.println(bankomat.toString());
                                        }
                                    } else {

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b100.zmniejszIlosc(Ilosc100);
                                            bankomat.setBanknotyIloscList();
                                            System.out.println(bankomat.toString());
                                        }
                                    }
                                    System.out.println(zmiennaPomINT);
                                }

                                /// rozmienianie dwusetek
                                if(nominaly.equals("200")){

                                    Ilosc200 = kwota/200;
                                    zmiennaPomINT = kwota%200;

                                    if(zmiennaPomINT == 190){

                                        Ilosc50 +=1;
                                        Ilosc20 += 2;
                                        Ilosc100 += 1;

                                        System.out.println(Ilosc200);
                                        System.out.println(Ilosc20);
                                        System.out.println(Ilosc100);
                                        System.out.println(Ilosc50);

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b200.zmniejszIlosc(Ilosc200);
                                            bankomat.b100.zmniejszIlosc(Ilosc100);
                                            bankomat.b50.zmniejszIlosc(Ilosc50);
                                            bankomat.b20.zmniejszIlosc(Ilosc20);
                                            bankomat.setBanknotyIloscList();

                                            System.out.println(bankomat.toString());
                                        }
                                    } else

                                    if(zmiennaPomINT == 180){

                                        Ilosc20 += 4;
                                        Ilosc100 += 1;

                                        System.out.println(Ilosc200);
                                        System.out.println(Ilosc20);
                                        System.out.println(Ilosc100);

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b200.zmniejszIlosc(Ilosc200);
                                            bankomat.b100.zmniejszIlosc(Ilosc100);
                                            bankomat.b20.zmniejszIlosc(Ilosc20);
                                            bankomat.setBanknotyIloscList();

                                            System.out.println(bankomat.toString());
                                        }
                                    } else

                                    if(zmiennaPomINT == 170){

                                        Ilosc50 +=1;
                                        Ilosc20 += 1;
                                        Ilosc100 += 1;


                                        System.out.println(Ilosc20);
                                        System.out.println(Ilosc50);
                                        System.out.println(Ilosc100);
                                        System.out.println(Ilosc200);

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b200.zmniejszIlosc(Ilosc200);
                                            bankomat.b50.zmniejszIlosc(Ilosc50);
                                            bankomat.b100.zmniejszIlosc(Ilosc100);
                                            bankomat.b20.zmniejszIlosc(Ilosc20);
                                            bankomat.setBanknotyIloscList();

                                            System.out.println(bankomat.toString());
                                        }
                                    } else


                                    if(zmiennaPomINT == 160){

                                        Ilosc20 += 3;
                                        Ilosc100 += 1;

                                        System.out.println(Ilosc200);
                                        System.out.println(Ilosc20);
                                        System.out.println(Ilosc100);

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b200.zmniejszIlosc(Ilosc200);
                                            bankomat.b100.zmniejszIlosc(Ilosc100);
                                            bankomat.b20.zmniejszIlosc(Ilosc20);
                                            bankomat.setBanknotyIloscList();

                                            System.out.println(bankomat.toString());
                                        }
                                    } else

                                    if(zmiennaPomINT == 150){

                                        Ilosc50 += 1;
                                        Ilosc100 += 1;

                                        System.out.println(Ilosc200);
                                        System.out.println(Ilosc50);
                                        System.out.println(Ilosc100);

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b200.zmniejszIlosc(Ilosc200);
                                            bankomat.b100.zmniejszIlosc(Ilosc100);
                                            bankomat.b50.zmniejszIlosc(Ilosc50);
                                            bankomat.setBanknotyIloscList();

                                            System.out.println(bankomat.toString());
                                        }
                                    } else

                                    if(zmiennaPomINT == 140){

                                        Ilosc100 += 1;
                                        Ilosc20 += 2;

                                        System.out.println(Ilosc200);
                                        System.out.println(Ilosc100);
                                        System.out.println(Ilosc20);

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b200.zmniejszIlosc(Ilosc200);
                                            bankomat.b100.zmniejszIlosc(Ilosc100);
                                            bankomat.b20.zmniejszIlosc(Ilosc20);
                                            bankomat.setBanknotyIloscList();

                                            System.out.println(bankomat.toString());
                                        }
                                    } else

                                    if(zmiennaPomINT == 130){

                                        Ilosc50 += 1;
                                        Ilosc20 += 4;

                                        System.out.println(Ilosc200);
                                        System.out.println(Ilosc50);
                                        System.out.println(Ilosc20);

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b200.zmniejszIlosc(Ilosc200);
                                            bankomat.b50.zmniejszIlosc(Ilosc50);
                                            bankomat.b20.zmniejszIlosc(Ilosc20);
                                            bankomat.setBanknotyIloscList();

                                            System.out.println(bankomat.toString());
                                        }
                                    } else

                                    if(zmiennaPomINT == 120){

                                        Ilosc100 += 1;
                                        Ilosc20 += 1;

                                        System.out.println(Ilosc200);
                                        System.out.println(Ilosc100);
                                        System.out.println(Ilosc20);

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b200.zmniejszIlosc(Ilosc200);
                                            bankomat.b100.zmniejszIlosc(Ilosc100);
                                            bankomat.b20.zmniejszIlosc(Ilosc20);
                                            bankomat.setBanknotyIloscList();

                                            System.out.println(bankomat.toString());
                                        }
                                    } else

                                    if(zmiennaPomINT == 110){

                                        Ilosc50 += 1;
                                        Ilosc20 += 3;

                                        System.out.println(Ilosc200);
                                        System.out.println(Ilosc50);
                                        System.out.println(Ilosc20);

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b200.zmniejszIlosc(Ilosc200);
                                            bankomat.b50.zmniejszIlosc(Ilosc50);
                                            bankomat.b20.zmniejszIlosc(Ilosc20);
                                            bankomat.setBanknotyIloscList();

                                            System.out.println(bankomat.toString());
                                        }
                                    } else

                                    if(zmiennaPomINT == 100){

                                        Ilosc100 += 1;

                                        System.out.println(Ilosc200);
                                        System.out.println(Ilosc100);

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b200.zmniejszIlosc(Ilosc200);
                                            bankomat.b100.zmniejszIlosc(Ilosc100);
                                            bankomat.setBanknotyIloscList();
                                            System.out.println(bankomat.toString());
                                        }
                                    } else

                                    if(zmiennaPomINT == 90){

                                        Ilosc50 += 1;
                                        Ilosc20 += 2;

                                        System.out.println(Ilosc200);
                                        System.out.println(Ilosc50);
                                        System.out.println(Ilosc20);

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b200.zmniejszIlosc(Ilosc200);
                                            bankomat.b50.zmniejszIlosc(Ilosc50);
                                            bankomat.b20.zmniejszIlosc(Ilosc20);
                                            bankomat.setBanknotyIloscList();
                                            System.out.println(bankomat.toString());
                                        }
                                    } else

                                    if(zmiennaPomINT == 80){

                                        Ilosc20 += 4;

                                        System.out.println(Ilosc200);
                                        System.out.println(Ilosc20);

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b200.zmniejszIlosc(Ilosc200);
                                            bankomat.b20.zmniejszIlosc(Ilosc20);
                                            bankomat.setBanknotyIloscList();
                                            System.out.println(bankomat.toString());
                                        }
                                    } else

                                    if(zmiennaPomINT == 70){

                                        Ilosc50 += 1;
                                        Ilosc20 += 1;

                                        System.out.println(Ilosc200);
                                        System.out.println(Ilosc50);
                                        System.out.println(Ilosc20);

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b200.zmniejszIlosc(Ilosc200);
                                            bankomat.b50.zmniejszIlosc(Ilosc50);
                                            bankomat.b20.zmniejszIlosc(Ilosc20);
                                            bankomat.setBanknotyIloscList();
                                            System.out.println(bankomat.toString());
                                        }
                                    } else

                                    if(zmiennaPomINT == 60){

                                        Ilosc20 += 3;

                                        System.out.println(Ilosc200);
                                        System.out.println(Ilosc20);

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b200.zmniejszIlosc(Ilosc200);
                                            bankomat.b20.zmniejszIlosc(Ilosc20);
                                            bankomat.setBanknotyIloscList();
                                            System.out.println(bankomat.toString());
                                        }
                                    } else

                                    if(zmiennaPomINT == 50){

                                        Ilosc50 += 1;

                                        System.out.println(Ilosc200);
                                        System.out.println(Ilosc50);

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b200.zmniejszIlosc(Ilosc200);
                                            bankomat.b50.zmniejszIlosc(Ilosc50);
                                            bankomat.setBanknotyIloscList();
                                            System.out.println(bankomat.toString());
                                        }
                                    } else

                                    if(zmiennaPomINT == 40){

                                        Ilosc20 += 2;
                                        System.out.println(Ilosc20);
                                        System.out.println(Ilosc200);

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b20.zmniejszIlosc(Ilosc20);
                                            bankomat.b200.zmniejszIlosc(Ilosc200);
                                            bankomat.setBanknotyIloscList();
                                            System.out.println(bankomat.toString());
                                        }
                                    } else if(zmiennaPomINT == 20 ){

                                        Ilosc20 += 1;
                                        System.out.println(Ilosc20);
                                        System.out.println(Ilosc200);

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b20.zmniejszIlosc(Ilosc20);
                                            bankomat.b200.zmniejszIlosc(Ilosc200);
                                            bankomat.setBanknotyIloscList();
                                            System.out.println(bankomat.toString());
                                        }
                                    } else {

                                        zmiennaPomFLOAT = osoba.getStanKonta();
                                        osoba.Wyplac(kwota);
                                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                            bankomat.b200.zmniejszIlosc(Ilosc200);
                                            bankomat.setBanknotyIloscList();
                                            System.out.println(bankomat.toString());
                                        }
                                    }
                                    System.out.println(zmiennaPomINT);
                                }

                            /// rozmienianie dwusetek
                            if(nominaly.equals("500")){

                                Ilosc500 = kwota/500;
                                zmiennaPomINT = kwota%500;

                                if(zmiennaPomINT == 490){

                                    Ilosc50 +=1;
                                    Ilosc20 +=2;
                                    Ilosc200 += 2;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc200);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b200.zmniejszIlosc(Ilosc200);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 480){

                                    Ilosc20 +=4;
                                    Ilosc200 += 2;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc200);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b200.zmniejszIlosc(Ilosc200);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 470){

                                    Ilosc20 +=1;
                                    Ilosc50 +=1;
                                    Ilosc200 += 2;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc200);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b200.zmniejszIlosc(Ilosc200);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 460){

                                    Ilosc20 +=3;
                                    Ilosc200 += 2;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc200);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b200.zmniejszIlosc(Ilosc200);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 450){

                                    Ilosc50 +=1;
                                    Ilosc200 += 2;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc200);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b200.zmniejszIlosc(Ilosc200);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 440){

                                    Ilosc20 +=2;
                                    Ilosc200 += 2;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc200);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b200.zmniejszIlosc(Ilosc200);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 430){

                                    Ilosc20 +=4;
                                    Ilosc50 +=1;
                                    Ilosc100 +=1;
                                    Ilosc200 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc200);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b200.zmniejszIlosc(Ilosc200);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 420){

                                    Ilosc20 +=1;
                                    Ilosc200 += 2;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc200);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b200.zmniejszIlosc(Ilosc200);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 410){

                                    Ilosc20 +=3;
                                    Ilosc50 +=1;
                                    Ilosc100 +=1;
                                    Ilosc200 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc200);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b200.zmniejszIlosc(Ilosc200);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 400){

                                    Ilosc200 += 2;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc200);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b200.zmniejszIlosc(Ilosc200);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 390){

                                    Ilosc20 +=2;
                                    Ilosc50 +=1;
                                    Ilosc100 +=1;
                                    Ilosc200 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc200);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b200.zmniejszIlosc(Ilosc200);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 380){

                                    Ilosc20 +=4;
                                    Ilosc100 +=1;
                                    Ilosc200 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc200);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b200.zmniejszIlosc(Ilosc200);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 370){

                                    Ilosc20 +=1;
                                    Ilosc50 +=1;
                                    Ilosc100 +=1;
                                    Ilosc200 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc200);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b200.zmniejszIlosc(Ilosc200);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 360){

                                    Ilosc20 +=3;
                                    Ilosc100 +=1;
                                    Ilosc200 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc200);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b200.zmniejszIlosc(Ilosc200);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 350){

                                    Ilosc100 +=1;
                                    Ilosc50 +=1;
                                    Ilosc200 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc200);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b200.zmniejszIlosc(Ilosc200);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 340){

                                    Ilosc20 +=2;
                                    Ilosc100 +=1;
                                    Ilosc200 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc200);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b200.zmniejszIlosc(Ilosc200);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 330){

                                    Ilosc20 +=4;
                                    Ilosc50 +=1;
                                    Ilosc200 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc200);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b200.zmniejszIlosc(Ilosc200);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 320){

                                    Ilosc20 +=1;
                                    Ilosc100 +=1;
                                    Ilosc200 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc200);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b200.zmniejszIlosc(Ilosc200);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 310){

                                    Ilosc20 +=3;
                                    Ilosc50 +=1;
                                    Ilosc200 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc200);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b200.zmniejszIlosc(Ilosc200);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 300){

                                    Ilosc100 +=1;
                                    Ilosc200 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc200);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b200.zmniejszIlosc(Ilosc200);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 290){

                                    Ilosc20 +=2;
                                    Ilosc50 +=1;
                                    Ilosc200 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc200);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b200.zmniejszIlosc(Ilosc200);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 280){

                                    Ilosc20 +=4;
                                    Ilosc200 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc200);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b200.zmniejszIlosc(Ilosc200);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 270){

                                    Ilosc20 +=1;
                                    Ilosc50 +=1;
                                    Ilosc200 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc200);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b200.zmniejszIlosc(Ilosc200);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 260){

                                    Ilosc20 +=3;
                                    Ilosc200 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc200);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b200.zmniejszIlosc(Ilosc200);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 250){

                                    Ilosc50 +=1;
                                    Ilosc200 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc200);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b200.zmniejszIlosc(Ilosc200);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 240){

                                    Ilosc20 +=2;
                                    Ilosc200 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc200);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b200.zmniejszIlosc(Ilosc200);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 230){

                                    Ilosc20 +=4;
                                    Ilosc50 += 1;
                                    Ilosc100 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc20);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else


                                if(zmiennaPomINT == 220){

                                    Ilosc200 += 1;
                                    Ilosc20 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc200);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b200.zmniejszIlosc(Ilosc200);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 210){

                                    Ilosc20 +=3;
                                    Ilosc50 += 1;
                                    Ilosc100 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc20);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 200){

                                    Ilosc200 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc200);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b200.zmniejszIlosc(Ilosc200);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 190){

                                    Ilosc50 +=1;
                                    Ilosc20 += 2;
                                    Ilosc100 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc20);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc50);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 180){

                                    Ilosc20 += 4;
                                    Ilosc100 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc20);
                                    System.out.println(Ilosc100);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 170){

                                    Ilosc50 +=1;
                                    Ilosc20 += 1;
                                    Ilosc100 += 1;


                                    System.out.println(Ilosc20);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc500);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else


                                if(zmiennaPomINT == 160){

                                    Ilosc20 += 3;
                                    Ilosc100 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc20);
                                    System.out.println(Ilosc100);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();

                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 150){

                                    Ilosc50 += 1;
                                    Ilosc100 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc100);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.setBanknotyIloscList();
                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 140){

                                    Ilosc100 += 1;
                                    Ilosc20 += 2;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();
                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 130){

                                    Ilosc50 += 1;
                                    Ilosc20 += 4;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();
                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 120){

                                    Ilosc100 += 1;
                                    Ilosc20 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc100);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();
                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 110){

                                    Ilosc50 += 1;
                                    Ilosc20 += 3;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();
                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 100){

                                    Ilosc100 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc100);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b100.zmniejszIlosc(Ilosc100);
                                        bankomat.setBanknotyIloscList();
                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 90){

                                    Ilosc50 += 1;
                                    Ilosc20 += 2;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();
                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 80){

                                    Ilosc20 += 4;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();
                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 70){

                                    Ilosc50 += 1;
                                    Ilosc20 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc50);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();
                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 60){

                                    Ilosc20 += 3;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc20);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.setBanknotyIloscList();
                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 50){

                                    Ilosc50 += 1;

                                    System.out.println(Ilosc500);
                                    System.out.println(Ilosc50);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.b50.zmniejszIlosc(Ilosc50);
                                        bankomat.setBanknotyIloscList();
                                        System.out.println(bankomat.toString());
                                    }
                                } else

                                if(zmiennaPomINT == 40){

                                    Ilosc20 += 2;
                                    System.out.println(Ilosc20);
                                    System.out.println(Ilosc500);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.setBanknotyIloscList();
                                        System.out.println(bankomat.toString());
                                    }
                                } else if(zmiennaPomINT == 20 ){

                                    Ilosc20 += 1;
                                    System.out.println(Ilosc20);
                                    System.out.println(Ilosc500);

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b20.zmniejszIlosc(Ilosc20);
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.setBanknotyIloscList();
                                        System.out.println(bankomat.toString());
                                    }
                                } else {

                                    zmiennaPomFLOAT = osoba.getStanKonta();
                                    osoba.Wyplac(kwota);
                                    if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                                        bankomat.b500.zmniejszIlosc(Ilosc500);
                                        bankomat.setBanknotyIloscList();
                                        System.out.println(bankomat.toString());
                                    }
                                }
                                System.out.println(zmiennaPomINT);
                            }
                        }

                    }
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"Podano błędną kwotę.","Błąd.",JOptionPane.INFORMATION_MESSAGE);
                }

                JOptionPane.showMessageDialog(null,"Z konta wypłacono: "+kwota+"zł.","Error",JOptionPane.INFORMATION_MESSAGE);
                String[] dane = new String[6];
                float SALDO = osoba.getStanKonta();

                //odczytuje plik i dodaje dane na liste
                try(BufferedReader fileReader = new BufferedReader(new FileReader(new File("dane.txt")))){

                    for (int i = 0; i < n; i++) {
                        al[i] = new ArrayList<String>();
                    }
                    String wiersz = fileReader.readLine();
                    int i = 0;

                    while((wiersz = fileReader.readLine()) != null){
                        dane = wiersz.split("\t");

                        //sprawdzenie uzytkownika
                        if(dane[0].equals(osoba.getLogin()) || dane[4].equals( String.valueOf(osoba.getNrtel()) )){
                            numerObslugiwanegoUzytkownika = i;
                        }

                        for(int j = 0 ; j < 6 ; j++){
                            al[i].add(dane[j]);
                        }
                        i++;
                    }

                    System.out.println(numerObslugiwanegoUzytkownika);
                    al[numerObslugiwanegoUzytkownika].remove(5);
                    al[numerObslugiwanegoUzytkownika].add(5,String.valueOf(SALDO));

                    {
                        for (i = 0; i < n; i++) {
                            for (int j = 0; j < al[i].size(); j++) {
                                System.out.print(al[i].get(j) + " ");
                            }
                            System.out.println();
                        }
                    }

                } catch(FileNotFoundException ey){
                    System.out.println("Nie odnaleziono pliku.");
                    ey.printStackTrace();
                } catch(IOException ex){
                    System.out.println("Plik uszkodzony.");
                }

                //zapisywanie nowego pliku
                try {
                    FileWriter myWriter = new FileWriter("dane.txt");
                        myWriter.write("login\tpin\timie\tnazwisko\tnumertelefonu\tsaldo\n");
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < al[i].size(); j++) {
                            myWriter.write(al[i].get(j) + "\t");
                        }
                        myWriter.write("\n");
                    }

                    myWriter.close();
                    System.out.println("Pomyślnie zapisano plik.");
                } catch (IOException ex) {
                    System.out.println("Błąd zapisu.");
                    ex.printStackTrace();
                }
            }
        });

        powrot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame BankomatOkno = new BankomatOkno(osoba,bankomat);
            }
        });
    }
}