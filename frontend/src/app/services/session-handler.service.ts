import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class SessionHandlerService {

  constructor(private cookieService: CookieService) { }
  saveEmail(email: string): void {
    this.cookieService.set('user_email', email);
  }

  saveUser(email: string, clientId: string): void {
    this.cookieService.set('user_email', email)
    this.cookieService.set('clientId', clientId)
  }

  getEmail(): string {
    return this.cookieService.get('user_email');
  }

  getClientId():string {
    return this.cookieService.get('clientId')
  }


  saveToken(token: string): void {
    this.cookieService.set('token', token)
  }

  getToken(): string {
    return this.cookieService.get('token')
  }

  resetEmail(): void {
    this.cookieService.set('user_email', '')
  }

  resetToken(): void {
    this.cookieService.set('token', '')
  }

  resetClientId(): void {
    this.cookieService.set('clientId', '')
  }
}
