import { Component } from '@angular/core';
import { PortfolioService } from '../services/portfolio.service';
import { SessionHandlerService } from '../services/session-handler.service';

@Component({
  selector: 'app-portfolio-list',
  templateUrl: './portfolio-list.component.html',
  styleUrls: ['./portfolio-list.component.css']
})
export class PortfolioListComponent {

  

  constructor(private portfolioService: PortfolioService, private sessionHandlerService: SessionHandlerService){}
  portfolio: any
  errorMessage: string = ''


  ngOnInit(){
    this.getPortfolio()
  }

  getPortfolio(){



    this.portfolioService.getCurrentPortfolio(this.sessionHandlerService.getClientId()).subscribe({
      next: (data) =>{
        this.portfolio= data;
      console.log(this.portfolio);
      },
      error: (err)=> this.errorMessage=err
    });

  }

}
