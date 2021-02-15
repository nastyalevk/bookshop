import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookCommentApproveComponent } from './book-comment-approve.component';

describe('BookCommentApproveComponent', () => {
  let component: BookCommentApproveComponent;
  let fixture: ComponentFixture<BookCommentApproveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookCommentApproveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BookCommentApproveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
