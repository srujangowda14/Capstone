import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoboAdvisorComponent } from './robo-advisor.component';

describe('RoboAdvisorComponent', () => {
  let component: RoboAdvisorComponent;
  let fixture: ComponentFixture<RoboAdvisorComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RoboAdvisorComponent]
    });
    fixture = TestBed.createComponent(RoboAdvisorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
