import { TestBed } from '@angular/core/testing';

import { InvestmentPreferenceService } from './investment-preference.service';

describe('InvestmentPreferenceService', () => {
  let service: InvestmentPreferenceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InvestmentPreferenceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
