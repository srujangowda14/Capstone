import { Component } from '@angular/core';
import { Portfolio } from 'src/app/model/portfolio';
import { Trade } from 'src/app/model/trade';
import { PortfolioService } from 'src/app/services/portfolio.service';
import { SessionHandlerService } from 'src/app/services/session-handler.service';
import { TradeService } from 'src/app/services/trade.service';

@Component({
  selector: 'app-p-and-lreport',
  templateUrl: './p-and-lreport.component.html',
  styleUrls: ['./p-and-lreport.component.css']
})
export class PAndLReportComponent {
  constructor(private sessionHandler: SessionHandlerService,
    private tradeService: TradeService,
    private portfolioService: PortfolioService){}

  userEmail: string='';
  portfolio:Portfolio[]=[];

  getUserEmail(){
    this.userEmail =this.sessionHandler.getEmail();
  }

  sellData: Trade[]=[];
  filteredSellData: Trade[] =[];

  getTradeData(){
      this.tradeService.getJsonData().subscribe({
        next : (data) =>{
          this.sellData= data;
          console.log(data);
          console.log(this.sellData);
          this.filterSellDataForUser();
        }
      });
  }

  getMyPortfolio(){
    return this.portfolioService.getMyPortfolio().subscribe({
        next: (data) =>{
        console.log(data);
        this.portfolio= data;
        console.log(this.portfolio);
        },
  });
    
    
  }

  filterSellDataForUser(){
    this.filteredSellData = this.sellData.filter(item => item.order.email === this.userEmail && item.direction === 'S');
    console.log(this.sellData);
    console.log(this.userEmail);
    console.log(this.filteredSellData);
  }

  getTotalInvestment(clientId: string, instrumentId: string):number{
      // while(!this.portfolio.length){console.log('srujan')}
      // if(this.portfolio.length){
        let myFilteredData:Portfolio = this.portfolio.find(item => item.instrumentId === instrumentId && item.clientId ===Number(clientId))!;
        let totalInvestment: number= myFilteredData.totalInvestment;
        return totalInvestment;
      // }
      return 0;
     

  }

  ngOnInit(): void {
    this.getUserEmail();
    this.getTradeData();
    this.filterSellDataForUser();
    this.getMyPortfolio();
  }
}
