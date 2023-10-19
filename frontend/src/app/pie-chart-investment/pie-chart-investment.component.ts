import { Component } from '@angular/core';
import { Chart } from 'chart.js';
import { PortfolioService } from '../services/portfolio.service';
import { SessionHandlerService } from '../services/session-handler.service';

@Component({
  selector: 'app-pie-chart-investment',
  templateUrl: './pie-chart-investment.component.html',
  styleUrls: ['./pie-chart-investment.component.css']
})
export class PieChartInvestmentComponent {

  
  constructor(private portfolioService: PortfolioService, private sessionHandlerService: SessionHandlerService){}
  portfolio: [] = []
  errorMessage: string = ''
  values: any = []
  labels: any = []
  colors: any = []

  getRandomColor() {
    const letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
      color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
  }

 
  

  loadData(){
    this.portfolioService.getCurrentPortfolio(this.sessionHandlerService.getClientId()).subscribe({
      next: (data) =>{
      console.log(data);
      this.portfolio= data;
      console.log(this.portfolio);
       for(var d of data){
        this.values.push(d.totalInvestment)
        this.labels.push(d.instrumentDescription)
        this.colors.push(this.getRandomColor())

       }
       console.log(this.values)
       console.log(this.labels)
       this.createChart();
      },
      error: (err)=> this.errorMessage=err
    });
  }

  createChart(){

    this.chart = new Chart("MyChart2", {
      type: 'pie', 

      data: {
        labels: this.labels,
	       datasets: [{
    label: 'My First Dataset',
    data: this.values,
    backgroundColor: this.colors,
    
    hoverOffset: 4
  }],
      },
      options: {
        aspectRatio:2.5
      }

    });
  }
  public chart: any;

  ngOnInit(): void {
    //Chart.register(Colors);
    this.loadData()
    
  }


}
