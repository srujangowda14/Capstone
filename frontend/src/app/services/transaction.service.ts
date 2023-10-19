import { Injectable } from '@angular/core';
import { TransactionHistory } from '../model/transaction-history';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { SessionHandlerService } from '../services/session-handler.service';


@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  constructor(private http: HttpClient, private sessionHandlerService: SessionHandlerService) { }
  
  th : TransactionHistory[] = [{symbol :"Goog",name :"Google",price:100,quantity: 15,ti:12345,type:"buy",date:new Date()},
  {symbol :"AMZ",name :"Amazon",price:200,quantity: 20,ti:54321,type:"sell",date:new Date()}
];

clientId : string = this.sessionHandlerService.getClientId()

serverUrl: string = 'https://a720855.roifmr.com/history/'+this.clientId;

  getTransactions(): Observable<any>{
    // return this.http.get('/assets/transactionDummy.json');
    return this.http.get(this.serverUrl);
  }
  getTransactionHistory(): Observable<any>{
    return this.http.get('/assets/trade.json');
  }
}
