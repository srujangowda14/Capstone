import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { SessionHandlerService } from './services/session-handler.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router, private sessionService: SessionHandlerService) {}

  canActivate(): boolean {
   
    const token = this.sessionService.getClientId();
   

    if (token) {
    
      return true;
    } else {
      
      this.router.navigate(['/login']);
      return false;
    }
  }
}
