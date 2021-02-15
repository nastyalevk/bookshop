import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdersByShopComponent } from './orders-by-shop.component';

describe('OrdersByShopComponent', () => {
  let component: OrdersByShopComponent;
  let fixture: ComponentFixture<OrdersByShopComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrdersByShopComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrdersByShopComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
