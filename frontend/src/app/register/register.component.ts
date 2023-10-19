import { Component } from '@angular/core';
import { Client } from '../model/client';
import { FormGroup, FormControl, FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { RegisterService } from '../services/register.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { SessionHandlerService } from '../services/session-handler.service';
import { ClientIdentification } from '../model/client-identification';
import moment from 'moment';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  data: any
  constructor(private registerService: RegisterService,
    private _router:Router, private http: HttpClient, private formBuilder: FormBuilder, private sessionHandlerService: SessionHandlerService){}


    url: string = '../../assets/database.json'
    emailValid: boolean =true;
    registrationMessage: string= '';

    
  registrationForm: FormGroup = new FormGroup({})

  ngOnInit(){
    this.registrationForm = this.formBuilder.group({
      email: new FormControl('', [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]),
      name: ['', Validators.required],
      dateOfBirth: ['', Validators.required],
      country: ['', Validators.required],
      postalCode: ['', Validators.required],
      ssn: new FormControl('', [Validators.required, Validators.minLength(9)]),
      aadhar: new FormControl('',[Validators.required]),
      });
  }
     

     onSubmit(): void{


          console.log(this.registrationForm.get('ssn')?.errors);
          console.log(this.registrationForm.get('aadhar')?.errors);
          if(!this.registrationForm.get('ssn')?.errors){
            this.registrationForm.get('aadhar')?.clearValidators();
            this.registrationForm.get('aadhar')?.setErrors(null);
            this.registrationForm.updateValueAndValidity();
            console.log(this.registrationForm.get('aadhar')?.errors);
          }
          if(!this.registrationForm.get('aadhar')?.errors){
            this.registrationForm.get('ssn')?.clearValidators();
            this.registrationForm.updateValueAndValidity();
          }
          console.log(this.registrationForm);


          if(this.registrationForm.valid){
            const identification = new ClientIdentification(
              this.registrationForm.get('ssn')?.value?.length==9?'SSN':'aadhar',
              this.registrationForm.get('ssn')?.value?.length==9?
                      this.registrationForm.get('ssn')?.value!
                      : this.registrationForm.get('aadhar')?.value!,
            )
            const identificationArray = new Array<ClientIdentification>;
            identificationArray.push(identification);
            console.log(identificationArray);
            const client = new Client('',
            this.registrationForm.get('email')?.value,
            this.registrationForm.get('name')?.value,
            this.registrationForm.get('dateOfBirth')?.value,
            this.registrationForm.get('country')?.value,
            this.registrationForm.get('postalCode')?.value,
            identificationArray,
            2000,
            0
            )
            this.registerService.registerClient(client).subscribe({
              next : (data) =>{
                  console.log(data);
                  this.sessionHandlerService.saveUser(client.email, data.clientId);
                  this.sessionHandlerService.saveToken(data.token);
                  this._router.navigate(["/home"], { queryParams: { source: 'register' } })
              },
              error : (err) => {
                this.emailValid = false;
                console.log(err);
              }
            })
          }      
     }

     closePopUp(){
      this.registrationMessage='';
    }

    addHyphen(){
      let val = this.registrationForm.get('ssn')?.value;
      if(val?.length==3){
        this.registrationForm.get('ssn')?.setValue(val+'-');
      }else if(val?.length==6){
        this.registrationForm.get('ssn')?.setValue(val+'-');
      }
    }

    // async ageValidator(control: AbstractControl): Promise<{ [key: string]: boolean } | null> {
    //   const birthDate = moment(control.value, 'MM/DD/YYYY'); // Adjust the date format as per your dateOfBirth input
    //   const currentDate = moment();
    //   const age = currentDate.diff(birthDate, 'years');
    
    //   return new Promise((resolve) => {
    //     if (age < 18) {
    //       resolve({ 'underAge': true });
    //     } else {
    //       resolve(null);
    //     }
    //   });
    // }

   

  }