import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShopPersonalPageComponent } from './shop-personal-page.component';

describe('ShopPersonalPageComponent', () => {
  let component: ShopPersonalPageComponent;
  let fixture: ComponentFixture<ShopPersonalPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShopPersonalPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShopPersonalPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
