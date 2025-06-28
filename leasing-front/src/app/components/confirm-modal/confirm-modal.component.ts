import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-confirm-modal',
  template: `
<div class="modal-backdrop fade show" *ngIf="visible"></div>
<div *ngIf="visible" class="modal d-block fade show" tabindex="-1" role="dialog" style="background-color: rgba(0,0,0,0.5);">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">{{ title }}</h5>
        <button type="button" class="btn-close" (click)="cancel.emit()" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p>{{ message }}</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" (click)="cancel.emit()">Annuler</button>
        <button type="button" class="btn btn-danger" (click)="confirm.emit()">Confirmer</button>
      </div>
    </div>
  </div>
</div>
  `
})
export class ConfirmModalComponent {
  @Input() title = 'Confirmation';
  @Input() message = 'Êtes-vous sûr ?';
  @Input() visible = false;
  @Output() confirm = new EventEmitter<void>();
  @Output() cancel = new EventEmitter<void>();
}
