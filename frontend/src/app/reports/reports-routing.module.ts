import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { SellReportComponent } from "./sell-report/sell-report.component";

const routes: Routes=[
   
]

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class ReportsRoutingModule { }