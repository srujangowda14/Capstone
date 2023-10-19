import { TestBed } from '@angular/core/testing';

import { StockindexService } from './stockindex.service';

describe('StockindexService', () => {
  let service: StockindexService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StockindexService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
