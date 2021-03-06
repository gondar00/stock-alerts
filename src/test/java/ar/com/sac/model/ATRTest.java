package ar.com.sac.model;

import ar.com.sac.model.formulas.AverageTrueRange;
import org.junit.Assert;
import org.junit.Test;

public class ATRTest {
   @Test
   public void ATR14test() {
      AverageTrueRange atr = new AverageTrueRange( 14, UtilTest.getQuotes() );
      Assert.assertEquals( "2.947039317273361991311730889719910919666290283203125", atr.calculate().toString());
   }
   
}
