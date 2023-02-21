public class Banknot20 extends Banknoty {
    int ilosc;

    @Override
    void ilosc() {
        this.ilosc = 1000;
    }

    @Override
    int wartosc() {
        return this.ilosc*20;
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
