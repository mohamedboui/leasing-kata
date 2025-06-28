import { createReducer, on } from '@ngrx/store';
import * as LeaseActions from './lease.actions';
import { Lease } from '../models/lease.model';

export interface LeaseState {
  leases: Lease[];
}

export const initialState: LeaseState = {
  leases: []
};

export const leaseReducer = createReducer(
  initialState,
  on(LeaseActions.loadLeasesSuccess, (state, { leases }) => ({ ...state, leases }))
);
