import javax.swing.*;

public class Konto {

        private String login;
        private int pin;
        private String imie;
        private String nazwisko;
        private int nrtel;
        protected float stanKonta;

        public Konto(String LOGIN, int PIN, String IMIE, String NAZWISKO, int NUMERTEL, float SALDO){
                this.login = LOGIN;
                this.pin = PIN;
                this.imie = IMIE;
                this.nazwisko = NAZWISKO;
                this.nrtel = NUMERTEL;
                this.stanKonta = SALDO;
        }

        void Wyplac(int kwota){
                if(this.stanKonta-kwota >= 0 ){
                        if(kwota >= 20){
                                this.stanKonta -= kwota;
                                round();
                                System.out.println(this.stanKonta);
                        }else {
                                JOptionPane.showMessageDialog(null,"Bankomat nie posiada tak małych nominałów.","Informacja",JOptionPane.INFORMATION_MESSAGE);
                        }


                } else {
                        JOptionPane.showMessageDialog(null,"Brak wystarczających środków na końcie.","Informacja",JOptionPane.INFORMATION_MESSAGE);
                }
        }

        void Wplac(int kwota){
                if(kwota >= 20){
                        this.stanKonta += kwota;
                        round();
                } else {
                        JOptionPane.showMessageDialog(null,"Bankomat nie przyjmuje tak małych nominałów.","Informacja",JOptionPane.INFORMATION_MESSAGE);
                }
        }

        void DoladowanieTelefonu(int kwota){
                if(this.stanKonta-kwota >= 0){
                        this.stanKonta -= kwota;
                        round();
                        System.out.println("Stan konta po doladowaniu:"+this.stanKonta);
                        JOptionPane.showMessageDialog(null,"Pomyślnie doładowano telefon o numerze: "+this.nrtel+"\nKwotą o wartości: "+kwota+"zł.\nStan twojego konta bankowego to:"+getStanKonta()+".\nDziękujemy za skorzystanie z usługi.","Informacja",JOptionPane.INFORMATION_MESSAGE);
                } else {
                        JOptionPane.showMessageDialog(null,"Brak wystarczających środków na końcie.","Informacja",JOptionPane.ERROR_MESSAGE);
                }
        }

        void round(){
                this.stanKonta = (float) (Math.round(this.stanKonta*100.0) / 100.0);
        }

        public String getImie() {
                return imie;
        }

        public String getNazwisko() {
                return nazwisko;
        }

        public int getNrtel() {
                return nrtel;
        }

        public float getStanKonta() {
                return stanKonta;
        }

        public int getPin() {
                return pin;
        }

        public String getLogin() {
                return login;
        }

        @Override
        public String toString() {
                return "Konto{" +
                        "login='" + login + '\'' +
                        ", pin=" + pin +
                        ", imie='" + imie + '\'' +
                        ", nazwisko='" + nazwisko + '\'' +
                        ", nrtel=" + nrtel +
                        ", stanKonta=" + stanKonta +
                        '}';
        }
}
