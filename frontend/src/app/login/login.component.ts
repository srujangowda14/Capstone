import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { RegisterService } from '../services/register.service';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { SessionHandlerService } from '../services/session-handler.service';
import { HttpErrorResponse } from '@angular/common/http';
import { throwError } from 'rxjs';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private registerService: RegisterService, private _router:Router, private formBuilder: FormBuilder, private sessionHandlerService: SessionHandlerService){}


  loginInvalid: boolean = false
  message: string = ''

  loginForm: FormGroup = new FormGroup({})

  ngOnInit(){
    this.loginForm = this.formBuilder.group({
      email: new FormControl('', [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]),
      clientId: ['', Validators.required]
      });
  }

 


  checkLogin(): void {
    //console.log(this.loginForm.value)


    this.registerService.loginFMTS({email: this.loginForm.get('email')?.value, clientId: this.loginForm.get('clientId')?.value}).subscribe((data: any) => {
      console.log(data)
      
      if(data.hasOwnProperty('token') && data.token === 0){
        this.message = 'Login Invalid'
      }
      else{
        console.log('login is valid')
        this.sessionHandlerService.saveEmail(data.email)
        console.log(this.sessionHandlerService.getEmail())
        this.sessionHandlerService.saveToken(data.token)
        console.log(data.clientId)
        this.sessionHandlerService.saveUser(data.email, String(data.clientId))
        console.log(this.sessionHandlerService.getClientId())
        this._router.navigate(["/home"], { queryParams: { source: 'login' } })

      }
    },
    (error: HttpErrorResponse) => {
      console.log(error.error)

      if(error.status === 401){
        this.message = "Invalid client id"
      }
      else{
        this.message = error.error.email
      }

      
    }
    
    
    )

  }


}
