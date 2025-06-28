import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Store } from '@ngrx/store';
import * as LeaseActions from '../../store/lease.actions';

@Component({
  selector: 'app-lease-create',
  templateUrl: './lease-create.component.html'
})
export class LeaseCreateComponent {
  form = this.fb.group({
    customerId: [null, Validators.required],
    carId: [null, Validators.required],
    startDate: ['', Validators.required],
    endDate: ['', Validators.required]
  });

  constructor(private fb: FormBuilder, private store: Store) {}

  submit() {
    if (this.form.valid) {
      this.store.dispatch(LeaseActions.createLease({ lease: this.form.value }));
    }
  }
}
