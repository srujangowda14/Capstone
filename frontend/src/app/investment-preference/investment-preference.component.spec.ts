import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InvestmentPreferenceComponent } from './investment-preference.component';

describe('InvestmentPreferenceComponent', () => {
  let component: InvestmentPreferenceComponent;
  let fixture: ComponentFixture<InvestmentPreferenceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InvestmentPreferenceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InvestmentPreferenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
