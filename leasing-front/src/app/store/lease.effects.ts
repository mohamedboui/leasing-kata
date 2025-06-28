import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { LeaseService } from '../services/lease.service';
import { ToastService } from '../services/toast.service';
import * as LeaseActions from './lease.actions';
import { catchError, map, mergeMap, of } from 'rxjs';

@Injectable()
export class LeaseEffects {
  constructor(
    private actions$: Actions,
    private service: LeaseService,
    private toastService: ToastService
  ) {}

  loadLeases$ = createEffect(() => this.actions$.pipe(
    ofType(LeaseActions.loadLeases),
    mergeMap(() => this.service.listLeases().pipe(
      map((leases: any[]) => LeaseActions.loadLeasesSuccess({ leases })),
      catchError(() => {
        this.toastService.show('Échec du chargement des leases', 'danger');
        return of({ type: '[Lease] Load Failed' });
      })
    ))
  ));

  returnLease$ = createEffect(() => this.actions$.pipe(
    ofType(LeaseActions.returnLease),
    mergeMap(action => this.service.returnLease(action.leaseId).pipe(
      map(() => {
        this.toastService.show('Véhicule retourné avec succès', 'success');
        return LeaseActions.loadLeases();
      }),
      catchError(() => {
        this.toastService.show('Échec du retour du véhicule', 'danger');
        return of({ type: '[Lease] Return Failed' });
      })
    ))
  ));

  createLease$ = createEffect(() => this.actions$.pipe(
    ofType(LeaseActions.createLease),
    mergeMap(action => this.service.createLease(action.lease).pipe(
      map(() => {
        this.toastService.show('Lease créé avec succès', 'success');
        return LeaseActions.loadLeases();
      }),
      catchError(() => {
        this.toastService.show('Échec de la création du lease', 'danger');
        return of({ type: '[Lease] Create Failed' });
      })
    ))
  ));
}
