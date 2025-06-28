import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Store } from '@ngrx/store';
import * as LeaseActions from '../../store/lease.actions';

@Component({
  selector: 'app-lease-return',
  templateUrl: './lease-return.component.html'
})
export class LeaseReturnComponent {
  form = this.fb.group({
    leaseId: [null, Validators.required]
  });

  constructor(private fb: FormBuilder, private store: Store) {}

  submit() {
    const value = this.form.value;
    if (this.form.valid && value.leaseId) {
      this.store.dispatch(LeaseActions.returnLease({ leaseId: Number(value.leaseId) }));
    }
  }
}
