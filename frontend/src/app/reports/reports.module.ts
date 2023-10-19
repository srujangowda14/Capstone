import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { BuyReportComponent } from './buy-report/buy-report.component';
import { SellReportComponent } from './sell-report/sell-report.component';
import { PAndLReportComponent } from './p-and-lreport/p-and-lreport.component';
import { ReportMainComponent } from './report-main/report-main.component';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  declarations: [
    ReportMainComponent,
    BuyReportComponent,
    SellReportComponent,
    PAndLReportComponent,
  ],
  imports: [
    CommonModule,
    RouterModule,
    SharedModule
  ],
  exports:[]
})
export class ReportsModule { }
