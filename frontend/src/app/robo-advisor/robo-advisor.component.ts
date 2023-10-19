import { Component } from '@angular/core';
import { TransactionHistory } from '../model/transaction-history';
import { TransactionService } from '../services/transaction.service';
import { PreferenceService } from '../services/preference.service';
import { InvestmentPreference } from '../model/investment-preference';
import { RoboadvisorService } from '../services/roboadvisor.service';
import { InstrumentSuggestions } from '../model/instrument-suggestions';
import { Router } from '@angular/router';

@Component({
  selector: 'app-robo-advisor',
  templateUrl: './robo-advisor.component.html',
  styleUrls: ['./robo-advisor.component.css']
})
export class RoboAdvisorComponent {
navigateToBuy(id : string) {
  this.router.navigate(['/buy',id])
}

navigateToSell(id : string) {
  this.router.navigate(['/sell',id])
}

  transaction_buy: InstrumentSuggestions[] = [];

  transaction_sell: InstrumentSuggestions[] = [];


  constructor(private roboadvisorService: RoboadvisorService, private router: Router) {

  }

  ngOnInit() {


    this.roboadvisorService.getStocksToBuy()
      .subscribe(data => this.transaction_buy = data);
    
    this.roboadvisorService.getStocksToSell().
    subscribe(data => this.transaction_sell = data);
  }


  suggestStocksToSell(transactionHistory: TransactionHistory[], risk_tolerance : string): TransactionHistory[] {
    // Group transactions by symbol and sort them by date in descending order
    const groupedTransactions: { [symbol: string]: TransactionHistory[] } = {};
    for (const transaction of transactionHistory) {
      if (!groupedTransactions[transaction.symbol]) {
        groupedTransactions[transaction.symbol] = [];
      }
      groupedTransactions[transaction.symbol].push(transaction);
    }
    // console.log(groupedTransactions);
  
    // Sort transactions within each group by date in descending order
    for (const symbol in groupedTransactions) {
      groupedTransactions[symbol].sort((a, b) => (a.date > b.date ? -1 : 1));
    }

    console.log(groupedTransactions);
  
    // Find the top 5 stocks to sell
    const top5StocksToSell: TransactionHistory[] = [];
    for (const symbol in groupedTransactions) {
      const transactions = groupedTransactions[symbol];
      if (transactions.length >= 2) {
        console.log("hello");
        const latestPrice = transactions[0].price;
        let formerPrice = 0;

        if(risk_tolerance=="CONSERVATIVE"){
          formerPrice = transactions[1].price;
        }
        else{
          formerPrice = transactions[(transactions.length-1)].price;
        }
        console.log(risk_tolerance)
        if (latestPrice > formerPrice) {
          top5StocksToSell.push(transactions[0]); // Add the latest transaction to the list
        }
      }
    }
    console.log(top5StocksToSell);
  
    // Sort the top 5 stocks to sell by date in descending order
    top5StocksToSell.sort((a, b) => (a.date > b.date ? -1 : 1));

    // console.log(top5StocksToSell);
  
    // Return the top 5 stocks to sell
    return top5StocksToSell.slice(0, 5);
  }

  suggestStocksToBuy(transactionHistory: TransactionHistory[], risk_tolerance: string): TransactionHistory[] {
    // Group transactions by symbol and sort them by date in descending order
    const groupedTransactions: { [symbol: string]: TransactionHistory[] } = {};
    for (const transaction of transactionHistory) {
      if (!groupedTransactions[transaction.symbol]) {
        groupedTransactions[transaction.symbol] = [];
      }
      groupedTransactions[transaction.symbol].push(transaction);
    }
    // console.log(groupedTransactions);
  
    // Sort transactions within each group by date in descending order
    for (const symbol in groupedTransactions) {
      groupedTransactions[symbol].sort((a, b) => (a.date > b.date ? -1 : 1));
    }

    console.log(groupedTransactions);
  
    // Find the top 5 stocks to sell
    const top5StocksToSell: TransactionHistory[] = [];
    for (const symbol in groupedTransactions) {
      const transactions = groupedTransactions[symbol];
      if (transactions.length >= 2) {
        console.log("hello");
        const latestPrice = transactions[0].price;
        const formerPrice = transactions[1].price;
        console.log(latestPrice);
        console.log(formerPrice)
        if (latestPrice < formerPrice) {
          top5StocksToSell.push(transactions[0]); // Add the latest transaction to the list
        }
      }
    }
    console.log(top5StocksToSell);
  
    // Sort the top 5 stocks to sell by date in descending order
    top5StocksToSell.sort((a, b) => (a.date > b.date ? -1 : 1));

    // console.log(top5StocksToSell);
  
    // Return the top 5 stocks to sell
    return top5StocksToSell.slice(0, 5);
  }
}
