import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuyReportComponent } from './buy-report.component';

describe('BuyReportComponent', () => {
  let component: BuyReportComponent;
  let fixture: ComponentFixture<BuyReportComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BuyReportComponent]
    });
    fixture = TestBed.createComponent(BuyReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
