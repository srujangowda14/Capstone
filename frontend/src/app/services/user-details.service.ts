import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { map, of, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserDetailsService {

  url: string = '../../assets/database.json'
  emailValid: boolean = true
  serverUrl: string = 'https://a720855.roifmr.com/'

  
  

  getJsonData() {
    return this.http.get<any>(this.url);
  }

  constructor(private http: HttpClient) { }


  
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


  getUserName(clientId: string){

    console.log(clientId)
    const params = new HttpParams().set('clientId', clientId)
   

   
    return this.http.get<any>(this.serverUrl+'client/username' , { params }).pipe(
      //catchError(this.handleError)
      map((response: any) => {
        console.log('here')
        console.log(response)
        return response.message
      })
    )
  }


  getCash(clientId: string){

    const params = new HttpParams().set('clientId', clientId)
   

    return this.http.get<any>(this.serverUrl+'client/balance', { params } ).pipe(
      //catchError(this.handleError)
      map((response: any) => {
        console.log('here')
        console.log(response)
        return response
      })
    )
   
  }

}
