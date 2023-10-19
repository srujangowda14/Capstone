// stock.service.ts

import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StockindexService {
  private apiKey = 'JXAAOSQ17GBY5KMQ'; // Replace with your Alpha Vantage API key
  private apiUrl = 'https://www.alphavantage.co/query';

  constructor(private http: HttpClient) {}

  getDowJonesData(): Observable<any> {
    const symbol = 'DJI'; // Dow Jones Industrial Average
    const functionType = 'TIME_SERIES_INTRADAY'; // For intraday data, you can also use TIME_SERIES_DAILY

    const params = new HttpParams()
    .set('function', functionType)
    .set('symbol', 'DJI') // Dow Jones Industrial Average
    .set('interval', '5min') // You can change the interval as needed
    .set('apikey', this.apiKey);

    return this.http.get<any>(this.apiUrl, { params });
  }
}
