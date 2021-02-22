import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PaginationService {

  page: number;
  size: number
  constructor() {
    this.page=1;
    this.size=8;
   }
   setPage(value: number){
     this.page=value;
   }
   getPage(): number{
     return this.page;
   }

   setSize(value: number){
    this.size=value;
  }
  getSize(): number{
    return this.size;
  }
}
