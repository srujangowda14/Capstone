import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstrumentListComponent } from './instrument-list.component';

describe('InstrumentListComponent', () => {
  let component: InstrumentListComponent;
  let fixture: ComponentFixture<InstrumentListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [InstrumentListComponent]
    });
    fixture = TestBed.createComponent(InstrumentListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
