import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, catchError, map, throwError } from 'rxjs';
import { Instrument } from '../model/instrument';

@Injectable({
  providedIn: 'root'
})
export class PortfolioService {
  private url: string = 'https://a720855.roifmr.com/fmts/trades/instruments';
  private portfolioJsonUrl ='../../assets/portfolio.json';
  private portfolioUrl ='https://a720855.roifmr.com/portfolio';
  constructor(private http:HttpClient) { }

  getAllInstrumentsForPortfolio():Observable<Instrument[]>{
      return this.http.get<Instrument[]>(this.url)
      .pipe(catchError(this.handleError));
  }

  getCurrentPortfolio(clientId: string){
    return this.http.get<any>(`${this.portfolioUrl}/${clientId}`)
    .pipe(catchError(this.handleError));
  }


  handleError(response: HttpErrorResponse){
    if(response.error instanceof ProgressEvent){
      console.log('Client side error');
    }else{
      console.log('Server side error')
    }

    return throwError(()=> 'Oops something went wrong');
  }

  getMyPortfolio(){
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'https://a720855.roifmr.com', // Replace with your server URL
    });
    return this.http.get<any>(this.portfolioJsonUrl, {headers: headers})
    .pipe(catchError(this.handleError));
  }
}
