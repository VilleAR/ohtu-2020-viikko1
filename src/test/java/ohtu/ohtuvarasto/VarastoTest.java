package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto2;
    Varasto varasto3;
    Varasto varasto4;
    Varasto varasto5;

    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto2 = new Varasto(-1);
        varasto3 = new Varasto(10, 1);
        varasto4 = new Varasto(-1, -5);
        varasto5 = new Varasto(15, 20);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void huonoLisays() {
        varasto.lisaaVarastoon(-3);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test 
    public void tayteen() {
        varasto.lisaaVarastoon(11);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test 
    public void liianIsoSaldo() {
        assertEquals(15, varasto5.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void uusiHuonoTilavuus() {
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    @Test 
    public void uusiHuonoSaldo() {
        assertEquals(0, varasto4.getTilavuus(), vertailuTarkkuus);
    }
    @Test
    public void uudellaVarastollaOikeaSaldo() {
        assertEquals(1, varasto3.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
    @Test
    public void huonoOtto() {
        double saatuMaara = varasto3.otaVarastosta(2);
        double sa2 = varasto3.otaVarastosta(-1);
        assertEquals(1, saatuMaara, vertailuTarkkuus);
        assertEquals(0.0, sa2, vertailuTarkkuus);

    }
    @Test
    public void kaikkiMenee() {
        double saatu = varasto3.otaVarastosta(9999);
        assertEquals(1, saatu, vertailuTarkkuus);
        assertEquals(0.0, varasto3.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    @Test
    public void merkkiEsitys() {

        String tavoite = "saldo = 1.0, vielä tilaa 9.0";
        assertEquals(tavoite, varasto3.toString());
    }
    

}