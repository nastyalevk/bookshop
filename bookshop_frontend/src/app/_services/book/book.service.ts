import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from 'src/app/model/book/book';

const bookUrl = 'http://localhost:8087/book/';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http: HttpClient) { }

  getAll(searchTitle: string, page: number, pageSize: number, sort: string): Observable<any> {
    return this.http.get<any>(bookUrl + `?bookName=${searchTitle}&page=${page - 1}&size=${pageSize}&sort=${sort}`);
  }

  getOne(id: number): Observable<any> {
    return this.http.get(bookUrl + id);
  }

  isBook(id: number): Observable<boolean> {
    return this.http.get<boolean>(bookUrl+"exist/" + id);
  }

  saveBook(book: Book): Observable<Book> {
    return this.http.post<Book>(bookUrl + 'create/', book);
  }

  getBooksByShop(page: number, pageSize: number, shopId: number): Observable<any> {
    return this.http.get<any>(bookUrl + `shop/?page=${page - 1}&size=${pageSize}&id=${shopId}`);
  }

}
