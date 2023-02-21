public class Banknot200 extends Banknoty {
    int ilosc;

    @Override
    void ilosc() {
        this.ilosc = 250;
    }

    @Override
    int wartosc() {
        return this.ilosc*200;
    }

    @Override
    void zmniejszIlosc(int oIle) {
        ilosc -= oIle;
    }

    @Override
    void zwiekszIlosc(int oIle) {
        ilosc += oIle;
    }

    @Override
    int getIlosc() {
        return ilosc;
    }

}