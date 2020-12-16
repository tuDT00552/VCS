import { AccountService } from './../../service/account.service';
import {
  AfterViewInit,
  ChangeDetectionStrategy,
  ChangeDetectorRef,
  Component,
  ContentChildren,
  EventEmitter,
  Input,
  OnInit,
  Output,
  QueryList,
  TemplateRef,
} from '@angular/core';
import { IGridCell } from './grid';

@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class GridComponent implements OnInit, AfterViewInit {
  @Input() cells: IGridCell[] = [];
  @Input() rows: [] = [];
  @Input() totalRows: number;
  @Input() isLoading = false;
  @Input() pageNumber: number;
  @Input() pagingMode: string | 'paging' | 'scroll';
  @Input() token: string;
  // tslint:disable-next-line: no-output-rename
  // @Output('isLoaded') isLoaded = new EventEmitter<any>();
  @Output('delete') delete = new EventEmitter<any>();
  @Output('edit') edit = new EventEmitter<any>();
  pager = {};
  pageOfItems = [];
  constructor(private cdr: ChangeDetectorRef,
    private accountService: AccountService) { }

  ngOnInit(): void {
  }

  delAccount(item) {
    this.delete.emit(item);
  }

  editAccount(item) {
    this.edit.emit(item);
  }

  ngAfterViewInit(): void { }
}
