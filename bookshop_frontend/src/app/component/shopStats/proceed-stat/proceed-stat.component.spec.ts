import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProceedStatComponent } from './proceed-stat.component';

describe('ProceedStatComponent', () => {
  let component: ProceedStatComponent;
  let fixture: ComponentFixture<ProceedStatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProceedStatComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProceedStatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
