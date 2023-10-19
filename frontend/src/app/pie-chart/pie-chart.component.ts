import { Component } from '@angular/core';
import Chart from 'chart.js/auto'
import { PortfolioService } from '../services/portfolio.service';
import { SessionHandlerService } from '../services/session-handler.service';
import { Colors } from 'chart.js';

@Component({
  selector: 'app-pie-chart',
  templateUrl: './pie-chart.component.html',
  styleUrls: ['./pie-chart.component.css']
})
export class PieChartComponent {

  
  
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
        this.values.push(d.quantity)
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

    this.chart = new Chart("MyChart", {
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
