import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdersStatComponent } from './orders-stat.component';

describe('OrdersStatComponent', () => {
  let component: OrdersStatComponent;
  let fixture: ComponentFixture<OrdersStatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrdersStatComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrdersStatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
