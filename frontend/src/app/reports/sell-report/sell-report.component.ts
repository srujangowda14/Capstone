import { Component, ElementRef, ViewChild } from '@angular/core';
import { Report } from 'src/app/model/report';
import { SessionHandlerService } from 'src/app/services/session-handler.service';
import { TradeService } from 'src/app/services/trade.service';
import { ReportServiceService } from '../services/report-service.service';
import * as XLSX from 'xlsx';

@Component({
  selector: 'app-sell-report',
  templateUrl: './sell-report.component.html',
  styleUrls: ['./sell-report.component.css']
})
export class SellReportComponent {
  constructor(private sessionHandler: SessionHandlerService,
    private reportService: ReportServiceService){}

    filteredSellData: Report[]=[];
    clientId:number=0;

    @ViewChild('table', { static: false }) table: ElementRef | undefined;

      exportTableToExcel() {
        const ws: XLSX.WorkSheet = XLSX.utils.table_to_sheet(this.table?.nativeElement);
        const wb: XLSX.WorkBook = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');
        const fileName = 'sell_report.xlsx';
    
        XLSX.writeFile(wb, fileName);
      }


  setClientId(){
    this.clientId = Number(this.sessionHandler.getClientId());
  }

  getBuyReport():void{
    this.reportService.getSellReport(this.clientId).subscribe({
      next: (data) =>{
        console.log(data);
          this.filteredSellData = data;
      },
      error: (err) => console.log(err)
    })
  }

 

  ngOnInit(): void {
      this.setClientId();
      this.getBuyReport();
  }
}
