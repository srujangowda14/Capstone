import { Component } from '@angular/core';
import { TransactionHistory } from '../model/transaction-history';
import { TransactionService } from '../services/transaction.service';
import { Trade } from '../model/trade';
import { TradeHistory } from '../model/TradeHistory';

@Component({
  selector: 'app-transaction-history',
  templateUrl: './transaction-history.component.html',
  styleUrls: ['./transaction-history.component.css']
})
export class TransactionHistoryComponent {

  constructor(private transactionService : TransactionService){}

  th : TradeHistory[] = [];
  p : any;

  

  ngOnInit(){
    this.transactionService.getTransactions()
    .subscribe(data => {
      console.log(data)
      this.th = data.sort((a: TradeHistory, b: TradeHistory) => {
        const dateA = new Date(a.timestamp).getTime();
        const dateB = new Date(b.timestamp).getTime();
      
        // Sort in ascending order, to reverse the order, swap `dateA` and `dateB`.
        return dateB - dateA;
    });
    
  })
}
}


