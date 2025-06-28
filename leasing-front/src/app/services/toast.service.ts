import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class ToastService {
  toasts: { text: string, type: string }[] = [];

  show(text: string, type: string = 'info') {
    this.toasts.push({ text, type });
    setTimeout(() => this.toasts.shift(), 3000);
  }

  success(msg: string) { this.show(msg, 'success'); }
  error(msg: string) { this.show(msg, 'danger'); }
}
