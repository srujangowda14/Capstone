import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PieChartInvestmentComponent } from './pie-chart-investment.component';

describe('PieChartInvestmentComponent', () => {
  let component: PieChartInvestmentComponent;
  let fixture: ComponentFixture<PieChartInvestmentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PieChartInvestmentComponent]
    });
    fixture = TestBed.createComponent(PieChartInvestmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
