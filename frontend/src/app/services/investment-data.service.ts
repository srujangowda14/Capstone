import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { InvestmentPreference } from '../investment-preference';

@Injectable({
  providedIn: 'root'
})
export class InvestmentDataService {

  private preferenceJsonUrl ='https://a720855.roifmr.com/preferences/prefs';
  private url='https://a720855.roifmr.com/preferences/prefs';
  id: number=0;

  constructor(private http:HttpClient) { }

  getPreference(){
    return this.http.get<any>(this.preferenceJsonUrl);
    
  }

  updatePref( body:InvestmentPreference){
    const headers= new HttpHeaders({'Content-type':'application/json'});
    console.log("Hi" + InvestmentPreference)
    return this.http.put<InvestmentPreference>(this.url,body,{headers:headers});
 
    }

    addPref( body:InvestmentPreference){
      const headers= new HttpHeaders({'Content-type':'application/json'});
      console.log("Hi" + InvestmentPreference)
      return this.http.post<InvestmentPreference>(this.url,body,{headers:headers});
   
      }

      getPreferenceById(clientId: number){
        const headers= new HttpHeaders({'Content-type':'application/json'});
        this.id=clientId;
        console.log("Hi" + InvestmentPreference)
        return this.http.get<InvestmentPreference>('https://a720855.roifmr.com/preferences/${id}');
      }
}
