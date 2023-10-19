import { Injectable } from '@angular/core';
import { Client } from '../model/client';
import { AbstractControl, AsyncValidatorFn, ValidationErrors } from '@angular/forms';
import { Observable, catchError, map, switchMap, throwError, of } from 'rxjs';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { SessionHandlerService } from './session-handler.service';


@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient, private sessionHandlerService: SessionHandlerService) { }

  clients : any
  url: string = '../../assets/database.json'
  emailValid: boolean = true
  fmtsUrl: string = 'https://a720855.roifmr.com/'
  serverUrl: string = 'https://a720855.roifmr.com/'

  isUser: boolean = true
  
  registerUrl = 'https://a720855.roifmr.com/client/register'




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



  getJsonData() {
    return this.http.get<any>(this.url);
  }

  registerClient(client:Client){
    const headers = new HttpHeaders({
      'Content-type': 'application/json'
      })
    return this.http.post<any>(this.registerUrl, client, {headers: headers})
         .pipe(catchError(this.handleError));
  }

  verifyEmail(email: string) {
    return this.getJsonData().pipe(
      map(data => {
        console.log(data)
        var foundItem = false
        var clientArr = data
        for(let client of clientArr){
          if(client.email === email){
            foundItem = true
          }
        }
        return foundItem // Convert to boolean
      })
    );
  }

  verifyUser(currentClient: Client): any{

    return this.getJsonData().pipe(
      map(data => {
        this.clients = data
        for(let client of this.clients){
          console.log('From form ' +currentClient.identifications[0].value);
          console.log('Service ' + client.identification[0].value);
        if(currentClient.email=== client.email)  
            return 'An user with this email ID already exists';
        else if(currentClient.identifications[0].value === client.identification.value){
          return 'The user with these details already exist with a different email ID.'
        }
            
      }
      return 'Registration successful';
      })
    )

    
  }

 
  



  

  registerFMTS(client: any){
    const headers = new HttpHeaders({
      'Content-type': 'application/json'
      })
    return this.http.post<any>(this.fmtsUrl+'fmts/client', client, {headers: headers} ).pipe(
      catchError(this.handleError)
      
    )
  }



  addUser(newUser: any) {
    return this.getJsonData().pipe(
      map(data => {
        console.log(data)
        var newClients = data
        newClients.push(newUser)
        data.clients = newClients
        this.sessionHandlerService.saveEmail(newUser.email)
        console.log(this.sessionHandlerService.getEmail())
        console.log(data)
        return data;
      }),
      switchMap(updatedData => {
        return this.http.put('http://localhost:5000/update-json', updatedData);
      })
    );
  }


  loginFMTS(client: any){
    console.log(client)
    client.clientId = Number(client.clientId)

    const headers = new HttpHeaders({
      'Content-type': 'application/json'
      })
    return this.http.post<any>(this.serverUrl+'client/login', client, {headers: headers} ).pipe(
      //catchError(this.handleError)
      // map((response: any) => {
      //   console.log('here')
      //   console.log(response)
      //   return response
      // })
    )


  }


  logOut(){
    this.sessionHandlerService.resetEmail()
    this.sessionHandlerService.resetClientId()
    this.sessionHandlerService.resetToken()
  }
  // registerUser(newClient: any){
  //   this.clients.clients.push(newClient)
  //   console.log(this.clients.clients)
  // }


  
}


