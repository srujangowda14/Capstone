import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-report-main',
  templateUrl: './report-main.component.html',
  styleUrls: ['./report-main.component.css']
})
export class ReportMainComponent {
  constructor(private router: Router, private route: ActivatedRoute) {}

  navigateToChild(childRoute: string) {
    this.router.navigate([childRoute], { relativeTo: this.route });
  }
}
