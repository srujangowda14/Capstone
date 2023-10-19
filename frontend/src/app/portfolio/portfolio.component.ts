import { Component, OnInit } from '@angular/core';
import { PortfolioService } from '../services/portfolio.service';
import { Instrument } from '../model/instrument';
import { Portfolio } from '../model/portfolio';
import { TradeService } from '../services/trade.service';
import { LivePricing } from '../model/live-pricing';
import { SessionHandlerService } from '../services/session-handler.service';

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit{
    constructor(private portfolioService: PortfolioService,
      private tradeService: TradeService, private sessionHandlerService: SessionHandlerService){}
    instruments: Instrument[]=[];
    errorMessage: string='';
    portfolio: Portfolio[]=[];
    prices: LivePricing[]=[];


    getMyPortfolio():void{
      this.portfolioService.getCurrentPortfolio(this.sessionHandlerService.getClientId()).subscribe({
            next: (data) =>{
            console.log(data);
            this.portfolio= data;
            console.log(this.portfolio);
            },
            error: (err)=> this.errorMessage=err
      });
    }

    ngOnInit(): void {
      this.getMyPortfolio();
      this.getLivePrice();
    }

    getLivePrice():void{
      this.tradeService.getAllPrices().subscribe({
        next: (data)=>{
          this.prices=data;

        },
        error : (err) => console.log(err)
      })
    }

    getPriceByInstrumentId(instrumentId: string):number{
        let instrument= this.prices.find(item => item.instrument.instrumentId === instrumentId);

        if(instrument){
          return instrument.askPrice;
        }

        return 0;
    }
  
}
