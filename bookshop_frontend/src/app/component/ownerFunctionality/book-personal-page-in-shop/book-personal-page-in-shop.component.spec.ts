import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookPersonalPageInShopComponent } from './book-personal-page-in-shop.component';

describe('BookPersonalPageInShopComponent', () => {
  let component: BookPersonalPageInShopComponent;
  let fixture: ComponentFixture<BookPersonalPageInShopComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookPersonalPageInShopComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BookPersonalPageInShopComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
