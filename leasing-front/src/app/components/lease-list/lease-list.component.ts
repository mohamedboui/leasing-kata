import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { Lease } from '../../models/lease.model';
import * as LeaseActions from '../../store/lease.actions';

@Component({
  selector: 'app-lease-list',
  templateUrl: './lease-list.component.html'
})
export class LeaseListComponent implements OnInit {
  leases$: Observable<Lease[]>;
  showModal = false;
  selectedLeaseId: number | null = null;

  constructor(private store: Store<{ lease: { leases: Lease[] } }>) {
    this.leases$ = store.select(state => state.lease.leases);
  }

  ngOnInit(): void {
    this.store.dispatch(LeaseActions.loadLeases());
  }

 openConfirm(leaseId: number) {
   this.selectedLeaseId = leaseId;
   this.showModal = true;
 }

  confirmReturn() {
    if (this.selectedLeaseId) {
      this.store.dispatch(LeaseActions.returnLease({leaseId: this.selectedLeaseId}));
    }
    this.resetModal();
  }

  cancelReturn() {
    this.resetModal();
  }

  private resetModal() {
    this.showModal = false;
    this.selectedLeaseId = null;
  }
}
