import { Subject } from 'rxjs';
import { Component, Input, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-confirm-modal',
  templateUrl: './confirm-modal.component.html',
  styleUrls: ['./confirm-modal.component.scss']
})
export class ConfirmModalComponent implements OnInit {

  @Input() title: string;
  @Input() msg: string;
  @Input() cancelTxt = 'Cancelar';
  @Input() okTxt = 'Sim';

  confirmResult: Subject<boolean>;

  constructor(private bsModalRef: BsModalRef) { }

  ngOnInit(): void {
    this.confirmResult = new Subject();
  }

  onClose(){
    this.confirmAndClose(false);
  }

  onConfim(){
    this.confirmAndClose(true);
  }

  private confirmAndClose(value: boolean){
    this.confirmResult.next(value);
    this.bsModalRef.hide();
  }

}
