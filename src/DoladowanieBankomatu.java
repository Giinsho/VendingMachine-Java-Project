public interface DoladowanieBankomatu {
    default void doladuj(Bankomat aktualnyStanBankomatu){

        aktualnyStanBankomatu.b20.zwiekszIlosc(500);
        aktualnyStanBankomatu.b50.zwiekszIlosc(400);
        aktualnyStanBankomatu.b100.zwiekszIlosc(250);
        aktualnyStanBankomatu.b200.zwiekszIlosc(125);
        aktualnyStanBankomatu.b500.zwiekszIlosc(50);
        aktualnyStanBankomatu.setStanBankomatu();
        aktualnyStanBankomatu.setBanknotyIloscList();

    }
}
