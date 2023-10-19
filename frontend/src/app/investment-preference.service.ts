import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';
import { InvestmentPreference } from './investment-preference';
@Injectable({
  providedIn: 'root'
})
export class InvestmentPreferenceService {
 

  
   
  
  @Injectable({
    providedIn: 'root', // Register the service at the root level
  })
  
    private clientPreferences: InvestmentPreference = 
      {
        investmentPurpose: '',
        riskTolerance: '',
        incomeCategory: '', 
        lengthOfInvestments: '', 
        clientId:0
      };
  
   
  
    
    getClientPreferences(): InvestmentPreference{
      return this.clientPreferences;
    }
  
   
  
    updateClientPreferences(updatePrefernce: InvestmentPreference): void{
      this.clientPreferences = updatePrefernce;
    }
  }

