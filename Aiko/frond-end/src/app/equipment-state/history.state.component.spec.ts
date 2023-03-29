import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoryStateComponent } from './history.state.component';

describe('HistoryStateComponent', () => {
  let component: HistoryStateComponent;
  let fixture: ComponentFixture<HistoryStateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HistoryStateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HistoryStateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
