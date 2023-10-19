import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, switchMap, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TradeService {

  constructor(private http: HttpClient) { }

  url: string = 'https://a720855.roifmr.com/'
  serverUrl: string = 'https://a720855.roifmr.com/'
  tradeUrl: string = '../../assets/trade.json'
  portfolioUrl: string = '../../assets/portfolio.json'

  getJsonData() {
    return this.http.get<any>(this.tradeUrl);
  }

  getPortfolioData(){
    return this.http.get<any>(this.portfolioUrl)
  }



  getCurrentPortfolio(clientId: number){
    return this.getPortfolioData().pipe(
      map(
        (data)=> {

          console.log('all portfolio')
          console.log(data)
          var currData = []
          for(let d of data){
            if(d.clientId == clientId){
              console.log('inside')
              currData.push(d)
            }
          }
          return currData
        }
      )
    )
  }

  getClientData(){
    return this.http.get<any>('../../assets/database.json')
  }

  handleError(response: HttpErrorResponse) {
    if (response.error instanceof ProgressEvent) {
    console.error('A client-side or network error occurred; ' +
    `${response.message} ${response.status} ${response.statusText}`);
    } else {
    console.log(`Backend returned code ${response.status}, ` +
    `body was: ${JSON.stringify(response.error)}`);
     
    }
    return throwError(
    () => 'Unable to contact service; please try again later.');
   }

  getAllPrices(){
    return this.http.get<any>(this.url+'fmts/trades/prices').pipe(
      catchError(this.handleError)
    )
  }

  getAllInstruments(){
    return this.http.get<any>(this.url+'fmts/trades/instruments').pipe(
      catchError(this.handleError)
    )
  }

  getStocks(){
    return this.http.get<any>(this.url+'fmts/trades/prices/STOCK').pipe(
      catchError(this.handleError)
    )
  }

  getGovt(){
    return this.http.get<any>(this.url+'fmts/trades/prices/GOVT').pipe(
      catchError(this.handleError)
    )
  }





  addToPortfolio(trade: any){
    return this.getPortfolioData().pipe(
      map(data => {
        console.log(data)
        console.log(trade)
        var portfolio = data
        var currentStock
        var found = 0

        for(let stock of portfolio){
          console.log(stock)
          if(stock.instrumentID === trade.instrumentId && stock.clientId == trade.clientId){
            console.log('found')
            stock.quantity += trade.quantity
            stock.boughtPrice = trade.executionPrice
            stock.totalInvestment += trade.cashValue
            
            found = 1
            
          }
        }

        if(found === 0){
         
          console.log('portfolio')
          console.log(portfolio)
          portfolio.push({
            instrumentID: trade.instrumentId,
            instrumentDescription: trade.instrumentDescription,
            boughtPrice: trade.executionPrice,
            totalInvestment: trade.cashValue,
            quantity: trade.quantity,
            clientId : Number(trade.clientId)

          })
              
            console.log('updated portfolio after adding')
            console.log(portfolio)
            return portfolio
          
  
        }
        else{
          console.log('updated portfolio existing')
          console.log(portfolio)
          return portfolio;
        }


      }),
      switchMap(updatedData => {
        return this.http.put('http://localhost:5000/portfolio-update', updatedData);
      })
    )
  }



  addToPortfolioSell(trade: any){
    return this.getPortfolioData().pipe(
      map(data => {
        console.log(data)
        console.log(trade)
        var portfolio = data
       
        for(let stock of portfolio){
          console.log(stock)
          if(stock.instrumentID === trade.instrumentId && stock.clientId === trade.clientId){
            console.log('found')
            stock.quantity -= trade.quantity
            stock.totalInvestment -= trade.cashValue
            console.log(portfolio)


            
          }
        }


        console.log(portfolio)
        portfolio = portfolio.filter((item: any) => item.quantity !== 0)
        console.log(portfolio)

        
        console.log('updated portfolio')
        console.log(portfolio)
        return portfolio;
        


      }),
      switchMap(updatedData => {
        return this.http.put('http://localhost:5000/portfolio-update', updatedData);
      })
    )
  }

  

  executeBuy(buyOrder: any){
    const headers = new HttpHeaders({
      'Content-type': 'application/json'
      })
    return this.http.post<any>(this.serverUrl+'trade/performTrade', buyOrder, {headers: headers} ).pipe(
      //catchError(this.handleError)
      // map((response: any) => {
      //   console.log('here')


      //   console.log(response)
      //   return response
      // })
    )

  }


  executeSell(sellOrder: any) {
    const headers = new HttpHeaders({
      'Content-type': 'application/json'
      })
    return this.http.post<any>(this.serverUrl+'trade/performTrade', sellOrder, {headers: headers} ).pipe(
      //catchError(this.handleError)
      map((response: any) => {
        console.log('here')


        console.log(response)
        return response
      })
    )
  }

  
}
