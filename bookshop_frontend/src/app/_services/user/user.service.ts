import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book } from '../../model/book/book';
import { User } from 'src/app/model/user/user';

const Url = 'http://localhost:8087/';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {}

  getPublicContent(): Observable<any> {
    return this.http.get<Book[]>(Url + 'book');
  }

  findAll(): Observable<User[]> {
    const users = this.http.get<User[]>(Url + 'user');
    return users;
  }
  getOne(id: number): Observable<User> {
    return this.http.get<User>(Url + 'user/' + id);
  }

  save(user: User) {
    console.log(user);
    return this.http.post<User>(Url + 'user/create/', user);
  }

  update(user: User) {
    console.log(user);
    return this.http.post<User>(Url + 'user/update/', user);
  }

  updateRoles(params: any) {
    return this.http.get<any>(Url + 'user/update-roles/', { params });
  }

  getUserByUsername(username: string): Observable<User> {
    return this.http.get<User>(Url + 'user/find/username/' + username);
  }
}