import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PAndLReportComponent } from './p-and-lreport.component';

describe('PAndLReportComponent', () => {
  let component: PAndLReportComponent;
  let fixture: ComponentFixture<PAndLReportComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PAndLReportComponent]
    });
    fixture = TestBed.createComponent(PAndLReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
