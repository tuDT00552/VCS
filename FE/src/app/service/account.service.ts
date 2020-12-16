import { AccountModel, AccountModelItem } from './../model/account-model';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private http: HttpClient) { }
  private API = 'http://localhost:8080/';
  token = localStorage.getItem("token");
  getAllAccount(page: number, size: number): Observable<AccountModelItem> {
    return this.http.get<AccountModelItem>(`${this.API}getListAccount?page=${page}&size=${size}`);
  }

  searchAccount(keyword: string, page: number, size: number): Observable<AccountModelItem> {
    return this.http.get<AccountModelItem>(`${this.API}searchListAccount?keyword=${keyword}&page=${page}&size=${size}`);
  }

  deleteAccount(input): Observable<AccountModelItem> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Token ' + this.token
      }), body: input,
    };
    return this.http.delete<AccountModelItem>(`${this.API}delete-account`, httpOptions);
  }

  saveAccount(data: AccountModel): Observable<AccountModel> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Token ' + this.token
      })
    };
    return this.http.post<AccountModel>(`${this.API}create-account`, data, httpOptions);
  }

  updateAccount(data: AccountModel): Observable<AccountModel> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Token ' + this.token
      })
    };
    return this.http.put<AccountModel>(`${this.API}update-account`, data, httpOptions);
  }

  login(data): Observable<any> {
    return this.http.post<any>(`${this.API}login`, data);
  }

}
