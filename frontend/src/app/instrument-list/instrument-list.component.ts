import { Component } from '@angular/core';
import { TradeService } from '../services/trade.service';
import { SessionHandlerService } from '../services/session-handler.service';

@Component({
  selector: 'app-instrument-list',
  templateUrl: './instrument-list.component.html',
  styleUrls: ['./instrument-list.component.css']
})
export class InstrumentListComponent {

  constructor(private tradeService: TradeService, private sessionHandlerService: SessionHandlerService){}
  instrumentList:any 
  allActive: boolean = true
  stocksActive: boolean = false
  govtActive: boolean = false

  ngOnInit(){
    this.loadInstruments()
    console.log(this.sessionHandlerService.getToken())
  }

  loadInstruments(){
    this.tradeService.getAllPrices().subscribe((data) => {
      console.log(data)
      this.instrumentList = data
      this.stocksActive =false
      this.allActive = true
      this.govtActive = false
    })


  }


  loadStocks(){
    this.tradeService.getStocks().subscribe((data) => {
      console.log(data)
      this.instrumentList = data
      this.stocksActive = true
      this.allActive = false
      this.govtActive = false
      
    })
  }

  loadGovt(){
    this.tradeService.getGovt().subscribe((data) => {
      console.log(data)
      this.instrumentList = data
      this.stocksActive = false
      this.allActive = false
      this.govtActive = true
      
    })
  }
}
