import { Injectable } from '@angular/core';
import { TransactionHistory } from '../model/transaction-history';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { SessionHandlerService } from '../services/session-handler.service';
@Injectable({
  providedIn: 'root'
})
export class RoboadvisorService {

  constructor(private http: HttpClient, private sessionHandlerService: SessionHandlerService) { }


  clientId : string = '/'+this.sessionHandlerService.getClientId();

serverUrl: string = 'https://a720855.roifmr.com/roboadvisor/analyse/';
buyUrl : string ='buy';
sellUrl : string = 'sell';
  getStocksToBuy(): Observable<any>{
    // return this.http.get('/assets/transactionDummy.json');
    return this.http.get(this.serverUrl+this.buyUrl+this.clientId);
  }
  getStocksToSell(): Observable<any>{
    // return this.http.get('/assets/transactionDummy.json');
    return this.http.get(this.serverUrl+this.sellUrl+this.clientId);
  }
}
