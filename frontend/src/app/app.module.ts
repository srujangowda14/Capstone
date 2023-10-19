import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { NgxPaginationModule } from 'ngx-pagination';

import { RegisterComponent } from './register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {RouterModule, Routes} from '@angular/router';
import { LandingComponent } from './landing/landing.component';
import { InstrumentListComponent } from './instrument-list/instrument-list.component';
import { BuyPageComponent } from './buy-page/buy-page.component';
import { TransactionHistoryComponent } from './transaction-history/transaction-history.component';
import { PortfolioComponent } from './portfolio/portfolio.component';
import {HttpClientModule} from '@angular/common/http';
import { SellPageComponent } from './sell-page/sell-page.component';
import { ReportsModule } from './reports/reports.module';
import { SharedModule } from './shared/shared.module';
import { PortfolioListComponent } from './portfolio-list/portfolio-list.component';
import { RoboAdvisorComponent } from './robo-advisor/robo-advisor.component';
import { InvestmentPreferenceComponent } from './investment-preference/investment-preference.component';
import { PieChartComponent } from './pie-chart/pie-chart.component';
import { PieChartInvestmentComponent } from './pie-chart-investment/pie-chart-investment.component';




const routes:Routes=[
  {path:'', component:LandingComponent},
  {path:'login', component:LoginComponent},
  {path:'home', component:HomeComponent},
  {path:'register', component:RegisterComponent},
  

];


@NgModule({
  declarations: [
    AppComponent,

    LoginComponent,

    RegisterComponent,

    HomeComponent,
    LandingComponent,
    InstrumentListComponent,
    BuyPageComponent,
    TransactionHistoryComponent,
    PortfolioComponent,
    SellPageComponent,
    PortfolioListComponent,
    RoboAdvisorComponent,
    InvestmentPreferenceComponent,
    PieChartComponent,
    PieChartInvestmentComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    ReportsModule,
    SharedModule,
    NgxPaginationModule,
    FormsModule,
  ],
  providers: [],
  exports: [RouterModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
