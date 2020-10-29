package ohtu.ohtuvarasto;

import static org.junit.Assert.*;
import static org.junit.Assert.*;

import org.junit.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VarastoTest {
  Varasto varasto;
  double vertailuTarkkuus = 0.0001;

  @Before
  public void setUp() {
    varasto = new Varasto(10);
  }

  @Test
  public void konstruktoriLuoTyhjanVaraston() {
    assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
  }

  @Test
  public void uudellaVarastollaOikeaTilavuus() {
    assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
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
  public void ottaminenLisääTilaa() {
    varasto.lisaaVarastoon(8);

    varasto.otaVarastosta(2);

    // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
    assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
  }

  @Test
  public void toinenKonstruktoriLuoTyhjanVarastonVirheellisillaParametreilla() {
    varasto = new Varasto(-1, -1);
    assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
  }

  @Test
  public void toinenKonstruktoriLuoUudenVarastonOikeillaArvoilla() {
    varasto = new Varasto(1, 1);
    assertEquals(1, varasto.getSaldo(), vertailuTarkkuus);
    assertEquals(1, varasto.getTilavuus(), vertailuTarkkuus);
  }

  @Test
  public void ylimaarainenAlkuSaldoMeneeHukkaan() {
    varasto = new Varasto(1, 2);
    assertEquals(1, varasto.getSaldo(), vertailuTarkkuus);
    assertEquals(1, varasto.getTilavuus(), vertailuTarkkuus);
  }

  @Test
  public void virheellinenTilavuusNollataan() {
    varasto = new Varasto(-1);
    assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
  }

  @Test
  public void virheellistaMaaraaEiLisataVarastoon() {
    varasto.lisaaVarastoon(-1);
    assertEquals(10.0, varasto.getTilavuus(), vertailuTarkkuus);
  }

  @Test
  public void ylimaarainenLisattySaldoMeneeHukkaan() {
    varasto.lisaaVarastoon(11.0);
    assertEquals(10.0, varasto.getSaldo(), vertailuTarkkuus);
  }

  @Test
  public void virheellinenOttoEiVahennaSaldoa() {
    varasto.lisaaVarastoon(5.0);
    varasto.otaVarastosta(-1.0);
    assertEquals(5.0, varasto.getSaldo(), vertailuTarkkuus);
  }

  @Test
  public void saldonYlittavaOttoTyhjentaaVaraston() {
    varasto.lisaaVarastoon(5.0)
    varasto.otaVarastosta(6.0);
    assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    assertEquals(
      "saldo = " + 0.0 + ", vielä tilaa " + 10.0,
      varasto.toString()
    );
  }
}
