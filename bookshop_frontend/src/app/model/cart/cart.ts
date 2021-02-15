import { Assortment } from "../assortment/assortment";
import { Book } from "../book/book";

export class Cart {
    book: Book;
    quantity: number;
    assortment:Assortment;

    constructor(book:Book, quantity:number, assortment:Assortment){
        this.book = book;
        this.assortment = assortment;
        this.quantity = quantity;
    }
}
