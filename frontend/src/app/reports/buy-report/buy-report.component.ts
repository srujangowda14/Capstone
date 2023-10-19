import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Trade } from 'src/app/model/trade';
import { SessionHandlerService } from 'src/app/services/session-handler.service';
import { TradeService } from 'src/app/services/trade.service';
import { ReportServiceService } from '../services/report-service.service';
import {Report} from 'src/app/model/report';
import * as XLSX from 'xlsx';
@Component({
  selector: 'app-buy-report',
  templateUrl: './buy-report.component.html',
  styleUrls: ['./buy-report.component.css']
})
export class BuyReportComponent implements OnInit{
    constructor(private sessionHandler: SessionHandlerService,
      private reportService: ReportServiceService){}

      @ViewChild('table', { static: false }) table: ElementRef | undefined;

      exportTableToExcel() {
        const ws: XLSX.WorkSheet = XLSX.utils.table_to_sheet(this.table?.nativeElement);
        const wb: XLSX.WorkBook = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');
        const fileName = 'buy_report.xlsx';
    
        XLSX.writeFile(wb, fileName);
      }

      filteredBuyData: Report[]=[];
      clientId:number=0;


    setClientId(){
      this.clientId = Number(this.sessionHandler.getClientId());
    }

    getBuyReport():void{
      this.reportService.getBuyReport(this.clientId).subscribe({
        next: (data) =>{
          console.log(data);
            this.filteredBuyData = data;
        },
        error: (err) => console.log(err)
      })
    }

   

    ngOnInit(): void {
        this.setClientId();
        this.getBuyReport();
    }
}
