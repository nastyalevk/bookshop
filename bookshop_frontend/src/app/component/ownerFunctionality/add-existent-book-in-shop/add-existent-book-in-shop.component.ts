import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Assortment } from 'src/app/model/assortment/assortment';
import { Book } from 'src/app/model/book/book';
import { AssortmentService } from 'src/app/_services/assortment/assortment.service';
import { BookService } from 'src/app/_services/book/book.service';

@Component({
  selector: 'app-add-existent-book-in-shop',
  templateUrl: './add-existent-book-in-shop.component.html',
  styleUrls: ['./add-existent-book-in-shop.component.css']
})
export class AddExistentBookInShopComponent implements OnInit {

  assortment: Assortment;
  bookId: number;
  shopId: number;
  book: Book;
  today = new Date();
  classifications=["OPEN", "ACTIVE", "WAITING", "CLOSED"];

  dd = String(this.today.getDate()).padStart(2, '0');
  mm = String(this.today.getMonth() + 1).padStart(2, '0');
  yyyy = String(this.today.getFullYear());
  hh = String(this.today.getHours());
  MM = String(this.today.getMinutes());
  ss = String(this.today.getSeconds());
  
  constructor(private route: ActivatedRoute, protected router: Router, private bookService: BookService,               
    private assortmentService: AssortmentService) {
    this.assortment=new Assortment();
    this.bookId = this.route.snapshot.params.bookId;
    this.shopId = this.route.snapshot.params.shopId;
    this.book = new Book();
   }

  ngOnInit(): void {
    this.bookService.getOne(this.bookId).subscribe(data=>{
      this.book = data
    })
  }

  onSubmitBook(){
    this.assortment.bookId = this.bookId.toString();
    this.assortment.shopId = this.shopId;
    this.assortment.creationDate = this.yyyy + "-" + this.mm + "-" + this.dd + " " + this.hh + ":" + this.MM + ":" + this.ss;
    this.assortmentService.saveAssortment(this.assortment).subscribe(data=>{
      this.assortment = this.assortment;
    })
    this.router.navigate([`/shop/addBooks/${this.shopId}`]);
  }
}
