import { AccountModel } from './model/account-model';
import { HttpClient } from '@angular/common/http';
import { ElementRef, OnInit, ViewChild } from '@angular/core';
import { Component } from '@angular/core';
import { IGridCell } from './components/grid/grid';
import { AccountService } from './service/account.service';
import { Title } from '@angular/platform-browser';
import { PageEvent } from '@angular/material/paginator';
import { NgxSpinnerService } from 'ngx-spinner';
import { FormControl, FormGroup } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  constructor(private accountService: AccountService,
    private titleService: Title,
    private spinner: NgxSpinnerService,
    private toastr: ToastrService) {
    this.titleService.setTitle("Bank Management");
  }
  isLoading = false;
  @ViewChild('closeAddExpenseModal') closeAddExpenseModal: ElementRef;
  @ViewChild('openModal') openModal: ElementRef;
  gridCells: IGridCell[] = [];
  removeField = ['createdAt', 'updatedAt', 'createdBy', 'updatedBy', 'id', 'deleted']
  totalRows = 50;
  totalElements: number = 0;
  todos: AccountModel[] = [];
  pageNumber: number;
  listData: AccountModel[];
  page = {
    page: 0,
    size: 25
  }
  token: string;
  keyword = new FormControl('');
  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  });
  accountForm = new FormGroup({
    id: new FormControl(''),
    account_number: new FormControl(''),
    firstname: new FormControl(''),
    lastname: new FormControl(''),
    address: new FormControl(''),
    balance: new FormControl(''),
    gender: new FormControl(''),
    city: new FormControl(''),
    employer: new FormControl(''),
    state: new FormControl(''),
    age: new FormControl(''),
    email: new FormControl('')
  });

  ngOnInit(): void {
    this.loginForm.get('username').setValue('tudt');
    this.loginForm.get('password').setValue('123456');
    this.token = localStorage.getItem("token");
    this.getData(0, 25);
    this.spinner.show();
  }

  login() {
    this.accountService.login(this.loginForm.value).subscribe(data => {
      
    }, error => {
      if(error.status === 400) {
        this.toastr.error(error.error,'Error!')
      } else if (error.status === 201 || error.status === 200) {
        this.toastr.success('Đăng nhập thành công','Success!')
        localStorage.setItem('token', error.error.text);
        window.location.reload();
      }
    });
  }

  logout() {
    this.toastr.warning('Đăng xuất thành công','Info!')
    localStorage.removeItem('token');
    window.location.reload();
  }

  save() {
    const account: AccountModel = this.accountForm.value;
    if (account.id) {
      this.accountService.updateAccount(account).subscribe(data => {
        this.closeAddExpenseModal.nativeElement.click();
        this.getData(0, 25);
        this.resetForm();
        this.toastr.success('Success!', 'Cập nhật thành công!');
      }, error => this.toastr.error(error.error.message, 'Error!'))
    } else {
      this.accountService.saveAccount(account).subscribe(data => {
        this.closeAddExpenseModal.nativeElement.click();
        this.getData(0, 25);
        this.resetForm();
        this.toastr.success('Success!', 'Thêm mới thành công!');
      }, error => this.toastr.error(error.error.message, 'Error!'))
    }
  }

  submitSearch() {
    this.searchData(this.keyword.value, 0, 25);
  }

  private getData(page, size) {
    this.spinner.show();
    this.accountService.getAllAccount(page, size).subscribe(data => {
      this.totalElements = data.totalElements;
      this.pageNumber = data.pageable.pageNumber;
      this.listData = data.content;
      this.setCell(data.content[0]);
    }, error => {
      this.spinner.hide();
      this.toastr.error(error.error.message, 'Error!');
    });
  }

  private searchData(keyword, page, size) {
    this.spinner.show();
    if (keyword) {
      this.accountService.searchAccount(keyword, page, size).subscribe(data => {
        this.totalElements = data.totalElements;
        this.pageNumber = data.pageable.pageNumber;
        this.listData = data.content;
        this.setCell(data.content[0]);
      }, error => {
        this.spinner.hide();
        this.toastr.error(error.error.message, 'Error!');
      });
    } else {
      this.getData(this.page.page, this.page.size);
    }
  }

  setCell(data: AccountModel) {
    this.gridCells = [];
    let keys = Object.keys(data);
    this.removeField.forEach(f => {
      keys.splice(keys.indexOf(f), 1);
    })
    keys.forEach(field => {
      this.gridCells.push({
        label: field.toUpperCase(),
        align: 'center',
        field: field,
        width: 70
      });
    });
    this.spinner.hide();
  }

  onDelete(item): void {
    this.accountService.deleteAccount(item).subscribe(() => {
      this.toastr.success('Xoá thành công', 'Success!')
      this.getData(this.page.page, this.page.size);
    }, error => this.toastr.error(error.error.message, 'Error!'));
  }

  onEdit(item): void {
    this.openModal.nativeElement.click();
    this.accountForm = new FormGroup({
      id: new FormControl(item.id),
      account_number: new FormControl(item.account_number),
      firstname: new FormControl(item.firstname),
      lastname: new FormControl(item.lastname),
      address: new FormControl(item.address),
      balance: new FormControl(item.balance),
      gender: new FormControl(item.gender),
      city: new FormControl(item.city),
      employer: new FormControl(item.employer),
      state: new FormControl(item.state),
      age: new FormControl(item.age),
      email: new FormControl(item.email)
    });
  }

  nextPage(event: PageEvent) {
    this.page.page = event.pageIndex;
    this.page.size = event.pageSize;
    this.getData(this.page.page, this.page.size);
  }

  resetForm() {
    this.accountForm = new FormGroup({
      id: new FormControl(''),
      account_number: new FormControl(''),
      firstname: new FormControl(''),
      lastname: new FormControl(''),
      address: new FormControl(''),
      balance: new FormControl(''),
      gender: new FormControl(''),
      city: new FormControl(''),
      employer: new FormControl(''),
      state: new FormControl(''),
      age: new FormControl(''),
      email: new FormControl('')
    });
  }

}
