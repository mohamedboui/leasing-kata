import { createAction, props } from '@ngrx/store';
import { Lease } from '../models/lease.model';

export const loadLeases = createAction('[Lease] Load Leases');
export const loadLeasesSuccess = createAction('[Lease] Load Leases Success', props<{ leases: Lease[] }>());
export const createLease = createAction('[Lease] Create Lease', props<{ lease: Partial<Lease> }>());
export const returnLease = createAction('[Lease] Return Lease', props<{ leaseId: number }>());
