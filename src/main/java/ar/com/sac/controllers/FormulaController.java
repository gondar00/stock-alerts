package ar.com.sac.controllers;

import ar.com.sac.services.FormulaService;
import java.io.IOException;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@RestController
@RequestMapping("/formulas")
public class FormulaController {
   @Autowired
   private FormulaService formulaService;
   
   @RequestMapping(value= "/average", method = RequestMethod.GET)
   public ResponseEntity<BigDecimal> average(@RequestParam(value = "period", required=false) Integer period, @RequestParam("symbol") String symbol ) throws IOException {
      if(period == null){
         period = 20;
      }
      return new ResponseEntity<BigDecimal>( formulaService.getAverage( period, symbol ), HttpStatus.OK );
   }
   
   @RequestMapping(value= "/variance", method = RequestMethod.GET)
   public ResponseEntity<BigDecimal> variance(@RequestParam(value = "period", required=false) Integer period, @RequestParam("symbol") String symbol ) throws IOException {
      if(period == null){
         period = 20;
      }
      return new ResponseEntity<BigDecimal>( formulaService.getVariance( period, symbol ), HttpStatus.OK );
   }
   
   @RequestMapping(value= "/sd", method = RequestMethod.GET)
   public ResponseEntity<BigDecimal> standardDeviation(@RequestParam(value = "period", required=false) Integer period, @RequestParam("symbol") String symbol ) throws IOException {
      if(period == null){
         period = 20;
      }
      return new ResponseEntity<BigDecimal>( formulaService.getStandardDeviation( period, symbol ), HttpStatus.OK );
   }
   
   @RequestMapping(value= "/sma", method = RequestMethod.GET)
   public ResponseEntity<BigDecimal> sma(@RequestParam("period") int period, @RequestParam("symbol") String symbol ) throws IOException {
      return new ResponseEntity<BigDecimal>( formulaService.getSMA( period, symbol ), HttpStatus.OK );
   }
   
   @RequestMapping(value= "/ema", method = RequestMethod.GET)
   public ResponseEntity<BigDecimal> ema(@RequestParam("period") int period, @RequestParam("symbol") String symbol ) throws IOException {
      return new ResponseEntity<BigDecimal>( formulaService.getEMA( period, symbol ), HttpStatus.OK );
   }
   
   @RequestMapping(value= "/rsi", method = RequestMethod.GET)
   public ResponseEntity<BigDecimal> rsi(@RequestParam(value = "period", required=false) Integer period, @RequestParam("symbol") String symbol ) throws IOException {
      if(period == null){
         period = 14;
      }
      return new ResponseEntity<BigDecimal>( formulaService.getRSI( period, symbol ), HttpStatus.OK );
   }
   
   @RequestMapping(value= "/macd", method = RequestMethod.GET)
   public ResponseEntity<BigDecimal> macd(@RequestParam(value = "fastPeriod", required=false) Integer fastPeriod, 
                                          @RequestParam(value = "slowPeriod", required=false) Integer slowPeriod,
                                          @RequestParam("symbol") String symbol ) throws IOException {
      if(fastPeriod == null){
         fastPeriod = 12;
      }
      if(slowPeriod == null){
         slowPeriod = 26;
      }
      return new ResponseEntity<BigDecimal>( formulaService.getMACD( fastPeriod, slowPeriod, symbol ), HttpStatus.OK );
   }
   
   @RequestMapping(value= "/macdsignalline", method = RequestMethod.GET)
   public ResponseEntity<BigDecimal> macdSignalLine(@RequestParam(value = "fastPeriod", required=false) Integer fastPeriod, 
                                          @RequestParam(value = "slowPeriod", required=false) Integer slowPeriod,
                                          @RequestParam(value = "signalPeriod", required=false) Integer signalPeriod,
                                          @RequestParam("symbol") String symbol ) throws IOException {
      if(fastPeriod == null){
         fastPeriod = 12;
      }
      if(slowPeriod == null){
         slowPeriod = 26;
      }
      if(signalPeriod == null){
         signalPeriod = 9;
      }
      
      return new ResponseEntity<BigDecimal>( formulaService.getMACDSignalLine( fastPeriod, slowPeriod, signalPeriod, symbol ), HttpStatus.OK );
   }
   
