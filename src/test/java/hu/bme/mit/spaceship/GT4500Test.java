package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

/*
 * teszttervezés:
 * 1. szingli tüzelésnél tudunk lőni
 * 2. szingli tüzelésnél tudunk kétszer lőni
 * 3. all tüzelésnél tudunk lőni háromszor
 * 4. all tüzelésnél egyszeri lövésnél is mindkettő store tüzelésre került
 * 5. szingli tüzelésnél nem tudunk lőni, ha nem tud tüzelni egyik izé sem
 */

public class GT4500Test {

  private GT4500 ship;
  TorpedoStore pr,sc;

  @BeforeEach
  public void init(){
    pr = mock(TorpedoStore.class);
    sc = mock(TorpedoStore.class);
    this.ship = new GT4500(pr,sc);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    when(pr.fire(anyInt())).thenReturn(true);
    when(sc.fire(anyInt())).thenReturn(true);
    // Arrange

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Single2_Success(){
    when(pr.fire(anyInt())).thenReturn(true);
    when(sc.fire(anyInt())).thenReturn(true);
    // Arrange

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Success(){
    when(pr.fire(anyInt())).thenReturn(true);
    when(sc.fire(anyInt())).thenReturn(true);
    // Arrange

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    assertEquals(true, result);
    result = ship.fireTorpedo(FiringMode.ALL);
    assertEquals(true, result);
    result = ship.fireTorpedo(FiringMode.ALL);
    assertEquals(true, result);

    // Assert
  }
  @Test
  public void fireTorpedo_All_bothfired_Success(){
    when(pr.fire(anyInt())).thenReturn(true);
    when(sc.fire(anyInt())).thenReturn(true);
    // Arrange

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    assertEquals(true, result);
    verify(pr).fire(1);
    verify(sc).fire(1);

    // Assert
  }
  @Test
  public void fireTorpedo_Single_fAIL(){
    when(pr.fire(anyInt())).thenReturn(false);
    when(sc.fire(anyInt())).thenReturn(false);
    // Arrange

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
  }

}
