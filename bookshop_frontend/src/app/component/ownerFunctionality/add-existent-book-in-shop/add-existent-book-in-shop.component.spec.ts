import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddExistentBookInShopComponent } from './add-existent-book-in-shop.component';

describe('AddExistentBookInShopComponent', () => {
  let component: AddExistentBookInShopComponent;
  let fixture: ComponentFixture<AddExistentBookInShopComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddExistentBookInShopComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddExistentBookInShopComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
