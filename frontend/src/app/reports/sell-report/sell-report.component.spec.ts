import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SellReportComponent } from './sell-report.component';

describe('SellReportComponent', () => {
  let component: SellReportComponent;
  let fixture: ComponentFixture<SellReportComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SellReportComponent]
    });
    fixture = TestBed.createComponent(SellReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
