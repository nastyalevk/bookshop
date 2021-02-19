import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbDateStruct, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Assortment } from 'src/app/model/assortment/assortment';
import { Book } from 'src/app/model/book/book';
import { AssortmentService } from 'src/app/_services/assortment/assortment.service';
import { BookService } from 'src/app/_services/book/book.service';
import { NgbdModalContentComponent } from '../../ngbd-modal-content/ngbd-modal-content.component';

@Component({
  selector: 'app-new-book',
  templateUrl: './new-book.component.html',
  styleUrls: ['./new-book.component.css']
})
export class NewBookComponent implements OnInit {

  book = new Book();
  id: number;
  assortment = new Assortment();
  classifications = ["OPEN", "ACTIVE", "WAITING", "CLOSED"];

  model: NgbDateStruct;
  bookId: number;
  today = new Date();

  dd = String(this.today.getDate()).padStart(2, '0');
  mm = String(this.today.getMonth() + 1).padStart(2, '0');
  yyyy = String(this.today.getFullYear());
  hh = String(this.today.getHours());
  MM = String(this.today.getMinutes());
  ss = String(this.today.getSeconds());

  constructor(private route: ActivatedRoute, protected router: Router, private bookService: BookService,
    private assortmentService: AssortmentService, private modalService: NgbModal) {
    this.id = this.route.snapshot.params.id;
  }

  ngOnInit(): void {
    this.book = new Book();
    this.assortment = new Assortment();
  }

  getAllOrders() {
    this.router.navigate([`/orders/shop/${this.id}`]);

  }

  editShop() {
    this.router.navigate([`/shop/edit/${this.id}`]);
  }

  addNewBook() {
  }

  addBook() {
    this.router.navigate([`/shop/addBooks/${this.id}`]);
  }

  shopAssortment() {
    this.router.navigate([`/shop/${this.id}`]);
  }

  onSubmitBook() {
    console.log(this.book);
    this.bookService.saveBook(this.book).subscribe(data => {
      this.assortment.bookId = data.id;
      this.assortment.shopId = this.id;
      this.assortment.creationDate = this.yyyy + "-" + this.mm + "-" + this.dd + " " + this.hh + ":" + this.MM + ":" + this.ss;
      console.log(this.assortment);
      this.assortmentService.saveAssortment(this.assortment).subscribe(() => {
        this.ngOnInit()
      },
        err => {
          const modalRef = this.modalService.open(NgbdModalContentComponent);
          modalRef.componentInstance.message = err.error.message;
        });
    },
      err => {
        const modalRef = this.modalService.open(NgbdModalContentComponent);
        modalRef.componentInstance.message = err.error.message;
      });
  }
}
