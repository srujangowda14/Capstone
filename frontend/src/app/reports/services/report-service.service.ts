import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReportServiceService {

  constructor(private httpClient: HttpClient) { }

  reportBaseUrl:string = `https://a720855.roifmr.com/report`;

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

  getBuyReport(clientId: number){
    return this.httpClient.get<any>(`${this.reportBaseUrl}/buyReport/${clientId}`)
    .pipe(catchError(this.handleError));
  }


  getSellReport(clientId: number){
    return this.httpClient.get<any>(`${this.reportBaseUrl}/sellReport/${clientId}`)
    .pipe(catchError(this.handleError));
  }
  
}
