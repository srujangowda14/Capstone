import { Component } from '@angular/core';
import { TradeService } from '../services/trade.service';
import { ActivatedRoute } from '@angular/router';
import { Guid } from 'guid-typescript'
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { SessionHandlerService } from '../services/session-handler.service';
import { UserDetailsService } from '../services/user-details.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-buy-page',
  templateUrl: './buy-page.component.html',
  styleUrls: ['./buy-page.component.css']
})
export class BuyPageComponent {

  constructor(private tradeService: TradeService, private route: ActivatedRoute, private formBuilder: FormBuilder, private sessionHandlerService: SessionHandlerService, private userDetailsService: UserDetailsService){}
  generatedGuid: string = ''

  currentInstrument: any = {}
  buyForm: FormGroup = new FormGroup({})
  message: string = ''
  executionPrice: number = 0
  cashValue: number = 0
  success: string = ''
  currentCash: number = 0

  ngOnInit(){
    
    
    this.buyForm = this.formBuilder.group({
      quantity: new FormControl('', Validators.required),
      targetPrice: ['', Validators.required]
      });

      this.loadCurrentInstrument()
      console.log(this.currentInstrument)
      console.log(Guid.create())
      console.log(this.sessionHandlerService.getClientId())

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
          this.buyForm.setValue({
            
            quantity: 0,
            targetPrice: this.currentInstrument.askPrice
    
          })

          
        }
      }
      
    })
  }

  buy(){
    //this.generatedGuid = (Guid.create())
   
    var currEmail = this.sessionHandlerService.getEmail()
    this.userDetailsService.getCash(this.sessionHandlerService.getClientId()).subscribe((cash) => {
    
    if(this.buyForm.get('quantity')?.value < this.currentInstrument.instrument.minQuantity || this.buyForm.get('quantity')?.value > this.currentInstrument.instrument.maxQuantity){
      this.message = 'You should enter quantity within max and min quantity'
      return
    }
        
      if(this.buyForm.get('quantity')?.value < 0){
        this.message = "Invalid quantity"
        return
      }

      var currClientId = this.sessionHandlerService.getClientId()
      console.log(currClientId)
      var token = this.sessionHandlerService.getToken()
      var currEmail = this.sessionHandlerService.getEmail()
      this.tradeService.executeBuy({quantity: this.buyForm.get('quantity')?.value, targetPrice: this.buyForm.get('targetPrice')?.value, clientId: currClientId, token: token, email: currEmail, instrumentId: this.currentInstrument.instrument.instrumentId, direction: 'B', instrumentDescription: this.currentInstrument.instrument.instrumentDescription }).subscribe((data: any) => {
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

      },
      
    
    (error: HttpErrorResponse) => {
      console.log(error.error)

      if(error.error.message === 'Cannot invoke \"java.util.Map.get(Object)\" because \"responseMap\" is null'){
        this.message = "Invalid target price"
      }
      else{
        this.message = error.error.message
      }

      
      
    }
      
      )
        

        })

      }

}
