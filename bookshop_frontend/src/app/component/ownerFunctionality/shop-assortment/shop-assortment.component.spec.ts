import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShopAssortmentComponent } from './shop-assortment.component';

describe('ShopAssortmentComponent', () => {
  let component: ShopAssortmentComponent;
  let fixture: ComponentFixture<ShopAssortmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShopAssortmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShopAssortmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
