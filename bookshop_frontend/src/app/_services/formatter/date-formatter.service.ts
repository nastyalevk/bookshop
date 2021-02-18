import { Injectable } from '@angular/core';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';

@Injectable({
  providedIn: 'root'
})
export class DateFormatterService {

  constructor() { }

  formatDate(d: NgbDateStruct):string{
    if (d === null) {
      return "";
    }
  
    return [ 
      d.year,
      (d.month < 10 ? ('0' + d.month) : d.month),
      (d.day < 10 ? ('0' + d.day) : d.day)     
    ].join('-');
  }
}
