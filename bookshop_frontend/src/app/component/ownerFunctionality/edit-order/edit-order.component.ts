import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbDateStruct, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Book } from 'src/app/model/book/book';
import { Order } from 'src/app/model/order/order';
import { OrderContent } from 'src/app/model/orderContent/order-content';
import { BookService } from 'src/app/_services/book/book.service';
import { OrderService } from 'src/app/_services/order/order.service';
import { TokenStorageService } from 'src/app/_services/token/token-storage.service';
import { NgbdModalContentComponent } from '../../ngbd-modal-content/ngbd-modal-content.component';

@Component({
  selector: 'app-edit-order',
  templateUrl: './edit-order.component.html',
  styleUrls: ['./edit-order.component.css']
})

export class EditOrderComponent implements OnInit {
  model: NgbDateStruct;
  order: Order;
  orderId: number;
  orderContents: OrderContent[] = [];
  books: Book[] = [];
  private roles: string[] = [];
  isClient = false;
  isOwner = false;
  quantities: Map<[number, number], number>;
  classifications = ["OPEN", "SUBMITTED", "PROCESSING", "PROCESSED", "CANCELED"];
  fullPrice = 0;

  dd: number;
  mm: number;
  yyyy: number;

  constructor(private orderService: OrderService, private route: ActivatedRoute, protected router: Router,
    private bookService: BookService, private tokenStorageService: TokenStorageService,
    private modalService: NgbModal) {
    this.orderId = this.route.snapshot.params.orderId;
    this.order = new Order();
    this.roles = this.tokenStorageService.getUser().roles;
    this.isClient = this.roles.includes('ROLE_CLIENT');
    this.isOwner = this.roles.includes('ROLE_OWNER');
    this.quantities = new Map<[number, number], number>();

  }

  ngOnInit(): void {
    this.orderService.getOne(this.orderId).subscribe(data => {
      this.order = data;
    },
      () => {
        this.router.navigate([`/error`]);
      });
    this.orderService.getOrderContents(this.orderId).subscribe(data => {
      this.orderContents = data;
      this.fullPrice = 0;
      for (let i of this.orderContents) {
        this.fullPrice += i.quantity * i.price;
        this.bookService.getOne(i.bookId).subscribe(data => {
          this.books.push(data);
        },
          err => {
            console.log(err.error.message);
            const modalRef = this.modalService.open(NgbdModalContentComponent);
            modalRef.componentInstance.message = err.error.message;
          });
      }
    },
      err => {
        console.log(err.error.message);
        const modalRef = this.modalService.open(NgbdModalContentComponent);
        modalRef.componentInstance.message = err.error.message;
      });
  }

  saveOrder() {
    if (this.model) {
      this.dd = this.model.day;
      this.mm = this.model.month;
      this.yyyy = this.model.year;
      this.order.orderCompleteDate = this.mm + '-' + this.dd + '-' + this.yyyy;
    }
    this.orderService.saveOrder(this.order).subscribe(data => {
      this.order = data;
    },
      err => {
        console.log(err.error.message);
        const modalRef = this.modalService.open(NgbdModalContentComponent);
        modalRef.componentInstance.message = err.error.message;
      });
    window.location.reload();
  }
  deleteOrder() {
    this.order.classification = "canceled";
    this.orderService.saveOrder(this.order).subscribe(() => { this.ngOnInit() },
      err => {
        console.log(err.error.message);
        const modalRef = this.modalService.open(NgbdModalContentComponent);
        modalRef.componentInstance.message = err.error.message;
      });
  }

  removeBook(orderContent: OrderContent) {
    this.orderService.deleteOrderContent(orderContent).subscribe(() => { this.ngOnInit() },
      err => {
        console.log(err.error.message);
        const modalRef = this.modalService.open(NgbdModalContentComponent);
        modalRef.componentInstance.message = err.error.message;
      });
  }

  saveChanges(orderContent: OrderContent) {
    orderContent.quantity = parseInt(orderContent.quantity);
    this.orderService.updateOrderContent(orderContent).subscribe(
      () => {
        this.ngOnInit();
      },
      err => {
        console.log(err.error.message);
        const modalRef = this.modalService.open(NgbdModalContentComponent);
        modalRef.componentInstance.message = err.error.message;
      }
    );
    this.order.cost = 0;
    for (let i of this.orderContents) {
      this.order.cost += i.quantity * i.price;
    }
    this.orderService.saveOrder(this.order).subscribe(
      data => {
        console.log(data);
        this.ngOnInit();
      },
      err => {
        console.log(err.error.message);
        const modalRef = this.modalService.open(NgbdModalContentComponent);
        modalRef.componentInstance.message = err.error.message;
      }
    );
  }

  isBookAvaliable(bookId: number, shopId: number): boolean {
    console.log(this.isAvaliable)
    return this.isAvaliable.get(bookId.toString() + shopId.toString());
  }
}
