import { Component } from '@angular/core';
import { SessionHandlerService } from '../services/session-handler.service';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent {

  constructor(private sessionHandlerService: SessionHandlerService){}

  getLoggedIn(): boolean{

    console.log(this.sessionHandlerService.getEmail())
    if(this.sessionHandlerService.getEmail() === ''){
      return true
    }
    else{
      return false
    }
  }
}
