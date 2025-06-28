import { Component } from '@angular/core';
import { ToastService } from '../../services/toast.service';

@Component({
  selector: 'app-toast',
  template: `
    <div class="toast-container position-fixed bottom-0 end-0 p-3">
      <div *ngFor="let toast of toastService.toasts" class="toast show text-white" [ngClass]="'bg-' + toast.type">
        <div class="toast-body">{{ toast.text }}</div>
      </div>
    </div>
  `,
  styles: [`.toast-container { z-index: 1055; }`]
})
export class ToastComponent {
  constructor(public toastService: ToastService) {}
}