   @RequestMapping(value= "/macdhistogram", method = RequestMethod.GET)
   public ResponseEntity<BigDecimal> macdHistogram(@RequestParam(value = "fastPeriod", required=false) Integer fastPeriod, 
                                          @RequestParam(value = "slowPeriod", required=false) Integer slowPeriod,
                                          @RequestParam(value = "signalPeriod", required=false) Integer signalPeriod,
                                          @RequestParam("symbol") String symbol ) throws IOException {
      if(fastPeriod == null){
         fastPeriod = 12;
      }
      if(slowPeriod == null){
         slowPeriod = 26;
      }
      if(signalPeriod == null){
         signalPeriod = 9;
      }
      
      return new ResponseEntity<BigDecimal>( formulaService.getMACDHistogram( fastPeriod, slowPeriod, signalPeriod, symbol ), HttpStatus.OK );
   }
   
   @RequestMapping(value= "/price", method = RequestMethod.GET)
   public ResponseEntity<BigDecimal> price( @RequestParam("symbol") String symbol ) throws IOException {
      return new ResponseEntity<BigDecimal>( formulaService.getPrice( symbol ), HttpStatus.OK );
   }
   
   @RequestMapping(value= "/volume", method = RequestMethod.GET)
   public ResponseEntity<BigDecimal> volume( @RequestParam("symbol") String symbol ) throws IOException {
      return new ResponseEntity<BigDecimal>( formulaService.getVolume( symbol ), HttpStatus.OK );
   }
   
   @RequestMapping(value= "/stochasticd", method = RequestMethod.GET)
   public ResponseEntity<BigDecimal> stochasticD(@RequestParam(value = "length", required=false) Integer length, 
                                          @RequestParam(value = "period", required=false) Integer period,
                                          @RequestParam("symbol") String symbol ) throws IOException {
      if(length == null){
         length = 14;
      }
      if(period == null){
         period = 3;
      }
      
      return new ResponseEntity<BigDecimal>( formulaService.getStochasticOscillatorD( length, period, symbol ), HttpStatus.OK );
   }
   
   @RequestMapping(value= "/stochastick", method = RequestMethod.GET)
   public ResponseEntity<BigDecimal> stochasticD(@RequestParam(value = "length", required=false) Integer length, 
                                          @RequestParam("symbol") String symbol ) throws IOException {
      if(length == null){
         length = 14;
      }
      
      return new ResponseEntity<BigDecimal>( formulaService.getStochasticOscillatorK( length, symbol ), HttpStatus.OK );
   }
   
   @RequestMapping(value= "/bblower", method = RequestMethod.GET)
   public ResponseEntity<BigDecimal> bbLower(@RequestParam(value = "period", required=false) Integer period, 
                                          @RequestParam(value = "k", required=false) Integer k,
                                          @RequestParam("symbol") String symbol ) throws IOException {
      if(k == null){
         k = 2;
      }
      if(period == null){
         period = 20;
      }
      
      return new ResponseEntity<BigDecimal>( formulaService.getBollingerBandLower( period, k, symbol ), HttpStatus.OK );
   }
   
   @RequestMapping(value= "/bbupper", method = RequestMethod.GET)
   public ResponseEntity<BigDecimal> bbUpper(@RequestParam(value = "period", required=false) Integer period, 
                                          @RequestParam(value = "k", required=false) Integer k,
                                          @RequestParam("symbol") String symbol ) throws IOException {
      if(k == null){
         k = 2;
      }
      if(period == null){
         period = 20;
      }
      
      return new ResponseEntity<BigDecimal>( formulaService.getBollingerBandUpper( period, k, symbol ), HttpStatus.OK );
   }
   
   @RequestMapping(value= "/atr", method = RequestMethod.GET)
   public ResponseEntity<BigDecimal> atr(@RequestParam(value = "period", required=false) Integer period, 
                                          @RequestParam("symbol") String symbol ) throws IOException {
      if(period == null){
         period = 14;
      }
      
      return new ResponseEntity<BigDecimal>( formulaService.getAverageTrueRange( period, symbol ), HttpStatus.OK );
   }
   
   @RequestMapping(value= "/atrp", method = RequestMethod.GET)
   public ResponseEntity<BigDecimal> atrp(@RequestParam(value = "period", required=false) Integer period, 
                                          @RequestParam("symbol") String symbol ) throws IOException {
      if(period == null){
         period = 14;
      }
      
      return new ResponseEntity<BigDecimal>( formulaService.getAverageTrueRangePercentage( period, symbol ), HttpStatus.OK );
   }
}
