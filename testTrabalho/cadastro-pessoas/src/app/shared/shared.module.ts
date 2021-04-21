import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AlertModalComponent } from './alert-modal/alert-modal.component';
import { ConfirmModalComponent } from './confirm-modal/confirm-modal.component';
import { ErrorMsgComponent } from './error-msg/error-msg.component';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    AlertModalComponent,
    ConfirmModalComponent,
    ErrorMsgComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  exports: [
    AlertModalComponent,
    ErrorMsgComponent
  ],
  entryComponents: [AlertModalComponent, ConfirmModalComponent]
})
export class SharedModule { }
