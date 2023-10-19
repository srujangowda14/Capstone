import { Component } from '@angular/core';
import { SessionHandlerService } from '../services/session-handler.service';
import { UserDetailsService } from '../services/user-details.service';
import { ActivatedRoute, NavigationEnd } from '@angular/router';
import { StockindexService } from '../services/stockindex.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  userName: string = ''
  constructor(private sessionHandlerService: SessionHandlerService, 
    private userDetailsService: UserDetailsService, 
    private route: ActivatedRoute,
    private stockIndexService : StockindexService ){}
  clientId: string = ''
  displayClientId: boolean = false
  cash: number = 0
  dowJonesData: any;


  // setUsername(){
  //   return this.userDetailsService.getUserName(this.sessionHandlerService.getEmail())
  //   //console.log(this.userName)

  //   console.log()
  // }

  getUsername() {
    console.log(this.sessionHandlerService.getEmail())
    console.log()
    this.userDetailsService.getUserName(this.sessionHandlerService.getClientId())
    .subscribe(
      (data) => {
        this.userName = data
      }
    )
    
  }



  getClientId(){
    
    this.clientId = this.sessionHandlerService.getClientId()
    console.log(this.clientId)
  }

  getCash(){
    this.userDetailsService.getCash(this.sessionHandlerService.getClientId()).subscribe((data) => {
      console.log(data)
      this.cash = data
    })
  }

  ngOnInit(){
    this.getUsername()
    this.getClientId()
    this.getCash()
    this.fetchDowJonesData();

    this.route.queryParams.subscribe(params => {
      console.log(params)
      if (params['source'] === 'register') {
        // Display the client ID.
        console.log('displaying')
        this.displayClientId = true;
      } else {
        // Do not display the client ID.
        this.displayClientId = false;
      }
    })
  }

  fetchDowJonesData() {
    this.stockIndexService.getDowJonesData().subscribe((data) => {
      // Handle the data as needed
      console.log(data);
      this.dowJonesData = data;
    });
  }

}
