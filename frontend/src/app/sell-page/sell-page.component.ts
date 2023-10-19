
import { Component } from '@angular/core';
import { TradeService } from '../services/trade.service';
import { ActivatedRoute } from '@angular/router';
import { Guid } from 'guid-typescript'
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { SessionHandlerService } from '../services/session-handler.service';
import { UserDetailsService } from '../services/user-details.service';
import { PortfolioService } from '../services/portfolio.service';
import { HttpErrorResponse } from '@angular/common/http';


@Component({
  selector: 'app-sell-page',
  templateUrl: './sell-page.component.html',
  styleUrls: ['./sell-page.component.css']
})
export class SellPageComponent {

  constructor(private tradeService: TradeService, private route: ActivatedRoute, private formBuilder: FormBuilder, private sessionHandlerService: SessionHandlerService, private userDetailsService: UserDetailsService, private portfolioService: PortfolioService){}
  generatedGuid: string = ''

  currentInstrument: any = {}
  sellForm: FormGroup = new FormGroup({})
  message: string = ''
  executionPrice: number = 0
  cashValue: number = 0
  success: string = ''
  currentCash: number = 0
  errorMessage: string = ''

  //portfolio parameters
  currentQuantity: number = 0
  currentBoughtPrice: number = 0
  currentInvestment: number = 0

  ngOnInit(){
    
    this.loadCurrentInstrument()
    this.loadCurrentCash()
    this.loadPortfolio()
    console.log(this.currentInstrument)
    console.log(Guid.create())
    console.log(this.sessionHandlerService.getClientId())
    this.sellForm = this.formBuilder.group({
      quantity: new FormControl('', Validators.required),
      targetPrice: ['', Validators.required]
      });
  }



  loadPortfolio(){



    this.portfolioService.getCurrentPortfolio(this.sessionHandlerService.getClientId()).subscribe({
      next: (data) =>{
        var portfolioArray = data
        console.log(portfolioArray)
        for(let p of portfolioArray){
          if(p.instrumentId === this.route.snapshot.params['id']){
            this.currentBoughtPrice = p.boughtPrice
            this.currentInvestment = p.totalInvestment
            this.currentQuantity = p.quantity
          }
        }
      },
      error: (err)=> this.errorMessage=err
    });
    
  }

  loadCurrentCash(){
    this.userDetailsService.getCash(this.sessionHandlerService.getClientId()).subscribe((data) => {
      this.currentCash = data
    })
  }

  isObjectEmpty(obj: any): boolean {
    return Object.keys(obj).length === 0;
  }

  loadCurrentInstrument(){
    this.tradeService.getAllPrices().subscribe((data) =>{
     
      for(let instrument of data){
        if(instrument.instrument.instrumentId === this.route.snapshot.params['id']){
          this.currentInstrument = instrument
          console.log(this.currentInstrument)
          this.sellForm.setValue({
            
            quantity: 0,
            targetPrice: this.currentInstrument.bidPrice
    
          })

          
        }
      }
      
    })
  }

  sell(){
    this.loadCurrentCash()
    this.loadPortfolio()
    
    var currEmail = this.sessionHandlerService.getEmail()

    
    

      if(this.sellForm.get('quantity')?.value < 1){
        this.message = "Invalid quantity"
        return
      }
      var currClientId = this.sessionHandlerService.getClientId()
      console.log(currClientId)
      var token = this.sessionHandlerService.getToken()
      this.tradeService.executeSell({quantity: this.sellForm.get('quantity')?.value, targetPrice: this.sellForm.get('targetPrice')?.value, clientId: currClientId, token: token, email: currEmail, instrumentId: this.currentInstrument.instrument.instrumentId, direction: 'S' }).subscribe((data: any) => {
        console.log(data)
        if(data === null){
          this.message = 'Set appropriate Target Price'
          this.success = ''
          return
        }
        

          
          this.message ='Trade executed successfully'
          this.cashValue = data.trade.cashValue
          this.executionPrice = data.trade.executionPrice
          this.success = 'success'

          var updatedCash = this.currentCash + this.cashValue
          this.currentQuantity -= data.trade.quantity
          this.currentInvestment -= this.cashValue 
          console.log(this.currentCash)
          console.log(this.cashValue)
          console.log(updatedCash)

       
      },
      (error: HttpErrorResponse) => {
        console.log(error.error)
  
        if(error.error.message === 'Cannot invoke \"java.util.Map.get(Object)\" because \"responseMap\" is null'){
          this.message = "Invalid target price"
        }
        else{
          this.message = error.error.message
        }
  
        
        
      })
    
    

  }

}
