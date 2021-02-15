import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShopCommentApproveComponent } from './shop-comment-approve.component';

describe('ShopCommentApproveComponent', () => {
  let component: ShopCommentApproveComponent;
  let fixture: ComponentFixture<ShopCommentApproveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShopCommentApproveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShopCommentApproveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
