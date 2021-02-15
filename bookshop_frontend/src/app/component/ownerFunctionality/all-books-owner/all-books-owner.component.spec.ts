import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllBooksOwnerComponent } from './all-books-owner.component';

describe('AllBooksOwnerComponent', () => {
  let component: AllBooksOwnerComponent;
  let fixture: ComponentFixture<AllBooksOwnerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllBooksOwnerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllBooksOwnerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
