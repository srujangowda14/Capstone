import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { InstrumentListComponent } from './instrument-list/instrument-list.component';
import { BuyPageComponent } from './buy-page/buy-page.component';
import { TransactionHistoryComponent } from './transaction-history/transaction-history.component';
import { ReportMainComponent } from './reports/report-main/report-main.component';
import { SellReportComponent } from './reports/sell-report/sell-report.component';
import { BuyReportComponent } from './reports/buy-report/buy-report.component';
import { PAndLReportComponent } from './reports/p-and-lreport/p-and-lreport.component';
import { SellPageComponent } from './sell-page/sell-page.component';
import { PortfolioListComponent } from './portfolio-list/portfolio-list.component';
import { RoboAdvisorComponent } from './robo-advisor/robo-advisor.component';
import { InvestmentPreferenceComponent } from './investment-preference/investment-preference.component';
import { AuthGuard } from './auth.guard';

const routes: Routes = [
  {path:'login',component:LoginComponent},
  {path:'home',component:HomeComponent},
  {path:'register', component:RegisterComponent},
  {path: 'trade', component: InstrumentListComponent, canActivate: [AuthGuard]},
  {path: 'buy/:id', component: BuyPageComponent, canActivate: [AuthGuard]},
  {path: 'sell', component: BuyPageComponent, canActivate: [AuthGuard]},
  {path: 'preferences', component: InvestmentPreferenceComponent, canActivate: [AuthGuard]},
  {path:'history',component:TransactionHistoryComponent, canActivate: [AuthGuard]},
  {
        path: 'reports', 
        component: ReportMainComponent,
        children: [
            { path: '', redirectTo: 'sellReport', pathMatch: 'full'},
            { path: 'sellReport', component: SellReportComponent},
            { path: 'buyReport', component: BuyReportComponent},
            { path: 'pAndLReport', component: PAndLReportComponent},
        ]
  },
  {path: 'sell/:id', component: SellPageComponent},
  {path:'history',component:TransactionHistoryComponent},
  {path: 'sell-list', component: PortfolioListComponent},
  {path: 'robo-advisor',component: RoboAdvisorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
