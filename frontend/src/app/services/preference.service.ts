import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TransactionHistory } from '../model/transaction-history';

@Injectable({
  providedIn: 'root'
})
export class PreferenceService {

  constructor(private http: HttpClient) { }


  getPreference(): Observable<any>{
    return this.http.get('/assets/preference.json');
  }
}
