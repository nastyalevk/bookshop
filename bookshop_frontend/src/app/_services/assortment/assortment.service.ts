import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Assortment } from 'src/app/model/assortment/assortment';

const Url = 'http://localhost:8087/assortment';

@Injectable({
  providedIn: 'root'
})
export class AssortmentService {

  constructor(private http: HttpClient) { }

  saveAssortment(assortment: Assortment): Observable<Assortment> {
    return this.http.post<Assortment>(Url + "/create", assortment);
  }

  updateAssortment(assortment: Assortment): Observable<Assortment> {
    return this.http.post<Assortment>(Url + "/update", assortment);
  }

  existsByBook(bookId: any, shopId: number): Observable<boolean> {
    return this.http.get<boolean>(Url + "/exists/" + bookId + "/" + shopId);
  }

  deleteAssortment(bookId: any, shopId: number) {
    return this.http.get<Assortment>(Url + "/delete/" + bookId + "/" + shopId);
  }

  getOne(bookId: any, shopId: number): Observable<Assortment> {
    return this.http.get<Assortment>(Url + "/" + bookId + "/" + shopId);
  }

  getByBook(bookId: any): Observable<Assortment[]> {
    return this.http.get<Assortment[]>(Url + "/" + bookId);
  }
}
