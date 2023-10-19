import { Component, OnInit } from '@angular/core';
import { InvestmentPreference } from '../investment-preference';
import { InvestmentPreferenceService } from '../investment-preference.service';
import { FormsModule } from '@angular/forms';
import { SessionHandlerService } from '../services/session-handler.service';
import { InvestmentDataService } from '../services/investment-data.service';
import { Router } from '@angular/router';

 

@Component({
  selector: 'app-investment-preference',
  templateUrl: './investment-preference.component.html',
  styleUrls: ['./investment-preference.component.css']
})
export class InvestmentPreferenceComponent implements OnInit {
   formDataList:InvestmentPreference[]=[];
  
  
  clientPreferences: InvestmentPreference = {
    investmentPurpose: '',
    riskTolerance: '',
    incomeCategory: '',
    lengthOfInvestments: '',
    clientId:0
  };

   
  client: InvestmentPreference = {
    investmentPurpose: '',
    riskTolerance: '',
    incomeCategory: '',
    lengthOfInvestments: '',
    clientId:0
  };
  isEditMode=true;
  curClientID:number=0;
  constructor(
    private preferenceService:InvestmentPreferenceService,
    private sessionService:SessionHandlerService,
    private investment:InvestmentDataService,
    private route: Router
  ){}

  ngOnInit(): void {
    this.clientPreferences = this.preferenceService.getClientPreferences();
    this.curClientID=Number(this.sessionService.getClientId());
    // this.getClientById();
    

    
  }


 

  submitPrefernce(){
    console.log(this.clientPreferences);
    this.preferenceService.updateClientPreferences(this.clientPreferences);
    this.isEditMode=false;
    this.clientPreferences.clientId=this.curClientID
    this.getPreference();
    this.route.navigate(["/home"], { queryParams: { source: 'register' } })
    //this.route.navigateByUrl("/home");

  }

    

    getPreference():void{
    this.investment.getPreference().subscribe({
          next: (data) =>{
          this.formDataList=data;
          let f=0;
          for (let i=0; i< this.formDataList.length; i++){
            if(this.formDataList[i].clientId===Number(this.sessionService.getClientId())){
                
            //  this.formDataList[i].incomeCategory=this.clientPreferences.incomeCategory;
            //   this.formDataList[i].investmentPurpose=this.clientPreferences.investmentPurpose;
            //   this.formDataList[i].riskTolerance=this.clientPreferences.riskTolerance;
            //   this.formDataList[i].lengthOfInvestments=this.clientPreferences.lengthOfInvestments;
            this.updatePref();
            f=1;
            }
            // this.formDataList.push(this.clientPreferences);
          }
         if(f==0){
          console.log(this.formDataList);
          this.addPref();
         }
         
         
          }});
   
  }
  updatePref() { 
    this.investment.updatePref(this.clientPreferences).subscribe({ next:
      (data)=>{ console.log(data);
         }
    });}

  // getClientById(){
  //   this.investment.getPreferenceById(this.curClientID).subscribe({ next:
  //     (response)=>{ this.client=response;
  //        }
  //   });
      
  // }

    addPref() { 
      this.investment.addPref(this.clientPreferences).subscribe({ next:
        (data)=>{ console.log(data);
           }
      });}
  
 
  editMode(){
    this.isEditMode=true;
  }

  
   
   



  
    
  
 

   
      

     

      
     
    
  }
 
 

