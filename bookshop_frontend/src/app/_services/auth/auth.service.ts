import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const AUTH_API = 'http://localhost:8087/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'signin', {
      username,
      password
    }, httpOptions);

  }

  register(username: string, email: string, password: string): Observable<any> {
    console.log("register");
    return this.http.post(AUTH_API + 'signup', {
      username,
      email,
      password
    }, httpOptions);
  }

  newUser(username: string, email: string): Observable<any> {
    return this.http.post(AUTH_API + 'user/new', {
      username,
      email
    }, httpOptions);
  }

  verifyToken(token: string): Observable<any>{
    return this.http.get<any>(AUTH_API + `verify/?code=${token}`);
  }
}