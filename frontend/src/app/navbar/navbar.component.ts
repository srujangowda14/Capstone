import { Component } from '@angular/core';
import { SessionHandlerService } from '../services/session-handler.service';
import { Router } from '@angular/router';
import { UserDetailsService } from '../services/user-details.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  constructor(private sessionHandlerService: SessionHandlerService,  private _router:Router, private userDetailsService: UserDetailsService){}

  icon: string = ''

  ngOnInit(){
    this.setIcon()
  }

  logOut(){
    this.sessionHandlerService.resetEmail()
    this.sessionHandlerService.resetClientId()
    this.sessionHandlerService.resetToken()
    this._router.navigateByUrl("/")

  }

  setIcon(){
    this.userDetailsService.getUserName(this.sessionHandlerService.getClientId())
    .subscribe({
      next: (data) => {
        this.icon = data.charAt(0)
      }
    })
  }


}
